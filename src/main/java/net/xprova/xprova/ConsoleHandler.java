package net.xprova.xprova;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import net.xprova.dot.GraphDotPrinter;
import net.xprova.graph.Graph;
import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.Generator;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.netlistgraph.NetlistGraphDotFormatter;
import net.xprova.netlistgraph.Vertex;
import net.xprova.piccolo.Command;
import net.xprova.verilogparser.VerilogParser;

public class ConsoleHandler {

	private GateLibrary lib = null;

	private NetlistGraph netlistGraph = null;

	private PrintStream out = null;

	private HashMap<String, FlipFlop> defsFF = null;

	public ConsoleHandler(PrintStream ps) {

		out = ps;

		defsFF = new HashMap<String, FlipFlop>();

	}

	@Command(aliases = { "load_lib", "ll" })
	public void loadLibrary(String libFile) throws Exception {

		ArrayList<Netlist> libMods = VerilogParser.parseFile(libFile, new GateLibrary());

		lib = new GateLibrary(libMods);

		out.printf("Loaded %d modules from library from %s\n", libMods.size(), libFile);

	}

	@Command(aliases = { "list_modules" })
	public void reportLibrary() {

		if (lib == null) {

			out.println("No library is currently loaded");

		} else {

			lib.printModules();

		}

	}

	@Command(aliases = { "read_verilog", "rv" })
	public void readVerilogFile(String args[]) throws Exception {

		// parse command input

		Option optModule = Option.builder("m").desc("name of module").hasArg().argName("MODULE").required(false)
				.build();

		Options options = new Options();

		options.addOption(optModule);

		CommandLineParser parser = new DefaultParser();

		CommandLine line;

		line = parser.parse(options, args);

		if (line.getArgList().isEmpty())
			throw new Exception("no file specified");

		// use first non-empty argument as file name

		String verilogFile = "";

		for (String str : line.getArgList())
			if (!str.isEmpty())
				verilogFile = str.trim();

		Netlist selNetlist = null;

		// read verilog file

		if (lib == null) {

			out.println("No library is current loaded");

		} else {

			ArrayList<Netlist> nls = VerilogParser.parseFile(verilogFile, lib);

			String moduleName = line.getOptionValue("m", nls.get(0).name);

			for (Netlist nl : nls) {

				if (nl.name.equals(moduleName)) {

					selNetlist = nl;

					break;

				}

			}

			if (selNetlist == null)
				throw new Exception(String.format("Cannot find module <%s> in file <%s>", moduleName, verilogFile));

			netlistGraph = new NetlistGraph(selNetlist);

			out.printf("Loaded design <%s>\n", selNetlist.name);

		}

	}

	@Command(aliases = { "write_verilog" })
	public void writeVerilogFile(String args[]) throws Exception {

		if (args.length != 1) {

			throw new Exception("requires one argument: filename");

		}

		String outputVerilogFile = args[0];

		Generator.generateFile(netlistGraph, outputVerilogFile);
	}

	@Command(aliases = { "info" })
	public void printNetlistInfo() {

		if (netlistGraph == null) {

			out.println("No design is currently loaded");

		} else {

			netlistGraph.printStats();

		}

	}

	@Command(aliases = { "export_dot" })
	public void exportDotFile(String[] args) throws Exception {

		// parse command input

		Option optIgnoreEdges = Option.builder().desc("list of edges to ignore").hasArg().argName("IGNORE_EDGES")
				.required(false).longOpt("ignore-edges").build();

		Option optIgnoreVertices = Option.builder().desc("list of vertices to ignore").hasArg()
				.argName("IGNORE_VERTICES").required(false).longOpt("ignore-vertices").build();

		Option optType = Option.builder("t").longOpt("type").hasArg().desc("graph elements: ([n]ets|[g]ates|[f]lops)")
				.build();

		Option optTo = Option.builder().longOpt("to").hasArg().desc("destination flip-flop").build();

		Option optCombine = Option.builder().longOpt("combine").hasArg().desc("list of vertex prefixes to combine")
				.build();

		Options options = new Options();

		options.addOption(optIgnoreEdges);

		options.addOption(optIgnoreVertices);

		options.addOption(optType);

		options.addOption(optTo);

		options.addOption(optCombine);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		if (line.getArgList().isEmpty())
			throw new Exception("no file specified");

		// use first non-empty argument as file name

		String dotFile = "";

		for (String str : line.getArgList())
			if (!str.isEmpty())
				dotFile = str.trim();

		// produce graph

		if (netlistGraph == null) {

			out.println("No design is currently loaded");

			return;

		}

		NetlistGraph effectiveNetlist;

		Transformer t1 = new Transformer(netlistGraph, defsFF);

		if (line.hasOption("to")) {

			String vName = line.getOptionValue("to");

			Vertex flop = netlistGraph.getVertex(vName);

			if (flop == null) {

				throw new Exception(
						String.format("netlist <%s> does not contain flip-flip <%s>", netlistGraph.getName(), vName));

			}

			HashSet<Vertex> flipflops = t1.getFlops();

			HashSet<Vertex> flopInputVertices = netlistGraph.bfs(flop, flipflops, true);

			Graph<Vertex> flopGraph = netlistGraph.reduce(flipflops);

			flopInputVertices.add(flop);

			flopInputVertices.addAll(flopGraph.getSources(flop));

			effectiveNetlist = netlistGraph.getSubGraph(flopInputVertices);

		} else {

			effectiveNetlist = netlistGraph;

		}

		HashSet<Vertex> selectedVertices;

		if (!line.hasOption('t')) {

			// no type specified, include all vertex types (net, gate,
			// flip-flop)

			selectedVertices = effectiveNetlist.getVertices();

		} else {

			// vertex types were specified

			String vTypes = line.getOptionValue('t');

			selectedVertices = new HashSet<Vertex>();

			Transformer t2 = new Transformer(effectiveNetlist, defsFF);

			HashSet<Vertex> flipflops = t2.getFlops();

			// are flipflops are flipflops2 the same?

			HashSet<Vertex> gates = effectiveNetlist.getModules();

			gates.removeAll(flipflops);

			if (vTypes.contains("n"))
				selectedVertices.addAll(effectiveNetlist.getNets());

			if (vTypes.contains("f"))
				selectedVertices.addAll(flipflops);

			if (vTypes.contains("g"))
				selectedVertices.addAll(gates);

		}

		Graph<Vertex> sg = effectiveNetlist.reduce(selectedVertices);

		if (line.hasOption("combine")) {

			for (String s : line.getOptionValue("combine").split(","))
				combineVertices(s, selectedVertices, effectiveNetlist, sg);

		}

		NetlistGraphDotFormatter formatter = new NetlistGraphDotFormatter(netlistGraph);

		if (line.hasOption("ignore-edges")) {

			formatter.setIgnoredEdges(line.getOptionValue("ignore-edges").split(","));

		}

		if (line.hasOption("ignore-vertices")) {

			formatter.setIgnoredVertices(line.getOptionValue("ignore-vertices").split(","));

		}

		GraphDotPrinter.printGraph(dotFile, sg, formatter, selectedVertices);

	}

	private void combineVertices(String prefix, HashSet<Vertex> selectedVertices, NetlistGraph effectiveNetlist,
			Graph<Vertex> sg) throws Exception {

		HashSet<Vertex> grp1 = new HashSet<Vertex>();

		HashSet<String> grp1_types = new HashSet<String>();

		Vertex comb = null;

		int n = prefix.length();

		for (Vertex v : selectedVertices) {

			String upToNCharacters = v.name.substring(0, Math.min(v.name.length(), n));

			if (upToNCharacters.equals(prefix)) {

				grp1.add(v);

				grp1_types.add(v.subtype);

				comb = v;

			}

		}

		if (!grp1.isEmpty()) {

			grp1.remove(comb);

			selectedVertices.removeAll(grp1);

			comb.name = prefix;

			if (grp1_types.size() > 1) {

				comb.subtype = "BLOCK";

			}

			sg.combineVertices(comb, grp1);

		}

	}

	@Command(aliases = { "augment_netlist", "aug" })
	public void augmentNetlist() throws Exception {

		if (netlistGraph == null) {

			out.println("No design is currently loaded");

		} else {

			Transformer t1 = new Transformer(netlistGraph, defsFF);

			t1.transformCDC();

		}

	}

	@Command(aliases = { "clear_ff_defs" })
	public void clearDefsFF(String[] args) {

		// specify flip-flop modules

		defsFF.clear();

	}

	@Command(aliases = { "def_ff" })
	public void addDefFF(String modName, String clkPort, String rstPort, String dPort) throws Exception {

		if (defsFF.containsKey(modName)) {

			throw new Exception(String.format("definition already exists for flip-flop <%s> ", modName));

		} else {

			FlipFlop ff = new FlipFlop(modName, clkPort, rstPort, dPort);

			defsFF.put(modName, ff);

		}

	}

	@Command(aliases = { "list_ff" })
	public void listDefFF() {

		String strFormat = "%20s %14s %14s %14s\n";

		out.print(String.format(strFormat, "Flip-flip Module", "Clock Port", "Reset Port", "Data Port"));
		out.print(String.format(strFormat, "----------------", "----------", "----------", "---------"));

		for (FlipFlop f : defsFF.values()) {

			out.print(String.format(strFormat, f.moduleName, f.clkPort, f.rstPort, f.dPort));

		}

	}

	@Command(aliases = { "report_domains" })
	public void reportClockDomains() throws Exception {

		Transformer t1 = new Transformer(netlistGraph, defsFF);

		HashSet<Vertex> clks = t1.getClocks();

		String strFormat = "%20s %14s\n";

		out.printf("Found %d clock domain(s):\n\n", clks.size());

		out.print(String.format(strFormat, "Clock Domain Net", "  FFs"));
		out.print(String.format(strFormat, "----------------", "-----"));

		for (Vertex clk : clks) {

			int count = t1.getDomainFlops(clk).size();

			out.print(String.format(strFormat, clk, count));

		}


	}

}
