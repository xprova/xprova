package net.xprova.xprova;

import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.piccolo.Command;
import net.xprova.verilogparser.VerilogParser;

public class ConsoleHandler {

	private GateLibrary lib = null;

	private NetlistGraph netlistGraph = null;

	private PrintStream out;

	public ConsoleHandler(PrintStream ps) {

		out = ps;

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

	@Command(aliases = { "info" })
	public void printNetlistInfo() {

		if (netlistGraph == null) {

			out.println("No design is currently loaded");

		} else {

			netlistGraph.printStats("n/a");

		}

	}

	@Command(aliases = { "export_dot" })
	public void exportDotFile(String[] args) throws Exception {

		// parse command input

		Option optModule = Option.builder("n").desc("list of pins to ignore").hasArg().argName("IGNORE_PINS")
				.required(false).build();

		Options options = new Options();

		options.addOption(optModule);

		CommandLineParser parser = new DefaultParser();

		CommandLine line;

		line = parser.parse(options, args);

		if (line.getArgList().isEmpty())
			throw new Exception("no file specified");

		// use first non-empty argument as file name

		String dotFile = "";

		for (String str : line.getArgList())
			if (!str.isEmpty())
				dotFile = str.trim();

		// produce graph

		String ignoreList = line.getOptionValue("n", "");

		if (netlistGraph == null) {

			out.println("No design is currently loaded");

		} else {

			netlistGraph.printGraph(dotFile, netlistGraph.getVertices(), ignoreList.split(","));

		}
	}

}
