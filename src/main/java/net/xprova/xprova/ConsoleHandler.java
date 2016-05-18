package net.xprova.xprova;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.piccolo.Command;
import net.xprova.verilogparser.VerilogParser;

public class ConsoleHandler {

	private GateLibrary lib = null;

	private NetlistGraph netlistGraph = null;

	private PrintStream out = System.out;

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
	public void readVerilogFile(String verilogFile) throws Exception {

		if (lib == null) {

			out.println("No library is current loaded");

		} else {

			ArrayList<Netlist> nls = VerilogParser.parseFile(verilogFile, lib);

			if (nls.size() > 1) {

				out.println("warning: file contains multiple modules");

			}

			netlistGraph = new NetlistGraph(nls.get(0));

			out.printf("loaded design <%s>\n", nls.get(0).name);

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
	public void exportDotFile(String dotFile) throws FileNotFoundException, UnsupportedEncodingException {

		if (netlistGraph == null) {

			out.println("No design is currently loaded");

		} else {

			netlistGraph.printGraph(dotFile);

		}
	}

}
