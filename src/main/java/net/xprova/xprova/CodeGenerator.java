package net.xprova.xprova;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.TreeSet;

import net.xprova.graph.Graph;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;

public class CodeGenerator {

	private NetlistGraph graph = null;

	private PrintStream out = null;

	public CodeGenerator(NetlistGraph graph, PrintStream out) {

		this.graph = graph;

		this.out = out;

	}

	public void generate() throws Exception {

		// flip-flop q output nets:
		TreeSet<Vertex> qNets = new TreeSet<Vertex>();

		// flip-flop d input nets:
		HashSet<Vertex> dNets = new HashSet<Vertex>();

		// netlist inputs:
		TreeSet<Vertex> inpNets = new TreeSet<Vertex>();

		// netlist outputs:
		HashSet<Vertex> outpNets = new HashSet<Vertex>();

		// nets that are not input, q or ignored:
		TreeSet<Vertex> internalNets = new TreeSet<Vertex>();

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

		Graph<Vertex> netGraph = graph.reduce(graph.getNets());

		HashSet<Vertex> toVisit = new HashSet<Vertex>();
		toVisit.addAll(dNets);
		toVisit.addAll(outpNets);
		toVisit.removeAll(graph.getInputs());

		HashSet<Vertex> visited = new HashSet<Vertex>();

		Stack<String> assigns = new Stack<String>();

		HashSet<Vertex> graphInputs = graph.getInputs();

		while (!toVisit.isEmpty()) {

			for (Vertex n : toVisit) {

				System.out.println(n);

				Vertex driver = graph.getSourceModule(n);

				ArrayList<Vertex> inputs = new ArrayList<Vertex>(graph.getSources(driver));

				String line = "// ??";

				if ("DFF".equals(driver.subtype))
					continue;

				if ("AND".equals(driver.subtype)) {

					line = String.format("%s[i] = %s[i] & %s[i];", n, inputs.get(0), inputs.get(1));

				} else if ("NAND".equals(driver.subtype)) {

					line = String.format("%s[i] = ~(%s[i] & %s[i]);", n, inputs.get(0), inputs.get(1));

				} else if ("OR".equals(driver.subtype)) {

					line = String.format("%s[i] = %s[i] | %s[i];", n, inputs.get(0), inputs.get(1));

				} else if ("NOR".equals(driver.subtype)) {

					line = String.format("%s[i] = ~(%s[i] | %s[i]);", n, inputs.get(0), inputs.get(1));

				} else if ("NOT".equals(driver.subtype)) {

					line = String.format("%s[i] = ~%s[i];", n, inputs.get(0));

				} else if ("XOR".equals(driver.subtype)) {

					line = String.format("%s[i] = (%s[i] ^ %s[i]);", n, inputs.get(0), inputs.get(1));

				} else {

					line = String.format("// ?? %s", driver.subtype);

				}

				assigns.add(line);

			}

			visited.addAll(toVisit);

			toVisit = netGraph.bfs(toVisit, 1, true);

			toVisit.removeAll(inNets);

			toVisit.removeAll(visited);

			toVisit.removeAll(graphInputs);

		}

		ArrayList<String> flopAssigns = new ArrayList<String>();

		for (Vertex v : graph.getModulesByType("DFF")) {

			Vertex qNet = graph.getNet(v, "Q");

			Vertex dNet = graph.getNet(v, "D");

			flopAssigns.add(String.format("%s[i+1] = %s[i];", qNet, dNet));

		}

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

		while (!assigns.isEmpty())
			out.printf("\t\t%s\n", assigns.pop());

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
