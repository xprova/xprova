package net.xprova.xprova;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import net.xprova.graph.Graph;
import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.Generator;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.Vertex;
import net.xprova.piccolo.Console;
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


	private static void produceFlopMsInputGraph(NetlistGraph graph, Vertex flop, String dotFile) throws Exception {

		NetlistGraph subgraph = Manipulator.getMsPath(graph, flop);

		String[] ignoreList = new String[] { "clk", "reset", "resetn", "clk1", "clk2", "U1", "n45" };

		subgraph.printGraph(dotFile, subgraph.getVertices(), ignoreList);

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

		subgraph.printGraph(dotFile, subgraph.getVertices(), ignoreList);

	}

	private static String loadBanner() {

		Scanner s = null;

		String bannerFileContent;

		try {

			final InputStream stream;

			stream = Console.class.getClassLoader().getResourceAsStream("xprova_banner.txt");

			s = new Scanner(stream);

			bannerFileContent = s.useDelimiter("\\Z").next();

		} catch (Exception e) {

			bannerFileContent = "<could not load internal banner file>\n";

		} finally {

			if (s != null)
				s.close();

		}

		return bannerFileContent;

	}

	private static void runDebug(Console c) {

		try {

			c.runCommand("ll tests/minfar.lib");
			c.runCommand("read_verilog -m top tests/source.v");
			c.runCommand("export_dot -n=SB,RB,CK tests/source.dot");
			c.runCommand("! dot -Tps tests/source.dot -o tests/source.ps");

		} catch (Exception e) {

			e.getCause().printStackTrace();

		}

	}

	public static void main(String[] args) {

		Console c = new Console();

		c.addHandler(new ConsoleHandler(System.out));

		c.setBanner(loadBanner());

		if (args.length == 1 && "runTests".equals(args[0])) {

			runDebug(c);

			return;

		} else {

			c.run();

		}

	}

}
