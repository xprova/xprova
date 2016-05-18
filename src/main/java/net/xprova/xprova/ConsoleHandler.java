package net.xprova.xprova;

import java.io.PrintStream;
import java.util.ArrayList;

import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.piccolo.Command;
import net.xprova.verilogparser.VerilogParser;

public class ConsoleHandler {

	private GateLibrary lib = null;

	private PrintStream out = System.out;

	private ArrayList<Netlist> loadedDesigns = null;

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

			loadedDesigns = VerilogParser.parseFile(verilogFile, lib);

			out.printf("Loaded %d modules from file %s\n", loadedDesigns.size(), verilogFile);

		}

	}

	@Command(aliases = "list_designs")
	public void listDesigns() {

		if (loadedDesigns == null) {

			out.println("No designs are loaded");

		} else {

			for (Netlist design : loadedDesigns) {

				out.println(design.name);

			}

		}

	}

	public void printNetlistInfo(String designName) {

		for (Netlist design : loadedDesigns) {

			out.println(design.name);

		}


	}

}
