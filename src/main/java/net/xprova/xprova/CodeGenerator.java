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

		// outNets are flip-flop input nets (next state)
		// and output ports

		// inNets are flip-flop output nets (current state)
		// and input ports

		// outNets must be computed based on inNets

		HashSet<Vertex> outNets = new HashSet<Vertex>();

		HashSet<Vertex> inNets = new HashSet<Vertex>();

		for (Vertex v : graph.getModulesByType("DFF")) {

			outNets.add(graph.getNet(v, "D"));

			inNets.add(graph.getNet(v, "Q"));

		}

		for (Vertex n : graph.getIONets()) {

			if ("input".equals(n.subtype)) {

				inNets.add(n);

			} else if ("output".equals(n.subtype)) {

				outNets.add(n);

			}

		}

		outNets.removeAll(inNets);

		Graph<Vertex> netGraph = graph.reduce(graph.getNets());

		HashSet<Vertex> toVisit = new HashSet<Vertex>(outNets);

		HashSet<Vertex> visited = new HashSet<Vertex>();

		ArrayList<String> assigns = new ArrayList<String>();

		while (!toVisit.isEmpty()) {

			for (Vertex n : toVisit) {

				Vertex driver = graph.getSourceModule(n);

				ArrayList<Vertex> inputs = new ArrayList<Vertex>(graph.getSources(driver));

				String line = "// ??";

				if ("AND".equals(driver.subtype)) {

					line = String.format("%s = %s & %s;", n.name, inputs.get(0).name, inputs.get(1).name);

				} else if ("OR".equals(driver.subtype)) {

					line = String.format("%s = %s | %s;", n.name, inputs.get(0).name, inputs.get(1).name);

				} else if ("NOT".equals(driver.subtype)) {

					line = String.format("%s = !%s;", n.name, inputs.get(0).name);

				} else if ("DFF".equals(driver.subtype)) {

					line = String.format("%s = %s;", n.name, driver.name);

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

			assigns.add(String.format("%s = %s;", qNet, dNet));

		}

		for (String line : assigns)
			out.println(line);

		for (Vertex v : inNets)
			out.println("// state bit : " + v);

	}
}
