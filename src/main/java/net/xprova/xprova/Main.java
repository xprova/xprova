package net.xprova.xprova;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import net.xprova.graph.Graph;
import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.ConnectivityException;
import net.xprova.netlistgraph.Generator;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;
import net.xprova.verilogparser.StructuralException;
import net.xprova.verilogparser.UnsupportedGrammerException;
import net.xprova.verilogparser.VerilogParser;

public class Main {

	private static GateLibrary loadLibrary(String libFile) throws Exception {

		ArrayList<Netlist> libMods = VerilogParser.parseFile(libFile, new GateLibrary());

		return new GateLibrary(libMods);

	}

	private static void doCMDWork(String verilogFile, String libFile, String outputVerilogFile) throws Exception {

		System.out.println("input file  : " + verilogFile);

		System.out.println("output file : " + outputVerilogFile);

		GateLibrary library1 = loadLibrary(libFile);

		ArrayList<Netlist> nlArr = VerilogParser.parseFile(verilogFile, library1);

		Netlist netlist = nlArr.get(0);

		if (nlArr.size() > 1) {

			System.out.println("warning: input file contains multiple modules");

		}

		NetlistGraph graph = new NetlistGraph(netlist);

		graph.printStats("Original");

		Manipulator2.transformCDC(graph);

		graph.printStats("Transformed");

		Generator.generateFile(graph, outputVerilogFile);

	}

	// private void doWork() throws Exception {
	//
	// String verilogFile = "D:\\dev\\cdctool\\synthesized\\design_synth.v";
	//
	// String dotFile = "D:\\dev\\cdctool\\synthesized\\graph.dot";
	//
	// String outputVerilogFile =
	// "D:\\dev\\cdctool\\synthesized\\design_synth_morphed.v";
	//
	// GateLibrary library1 = new GateLibrary("90nm");
	//
	// Netlist netlist = VerilogParser.parse(verilogFile, library1);
	//
	// NetlistGraph graph = new NetlistGraph(netlist);
	//
	// // Manipulator.forkCDCPaths(graph);
	//
	// // System.out.println(Manipulator.getHazardousFlops(graph,
	// // "clk2").toString());
	//
	// graph.printStats("Original");
	//
	// Manipulator2.transformCDC(graph);
	//
	// graph.printStats("Transformed");
	//
	// Vertex v = graph.getVertex("u2_reg1_q_reg_0_");
	//
	// HashSet<Vertex> set1 = graph.bfs(v, Manipulator2.getFlops(graph), true);
	//
	// set1.add(v);
	//
	// graph.printGraph(dotFile);
	//
	// // Manipulator.getMsFlopGraph(graph).printGraph(dotFile);
	//
	// runCmd("dot -Tsvg D:\\dev\\cdctool\\synthesized\\graph.dot -o
	// D:\\dev\\cdctool\\synthesized\\graph.svg");
	//
	// // Manipulator.printMsFlopGraph(Manipulator.getMsFlopGraph(graph));
	//
	// // produceFlopInputGraph(graph, graph.getVertex("u2_data_out_reg_0_"),
	// // dotFile);
	//
	// Generator.generateFile(graph, outputVerilogFile);
	//
	// }

	private static void runCmd(String cmd) throws IOException {

		final Runtime rt = Runtime.getRuntime();

		rt.exec(cmd);

	}

	private static void produceFlopMsInputGraph(NetlistGraph graph, Vertex flop, String dotFile) throws Exception {

		NetlistGraph subgraph = Manipulator.getMsPath(graph, flop);

		String[] ignoreList = new String[] { "clk", "reset", "resetn", "clk1", "clk2", "U1", "n45" };

		subgraph.printGraph(dotFile, ignoreList);

	}

	private static void produceFlopInputGraph(NetlistGraph graph, Vertex flop, String dotFile) throws Exception {

		HashSet<Vertex> flipflops = Manipulator.getFlops(graph);

		HashSet<Vertex> flopInputVertices = graph.bfs(flop, flipflops, true);

		Graph<Vertex> flopGraph = graph.reduce(flipflops);

		flopInputVertices.add(flop);

		flopInputVertices.addAll(flopGraph.getSources(flop));

		NetlistGraph subgraph = graph.getSubGraph(flopInputVertices);

		String[] ignoreList = new String[] { "clk", "reset", "resetn", "clk1", "clk2", "U1", "n45", "n1", "n2", "rst",
				"u1_sync1_n2", "u1_sync1_U4" };

		subgraph.printGraph(dotFile, ignoreList);

	}

	public static void printUsage() {

		// following format guidelines from http://docopt.org/

		String[] usageMsgLines = { "xprova v0.0.1-SNAPSHOT", "", "Usage:", "  xprova source.v library.v augmented.v",
				// "",
				// "Options:",
				// " -h --help Show this screen.", // TODO
				// " --version Show version.", // TODO
		};

		for (int i = 0; i < usageMsgLines.length; i++)
			System.out.println(usageMsgLines[i]);

	}

	public static void main(String[] args) {

		try {

			if (args.length == 3) {

				doCMDWork(args[0], args[1], args[2]);

			} else {

				printUsage();

			}

		} catch (UnsupportedGrammerException e) {

			System.err.println(e.getMessage());

		} catch (StructuralException e) {

			System.err.println(e.getMessage());

		} catch (ConnectivityException e) {

			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
