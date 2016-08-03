package net.xprova.simulations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.xprova.graph.Graph;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;
import net.xprova.netlistgraph.VertexType;
import net.xprova.propertylanguage.Property;
import net.xprova.propertylanguage.TreeNode;
import net.xprova.verilogparser.VerilogParser;

public class CodeGenerator {

	public NetlistGraph graph = null;

	// flip-flop q output nets:
	private TreeSet<Vertex> qNets;

	// netlist inputs:
	TreeSet<Vertex> inpNets = new TreeSet<Vertex>();

	// nets that are not input, q or ignored:
	TreeSet<Vertex> internalNets = new TreeSet<Vertex>();

	// map: flip-flop output (q) -> input (d) nets
	HashMap<Vertex, Vertex> flopMap;

	// Java-friendly net names
	HashMap<Vertex, String> jNetNames;

	private ArrayList<String> assigns;

	public ArrayList<Vertex> assertionNets, assumptionNets;

	private int resetState;

	public CodeGenerator(NetlistGraph graph, ArrayList<Property> assumptions, ArrayList<Property> assertions)
			throws Exception {

		this.graph = new NetlistGraph(graph);

		// this.assumptions = assumptions;
		// this.assertions = assertions;
		//
		// HashSet<Property> properties = new HashSet<Property>();
		//
		// properties.addAll(assumptions);
		// properties.addAll(assertions);
		//
		// assertionNets = new ArrayList<Vertex>();
		//
		// checkProperties(properties);
		//
		// addPropertyFlops(properties);

		assumptionNets = new ArrayList<Vertex>();

		assertionNets = new ArrayList<Vertex>();

		Vertex clk = new Vertex("clk_dummy", VertexType.NET, "input");
		Vertex rst = new Vertex("rst_dummy", VertexType.NET, "input");

		this.graph.addVertex(clk);
		this.graph.addVertex(rst);

		this.graph.addInput(clk);
		this.graph.addInput(rst);

		for (Property p : assumptions)
			assumptionNets.add(addProperty(this.graph, p.root, clk, rst));

		for (Property p : assertions)
			assertionNets.add(addProperty(this.graph, p.root, clk, rst));

	}

	public Vertex addProperty(NetlistGraph graph, TreeNode root, Vertex clk, Vertex rst) throws Exception {

		if (root.delay < 0) {

			throw new Exception("encountered node with negative delay in property expression");

		} else if (root.delay > 0) {

			int delay = root.delay;

			root.delay = 0;

			Vertex net = addProperty(graph, root, clk, rst);

			// create chain of flip-flops

			for (int i = 0; i < delay; i++) {

				String ffName = "dff" + graph.getVertexCount();

				Vertex newFlop = new Vertex(ffName, VertexType.MODULE, "DFF");

				graph.addVertex(newFlop);

				graph.addConnection(net, newFlop, "D");

				graph.addConnection(clk, newFlop, "CK");

				graph.addConnection(rst, newFlop, "RS");

				String inputName = "n" + graph.getVertexCount();

				Vertex flopOutput = new Vertex(inputName, VertexType.NET, "wire");

				graph.addVertex(flopOutput);

				graph.addConnection(newFlop, flopOutput, "Q");

				net = flopOutput;
			}

			return net;

		} else if (root.isTerminal()) {

			Vertex v = graph.getVertex(root.name);

			if (v == null) {

				throw new Exception("graph does not contain identifier " + root.name);

			} else {

				return v;

			}

		} else {

			if (root.name.equals("(")) {

				return addProperty(graph, root.children.get(0), clk, rst);

			} else if (root.name.equals("&") || root.name.equals("|") || root.name.equals("^")) {

				// AND/OR gate

				String name = root.name.equals("&") ? "and" : (root.name.equals("|") ? "or" : "xor");

				String modType = root.name.equals("&") ? "AND" : (root.name.equals("|") ? "OR" : "XOR");

				Vertex gate = new Vertex(name + graph.getVertexCount(), VertexType.MODULE, modType);

				Vertex gateOutput = new Vertex("n" + graph.getVertexCount(), VertexType.NET, "wire");

				graph.addVertex(gate);

				graph.addVertex(gateOutput);

				graph.addConnection(gate, gateOutput);

				for (TreeNode c : root.children) {

					Vertex cInput = addProperty(graph, c, clk, rst);

					graph.addConnection(cInput, gate);

				}

				return gateOutput;

			} else if (root.name.equals("~")) {

				// inverter

				Vertex gate = new Vertex("not" + graph.getVertexCount(), VertexType.MODULE, "NOT");

				Vertex gateOutput = new Vertex("n" + graph.getVertexCount(), VertexType.NET, "wire");

				graph.addVertex(gate);

				graph.addVertex(gateOutput);

				graph.addConnection(gate, gateOutput);

				Vertex gateInput = addProperty(graph, root.children.get(0), clk, rst);

				graph.addConnection(gateInput, gate);

				return gateOutput;

			}

			throw new Exception("property operator not yet implemented");

		}

	}

	public ArrayList<String> generate(String templateCode) throws Exception {

		populateStructures();

		HashMap<String, String> netNameMapping = new HashMap<String, String>();

		for (Entry<Vertex, String> entry : jNetNames.entrySet()) {

			netNameMapping.put(entry.getKey().toString(), entry.getValue());

		}

		String expandComment = " // {EXPANDED}";

		String[] arr = templateCode.split("(\r)?(\n)");

		ArrayList<String> lines = new ArrayList<String>();

		for (String s : arr) {

			boolean isComment = s.trim().startsWith("//");

			if (isComment) {

				lines.add(s);

				if (s.contains("{STATE_BIT}") || s.contains("{STATE_BIT_INDEX}") || s.contains("{NEXT_STATE_BIT}")
						|| s.contains("{STATE_BIT_ORG}")) {

					s = s.replaceFirst("//( )+", "");

					int ind = 0;

					for (Vertex v : qNets) {

						String si = s;

						si = si.replace("{STATE_BIT}", jNetNames.get(v));

						si = si.replace("{STATE_BIT_INDEX}", Integer.toString(ind));

						si = si.replace("{NEXT_STATE_BIT}", jNetNames.get(flopMap.get(v)));

						si = si.replace("{STATE_BIT_ORG}", v.name.replace("\\", "\\\\"));

						si += expandComment;

						lines.add(si);

						ind = ind + 1;

					}

				} else if (s.contains("{NON_STATE_BIT}") || s.contains("{NON_STATE_BIT_INDEX}")
						|| s.contains("{NON_STATE_BIT_ORG}")) {

					s = s.replaceFirst("//( )+", "");

					int ind = 0;

					for (Vertex v : internalNets) {

						String si = s;

						si = si.replace("{NON_STATE_BIT}", jNetNames.get(v));

						si = si.replace("{NON_STATE_BIT_INDEX}", Integer.toString(ind));

						si = si.replace("{NON_STATE_BIT_ORG}", v.name.replace("\\", "\\\\"));

						si += expandComment;

						lines.add(si);

						ind = ind + 1;

					}

				} else if (s.contains("{INPUT_BIT}") || s.contains("{INPUT_BIT_INDEX}")
						|| s.contains("{INPUT_BIT_ORG}")) {

					s = s.replaceFirst("//( )+", "");

					int ind = 0;

					for (Vertex v : inpNets) {

						String si = s;

						si = si.replace("{INPUT_BIT}", jNetNames.get(v));

						si = si.replace("{INPUT_BIT_INDEX}", Integer.toString(ind));

						si = si.replace("{INPUT_BIT_ORG}", v.name.replace("\\", "\\\\"));

						si += expandComment;

						lines.add(si);

						ind = ind + 1;

					}

				} else if (s.contains("{COMB_ASSIGN}")) {

					String s2 = s.replaceFirst("//(.)+", "");

					String regexPos1 = "\\{POSTFIX1=([^\\}.]+)\\}";
					String regexPos2 = "\\{POSTFIX2=([^\\}.]+)\\}";
					String regexPre1 = "\\{PREFIX1=([^\\}.]+)\\}";
					String regexPre2 = "\\{PREFIX2=([^\\}.]+)\\}";

					Matcher m1 = Pattern.compile(regexPos1).matcher(s);
					Matcher m2 = Pattern.compile(regexPos2).matcher(s);
					Matcher m3 = Pattern.compile(regexPre1).matcher(s);
					Matcher m4 = Pattern.compile(regexPre2).matcher(s);

					String postfix1 = m1.find() ? m1.group(1) : "";
					String postfix2 = m2.find() ? m2.group(1) : "";
					String prefix1 = m3.find() ? m3.group(1) : "";
					String prefix2 = m4.find() ? m4.group(1) : "";

					for (String a : assigns) {

						String si = s2 + a;

						si = si.replace("{POSTFIX1}", postfix1);
						si = si.replace("{POSTFIX2}", postfix2);
						si = si.replace("{PREFIX1}", prefix1);
						si = si.replace("{PREFIX2}", prefix2);

						si += expandComment;

						lines.add(si);

					}

				} else if (s.contains("{NET:")) {

					s = s.replaceFirst("//( )+", "");

					for (Entry<Vertex, String> entry : jNetNames.entrySet()) {

						String key = String.format("{NET:%s}", entry.getKey().name);

						s = s.replace(key, entry.getValue());

					}

					s += expandComment;

					lines.add(s);

				} else if (s.contains("{RESET_STATE}")) {

					s = s.replaceFirst("//( )+", "");

					s = s.replace("{RESET_STATE}", String.format("0x%x", resetState));

					s += expandComment;

					lines.add(s);

				} else if (s.contains("{STATE_BIT_COUNT}")) {

					s = s.replaceFirst("//( )+", "");

					s = s.replace("{STATE_BIT_COUNT}", "" + qNets.size());

					s += expandComment;

					lines.add(s);

				} else if (s.contains("{INPUT_BIT_COUNT}")) {

					s = s.replaceFirst("//( )+", "");

					s = s.replace("{INPUT_BIT_COUNT}", "" + inpNets.size());

					s += expandComment;

					lines.add(s);

				} else if (s.contains("{ASSUMPTION}")) {

					s = s.replaceFirst("//( )+", "");

					for (Vertex as : assumptionNets) {

						String si = s.replace("{ASSUMPTION}", netNameMapping.get(as.name));

						si += expandComment;

						lines.add(si);

					}

				} else if (s.contains("{ASSERTION}")) {

					s = s.replaceFirst("//( )+", "");

					for (Vertex as : assertionNets) {

						String si = s.replace("{ASSERTION}", netNameMapping.get(as.name));

						si += expandComment;

						lines.add(si);

					}

				}

			} else {

				if (!s.contains(expandComment))
					lines.add(s);
			}

		}

		return lines;

	}

	private void populateStructures() throws Exception {

		qNets = new TreeSet<Vertex>();

		inpNets = new TreeSet<Vertex>();

		internalNets = new TreeSet<Vertex>();

		flopMap = new HashMap<Vertex, Vertex>();

		jNetNames = new HashMap<Vertex, String>();

		// flip-flop d input nets:
		HashSet<Vertex> dNets = new HashSet<Vertex>();

		// clk and rst:
		HashSet<Vertex> ignoreNets = new HashSet<Vertex>();

		// inNets = qNets + inputs:
		HashSet<Vertex> inNets = new HashSet<Vertex>();

		// populating the above sets

		int netCount = 0;

		for (Vertex v : graph.getNets()) {

			String jNetName = v.name;

			// to obtain a Java-friendly variable name,
			// replace all non-word chars with underscores
			jNetName = "n" + jNetName.replaceAll("[\\W]+", "_");

			jNetName += "_" + netCount;

			jNetNames.put(v, jNetName);

			netCount += 1;

		}

		for (Vertex v : graph.getModulesByType("DFF")) {

			qNets.add(graph.getNet(v, "Q"));

			dNets.add(graph.getNet(v, "D"));

			ignoreNets.add(graph.getNet(v, "CK"));

			ignoreNets.add(graph.getNet(v, "RS"));

		}

		inpNets = new TreeSet<Vertex>(graph.getInputs());

		inpNets.removeAll(ignoreNets);

		internalNets.addAll(graph.getNets());
		internalNets.removeAll(inpNets);
		internalNets.removeAll(qNets);
		internalNets.removeAll(ignoreNets);

		inNets.addAll(qNets);
		inNets.addAll(graph.getInputs());

		// graph traversal:

		assigns = new ArrayList<String>();

		Graph<Vertex> netGraph = graph.reduce(graph.getNets());

		HashSet<Vertex> toVisit = new HashSet<Vertex>();

		HashSet<Vertex> processed = new HashSet<Vertex>();

		processed.addAll(qNets);
		processed.addAll(inpNets);

		for (Vertex v : graph.getModulesByType("TIE0")) {

			Vertex vd = graph.getNet(v, "y");

			processed.add(vd);

			assigns.add(String.format("{PREFIX1}%s{POSTFIX1} = 0;", jNetNames.get(vd)));

		}

		for (Vertex v : graph.getModulesByType("TIE1")) {

			Vertex vd = graph.getNet(v, "y");

			processed.add(vd);

			assigns.add(String.format("{PREFIX1}%s{POSTFIX1} = -1;", jNetNames.get(vd)));

		}

		for (Vertex v : graph.getModulesByType("TIEX")) {

			Vertex vd = graph.getNet(v, "y");

			processed.add(vd);

			assigns.add(String.format("{PREFIX1}%s{POSTFIX1} = 0xf0f0f0f0;", jNetNames.get(vd)));

		}

		toVisit = netGraph.bfs(processed, 1, false);

		while (!toVisit.isEmpty()) {

			HashSet<Vertex> toVisitNext = new HashSet<Vertex>();

			final String strAND = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} & {PREFIX2}%s{POSTFIX2};";
			final String strNAND = "{PREFIX1}%s{POSTFIX1} = ~({PREFIX2}%s{POSTFIX2} & {PREFIX2}%s{POSTFIX2});";
			final String strOR = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} | {PREFIX2}%s{POSTFIX2};";
			final String strNOR = "{PREFIX1}%s{POSTFIX1} = ~({PREFIX2}%s{POSTFIX2} | {PREFIX2}%s{POSTFIX2});";
			final String strNOT = "{PREFIX1}%s{POSTFIX1} = ~{PREFIX2}%s{POSTFIX2};";
			final String strXOR = "{PREFIX1}%s{POSTFIX1} = ({PREFIX2}%s{POSTFIX2} ^ {PREFIX2}%s{POSTFIX2});";
			final String strCASSIGN = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2};";
			final String strMUX2 = "{PREFIX1}%s{POSTFIX1} = ({PREFIX2}%s{POSTFIX2} & ~{PREFIX2}%s{POSTFIX2}) | ({PREFIX2}%s{POSTFIX2} & {PREFIX2}%s{POSTFIX2});";
			final String strX2H = "{PREFIX1}%s{POSTFIX1} = (({PREFIX2}%s{POSTFIX2} != 0) & ({PREFIX2}%s{POSTFIX2} != -1)) ? -1 : 0 ;";

			for (Vertex n : toVisit) {

				Vertex driver = graph.getSourceModule(n);

				ArrayList<Vertex> inputs = new ArrayList<Vertex>(graph.getSources(driver));

				boolean areInputsProcessed = true;

				for (Vertex i : inputs)
					areInputsProcessed = areInputsProcessed & processed.contains(i);

				if (!areInputsProcessed)
					continue;

				String line = "// ??";

				String nNameJ = jNetNames.get(n);

				String net1 = inputs.size() > 0 ? jNetNames.get(inputs.get(0)) : "";
				String net2 = inputs.size() > 1 ? jNetNames.get(inputs.get(1)) : "";

				if ("DFF".equals(driver.subtype))
					continue;

				if ("AND".equals(driver.subtype)) {

					line = String.format(strAND, nNameJ, net1, net2);

				} else if ("NAND".equals(driver.subtype)) {

					line = String.format(strNAND, nNameJ, net1, net2);

				} else if ("OR".equals(driver.subtype)) {

					line = String.format(strOR, nNameJ, net1, net2);

				} else if ("NOR".equals(driver.subtype)) {

					line = String.format(strNOR, nNameJ, net1, net2);

				} else if ("NOT".equals(driver.subtype)) {

					line = String.format(strNOT, nNameJ, net1);

				} else if ("XOR".equals(driver.subtype)) {

					line = String.format(strXOR, nNameJ, net1, net2);

				} else if (VerilogParser.CASSIGN_MOD.equals(driver.subtype)) {

					line = String.format(strCASSIGN, nNameJ, net1);

				} else if ("MUX2".equals(driver.subtype)) {

					String netA = jNetNames.get(graph.getNet(driver, "a"));
					String netB = jNetNames.get(graph.getNet(driver, "b"));
					String netS = jNetNames.get(graph.getNet(driver, "s"));

					line = String.format(strMUX2, nNameJ, netA, netS, netB, netS);

				} else if ("X2H".equals(driver.subtype)) {

					line = String.format(strX2H, nNameJ, net1, net1);

				} else {

					throw new Exception("unrecognized gate " + driver.subtype);

				}

				assigns.add(line);

				processed.add(n);

				toVisitNext.addAll(netGraph.getDestinations(n));

			}

			toVisitNext.removeAll(processed);

			toVisit = toVisitNext;

		}

		for (Vertex v : graph.getModulesByType("DFF")) {

			Vertex qNet = graph.getNet(v, "Q");

			Vertex dNet = graph.getNet(v, "D");

			flopMap.put(qNet, dNet);

		}

		// finally, determine the design's resetState

		HashSet<Vertex> ioNets = graph.getIONets();

		for (Vertex q : qNets.descendingSet()) {

			Vertex v = graph.getSourceModule(q);

			Vertex rsNet = graph.getNet(v, "RS");
			Vertex stNet = graph.getNet(v, "ST");

			int bit;

			if (ioNets.contains(rsNet) && !ioNets.contains(stNet)) {

				bit = 0;

			} else if (!ioNets.contains(rsNet) && ioNets.contains(stNet)) {

				bit = 1;

			} else {

				String strE = String.format("could not determine initial state of FF <%s>", v);

				throw new Exception(strE);

			}

			resetState = (resetState << 1) + bit;

		}

	}

}
