package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Arrays;
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
import net.xprova.propertylanguage.PropertyBuilder;
import net.xprova.verilogparser.VerilogParser;

public class CodeGenerator {

	// data structures:
	//
	// qNets: flip-flop q output nets
	//
	// inpNets: netlist inputs
	//
	// internalNets: nets that are not input, q or ignored
	//
	// flopMap: map from flip-flop output (q) to input (d) nets

	// jNetNames: Java-friendly net names
	//
	// assigns: combinations assignments

	private static TreeSet<Vertex> qNets, inpNets, internalNets;

	private static HashMap<Vertex, Vertex> flopMap;

	private static HashMap<Vertex, String> jNetNames;

	private static ArrayList<String> assigns;

	private static int resetState;

	private static final String netIgnorePrefix = "!";

	private static Vertex addPropertyNet(NetlistGraph graph) {

		// adds a net vertex with a unique name to graph

		int i = graph.getVertexCount();

		String inputName;

		do {

			inputName = "*n" + i;

			i++;

		} while (graph.getVertex(inputName) != null);

		Vertex v = new Vertex(inputName, VertexType.NET, "wire");

		graph.addVertex(v);

		return v;

	}

	private static Vertex addPropertyModule(NetlistGraph graph, String subtype) {

		// adds a module vertex with a unique name to graph

		int i = graph.getVertexCount();

		String inputName;

		do {

			inputName = "*" + subtype.toLowerCase() + "_" + i;

			i++;

		} while (graph.getVertex(inputName) != null);

		Vertex v = new Vertex(inputName, VertexType.MODULE, subtype);

		graph.addVertex(v);

		return v;

	}

	private static Vertex addProperty(NetlistGraph graph, Property root, Vertex clk, Vertex rst, Vertex set)
			throws Exception {

		if (root.delay < 0) {

			throw new Exception("encountered node with negative delay in property expression");

		}

		if (root.delay > 0) {

			int delay = root.delay;

			root.delay = 0;

			Vertex net = addProperty(graph, root, clk, rst, set);

			// create chain of flip-flops

			for (int i = 0; i < delay; i++) {

				Vertex newFlop = addPropertyModule(graph, "DFF");

				Vertex flopOutput = addPropertyNet(graph);

				graph.addConnection(net, newFlop, "D");

				graph.addConnection(clk, newFlop, "CK");

				graph.addConnection(rst, newFlop, "RS");

				graph.addConnection(newFlop, flopOutput, "Q");

				net = flopOutput;
			}

			root.delay = delay;

			return net;

		}

		if (root.isTerminal()) {

			boolean isHigh = root.name.equals(PropertyBuilder.HIGH);
			boolean isLow = root.name.equals(PropertyBuilder.LOW);

			if (isHigh | isLow) {

				String tieModName = isHigh ? "TIE1" : "TIE0";

				Vertex tieMod = addPropertyModule(graph, tieModName);

				Vertex tie1Output = addPropertyNet(graph);

				graph.addVertex(tieMod);

				graph.addVertex(tie1Output);

				graph.addConnection(tieMod, tie1Output, "y");

				return tie1Output;

			} else if (root.isNumber()) {

				Vertex driver = addPropertyModule(graph, "CONST");

				driver.tag = root.name;

				Vertex net = addPropertyNet(graph);

				graph.addConnection(driver, net, "y");

				return net;

			} else {

				Vertex v = graph.getVertex(root.name);

				if (v == null) {

					throw new Exception("graph does not contain identifier " + root.name);

				} else {

					return v;

				}

			}

		}

		if (root.name.equals(PropertyBuilder.LPAREN)) {

			return addProperty(graph, root.children.get(0), clk, rst, set);

		}

		final String[] comb = { PropertyBuilder.AND, PropertyBuilder.OR, PropertyBuilder.XOR };

		if (Arrays.asList(comb).contains(root.name)) {

			// AND/OR/XOR gate

			String modType;

			if (root.name.equals(PropertyBuilder.AND)) {

				modType = "AND";

			} else if (root.name.equals(PropertyBuilder.OR)) {

				modType = "OR";

			} else {

				modType = "XOR";

			}

			Vertex gate = addPropertyModule(graph, modType);

			Vertex gateOutput = addPropertyNet(graph);

			graph.addConnection(gate, gateOutput);

			for (Property c : root.children) {

				Vertex cInput = addProperty(graph, c, clk, rst, set);

				graph.addConnection(cInput, gate);

			}

			return gateOutput;

		}

		if (root.name.equals(PropertyBuilder.NOT)) {

			// inverter

			Vertex gate = addPropertyModule(graph, "NOT");

			Vertex gateOutput = addPropertyNet(graph);

			graph.addConnection(gate, gateOutput, "y");

			Vertex gateInput = addProperty(graph, root.children.get(0), clk, rst, set);

			graph.addConnection(gateInput, gate, "a");

			return gateOutput;

		}

		if (root.name.equals(PropertyBuilder.ALWAYS)) {

			Vertex alwaysInput = addProperty(graph, root.children.get(0), clk, rst, set);

			Vertex alwaysNet = insertSingleInputAlwaysBlock(graph, alwaysInput, clk, set, rst);

			return alwaysNet;

		}

		if (root.name.equals(PropertyBuilder.EVENTUALLY)) {

			// create a liveness net

			if (root.children.size() == 2) {

				Vertex trigger = addProperty(graph, root.children.get(0), clk, rst, set);

				Vertex expr = addProperty(graph, root.children.get(1), clk, rst, set);

				return insertTwoInputEventuallyBlock(graph, trigger, expr, clk, set, rst);

			} else if (root.children.size() == 1) {

				Vertex expr = addProperty(graph, root.children.get(0), clk, rst, set);

				return insetSingleInputEventuallyBlock(graph, expr, clk, set, rst);

			}

		}

		final String[] twoOps = { PropertyBuilder.EQ, PropertyBuilder.NEQ, PropertyBuilder.LT, PropertyBuilder.GT,
				PropertyBuilder.LE, PropertyBuilder.GE };

		if (Arrays.asList(twoOps).contains(root.name)) {

			Vertex op1 = addProperty(graph, root.children.get(0), clk, rst, set);

			Vertex op2 = addProperty(graph, root.children.get(1), clk, rst, set);

			Vertex mod = addPropertyModule(graph, root.name);

			Vertex output = addPropertyNet(graph);

			graph.addConnection(op1, mod);

			graph.addConnection(op2, mod);

			graph.addConnection(mod, output);

			return output;

		}

		if (root.name.equals(PropertyBuilder.WHEN)) {

			Vertex trigger = addProperty(graph, root.children.get(0), clk, rst, set);

			Vertex expr = addProperty(graph, root.children.get(1), clk, rst, set);

			Vertex output = insertWhenBlock(graph, trigger, expr, clk, rst, set);

			return output;

		}

		if (root.name.equals("{")) {

			Vertex g = addPropertyModule(graph, "GROUP");

			for (int i = 0; i < root.children.size(); i++) {

				Vertex child = addProperty(graph, root.children.get(i), clk, rst, set);

				graph.addConnection(child, g, "" + i);

			}

			Vertex gout = addPropertyNet(graph);

			graph.addConnection(g, gout, "y");

			return gout;

		}


		throw new Exception("property operator not yet implemented");

	}

	private static Vertex insertWhenBlock(NetlistGraph graph, Vertex trigger, Vertex expr, Vertex clk, Vertex rst,
			Vertex set) throws Exception {

		Vertex muxOutput = addPropertyNet(graph);

		Vertex flopOutput = addPropertyNet(graph);

		Vertex flop = addPropertyModule(graph, "DFF");

		Vertex mux = addPropertyModule(graph, "MUX2");

		graph.addConnection(flopOutput, mux, "a");

		graph.addConnection(expr, mux, "b");

		graph.addConnection(mux, muxOutput, "y");

		graph.addConnection(trigger, mux, "s");

		graph.addConnection(muxOutput, flop, "D");

		graph.addConnection(flop, flopOutput, "Q");

		graph.addConnection(set, flop, "RS");

		graph.addConnection(clk, flop, "CK");

		return flopOutput;

	}

	private static Vertex insertSingleInputAlwaysBlock(NetlistGraph graph, Vertex input, Vertex clk, Vertex set,
			Vertex rst) throws Exception {

		// a block with one input (input) and one output (flopOutput)

		// returns high until input is low then returns low forever (starting
		// from the same cycle)

		Vertex and = addPropertyModule(graph, "AND");

		Vertex flopInput = addPropertyNet(graph);

		Vertex flop = addPropertyModule(graph, "DFF");

		Vertex flopOutput = addPropertyNet(graph);

		graph.addConnection(and, flopInput, "y");

		graph.addConnection(flopInput, flop, "D");

		graph.addConnection(flop, flopOutput, "Q");

		graph.addConnection(clk, flop, "CK");

		graph.addConnection(set, flop, "ST");

		graph.addConnection(flopOutput, and, "a");

		graph.addConnection(input, and, "b");

		return flopInput;

	}

	private static boolean isLive(Property p) {

		// TODO: expand this stub (only eventually for now)

		return p.name.equals(PropertyBuilder.EVENTUALLY);

	}

	public static ArrayList<String> generate(NetlistGraph graph, ArrayList<Property> assumptions,
			ArrayList<Property> assertions, String templateCode) throws Exception {

		// Step 1: Add properties to graph

		HashMap<Property, Vertex> assumptionNets = new HashMap<Property, Vertex>();
		HashMap<Property, Vertex> assertionNets = new HashMap<Property, Vertex>();

		HashMap<Property, Vertex> liveAssertionNets = new HashMap<Property, Vertex>();

		Vertex clk = new Vertex(netIgnorePrefix + "clk_prop", VertexType.NET, "input");
		Vertex rst = new Vertex(netIgnorePrefix + "rst_prop", VertexType.NET, "input");
		Vertex set = new Vertex(netIgnorePrefix + "set_prop", VertexType.NET, "input");

		graph.addVertex(clk);
		graph.addVertex(rst);
		graph.addVertex(set);

		for (Property p : assumptions) {

			if (isLive(p)) {

				throw new Exception("specified live property as an assumption");

			} else {

				assumptionNets.put(p, addProperty(graph, p, clk, rst, set));

			}

		}

		for (Property p : assertions) {

			if (isLive(p)) {

				liveAssertionNets.put(p, addProperty(graph, p, clk, rst, set));

			} else {

				assertionNets.put(p, addProperty(graph, p, clk, rst, set));

			}

		}

		// Step 2 : Populate code generation structures

		populateStructures(graph);

		// Step 3 : Generate code

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

					for (Entry<Property, Vertex> entry : assumptionNets.entrySet()) {

						String netName = entry.getValue().name;

						String si = s.replace("{ASSUMPTION}", netNameMapping.get(netName));

						si = si.replace("{MAXDELAY}", "" + getMaxDelay(entry.getKey()));

						si += expandComment;

						lines.add(si);

					}

				} else if (s.contains("{ASSERTION}")) {

					s = s.replaceFirst("//( )+", "");

					for (Entry<Property, Vertex> entry : assertionNets.entrySet()) {

						String netName = entry.getValue().name;

						String si = s.replace("{ASSERTION}", netNameMapping.get(netName));

						si = si.replace("{MAXDELAY}", "" + getMaxDelay(entry.getKey()));

						si += expandComment;

						lines.add(si);

					}

				} else if (s.contains("{LIVE_ASSERTION}")) {

					s = s.replaceFirst("//( )+", "");

					for (Entry<Property, Vertex> entry : liveAssertionNets.entrySet()) {

						String netName = entry.getValue().name;

						String si = s.replace("{LIVE_ASSERTION}", netNameMapping.get(netName));

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

	private static void populateStructures(NetlistGraph graph) throws Exception {

		qNets = new TreeSet<Vertex>();

		inpNets = new TreeSet<Vertex>();

		internalNets = new TreeSet<Vertex>();

		flopMap = new HashMap<Vertex, Vertex>();

		jNetNames = new HashMap<Vertex, String>();

		assigns = new ArrayList<String>();

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

			Vertex rsNet = graph.getNet(v, "RS");
			Vertex stNet = graph.getNet(v, "ST");

			if (rsNet != null && graph.isInput(rsNet))
				ignoreNets.add(rsNet);

			if (stNet != null && graph.isInput(stNet))
				ignoreNets.add(stNet);

		}

		for (Vertex v : graph.getNets()) {

			if (v.name.startsWith(netIgnorePrefix))
				ignoreNets.add(v);

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

		for (Vertex v : graph.getModulesByType("CONST")) {

			Vertex vd = graph.getNet(v, "y");

			processed.add(vd);

			assigns.add(String.format("{PREFIX1}%s{POSTFIX1} = %s;", jNetNames.get(vd), v.tag));

		}

		toVisit = netGraph.bfs(processed, 1, false);

		while (!toVisit.isEmpty()) {

			HashSet<Vertex> toVisitNext = new HashSet<Vertex>();

			final String[] strAND = { "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2}", " & {PREFIX2}%s{POSTFIX2}",
					";" };
			final String[] strNAND = { "{PREFIX1}%s{POSTFIX1} = ~({PREFIX2}%s{POSTFIX2}", " & {PREFIX2}%s{POSTFIX2}",
					");" };
			final String[] strOR = { "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2}", " | {PREFIX2}%s{POSTFIX2}", ";" };
			final String[] strNOR = { "{PREFIX1}%s{POSTFIX1} = ~({PREFIX2}%s{POSTFIX2}", " | {PREFIX2}%s{POSTFIX2}",
					");" };
			final String[] strXOR = { "{PREFIX1}%s{POSTFIX1} = ({PREFIX2}%s{POSTFIX2}", " ^ {PREFIX2}%s{POSTFIX2})",
					";" };

			final String strNOT = "{PREFIX1}%s{POSTFIX1} = ~{PREFIX2}%s{POSTFIX2};";
			final String strCASSIGN = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2};";
			final String strMUX2 = "{PREFIX1}%s{POSTFIX1} = ({PREFIX2}%s{POSTFIX2} & ~{PREFIX2}%s{POSTFIX2}) | ({PREFIX2}%s{POSTFIX2} & {PREFIX2}%s{POSTFIX2});";
			final String strX2H = "{PREFIX1}%s{POSTFIX1} = (({PREFIX2}%s{POSTFIX2} != 0) & ({PREFIX2}%s{POSTFIX2} != -1)) ? -1 : 0 ;";
			final String strEQ = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} == {PREFIX2}%s{POSTFIX2} ? -1 : 0;";
			final String strNEQ = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} != {PREFIX2}%s{POSTFIX2} ? -1 : 0;";
			final String strLT = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} < {PREFIX2}%s{POSTFIX2} ? -1 : 0;";
			final String strGT = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} > {PREFIX2}%s{POSTFIX2} ? -1 : 0;";
			final String strLE = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} <= {PREFIX2}%s{POSTFIX2} ? -1 : 0;";
			final String strGE = "{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} >= {PREFIX2}%s{POSTFIX2} ? -1 : 0;";
			final String strGroup_A = "{PREFIX1}%s{POSTFIX1} = 0;";
			final String strGroup_B = "{PREFIX1}%s{POSTFIX1} += ({PREFIX1}%s{POSTFIX1} & 1) << %d;";

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

				// note: the arrays `combFuns` and `combFunParts` must contain
				// elements in a corresponding order
				final String[] combFuns = new String[] { "AND", "NAND", "OR", "NOR", "XOR" };
				final String[][] combFunParts = new String[][] { strAND, strNAND, strOR, strNOR, strXOR };

				String net1 = inputs.size() > 0 ? jNetNames.get(inputs.get(0)) : "";

				if ("DFF".equals(driver.subtype))
					continue;

				int combInd = Arrays.asList(combFuns).indexOf(driver.subtype);

				if (combInd != -1) {

					String[] combParts = combFunParts[combInd];

					line = String.format(combParts[0], nNameJ, net1);

					for (int i = 1; i < inputs.size(); i++) {

						String netI = jNetNames.get(inputs.get(i));

						line += String.format(combParts[1], netI);

					}

					line += combParts[2];

					assigns.add(line);

				} else if ("NOT".equals(driver.subtype)) {

					line = String.format(strNOT, nNameJ, net1);

					assigns.add(line);

				} else if (VerilogParser.CASSIGN_MOD.equals(driver.subtype)) {

					line = String.format(strCASSIGN, nNameJ, net1);

					assigns.add(line);

				} else if ("MUX2".equals(driver.subtype)) {

					String netA = jNetNames.get(graph.getNet(driver, "a"));
					String netB = jNetNames.get(graph.getNet(driver, "b"));
					String netS = jNetNames.get(graph.getNet(driver, "s"));

					line = String.format(strMUX2, nNameJ, netA, netS, netB, netS);

					assigns.add(line);

				} else if ("X2H".equals(driver.subtype)) {

					line = String.format(strX2H, nNameJ, net1, net1);

					assigns.add(line);

				} else if ("==".equals(driver.subtype)) {

					String net2 = inputs.size() > 0 ? jNetNames.get(inputs.get(1)) : "";

					line = String.format(strEQ, nNameJ, net1, net2);

					assigns.add(line);

				} else if ("!=".equals(driver.subtype)) {

					String net2 = inputs.size() > 0 ? jNetNames.get(inputs.get(1)) : "";

					line = String.format(strNEQ, nNameJ, net1, net2);

					assigns.add(line);

				} else if ("<".equals(driver.subtype)) {

					String net2 = inputs.size() > 0 ? jNetNames.get(inputs.get(1)) : "";

					line = String.format(strLT, nNameJ, net1, net2);

					assigns.add(line);

				} else if (">".equals(driver.subtype)) {

					String net2 = inputs.size() > 0 ? jNetNames.get(inputs.get(1)) : "";

					line = String.format(strGT, nNameJ, net1, net2);

					assigns.add(line);

				} else if ("<=".equals(driver.subtype)) {

					String net2 = inputs.size() > 0 ? jNetNames.get(inputs.get(1)) : "";

					line = String.format(strLE, nNameJ, net1, net2);

					assigns.add(line);

				} else if (">=".equals(driver.subtype)) {

					String net2 = inputs.size() > 0 ? jNetNames.get(inputs.get(1)) : "";

					line = String.format(strGE, nNameJ, net1, net2);

					assigns.add(line);

				} else if ("GROUP".equals(driver.subtype)) {

					line = String.format(strGroup_A, nNameJ);

					assigns.add(line);

					for (int i = 0; i < inputs.size(); i++) {

						String netI = jNetNames.get(graph.getNet(driver, "" + i));

						line = String.format(strGroup_B, nNameJ, netI, i);

						assigns.add(line);

					}

				} else {

					throw new Exception("unrecognized gate " + driver.subtype);

				}

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

	private static Vertex insetSingleInputEventuallyBlock(NetlistGraph graph, Vertex expr, Vertex clk, Vertex set,
			Vertex rst) throws Exception {

		Vertex or2 = addPropertyModule(graph, "OR"); // expr

		Vertex not = addPropertyModule(graph, "NOT");

		Vertex flopOutput2 = addPropertyNet(graph);

		Vertex flopInput2 = addPropertyNet(graph);

		Vertex liveNet = addPropertyNet(graph);

		Vertex flop2 = addPropertyModule(graph, "DFF");

		graph.addConnection(clk, flop2, "CK");
		graph.addConnection(set, flop2, "RS");

		graph.addConnection(flopInput2, flop2, "D");

		graph.addConnection(flop2, flopOutput2, "Q");

		graph.addConnection(expr, or2);

		graph.addConnection(or2, flopInput2);

		graph.addConnection(flopOutput2, or2);

		graph.addConnection(flopOutput2, not);
		graph.addConnection(not, liveNet);

		return liveNet;

	}

	private static Vertex insertTriggerBlock(NetlistGraph graph, Vertex trigger_in, Vertex expr, Vertex clk, Vertex set,
			Vertex rst) throws Exception {

		// a block with one input (trigger_in) and one output (triggerFired)

		// trigger_out is initially low but once trigger_in goes high,
		// trigger_out goes high and remains high forever

		Vertex flop1 = addPropertyModule(graph, "DFF");
		Vertex or1 = addPropertyModule(graph, "OR");

		Vertex flopInput1 = addPropertyNet(graph);
		Vertex triggerFired = addPropertyNet(graph);

		graph.addConnection(trigger_in, or1);
		graph.addConnection(or1, flopInput1);
		graph.addConnection(triggerFired, or1);

		graph.addConnection(flopInput1, flop1, "D");
		graph.addConnection(flop1, triggerFired, "Q");

		graph.addConnection(clk, flop1, "CK");
		graph.addConnection(set, flop1, "RS");

		return triggerFired;
	}

	private static Vertex insertTwoInputEventuallyBlock(NetlistGraph graph, Vertex trigger, Vertex expr, Vertex clk,
			Vertex set, Vertex rst) throws Exception {

		// a block with two inputs (trigger and expr) and one output (liveNet)

		// initially returns low
		// when trigger goes high then it returns high until expr goes high too
		// where it returns low forever

		Vertex triggerFired = insertTriggerBlock(graph, trigger, expr, clk, set, rst);

		Vertex eventuallyNet = insetSingleInputEventuallyBlock(graph, expr, clk, set, rst);

		Vertex and = addPropertyModule(graph, "AND");

		Vertex liveNet = addPropertyNet(graph);

		graph.addConnection(triggerFired, and);

		graph.addConnection(eventuallyNet, and);

		graph.addConnection(and, liveNet);

		return liveNet;

	}

	private static int getMaxDelay(Property p) {

		Property copy = new Property(p);

		copy.flattenDelays(0);

		return copy.getMaxDelay(0);

	}

}
