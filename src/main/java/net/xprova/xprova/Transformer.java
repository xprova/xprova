package net.xprova.xprova;

import java.util.HashMap;
import java.util.HashSet;

import net.xprova.graph.Graph;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;
import net.xprova.netlistgraph.VertexType;

public class Transformer {

	// model1: DFF cell with M and V ports
	// model2: DFF cell with T port
	// fow now both model1 and model2 are set to the cell DFFx

	private static final String model1 = "DFFx", model2 = "DFFx";

	private static final String portV = "V", dup_suffix = "_dup", portM = "M", portT = "T";

	private HashMap<String, FlipFlop> defsFF;

	private NetlistGraph graph;

	public Transformer(NetlistGraph graph, HashMap<String, FlipFlop> defsFF) {

		this.graph = graph;

		this.defsFF = defsFF;

	}

	public HashSet<Vertex> getClocks() {

		HashSet<Vertex> clocks = new HashSet<Vertex>();

		for (Vertex v : graph.getModules()) {

			FlipFlop entry = defsFF.get(v.subtype);

			if (entry != null) {

				Vertex clk = graph.getNet(v, entry.clkPort);

				clocks.add(clk);

			}

		}

		return clocks;

	}

	public HashSet<Vertex> getDomainFlops(Vertex clk) throws Exception {

		HashSet<Vertex> flops = graph.getModulesByTypes(defsFF.keySet());

		HashSet<Vertex> domainFlops = new HashSet<Vertex>();

		for (Vertex f : flops) {

			String clkPort = defsFF.get(f.subtype).clkPort;

			if (clk == graph.getNet(f, clkPort)) {

				domainFlops.add(f);

			}

		}

		return domainFlops;

	}

	public void transformCDC(boolean addRandomBits) throws Exception {

		HashSet<Vertex> clocks = getClocks();

		HashMap<Vertex, Integer> dupCount = new HashMap<Vertex, Integer>();

		// dupCount holds number of duplicated instances per vertex
		// if a vertex has no entry then it hasn't been duplicated

		int h2x_adapter_count = 0;

		for (Vertex vclk : clocks) {

			HashSet<Vertex> flops = graph.getModulesByTypes(defsFF.keySet());

			Graph<Vertex> flopGraph = graph.reduce(flops);

			HashSet<Vertex> localFlops = getDomainFlops(vclk);

			// determine hazardous flops

			// (a) first add all flops in different clock domains

			HashSet<Vertex> hazardousFlops = new HashSet<Vertex>(flops);

			hazardousFlops.removeAll(localFlops);

			// (b) now add all flops one combinational path away from flops in
			// different clock domains

			hazardousFlops.addAll(flopGraph.bfs(hazardousFlops, 1, false));

			// determine susceptible flops: any flops in the current domain
			// which
			// are destinations of hazardous flops

			HashSet<Vertex> susFlops = flopGraph.bfs(hazardousFlops, 1, false);

			susFlops.retainAll(localFlops);

			// determine hazardous combinational logic
			// this is the set of gates and nets that depend on hazardous flops

			HashSet<Vertex> hazardousCombLogic = graph.bfs(hazardousFlops, flops, false);

			// Okay, now to build the V inputs of susceptible flops we want to
			// retrace back from their D inputs, duplicating all nets until we
			// encounter:
			//
			// (1) a non-hazardous combinational net : connect to it
			// (2) output net of a hazardous source flop : use H2X adapter to
			// connect to it

			HashMap<Vertex, Vertex> dupMap = new HashMap<Vertex, Vertex>();

			HashMap<Vertex, Vertex> xNets = new HashMap<Vertex, Vertex>();

			// xNets maps hazardous flops with instantiated H2X adapters to
			// their
			// adapters

			HashSet<Vertex> current = new HashSet<Vertex>(); // current
																// duplicates

			HashSet<Vertex> visited = new HashSet<Vertex>();

			for (Vertex s : susFlops) {

				Vertex dNet = graph.getNet(s, defsFF.get(s.subtype).dPort);

				Vertex vNet;

				// first check if a duplicate of dNet exists already

				if (dupMap.get(dNet) != null) {

					vNet = dupMap.get(dNet);

				} else {

					// duplicate net

					vNet = new Vertex(dNet);

					Integer dNet_dupCount = dupCount.get(dNet);

					dNet_dupCount = (dNet_dupCount == null) ? 1 : dNet_dupCount + 1;

					dupCount.put(dNet, dNet_dupCount);

					vNet.name = affixToNetName(vNet.name, dup_suffix + dNet_dupCount);

					graph.addVertex(vNet);

					dupMap.put(dNet, vNet);

					current.add(dNet);

				}

				// add connection to v port

				graph.addConnection(vNet, s, portV);

				s.subtype = model1;

			}

			boolean insertAdapters = false;

			HashSet<Vertex> next = new HashSet<Vertex>(); // next duplicates

			while (!current.isEmpty()) {

				for (Vertex v : current) {

					// trace its input connections

					for (Vertex s : graph.getSources(v)) {

						if (hazardousFlops.contains(s)) {

							// v is the output net of a hazardous flip flops

							if (insertAdapters) {

								// need to insert an adapter or connect to an
								// existing adapter

								Vertex h2x_adapter;

								if (!xNets.containsKey(s)) {

									// will insert an H2X adapter here

									h2x_adapter_count = h2x_adapter_count + 1;

									h2x_adapter = new Vertex("H2X_u" + h2x_adapter_count, VertexType.MODULE, "H2X");

									graph.addVertex(h2x_adapter);

									// OK, now need to connect the adapter to
									// the hazardous flop

									// if the hazardous flop is in the same
									// clock domain then connect to its M port

									// otherwise (when it's in another domain)
									// connect to its T port

									boolean sameDomain = graph.getNet(s, defsFF.get(s).clkPort) == vclk;

									String port = sameDomain ? portM : portT;

									Vertex adapterInput = new Vertex("H2X_u" + h2x_adapter_count + "_inp",
											VertexType.NET, "wire");

									graph.addVertex(adapterInput);

									graph.addConnection(s, adapterInput, port);

									graph.addConnection(adapterInput, h2x_adapter, "I");

									s.subtype = model1;

									xNets.put(s, h2x_adapter);

								} else {

									h2x_adapter = xNets.get(s);

									throw new Exception("flipflop <" + s + "> has an H2X adapter already");

									// with xNets in place the code should be
									// able to handle this case

									// I am throwing an exception here because
									// this cased oesn't occur in my current
									// test design so I can't check if this
									// works correctly

									// if this exception is thrown, comment it
									// out and then see if the tool correctly
									// connects dupMap.get(v) to the existing
									// h2x_adapter

								}

								graph.addConnection(h2x_adapter, dupMap.get(v), "O");

							} else {

								// connect directly to either M or T port
								// without an adapter

								boolean sameDomain = graph.getNet(s, defsFF.get(s.subtype).clkPort) == vclk;

								String port = sameDomain ? portM : portT;

								graph.addConnection(s, dupMap.get(v), port);

								s.subtype = model1;

								// System.out
								// .println("just added connection to port "
								// + port
								// + " of vertex "
								// + dupMap.get(v));

							}

						} else if (hazardousCombLogic.contains(s)) {

							// s is a hazardous net or gate

							// if not duplicated already then create a duplicate

							// afterwards connect to the duplicate and add s to
							// `next`

							Vertex s_dup = dupMap.get(s);

							if (s_dup == null) {

								s_dup = new Vertex(s);

								Integer s_dupCount = dupCount.get(s);

								s_dupCount = (s_dupCount == null) ? 1 : s_dupCount + 1;

								dupCount.put(s, s_dupCount);

								// s_dup.name = s_dup.name + dup_suffix
								// + s_dupCount;

								s_dup.name = affixToNetName(s_dup.name, dup_suffix + s_dupCount);

								if ("input".equals(s_dup.subtype) || "output".equals(s_dup.subtype)) {

									// when duplicating any net which is an i/o
									// port
									// must change duplicate to wire to make it
									// an internal net

									s_dup.subtype = "wire";
								}

								graph.addVertex(s_dup);

								dupMap.put(s, s_dup);

								// System.out.println("just duplicated " + s);

							}

							String port = graph.getPinName(s, v);

							next.add(s);

							graph.addConnection(s_dup, dupMap.get(v), port);

						} else {

							// s is a safe net, just connect to

							String port = graph.getPinName(s, v);

							graph.addConnection(s, dupMap.get(v), port);

						}

					}

				}

				visited.addAll(current);

				next.removeAll(visited);

				current = new HashSet<Vertex>(next);

				next.clear();

			}

		}

		// as a last step, add random bits

		// all DFFx flops must have r1 (mis-interpretation bit)

		// if DFFx flop has an m pin connection (it can propagate metastability)
		// then it must also have an r2 bit

		HashSet<Vertex> DFFxs = graph.getModulesByType(model1);

		if (addRandomBits) {

			graph.addPort("r"); // add port first

			int rbits = 0;

			for (Vertex flop : DFFxs) {

				if (graph.getNet(flop, "M") != null) {

					Vertex r1 = new Vertex("r[" + rbits + "]", VertexType.NET, "input");

					graph.addVertex(r1);

					graph.addConnection(r1, flop, "rV");

					graph.addInput(r1);

					rbits = rbits + 1;

				}

				if (graph.getNet(flop, "V") != null) {

					Vertex r2 = new Vertex("r[" + rbits + "]", VertexType.NET, "input");

					graph.addVertex(r2);

					graph.addConnection(r2, flop, "rD");

					graph.addInput(r2);

					rbits = rbits + 1;

				}

			}

		}

		for (Vertex flop : DFFxs) {

			// additional fix

			// if flop has no V connection then change it to DFFx2

			if (graph.getNet(flop, portV) == null) {

				flop.subtype = model2;

			}

		}

	}

	private String affixToNetName(String net, String postfix) {

		// this function appends postfix to net while handling the cases
		// when net contains an array net (e.g. data[0])

		int k = net.indexOf("[");

		if (k == -1) {

			return net.concat(postfix);

		} else {

			int k2 = net.indexOf("]");

			if (k2 != net.length() - 1) {

				System.err.println("warning: tried to change name of an invalid net: " + net);

			}

			String part1 = net.substring(0, k);

			String part2 = net.substring(k, k2 + 1);

			return part1 + postfix + part2;

		}

	}

}
