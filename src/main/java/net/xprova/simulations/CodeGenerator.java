package net.xprova.simulations;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.xprova.graph.Graph;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;

public class CodeGenerator {

	private NetlistGraph graph = null;

	private PrintStream out = null;

	// flip-flop q output nets:
	private TreeSet<Vertex> qNets;

	// netlist inputs:
	TreeSet<Vertex> inpNets = new TreeSet<Vertex>();

	// nets that are not input, q or ignored:
	TreeSet<Vertex> internalNets = new TreeSet<Vertex>();

	private ArrayList<String> flopAssigns;

	private ArrayList<String> assigns;

	public CodeGenerator(NetlistGraph graph, PrintStream out) throws Exception {

		this.graph = graph;

		this.out = out;

	}

	public void generate(String templateCode) throws Exception {

		populateStructures();

		String expandComment = " // {EXPANDED}";

		String[] arr = templateCode.split("(\r)?(\n)");

		ArrayList<String> lines = new ArrayList<String>();

		for (String s : arr) {

			boolean isComment = s.trim().startsWith("//");

			if (isComment) {

				lines.add(s);

				if (s.contains("{STATE_BIT}") || s.contains("{STATE_BIT_INDEX}")) {

					s = s.replaceFirst("//( )+", "");

					int ind = 0;

					for (Vertex v : qNets) {

						String si = s;

						si = si.replace("{STATE_BIT}", v.toString());

						si = si.replace("{STATE_BIT_INDEX}", Integer.toString(ind));

						si += expandComment;

						lines.add(si);

						ind = ind + 1;

					}

				} else if (s.contains("{NON_STATE_BIT}") || s.contains("{NON_STATE_BIT_INDEX}")) {

					s = s.replaceFirst("//( )+", "");

					int ind = 0;

					for (Vertex v : internalNets) {

						String si = s;

						si = si.replace("{NON_STATE_BIT}", v.toString());

						si = si.replace("{NON_STATE_BIT_INDEX}", Integer.toString(ind));

						si += expandComment;

						lines.add(si);

						ind = ind + 1;

					}

				} else if (s.contains("{INPUT_BIT}") || s.contains("{INPUT_BIT_INDEX}")) {

					s = s.replaceFirst("//( )+", "");

					int ind = 0;

					for (Vertex v : inpNets) {

						String si = s;

						si = si.replace("{INPUT_BIT}", v.toString());

						si = si.replace("{INPUT_BIT_INDEX}", Integer.toString(ind));

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

				} else if (s.contains("{STATE_ASSIGN}")) {

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

					for (String a : flopAssigns) {

						String si = s2 + a;

						si = si.replace("{POSTFIX1}", postfix1);
						si = si.replace("{POSTFIX2}", postfix2);
						si = si.replace("{PREFIX1}", prefix1);
						si = si.replace("{PREFIX2}", prefix2);

						si += expandComment;

						lines.add(si);

					}

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

				}

			} else {

				if (!s.contains(expandComment))
					lines.add(s);
			}

		}

		for (String l : lines)
			out.println(l);

		// populateStructures();
		//
		// generate();
		//
		// pg("State bits:\n");
		//
		// for (Vertex s : qNets)
		// pg("%s\n", s);

	}

	private void populateStructures() throws Exception {

		qNets = new TreeSet<Vertex>();

		inpNets = new TreeSet<Vertex>();

		internalNets = new TreeSet<Vertex>();

		// flip-flop d input nets:
		HashSet<Vertex> dNets = new HashSet<Vertex>();

		// netlist outputs:
		HashSet<Vertex> outpNets = new HashSet<Vertex>();

		// clk and rst:
		HashSet<Vertex> ignoreNets = new HashSet<Vertex>();

		// inNets = qNets + inputs:
		HashSet<Vertex> inNets = new HashSet<Vertex>();

		// populating the above sets

		for (Vertex v : graph.getModulesByType("DFF")) {

			qNets.add(graph.getNet(v, "Q"));

			dNets.add(graph.getNet(v, "D"));

			ignoreNets.add(graph.getNet(v, "CK"));

			ignoreNets.add(graph.getNet(v, "RS"));

		}

		inpNets = new TreeSet<Vertex>(graph.getInputs());

		inpNets.removeAll(ignoreNets);

		outpNets = graph.getOutputs();

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

		toVisit = netGraph.bfs(processed, 1, false);

		HashSet<Vertex> graphInputs = graph.getInputs();

		while (!toVisit.isEmpty()) {

			HashSet<Vertex> toVisitNext = new HashSet<Vertex>();

			for (Vertex n : toVisit) {

				// out.println(n);

				Vertex driver = graph.getSourceModule(n);

				ArrayList<Vertex> inputs = new ArrayList<Vertex>(graph.getSources(driver));

				boolean areInputsProcessed = true;

				for (Vertex i : inputs)
					areInputsProcessed = areInputsProcessed & processed.contains(i);

				if (!areInputsProcessed)
					continue;

				String line = "// ??";

				if ("DFF".equals(driver.subtype))
					continue;

				if ("AND".equals(driver.subtype)) {

					line = String.format("{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} & {PREFIX2}%s{POSTFIX2};", n,
							inputs.get(0), inputs.get(1));

				} else if ("NAND".equals(driver.subtype)) {

					line = String.format("{PREFIX1}%s{POSTFIX1} = ~({PREFIX2}%s{POSTFIX2} & {PREFIX2}%s{POSTFIX2});", n,
							inputs.get(0), inputs.get(1));

				} else if ("OR".equals(driver.subtype)) {

					line = String.format("{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2} | {PREFIX2}%s{POSTFIX2};", n,
							inputs.get(0), inputs.get(1));

				} else if ("NOR".equals(driver.subtype)) {

					line = String.format("{PREFIX1}%s{POSTFIX1} = ~({PREFIX2}%s{POSTFIX2} | {PREFIX2}%s{POSTFIX2});", n,
							inputs.get(0), inputs.get(1));

				} else if ("NOT".equals(driver.subtype)) {

					line = String.format("{PREFIX1}%s{POSTFIX1} = ~{PREFIX2}%s{POSTFIX2};", n, inputs.get(0));

				} else if ("XOR".equals(driver.subtype)) {

					line = String.format("{PREFIX1}%s{POSTFIX1} = ({PREFIX2}%s{POSTFIX2} ^ {PREFIX2}%s{POSTFIX2});", n,
							inputs.get(0), inputs.get(1));

				} else {

					line = String.format("// ?? %s", driver.subtype);

				}

				assigns.add(line);

				processed.add(n);

				toVisitNext.addAll(netGraph.getDestinations(n));

			}

			// visited.addAll(toVisit);

			// toVisit = netGraph.bfs(toVisit, 1, true);

			// toVisit.removeAll(inNets);

			toVisitNext.removeAll(processed);

			toVisit = toVisitNext;

			// toVisit.removeAll(graphInputs);

		}

		// Collections.reverse(assigns);

		flopAssigns = new ArrayList<String>();

		for (Vertex v : graph.getModulesByType("DFF")) {

			Vertex qNet = graph.getNet(v, "Q");

			Vertex dNet = graph.getNet(v, "D");

			flopAssigns.add(String.format("{PREFIX1}%s{POSTFIX1} = {PREFIX2}%s{POSTFIX2};", qNet, dNet));

		}

		Collections.sort(flopAssigns);

	}

	public void generate_old() throws Exception {

		// print out

		String strHeader = "public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {\n";

		out.println(strHeader);

		for (Vertex v : qNets)
			out.printf("\tint[] %s = new int[cycles];\n", v);

		out.println();

		for (Vertex v : qNets)
			out.printf("\t%s[0] = initial[%d];\n", v, qNets.headSet(v).size());

		out.println();

		for (Vertex v : inpNets)
			out.printf("\tint %s[] = inputs.get(%d);\n", v, inpNets.headSet(v).size());

		out.println();

		for (Vertex v : internalNets)
			out.printf("\tint[] %s = new int[cycles];\n", v);

		out.println();

		out.println("\tfor (int i=0; i<cycles; i++) {\n");

		for (String s : assigns)
			out.printf("\t\t%s\n", s);

		out.println();

		out.println("\t\tif (i < cycles-1) {\n");

		for (String line : flopAssigns)
			out.printf("\t\t\t%s\n", line);

		out.println("\t\t}\n");

		out.println("\t}\n");

		out.println("\tArrayList<int[]> waveforms = new ArrayList<int[]>();\n");

		for (Vertex v : qNets)
			out.printf("\twaveforms.add(%s);\n", v);

		out.println();

		for (Vertex v : inpNets)
			out.printf("\twaveforms.add(%s);\n", v);

		out.println();

		for (Vertex v : internalNets)
			out.printf("\twaveforms.add(%s);\n", v);

		out.println();

		out.println("\treturn waveforms;");

		out.println("}");

		//

		out.println();

		out.println("public ArrayList<String> getSignalNames() {\n");

		out.println("\tArrayList<String> result = new ArrayList<String>();\n");

		for (Vertex v : qNets)
			out.printf("\tresult.add(\"%s\");\n", v);

		out.println();

		for (Vertex v : inpNets)
			out.printf("\tresult.add(\"%s\");\n", v);

		out.println();

		for (Vertex v : internalNets)
			out.printf("\tresult.add(\"%s\");\n", v);

		out.println();

		out.println("\treturn result;");

		out.println("}");

		//

		out.println();

		out.println("public int getStateBitCount() {\n");

		out.printf("\treturn %d;\n", qNets.size());

		out.println("}");

		//

		out.println();

		out.println("public int getInputBitCount() {\n");

		out.printf("\treturn %d;\n", inpNets.size());

		out.println("}");

	}

}
