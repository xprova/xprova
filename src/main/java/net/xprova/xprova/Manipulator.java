package net.xprova.xprova;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import net.xprova.graph.Graph;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;
import net.xprova.netlistgraph.VertexType;

@Deprecated
public class Manipulator {

	// the below are dummy vertices used as keys for dupCount entries

	private static final Vertex INTERPRETER = new Vertex(), XOR = new Vertex();

	private static final String clkPort = "CK", portD = "D", portQ = "Q";

	public static String[] flipFlopTypes = null;

	private static HashSet<Vertex> getCombinationalPath(NetlistGraph graph, Vertex v1, Vertex v2,
			HashSet<Vertex> flipflops) throws Exception {

		// returns all vertices that lie on the combinational path from v1 to v2

		// containers

		HashMap<Vertex, HashSet<Vertex>> onPathSources = new HashMap<Vertex, HashSet<Vertex>>(); // predecessors

		HashSet<Vertex> f1Outputs = graph.getDestinations(v1);

		// algorithm: do a BFS from f1, for each visited vertex make a set of
		// all preceding vertices

		for (Vertex o : f1Outputs) {

			onPathSources.put(o, new HashSet<Vertex>());

		}

		HashSet<Vertex> visitNext = new HashSet<Vertex>();

		visitNext.addAll(f1Outputs);

		while (!visitNext.isEmpty()) {

			HashSet<Vertex> group = new HashSet<Vertex>(visitNext);

			visitNext.clear();

			for (Vertex s : group) {

				for (Vertex d : graph.getDestinations(s)) {

					if (!onPathSources.containsKey(d)) {

						onPathSources.put(d, new HashSet<Vertex>());

					}

					if (onPathSources.get(s).contains(s)) {

						throw new Exception("combinational path between flip-flops " + v1 + " and " + v2
								+ " contains a cycle, vertex " + s + " feeds to itself");

					}

					HashSet<Vertex> dSources = onPathSources.get(d);

					// net or combinational module

					dSources.add(s);

					dSources.addAll(onPathSources.get(s));

					if (!flipflops.contains(d)) {

						visitNext.add(d);

					}

				}

			}

		}

		if (onPathSources.containsKey(v2)) {

			return onPathSources.get(v2);

		} else {

			return new HashSet<Vertex>();

		}

	}

	public static void printMsFlopGraph(Graph<Vertex> graph) {

		for (Vertex flop : graph.getVertices()) {

			for (Vertex destination : graph.getDestinations(flop)) {

				System.out.println(flop + " -> " + destination);

			}

		}

	}

	public static NetlistGraph getMsPath(NetlistGraph graph, Vertex destinationFF) throws Exception {

		HashSet<Vertex> flipflops = getFlops(graph);

		Graph<Vertex> msFlopGraph = getMsFlopGraph(graph);

		HashSet<Vertex> inPath = new HashSet<Vertex>();

		inPath.add(destinationFF);

		for (Vertex source : msFlopGraph.getSources(destinationFF)) {

			inPath.addAll(getCombinationalPath(graph, source, destinationFF, flipflops));

			inPath.add(source);

		}

		NetlistGraph subgraph = graph.getSubGraph(inPath);

		return subgraph;

	}

	public static HashSet<Vertex> getCDCFlops(NetlistGraph graph) throws Exception {

		HashSet<Vertex> flipflops = getFlops(graph);

		Graph<Vertex> flopGraph = graph.reduce(flipflops);

		HashSet<Vertex> cdcFlops = new HashSet<Vertex>();

		for (Vertex flop : flipflops) {

			for (Vertex flopSource : flopGraph.getSources(flop)) {

				Vertex clk1 = graph.getNet(flop, clkPort);

				Vertex clk2 = graph.getNet(flopSource, clkPort);

				if (clk1 != clk2) {

					cdcFlops.add(flop);

				}
			}

		}

		return cdcFlops;

	}

	private static HashSet<Vertex> getFlops(NetlistGraph graph) throws Exception {

		if (flipFlopTypes == null) {

			throw new Exception("flipFlopTypes not set");
		}

		HashSet<String> fTypes = new HashSet<String>(Arrays.asList(flipFlopTypes));

		HashSet<Vertex> flops = new HashSet<Vertex>();

		for (Vertex v : graph.getModules()) {

			if (fTypes.contains(v.subtype))

				flops.add(v);

		}

		return flops;

	}

	public static HashSet<Vertex> getDomainFlops(NetlistGraph graph, String clk) throws Exception {

		HashSet<Vertex> flops = getFlops(graph);

		HashSet<Vertex> domainFlops = new HashSet<Vertex>();

		for (Vertex f : flops) {

			if (clk.equals(graph.getNet(f, clkPort).toString())) {

				domainFlops.add(f);

			}

		}

		return domainFlops;

	}

	public static HashSet<Vertex> getMSFlops(NetlistGraph graph) throws Exception {

		// returns all flip-flops that can potentially become metastable
		// this includes destinations of CDC paths and other flip-flops
		// with a certain depth (determined by stages) from these

		HashSet<Vertex> cdcFlops = getCDCFlops(graph);

		HashSet<Vertex> flipflops = getFlops(graph);

		Graph<Vertex> flopGraph = graph.reduce(flipflops);

		int stages = 1;

		cdcFlops.addAll(flopGraph.bfs(cdcFlops, stages, false));

		return cdcFlops;

	}

	/**
	 * returns a set of all flip-flops that are hazardous to flip-flops in clock
	 * domain clk
	 * <p>
	 * A hazardous flip-flop is either one which is asynchronous (clocked by a
	 * different clock) or one in the current domain which is one combinational
	 * path away from an asynchronous flipflop
	 * </p>
	 *
	 * @param graph
	 * @param clk
	 * @return
	 * @throws Exception
	 */
	public static HashSet<Vertex> getHazardousFlops(NetlistGraph graph, String clk) throws Exception {

		// first add all flops in different clock domains

		HashSet<Vertex> hazardousFlops = getFlops(graph);

		hazardousFlops.removeAll(getDomainFlops(graph, clk));

		// now add all flops one combinational path away from flops in different
		// clock domains

		Graph<Vertex> flopGraph = graph.reduce(getFlops(graph));

		hazardousFlops.addAll(flopGraph.bfs(hazardousFlops, 1, false));

		return hazardousFlops;

	}

	public static Graph<Vertex> getMsFlopGraph(NetlistGraph graph) throws Exception {

		Graph<Vertex> msFlopGraph = new Graph<Vertex>();

		HashSet<Vertex> flipflops = getFlops(graph);

		Graph<Vertex> flopGraph = graph.reduce(flipflops);

		HashSet<Vertex> stageFlops = new HashSet<Vertex>();

		for (Vertex flop : flipflops) {

			for (Vertex flopSource : flopGraph.getSources(flop)) {

				Vertex clk1 = graph.getNet(flop, clkPort);

				Vertex clk2 = graph.getNet(flopSource, clkPort);

				if (clk1 != clk2) {

					msFlopGraph.addVertex(flopSource);

					msFlopGraph.addVertex(flop);

					try {

						msFlopGraph.addConnection(flopSource, flop);

					} catch (Exception e) {

						e.printStackTrace();

					}

					stageFlops.add(flop);

				}
			}

		}

		HashSet<Vertex> nextStage = new HashSet<Vertex>();

		int stages = 1;

		for (int i = 0; i < stages; i++) {

			for (Vertex v : stageFlops) {

				for (Vertex d : flopGraph.getDestinations(v)) {

					nextStage.add(d);

					msFlopGraph.addVertex(d);

					try {

						msFlopGraph.addConnection(v, d);

					} catch (Exception e) {

						e.printStackTrace();

					}

				}

			}

			stageFlops = new HashSet<Vertex>(nextStage);

		}

		return msFlopGraph;

	}

	private static Graph<Vertex> getFlopIO(NetlistGraph graph, HashSet<Vertex> flipflops) {

		// returns a graph composed of flipflops, their immediate inputs,
		// immediate outputs and their interconnections

		HashSet<Vertex> graphVertices = new HashSet<Vertex>();

		graphVertices.addAll(graph.bfs(flipflops, 1, false));

		graphVertices.addAll(graph.bfs(flipflops, 1, true));

		graphVertices.addAll(flipflops);

		return graph.reduce(graphVertices);
	}

	public static void forkCDCPaths(NetlistGraph graph) throws Exception {

		HashMap<Vertex, Integer> dupCounts = new HashMap<Vertex, Integer>();

		HashSet<Vertex> flipflops = getFlops(graph);

		HashSet<Vertex> flipflopOutputs = graph.bfs(flipflops, 1, false);

		Graph<Vertex> flopIO = getFlopIO(graph, flipflops);

		Graph<Vertex> msFlopGraph = getMsFlopGraph(graph);

		// adding metastable flip flop nets and VOR module

		HashMap<Vertex, Vertex> mNets = new HashMap<Vertex, Vertex>();

		HashMap<Vertex, Vertex> zNets = new HashMap<Vertex, Vertex>();

		HashMap<Vertex, Vertex> vNets = new HashMap<Vertex, Vertex>();

		HashMap<Vertex, Vertex> vorMods = new HashMap<Vertex, Vertex>();

		for (Vertex flop : msFlopGraph.getVertices()) {

			Vertex flopM = new Vertex(flop + "_m", VertexType.NET, "wire");

			Vertex H2X = new Vertex(flop + "_h2x", VertexType.MODULE, "H2X");

			Vertex flopMZ = new Vertex(flop + "_mz", VertexType.NET, "wire");

			Vertex flopVnet = new Vertex(flop + "_v", VertexType.NET, "wire");

			Vertex flopVOR = new Vertex("ZOR" + (vorMods.keySet().size() + 1), VertexType.MODULE, "ZOR1");

			// adding vertices

			graph.addVertex(flopM);

			graph.addVertex(H2X);

			graph.addVertex(flopMZ);

			graph.addVertex(flopVnet);

			graph.addVertex(flopVOR);

			// adding connections

			graph.addConnection(flop, flopM, "m");

			graph.addConnection(flopVnet, flop, "v");

			graph.addConnection(flopVOR, flopVnet, "vout");

			graph.addConnection(flopM, H2X, "I");

			graph.addConnection(H2X, flopMZ, "O");

			// adding map entries

			mNets.put(flop, flopM);

			vNets.put(flop, flopVnet);

			vorMods.put(flop, flopVOR);

			zNets.put(flop, flopMZ);

			// changing flop module

			flop.subtype = "DFFm";

		}

		// loop

		// HashSet<Vertex> temp = new HashSet<Vertex>();
		// temp.add(graph.getVertex("ack_reg"));
		// for (Vertex flop : temp) {

		for (Vertex flop : msFlopGraph.getVertices()) {

			Vertex dclk = graph.getNet(flop, clkPort);

			HashSet<Vertex> flopcdcPaths = new HashSet<Vertex>();

			for (Vertex flopSource : msFlopGraph.getSources(flop)) {

				HashSet<Vertex> path = getCombinationalPath(graph, flopSource, flop, flipflops);

				flopcdcPaths.addAll(path);

			}

			flopcdcPaths.removeAll(flipflops);

			HashSet<Vertex> flopInputs = graph.getSources(flop);

			Vertex flopVor = vorMods.get(flop);

			flopInputs.retainAll(flopcdcPaths);

			for (Vertex input : flopInputs) {

				String port = graph.getPinName(input, flop);

				Vertex inputDup = input;

				int vcount = 1;

				HashSet<Vertex> sourceMsFlopOutputs = flopIO.getSources(input);

				if (sourceMsFlopOutputs.size() == 1
						&& sourceMsFlopOutputs.iterator().next().type == VertexType.MODULE) {

					// input is also the output net of a flip-flop

					// this special case (two flip-flops connected in a chain)
					// is handled here

					Vertex flop1 = sourceMsFlopOutputs.iterator().next();

					Vertex flop2 = flop;

					// first, need to remove vor and vnet of destination
					// flip-flop as these are not actually needed

					graph.removeVertex(vorMods.get(flop2));

					graph.removeVertex(vNets.get(flop2));

					// also remove them from hashsets vorMods and vNets

					vorMods.remove(flop2);

					vNets.remove(flop2);

					// now connect m net of flop1 to flop2 directly via the v
					// pin

					graph.addConnection(mNets.get(flop1), flop2, "v");

					graph.removeConnection(input, flop2);

					insertInterpreter(graph, input, flop2, portD, dupCounts, mNets, dclk);

					continue;

				}

				sourceMsFlopOutputs.retainAll(flopcdcPaths);

				for (Vertex source : sourceMsFlopOutputs) {

					Vertex sourceFlop = graph.getSourceModule(source);

					Vertex sourceMZ = zNets.get(sourceFlop);

					inputDup = dupPath(graph, flipflopOutputs, flopcdcPaths, dupCounts, source, sourceMZ, input, mNets,
							dclk, sourceMsFlopOutputs);

					port = "V_" + vcount;

					graph.addConnection(inputDup, flopVor, port);

					flopVor.subtype = "ZOR" + vcount;

					vcount = vcount + 1;

				}

				// now forking paths

				port = graph.getPinName(input, flop);

				inputDup = Manipulator.forkPathRecur(graph, input, flipflopOutputs, flopcdcPaths, dupCounts, mNets,
						dclk, sourceMsFlopOutputs);

				graph.removeConnection(input, flop);

				graph.addConnection(inputDup, flop, port);

			}

		}

		// checking for unconnected vor modules

		// these belong to flip-flops at the start of cdc paths. Such flip-flops
		// become metastable (exhibit prolonged transition delays) when they
		// transition

		for (Vertex flop : vorMods.keySet()) {

			Vertex vor = vorMods.get(flop);

			if (graph.getSources(vor).isEmpty()) {

				Vertex Q = graph.getNet(flop, portQ);

				Vertex D = graph.getNet(flop, portD);

				Vertex V = vNets.get(flop);

				// create and add xor vertex

				int dupCount = dupCounts.containsKey(XOR) ? dupCounts.get(XOR) : 1;

				Vertex xor = new Vertex("XOR" + dupCount, VertexType.MODULE, "XOR");

				dupCounts.put(XOR, dupCount + 1);

				graph.addVertex(xor);

				// create xor vertex

				// dupCount = dupCounts.containsKey(TR) ? dupCounts.get(TR) : 1;
				//
				// Vertex tr = new Vertex("tr" + dupCount, VertexType.NET,
				// "wire");
				//
				// dupCounts.put(TR, dupCount + 1);
				//
				// graph.addVertex(tr);

				// connections

				graph.addConnection(D, xor, "I1");

				graph.addConnection(Q, xor, "I2");

				graph.removeVertex(vor);

				graph.addConnection(xor, V, "O");

				// graph.addConnection(tr, vor, "V_1");

			}

		}

		// eliminating vor modules with a single input

		// for (Vertex flop : vorMods.keySet()) {
		//
		// Vertex vor = vorMods.get(flop);
		//
		// if (graph.getSources(vor).size() == 1) {
		//
		// // need to take out this vor and its preceeding net
		//
		// Vertex preVor = graph.getSources(vor).iterator().next();
		//
		// Vertex preVorSource = graph.getSourceModule(preVor);
		//
		// String port = graph.getPinName(preVorSource, preVor);
		//
		// Vertex vNet = graph.getDestinations(vor).iterator().next();
		//
		// graph.removeVertex(vor);
		//
		// graph.removeVertex(preVor);
		//
		// graph.addConnection(preVorSource, vNet, port);
		//
		// }
		//
		//
		// }

		// adding r input connections to DFFm and INTERP1 modules

		graph.addPort("r"); // add port first

		HashSet<Vertex> needRs = new HashSet<Vertex>();

		needRs.addAll(graph.getModulesByType("DFFm"));

		needRs.addAll(graph.getModulesByType("INTERPD"));

		needRs.addAll(graph.getModulesByType("INTERPS"));

		int rcount = 0;

		for (Vertex v : needRs) {

			Vertex rinp = new Vertex("r[" + rcount + "]", VertexType.NET, "input");

			graph.addVertex(rinp);

			graph.addConnection(rinp, v, "r");

			rcount = rcount + 1;

		}

	}

	/**
	 * A function that duplicates a path of vertices
	 *
	 * <p>
	 * Notes:
	 * <ul>
	 * <li>If a path vertex N is a flip-flop output that is not sourceNet and is
	 * in sourceMsFlopOutputs, an interpreter (of N) is placed and the
	 * interpreted net is connected to the duplicate path.</li>
	 * <li>If a path vertex N is a flip-flop output that is not sourceNet and is
	 * NOT in sourceMsFlopOutputs, the interpreted net is connected to the
	 * duplicate path.</li>
	 * <li>When sourceNet is encountered, the duplicate path is connected to
	 * sourceMZ.</li>
	 * </ul>
	 *
	 * @param graph
	 *            : netlist
	 * @param flipflopOutputs
	 *            : set of flip-flop output nets
	 * @param path
	 *            : set of path vertices
	 * @param dupCounts
	 *            : a map that holds node duplication records
	 * @param finalNet
	 *            : path end node
	 * @param sourceNet
	 *            : flip-flop output net that is the path start
	 * @param sourceMZ
	 *            : the MZ net of the flip-flop that drives sourceNet
	 */
	private static Vertex dupPath(NetlistGraph graph, HashSet<Vertex> flipflopOutputs, HashSet<Vertex> path,
			HashMap<Vertex, Integer> dupCounts, Vertex sourceNet, Vertex sourceMZ, Vertex finalNet,
			HashMap<Vertex, Vertex> mNets, Vertex dclk, HashSet<Vertex> sourceMsFlopOutputs) throws Exception {

		HashMap<Vertex, Vertex> dupMap = new HashMap<Vertex, Vertex>();

		// first, duplicate vertices

		for (Vertex v : path) {

			if (flipflopOutputs.contains(v)) {

				// do not duplicate the output nets of ms flip flops

				// connections to these nets will need be either made to the M
				// output net or derived from the Q output net via an
				// interpreter

				continue;

			}

			Vertex vdup = duplicateVertex(v, dupCounts);

			graph.addVertex(vdup);

			dupMap.put(v, vdup);

		}

		// now add connections between duplicate vertices

		for (Vertex v : path) {

			if (flipflopOutputs.contains(v)) {

				// ditto

				continue;

			}

			Vertex vdup = dupMap.get(v);

			for (Vertex source : graph.getSources(v)) {

				String port = graph.getPinName(source, v);

				Vertex dupSource = dupMap.containsKey(source) ? dupMap.get(source) : source;

				if (source == sourceNet) {

					// metastable flip-flop output that is the path source

					// connect path to the flop M output

					graph.addConnection(sourceMZ, vdup, port);

				} else if (flipflopOutputs.contains(dupSource)) {

					// metastable flip-flop output that is not the path source

					// is there a cdc path between `source` and the destination
					// flip-flop of this path?

					if (sourceMsFlopOutputs.contains(source)) {

						// yes, create interpreter

						insertInterpreter(graph, source, vdup, port, dupCounts, mNets, dclk);

					} else {

						// no, make a direction connection

						graph.addConnection(source, vdup, port);

					}

				} else {

					// neither of the above

					graph.addConnection(dupSource, vdup, port);

				}

			}

		}

		return dupMap.get(finalNet);

	}

	private static String affixToNetName(String net, String postfix) {

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

	private static void insertInterpreter(NetlistGraph graph, Vertex source, Vertex destination, String port,
			HashMap<Vertex, Integer> dupCounts, HashMap<Vertex, Vertex> mNets, Vertex dclk) throws Exception {

		// determining associated m net of source

		Vertex flop = graph.getSourceModule(source);

		Vertex mNet = mNets.get(flop);

		// determining source clock

		Vertex sclk = graph.getNet(flop, clkPort);
		// determining interpreter module class

		String modClass = sclk.equals(dclk) ? "INTERPS" : "INTERPD";

		// creating interpreter and output net

		int dupCount2 = dupCounts.containsKey(INTERPRETER) ? dupCounts.get(INTERPRETER) : 1;

		int dupCount3 = dupCounts.containsKey(source) ? dupCounts.get(source) : 1;

		Vertex interpreter = new Vertex("INTERP" + dupCount2, VertexType.MODULE, modClass);

		String intOutNetName = affixToNetName(source.toString(), "_i" + dupCount3);

		Vertex interpreter_output = new Vertex(intOutNetName, VertexType.NET, "wire");

		dupCounts.put(INTERPRETER, dupCount2 + 1);

		dupCounts.put(source, dupCount3 + 1);

		graph.addVertex(interpreter);

		graph.addVertex(interpreter_output);

		// adding connections

		graph.addConnection(source, interpreter, "Q");

		graph.addConnection(interpreter, interpreter_output, "Qi");

		graph.addConnection(interpreter_output, destination, port);

		graph.addConnection(mNet, interpreter, "M");

		graph.addConnection(sclk, interpreter, "sclk");

		if (!sclk.equals(dclk)) {

			graph.addConnection(dclk, interpreter, "dclk");

		}

	}

	private static Vertex duplicateVertex(Vertex original, HashMap<Vertex, Integer> dupCounts) {

		Vertex vdup = new Vertex(original);

		int dupCount = dupCounts.containsKey(original) ? dupCounts.get(original) : 1;

		vdup.name = affixToNetName(vdup.name, "_" + dupCount);

		dupCounts.put(original, dupCount + 1);

		return vdup;

	}

	/**
	 * A recursive function that duplicates a path of vertices and unmerges all
	 * many-to-one connections
	 *
	 * <p>
	 * Notes:
	 * <ul>
	 * <li>Recursion terminates when a flip-flop output net is encountered.</li>
	 * <li>An interpreter is inserted between the flip-flop output net and the
	 * path</li>
	 * </ul>
	 * </p>
	 *
	 * @param v
	 *            : path end node
	 * @param flopOutputNets
	 *            : flip-flop output nets, required for termination check
	 * @param path
	 *            : set of path vertices
	 * @param graph
	 *            : netlist
	 * @param dupCounts
	 *            : a map that holds node duplication records
	 *
	 * @return duplicate of path end node
	 */
	private static Vertex forkPathRecur(NetlistGraph graph, Vertex v, HashSet<Vertex> flopOutputNets,
			HashSet<Vertex> path, HashMap<Vertex, Integer> dupCounts, HashMap<Vertex, Vertex> mNets, Vertex dclk,
			HashSet<Vertex> sourceMsFlopOutputs) throws Exception {

		// this is a recursive function that duplicates a path and unmerges all
		// many-to-one connections

		// inputs:

		// v : end node of the path
		// flopOutputNets : flip-flop output nets, needed for termination
		// path : set of vertices on the path

		Vertex vdup = duplicateVertex(v, dupCounts);

		graph.addVertex(vdup);

		for (Vertex source : graph.getSources(v)) {

			String port = graph.getPinName(source, v);

			Vertex s;

			if (flopOutputNets.contains(source)) {

				// source is an ms flop output net

				// is there a cdc path between source and v?

				if (sourceMsFlopOutputs.contains(source)) {

					// yes, insert interpreter

					insertInterpreter(graph, source, vdup, port, dupCounts, mNets, dclk);

				} else {

					// no, connect path to the d output of `source`

					graph.addConnection(source, vdup, port);

				}

			} else if (path.contains(source)) {

				// other module/net on the path of metastable nodes, fork ...

				s = forkPathRecur(graph, source, flopOutputNets, path, dupCounts, mNets, dclk, sourceMsFlopOutputs);

				graph.addConnection(s, vdup, port);

			} else {

				// net not on the path of metastable nodes

				s = source;

				graph.addConnection(s, vdup, port);

			}

		}

		return vdup;

	}

}
