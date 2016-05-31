package net.xprova.xprova;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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
		HashSet<Vertex> qNets = new HashSet<Vertex>();

		// flip-flop d input nets:
		HashSet<Vertex> dNets = new HashSet<Vertex>();

		// netlist inputs:
		HashSet<Vertex> inpNets = new HashSet<Vertex>();

		// netlist outputs:
		HashSet<Vertex> outpNets = new HashSet<Vertex>();

		// nets that are not input, q or ignored:
		HashSet<Vertex> internalNets = new HashSet<Vertex>();

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

		inpNets = graph.getInputs();

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

		HashSet<Vertex> visited = new HashSet<Vertex>();

		ArrayList<String> assigns = new ArrayList<String>();

		while (!toVisit.isEmpty()) {

			for (Vertex n : toVisit) {

				Vertex driver = graph.getSourceModule(n);

				ArrayList<Vertex> inputs = new ArrayList<Vertex>(graph.getSources(driver));

				String line = "// ??";

				if ("DFF".equals(driver.subtype))
					continue;

				if ("AND".equals(driver.subtype)) {

					String in1 = inNets.contains(inputs.get(0)) ? inputs.get(0).name + "[i-1]" : inputs.get(0).name;
					String in2 = inNets.contains(inputs.get(1)) ? inputs.get(1).name + "[i-1]" : inputs.get(1).name;

					line = String.format("%s = %s & %s;", n.name, in1, in2);

				} else if ("OR".equals(driver.subtype)) {

					String in1 = inNets.contains(inputs.get(0)) ? inputs.get(0).name + "[i-1]" : inputs.get(0).name;
					String in2 = inNets.contains(inputs.get(1)) ? inputs.get(1).name + "[i-1]" : inputs.get(1).name;

					line = String.format("%s = %s | %s;", n.name, in1, in2);

				} else if ("NOT".equals(driver.subtype)) {

					String in1 = inNets.contains(inputs.get(0)) ? inputs.get(0).name + "[i-1]" : inputs.get(0).name;

					line = String.format("%s = !%s;", n.name, in1);

				} else {

					line = String.format("// ?? %s", driver.subtype);

				}

				assigns.add(line);

			}

			visited.addAll(toVisit);

			toVisit = netGraph.bfs(toVisit, 1, true);

			toVisit.removeAll(inNets);

			toVisit.removeAll(visited);

		}

		Collections.reverse(assigns);

		for (Vertex v : graph.getModulesByType("DFF")) {

			Vertex qNet = graph.getNet(v, "Q");

			Vertex dNet = graph.getNet(v, "D");

			assigns.add(String.format("%s[i] = %s;", qNet, dNet));

		}

		// print out

		ArrayList<String> list1 = new ArrayList<String>();

		for (Vertex v : inpNets)
			list1.add("boolean[] " + v.name);

		for (Vertex v : qNets)
			list1.add("boolean[] " + v.name);

		Collections.sort(list1);

		String inputList = String.join(", ", list1);

		out.printf("private void simulate(%s, int cycles) {\n\n", inputList);

		for (Vertex v : internalNets)
			out.printf("\tboolean %s;\n", v);

		out.println();

		out.println("\tfor (int i=1; i<=cycles; i++) {\n");

		for (String line : assigns)
			out.printf("\t\t%s\n", line);

		out.println("\n\t}");

		out.println("}");

	}
}
