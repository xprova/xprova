package net.xprova.xprova;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

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
import net.xprova.piccolo.Console;
import net.xprova.propertylanguage.Property;
import net.xprova.propertylanguage.PropertyBuilder;
import net.xprova.simulations.CodeGenerator;
import net.xprova.simulations.Waveform;
import net.xprova.verilogparser.VerilogParser;

public class ConsoleHandler {

	private GateLibrary lib = null;

	private NetlistGraph current = null;

	private PrintStream out = null;

	private HashMap<String, FlipFlop> defsFF = null;

	private ArrayList<Property> assumptions = null;

	private ArrayList<Property> assertions = null;

	private TreeMap<String, NetlistGraph> designs = null;

	public ConsoleHandler(PrintStream ps) {

		out = ps;

		defsFF = new HashMap<String, FlipFlop>();

		assumptions = new ArrayList<Property>();

		assertions = new ArrayList<Property>();

		designs = new TreeMap<String, NetlistGraph>();

	}

	// Commands:

	//@formatter:off
	@Command(
		description = "add and manage cell libraries",
		help = {
			"Usage:",
			"  library load <verilog_file>",
			"  library list"
		}
	)
	//@formatter:on
	public void library(String[] args) throws Exception {

		if (args.length > 0) {

			String cmd = args[0];

			if ("load".equals(cmd) && args.length > 1) {

				String libFile = args[1];

				ArrayList<Netlist> libMods = VerilogParser.parseFile(libFile, new GateLibrary());

				lib = new GateLibrary(libMods);

				out.printf("Loaded %d modules from library %s\n", libMods.size(), libFile);

				return;

			} else if ("list".equals(cmd)) {

				if (lib == null)
					out.println("No library is currently loaded");
				else
					lib.printModules();

				return;

			}

		}

		throw new Exception("error parsing arguments of library");

	}

	//@formatter:off
	@Command(
		aliases = { "read" },
		description = "read verilog file (gate-level netlist)",
		help = {
			"Usage:",
			"  read [-m <module>] [-b] <verilog_file>",
			"",
			"Options:",
			"  -m <module>      specify top module (first in file be default)",
			"  -b --behavioral  read behavioral design (synthesize in background using yosys)"
		}
	)
	//@formatter:on
	public void read(String args[]) throws Exception {

		// parse command input

		Option optModule = Option.builder("m").hasArg().build();

		Option optBehav = Option.builder("b").longOpt("behavioral").build();

		Options options = new Options();

		options.addOption(optModule);

		options.addOption(optBehav);

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

		// read verilog file

		if (lib == null) {

			throw new Exception("No library is currently loaded");

		} else {

			if (line.hasOption("b")) {

				String synthFile = getTempFile("synth.v");

				String hash = getFileChecksum(verilogFile);

				String storedHash = getStoredChecksum();

				if (hash.equals(storedHash)) {

					System.out.println("No design file changes detected, synthesis skipped");

				} else {

					out.printf("Synthesizing behavioral design to %s ...\n", synthFile);

					(new SynthesisEngine()).synthesis(verilogFile, synthFile, out);

					storeChecksum(hash);

				}

				verilogFile = synthFile;

			}

			ArrayList<Netlist> nls = VerilogParser.parseFile(verilogFile, lib);

			HashMap<String, Netlist> newNLs = new HashMap<String, Netlist>();

			for (Netlist nl : nls)
				newNLs.put(nl.name, nl);

			String moduleName = line.getOptionValue("m", nls.get(0).name);

			if (!newNLs.containsKey(moduleName))
				throw new Exception(String.format("Cannot find module <%s> in file <%s>", moduleName, verilogFile));

			ArrayList<NetlistGraph> newDesigns = new ArrayList<NetlistGraph>();

			out.printf("Reading file <%s> ...\n", verilogFile);

			for (Netlist nl : nls) {

				out.printf("Parsing design <%s> ...\n", nl.name, verilogFile);

				newDesigns.add(new NetlistGraph(nl));

			}

			int countBefore = designs.size();

			for (NetlistGraph ng : newDesigns)
				designs.put(ng.getName(), ng);

			int countAfter = designs.size();

			int countNew = countAfter - countBefore;

			int countOverwritten = newDesigns.size() - countNew;

			out.printf("Successfully parsed and loaded %d new design(s) \n", newDesigns.size());

			if (countOverwritten > 0) {

				out.printf("%d existing modules were re-read.\n", countOverwritten);

			}

			out.printf("Current design is <%s>\n", moduleName);

			current = designs.get(moduleName);

		}

	}

	//@formatter:off
	@Command(
		description = "export current design as a verilog netlist",
		help = {
			"Usage:",
			"  write <verilog_file>",
		}
	)
	//@formatter:on
	public void write(String args[]) throws Exception {

		if (args.length != 1)
			throw new Exception("requires one argument: filename");

		String outputVerilogFile = args[0];

		Generator.generateFile(current, outputVerilogFile);
	}

	//@formatter:off
	@Command(
		description = "export a graph representation of the loaded design in DOT format",
		help = {
			"Usage:",
			"  dot [--ignore-edges e1,e2,...] [--ignore-vertices v1,v2,...]",
			"      [--type fng] [--to flipflop] [--combine v1,v2,...]",
			"      [--pdf] [--module-types] <output_file>",
			"",
			"Options:",
			"  -e --ignore-edges e1,e2,...     exclude edges from graph",
			"  -v --ignore-vertices v1,v2,...  exclude vertices from graph",
			"  -o --to <flipflop>              export sub-graph of flipflop and its combinational logic",
			"  -t --type fng                   include (f)lip-flops, (n)ets and/or (g)ates",
			"  -c --combine v1,v2,...          combine group of vertices into a single vertex",
			"  -p --pdf                        output a PDF file directly (requires DOT)",
			"  -m --module-types               label modules with types instead of instance names"
		}
	)
	//@formatter:on
	public void dot(String[] args) throws Exception {

		// parse command input

		Option[] opts = {

				Option.builder("e").hasArg().longOpt("ignore-edges").build(),

				Option.builder("v").hasArg().longOpt("ignore-vertices").build(),

				Option.builder("t").longOpt("type").hasArg().build(),

				Option.builder("o").longOpt("to").hasArg().build(),

				Option.builder("c").longOpt("combine").hasArg().build(),

				Option.builder("p").longOpt("pdf").build(),

				Option.builder("m").longOpt("module-types").build(),

		};

		Options options = new Options();

		for (Option opt : opts)
			options.addOption(opt);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		if (line.getArgList().isEmpty())
			throw new Exception("no file specified");

		// use first non-empty argument as file name

		String outFile = "";

		for (String str : line.getArgList())
			if (!str.isEmpty())
				outFile = str.trim();

		// produce graph

		assertDesignLoaded();

		NetlistGraph effectiveNetlist;

		if (line.hasOption("to")) {

			String vName = line.getOptionValue("to");

			Vertex flop = current.getVertex(vName);

			if (flop == null) {

				throw new Exception(
						String.format("netlist <%s> does not contain flip-flop <%s>", current.getName(), vName));

			}

			HashSet<Vertex> flipflops = current.getModulesByTypes(defsFF.keySet());

			HashSet<Vertex> flopInputVertices = current.bfs(flop, flipflops, true);

			Graph<Vertex> flopGraph = current.reduce(flipflops);

			flopInputVertices.add(flop);

			flopInputVertices.addAll(flopGraph.getSources(flop));

			effectiveNetlist = current.getSubGraph(flopInputVertices);

		} else {

			effectiveNetlist = current;

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

			HashSet<Vertex> flipflops = current.getModulesByTypes(defsFF.keySet());

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

		boolean useModuleTypeLabels = line.hasOption("m");

		NetlistGraphDotFormatter formatter = new NetlistGraphDotFormatter(current, useModuleTypeLabels);

		if (line.hasOption("ignore-edges")) {

			formatter.setIgnoredEdges(line.getOptionValue("ignore-edges").split(","));

		}

		if (line.hasOption("ignore-vertices")) {

			formatter.setIgnoredVertices(line.getOptionValue("ignore-vertices").split(","));

		}

		if (line.hasOption("--pdf")) {

			String tempDotFile = getTempFile("netlist.dot");

			// write temp dot file

			GraphDotPrinter.printGraph(tempDotFile, sg, formatter, selectedVertices);

			// invoke DOT

			String cmdDot = "dot -Tpdf " + tempDotFile + " -o " + outFile;

			int dotExitCode = executeProgram(cmdDot, true, true);

			if (dotExitCode != 0)
				throw new Exception("Error while attempting to convert dot file to pdf using DOT");

		} else {

			GraphDotPrinter.printGraph(outFile, sg, formatter, selectedVertices);
		}

	}

	//@formatter:off
	@Command(
		aliases = { "augment"},
		description = "add metastable flip-flop models and associated connections",
		help = {
			"Usage:",
			"  augment",
		}
	)
	//@formatter:on
	public void augment() throws Exception {

		if (current == null) {

			out.println("No design is currently loaded");

		} else {

			Transformer t1 = new Transformer(current, defsFF);

			t1.transformCDC(true);

		}

	}

	//@formatter:off
	@Command(
		description = "manage flip-flop definitions",
		help = {
			"Usage:",
			"  flop clear",
			"  flop list",
			"  flop define <module> <clk> <rst> <d>",
		}
	)
	//@formatter:on
	public void flop(String[] args) throws Exception {

		String cmd = args[0];

		if ("clear".equals(cmd)) {

			out.println("Cleared flip-flop definitions");

			defsFF.clear();

			return;

		} else if ("list".equals(cmd)) {

			if (defsFF.isEmpty()) {

				out.println("No flip-flop definitions");

			} else {

				String strFormat = "%20s %14s %14s %14s\n";

				out.print(String.format(strFormat, "Flip-flop Module", "Clock Port", "Reset Port", "Data Port"));
				out.print(String.format(strFormat, "----------------", "----------", "----------", "---------"));

				for (FlipFlop f : defsFF.values())
					out.print(String.format(strFormat, f.moduleName, f.clkPort, f.rstPort, f.dPort));

			}

			return;

		} else if ("define".equals(cmd)) {

			String modName = args[1];
			String clkPort = args[2];
			String rstPort = args[3];
			String dPort = args[4];

			if (defsFF.containsKey(modName)) {

				throw new Exception(String.format("definition already exists for flip-flop <%s> ", modName));

			} else {

				FlipFlop ff = new FlipFlop(modName, clkPort, rstPort, dPort);

				defsFF.put(modName, ff);

				out.printf("defined flip-flop <%s>\n", modName);

				return;
			}

		}

		throw new Exception("Unable to parse def_ff arguments");
	}

	//@formatter:off
	@Command(
		description = "generate design reports",
		help = {
			"Usage:",
			"  report domains",
			"  report xpaths",
		}
	)
	//@formatter:on
	public void report(String arg) throws Exception {

		if ("domains".equals(arg)) {

			reportClockDomains();

		} else if ("xpaths".equals(arg)) {

			reportCrossoverPaths();

		} else {

			out.println("Unrecognized option");

		}

	}

	//@formatter:off
	@Command(
		description = "manage loaded designs",
		help = {
			"Usage:",
			"  design list",
			"  design current <name>",
			"  design remove (*|<name>)"
		}
	)
	//@formatter:on
	public void design(String args[]) throws Exception {

		if (args.length > 0) {

			String cmd = args[0];

			if (cmd.equals("list")) {

				if (designs.size() > 0) {

					out.println("Loaded designs:");

					for (Entry<String, NetlistGraph> e : designs.entrySet()) {

						String curStr = (e.getValue() == current) ? " (current)" : "";

						out.printf("  * %s%s\n", e.getKey(), curStr);

					}

					return;

				} else {

					out.println("no designs are currently loaded");

					return;

				}

			} else if (cmd.equals("current") && args.length > 1) {

				String designName = args[1];

				NetlistGraph d = designs.get(designName);

				if (d == null) {

					String strE = String.format("Design <%s> not found", designName);

					throw new Exception(strE);

				} else {

					current = d;

					out.printf("Current design is <%s>\n", designName);

					return;

				}

			} else if (cmd.equals("remove") && args.length > 1) {

				String designName = args[1];

				if (designName.equals("*")) {

					designs.clear();

					current = null;

					out.println("Removed all designs");

					return;

				}

				NetlistGraph d = designs.get(designName);

				if (d == null) {

					String strE = String.format("Design <%s> not found", designName);

					throw new Exception(strE);

				} else {

					designs.remove(designName);

					out.printf("Removed design <%s>\n", designName);

					if (current == d) {

						current = null;

						if (designs.size() > 0) {

							Entry<String, NetlistGraph> entry = designs.entrySet().iterator().next();

							current = entry.getValue();

							out.printf("Current design is <%s>\n", entry.getKey());

						}

					}

					return;

				}

			}

		}

		throw new Exception("error parsing arguments of design");

	}

	//@formatter:off
	@Command(
		description = "rename nets or modules",
		help = {
			"Usage:",
			"  rename [--ignore net1,net2 ...] [--format net%d] nets",
			"  rename [--ignore mod1,mod2 ...] [--format mod%s%d] modules",
			"  rename [--format _%s_%d_] splitarr",
			"",
			"Options:",
			"  -i --ignore item1,item2 ...  exclude nets/modules from renaming",
			"  -f --format %s%d ...         specify renaming format (%s = module type or array name, %d = index)"
		}
	)
	//@formatter:on
	public void rename(String args[]) throws Exception {

		// parse input

		Option optIgnore = Option.builder("i").hasArg().required(false).longOpt("ignore").build();

		Option optFormat = Option.builder("f").hasArg().required(false).longOpt("format").build();

		Options options = new Options();

		options.addOption(optIgnore);
		options.addOption(optFormat);

		options.addOption(optIgnore);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		String ignoreStr = line.getOptionValue("ignore", "");

		HashSet<String> ignored = new HashSet<String>(Arrays.asList(ignoreStr.split(",")));

		if (args.length > 0) {

			// use first non-empty argument as file name

			String cmd = "";

			for (String str : line.getArgList())
				if (!str.isEmpty())
					cmd = str.trim();

			if ("modules".equals(cmd)) {

				String strFormat = line.getOptionValue("format", "%s%d");

				renameModules(ignored, strFormat);

				return;

			} else if ("nets".equals(cmd)) {

				String strFormat = line.getOptionValue("format", "n%d");

				renameNets(ignored, strFormat);

				return;

			} else if ("splitarr".equals(cmd)) {

				String strFormat = line.getOptionValue("format", "%s_%d_");

				splitArr(strFormat);

				return;

			}

		}

		throw new Exception("error parsing arguments of rename");

	}

	//@formatter:off
	@Command(
		description = "generate state space graph",
		help = {
			"Usage:",
			"  space"
		}
	)
	//@formatter:on
	public void space() throws Exception {

		// code:

		assertDesignLoaded();

		final String codeGenTemplateFile = "template3.j";

		final String codeGenClassName = "CodeSimulator";

		NetlistGraph currentCopy = current;

		String javaFile = getTempFile(codeGenClassName + ".java");

		String compileCmd = "javac " + javaFile;

		String runCodeGenCmd = String.format("java -classpath %s %s", getTempFile(""), codeGenClassName);

		// generate code

		String templateCode = loadResourceString(codeGenTemplateFile);

		ArrayList<Property> emptyList = new ArrayList<Property>();

		ArrayList<String> lines = CodeGenerator.generate(currentCopy, emptyList, emptyList, templateCode);

		out.println("Saving code to " + javaFile + " ...");

		PrintStream fout = new PrintStream(javaFile);

		for (String l : lines)
			fout.println(l);

		fout.close();

		// compile using javac

		out.println("Compiling ...");

		int compileExitCode = executeProgram(compileCmd, true, true);

		if (compileExitCode != 0)
			throw new Exception("Compilation failed");

		// run code

		out.println("Executing compiled code ...");

		// TODO: check exit code and report if failed:

		executeProgram(runCodeGenCmd, true, true);

		int exitCode = executeProgram(compileCmd, true, true);

		if (exitCode != 0)
			throw new Exception("state space exploration failed");

	}

	//@formatter:off
	@Command(
		description = "run formal verification",
		help = {
			"Usage:",
			"  prove [--print] [--vcd <file>] [--txt <file>] [--gtkwave]",
			"        [--wavejson] [--signals sig1,sig2...] [--keep]",
			"        [--onlycode] [--dfs|--hash|--cpp]",
			"",
			"Options:",
			"  -p --print       print counter-example to console",
			"  -v --vcd <file>  export counter-example to vcd file",
			"  -t --txt <file>  export counter-example to plain-text file",
			"  -g --gtkwave     open counter-example using gtkwave",
			"  -w --wavejson    print counter-example in WaveJSON format",
			"  -s --signals     list of signals to include in counter-example",
			"  -k --keep        keep assertion logic in current design (debugging)",
			"  -c --onlycode    generate code but do not compile or run Java model (debugging)",
			"  -d --dfs         use DFS (required for verifying liveness properties)",
			"  -h --hash        force use of hash tables",
			"  --cpp            generate and use c++ program",
		}
	)
	//@formatter:on
	public void prove(String args[]) throws Exception {

		// parse command input

		Option[] opts = {

				Option.builder("v").longOpt("vcd").hasArg().build(),

				Option.builder("t").longOpt("txt").hasArg().build(),

				Option.builder("s").longOpt("signals").hasArg().build(),

				Option.builder("p").longOpt("print").build(),

				Option.builder("w").longOpt("wavejson").build(),

				Option.builder("g").longOpt("gtkwave").build(),

				Option.builder("k").longOpt("keep").build(),

				Option.builder("c").longOpt("onlycode").build(),

				Option.builder("d").longOpt("depth").build(),

				Option.builder("h").longOpt("hash").build(),

				Option.builder().longOpt("cpp").build(),

		};

		Options options = new Options();

		for (Option o : opts)
			options.addOption(o);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		// code:

		assertDesignLoaded();

		boolean keepAssertionLogic = line.hasOption("k");

		boolean useDepthTemplate = line.hasOption("d");

		boolean useHashTemplate = line.hasOption("h");

		boolean useCppTemplate = line.hasOption("cpp");

		String codeGenTemplateFile;

		final String codeGenClassName = "CodeSimulator";

		if (useCppTemplate) {

			codeGenTemplateFile = "template1.c";

		} else if (useDepthTemplate) {

			codeGenTemplateFile = "template2.j";

		} else if (useHashTemplate) {

			codeGenTemplateFile = "template4.j";

		} else {

			codeGenTemplateFile = "template1.j";

		}

		String defaultTextFile = getTempFile("counter-example.txt");

		String txtFile = line.getOptionValue("txt", defaultTextFile);

		String txtArg = "--txt " + txtFile;

		String compileCmd;

		String genCodeFile;

		String runCodeGenCmd;

		boolean isJavaTemplate = !useCppTemplate;

		if (isJavaTemplate) {

			genCodeFile = getTempFile(codeGenClassName + ".java");

			compileCmd = "javac " + genCodeFile;

			runCodeGenCmd = String.format("java -Xmx6g -classpath %s %s %s", getTempFile(""), codeGenClassName, txtArg);

		} else {

			genCodeFile = getTempFile(codeGenClassName + ".cpp");

			compileCmd = String.format("g++ -o %s.exe -O2 %s", codeGenClassName, genCodeFile);

			runCodeGenCmd = String.format("%s.exe", codeGenClassName);

		}

		NetlistGraph currentCopy = keepAssertionLogic ? current : new NetlistGraph(current);

		(new Transformer(currentCopy, defsFF)).expandDFFx();

		// generate code

		String templateCode = loadResourceString(codeGenTemplateFile);

		ArrayList<String> lines = CodeGenerator.generate(currentCopy, assumptions, assertions, templateCode);

		out.println("Saving code to " + genCodeFile + " ...");

		PrintStream fout = new PrintStream(genCodeFile);

		for (String l : lines)
			fout.println(l);

		fout.close();

		if (line.hasOption("c"))
			return;

		// compile

		out.println("Compiling ...");

		int compileExitCode = executeProgram(compileCmd, true, true);

		if (compileExitCode != 0)
			throw new Exception("Compilation failed");

		// run code

		out.println("Executing compiled code ...");

		int genExitCode = executeProgram(runCodeGenCmd, true, true);

		if (genExitCode == 100) {

			// 100 is a special exit code for terminating successfully after
			// finding a counter-example

			boolean runGtkwave = line.hasOption("g");

			boolean printToConsole = line.hasOption("p");

			boolean writeVCD = line.hasOption("v");

			boolean printWaveJSON = line.hasOption('w');

			String defVCD = getTempFile("counter-example.vcd");

			String vcdFile = line.getOptionValue("v", defVCD);

			boolean loadCounter = runGtkwave || printToConsole || writeVCD || printWaveJSON;

			if (loadCounter) {

				Waveform counter = new Waveform(txtFile);

				if (line.hasOption('s'))
					counter.selectSignals(line.getOptionValue('s').split(","));

				if (printToConsole)
					counter.print(System.out);

				if (runGtkwave || writeVCD)
					counter.writeVCDFile(vcdFile, runGtkwave);

				if (printWaveJSON)
					counter.printWaveJSON(System.out);

			}

		} else if (genExitCode != 0) {

			throw new Exception("state space exploration failed");

		}

	}

	//@formatter:off
	@Command(
		aliases = {"synth"},
		description = "synthesize behavioral verilog design using yosys",
		help = {
			"Usage:",
			"  synth <input> <output>"
		}
	)
	//@formatter:on
	public void synth(String args[]) throws Exception {

		// TODO: implement the following switches
		// -v : verbose mode, show output of yosys
		// --lib myfile.lib: specify custom library for yosys

		String behavioralDesign = args[0];

		String synthDesign = args[1];

		(new SynthesisEngine()).synthesis(behavioralDesign, synthDesign, out);

	}

	//@formatter:off
	@Command(
		description = "add an assumption for formal verification",
		help = {
			"Usage:",
			"  assume <property>",
		}
	)
	//@formatter:on
	public void assume(String args[]) throws Exception {

		String pStr = String.join(" ", args);

		Property p = PropertyBuilder.build(pStr, getIdentifiers());

		System.out.println("Assumption : " + pStr);

		p.print();

		assumptions.add(p);

	}

	//@formatter:off
	@Command(
		aliases = {"assert"},
		description = "add an assertion for formal verification",
		help = {
			"Usage:",
			"  assert <property>",
		}
	)
	//@formatter:on
	public void assertp(String args[]) throws Exception {

		String pStr = String.join(" ", args);

		Property p = PropertyBuilder.build(pStr, getIdentifiers());

		System.out.println("Assertion : " + pStr);

		p.print();

		assertions.add(p);
	}

	//@formatter:off
	@Command(
		description = "manage design properties",
		help = {
			"Usage:",
			"  props list",
			"  props clear (*|<num>)"
		}
	)
	//@formatter:on
	public void props(String args[]) throws Exception {

		String cmd = args.length > 0 ? args[0] : "";

		if ("list".equals(cmd)) {

			if (assumptions.size() + assertions.size() > 0) {

				out.println("Properties:");

				int i = 1;

				for (Property p : assumptions)
					out.printf("  [%d] Assumption : %s\n", i++, p);

				for (Property p : assertions)
					out.printf("  [%d] Assertion  : %s\n", i++, p);

			} else {

				out.println("no properties defined");

			}

			return;

		} else if ("clear".equals(cmd) && args.length > 1) {

			if ("*".equals(args[1])) {

				assumptions.clear();

				assertions.clear();

			} else {

				int n = Integer.valueOf(args[1]);

				if (n < 1 || n > (assumptions.size() + assertions.size())) {

					throw new Exception("invalid property index");
				}

				if (n > assumptions.size()) {

					assertions.remove(n - assumptions.size() - 1);

				} else {

					assumptions.remove(n - 1);

				}

			}

			return;

		}

	}

	//@formatter:off
	@Command(
		description = "substitute cells with designs",
		help = {
			"Usage:",
			"  report -r",
			"  report <cell_type>",
		}
	)
	//@formatter:on
	public void expand(String args[]) throws Exception {

		String cmd = args.length > 0 ? args[0] : "";

		if ("DFFx".equals(cmd)) {

			(new Transformer(current, defsFF)).expandDFFx();

			return;

		}

		// TODO: implement -r

	}

	@Command
	public void depen(String file) throws Exception {

		HashMap<String, HashSet<String>> dependencies = VerilogParser.getDependencies(file);

		ArrayList<TreeSet<String>> hierarchy = DependencyTree.depen(dependencies);

		System.out.println("Design dependencies (bottom to top):");

		for (int i = 0; i < hierarchy.size(); i++) {

			System.out.printf("Level %d:\n", i);

			for (String s : hierarchy.get(i))
				System.out.println("  - " + s);

		}

	}

	// Internal Methods:

	private int executeProgram(String cmd, boolean showOutput, boolean waitFor) throws Exception {

		final Runtime rt = Runtime.getRuntime();

		Process proc = rt.exec(cmd);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		String sErr = "", sOut = "";

		ArrayList<String> errLines = new ArrayList<String>();

		// stderr of the process must be read to prevent the process from
		// blocking. The read err lines are current stored in a list so that the
		// can be printed after stdout messages (the latter printed as they are
		// read)

		if (waitFor) {

			while ((sErr = stdError.readLine()) != null)
				errLines.add(sErr);

			while ((sOut = stdInput.readLine()) != null)
				System.out.println(sOut);

		}

		stdInput.close();

		stdError.close();

		for (String s : errLines)
			System.out.println(s);

		if (waitFor) {

			proc.waitFor();

			return proc.exitValue();

		} else {

			return 0;
		}

	}

	private void assertDesignLoaded() throws Exception {

		if (current == null)
			throw new Exception("no design is loaded");

	}

	private void reportClockDomains() throws Exception {

		Transformer t1 = new Transformer(current, defsFF);

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

	private void reportCrossoverPaths() {

		String strFormat = "%20s -> %20s";

		ArrayList<String> paths = new ArrayList<String>();

		HashSet<Vertex> flops = current.getModulesByTypes(defsFF.keySet());

		Graph<Vertex> ffGraph = current.reduce(flops);

		for (Vertex src : flops) {

			for (Vertex dst : ffGraph.getDestinations(src)) {

				Vertex clk1 = current.getNet(src, defsFF.get(src.subtype).clkPort);
				Vertex clk2 = current.getNet(dst, defsFF.get(dst.subtype).clkPort);

				if (clk1 != clk2)
					paths.add(String.format(strFormat, src, dst));

			}

		}

		Collections.sort(paths);

		// output

		out.printf("Found %d crossover paths:\n\n", paths.size());

		for (String p : paths) {

			out.println(p);

		}

	}

	private void renameModules(HashSet<String> ignored, String strFormat) {

		// rename modules

		List<Vertex> sortedMods = new ArrayList<Vertex>(current.getModules());

		Collections.sort(sortedMods, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex arg0, Vertex arg1) {

				return arg0.name.compareTo(arg1.name);
			}

		});

		HashMap<String, Integer> modCounts = new HashMap<String, Integer>();

		for (Vertex v : sortedMods) {

			if (!ignored.contains(v.name)) {

				String lowerMod = v.subtype.toLowerCase();

				Integer c = modCounts.get(lowerMod);

				c = (c == null) ? 1 : c + 1;

				v.name = String.format(strFormat, lowerMod, c);

				modCounts.put(lowerMod, c);

			}

		}

	}

	private void renameNets(HashSet<String> ignored, String strFormat) {

		// renames internal (i.e. non-port) nets

		// rename nets

		int netCount = 0;

		HashSet<Vertex> nonIO = current.getNets();

		nonIO.removeAll(current.getIONets());

		List<Vertex> nonIOList = new ArrayList<Vertex>(nonIO);

		Collections.sort(nonIOList, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex arg0, Vertex arg1) {

				return arg0.name.compareTo(arg1.name);
			}

		});

		for (Vertex n : nonIOList) {

			if (!ignored.contains(n.name)) {

				String jNetName = n.name;

				// to obtain a Java-friendly variable name,
				// replace all non-word chars with underscores
				jNetName = jNetName.replaceAll("[\\W]+", "_");

				netCount += 1;

				String nn = strFormat;

				nn = nn.replace("%d", "" + netCount);

				nn = nn.replace("%s", "" + jNetName);

				n.name = nn;

			}

		}

	}

	private static String loadResourceString(String file) {

		Scanner s = null;

		String bannerFileContent;

		try {

			final InputStream stream;

			stream = Console.class.getClassLoader().getResourceAsStream(file);

			s = new Scanner(stream);

			bannerFileContent = s.useDelimiter("\\Z").next();

		} catch (Exception e) {

			bannerFileContent = "<could not load internal file>\n";

		} finally {

			if (s != null)
				s.close();

		}

		return bannerFileContent;

	}

	private void splitArr(String netNameFormat) {

		for (Vertex v : current.getNets()) {

			int k1 = v.name.indexOf("[");
			int k2 = v.name.indexOf("]");

			String name = v.name;

			int bit = 0;

			if (k1 != -1 && k2 != -1) {

				name = v.name.substring(0, k1);

				bit = Integer.valueOf(v.name.substring(k1 + 1, k2));

				v.name = String.format(netNameFormat, name, bit);
			}

		}

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

	private String getTempFile(String filename) {

		// returns absolute path of file with name `filename` in a temp
		// directory

		File tempDir = new File(System.getProperty("java.io.tmpdir"));

		File temp = new File(tempDir, filename);

		return temp.getAbsolutePath();

	}

	private Map<String, Integer> getIdentifiers() throws Exception {

		assertDesignLoaded();

		HashMap<String, Integer> result = new HashMap<String, Integer>();

		for (Vertex n : current.getNets()) {

			result.put(n.name, 1);

			if (n.arraySize > 0)
				result.put(n.arrayName, n.arraySize);

		}

		return result;

	}

	private String getStoredChecksum() {

		File hashFile = new File(".xprova.hash");

		BufferedReader br;

		FileReader fr;

		try {

			fr = new FileReader(hashFile);

		} catch (Exception e) {

			return null;

		}

		br = new BufferedReader(fr);

		String result;

		try {
			result = br.readLine();

			br.close();

		} catch (IOException e) {

			result = null;

		}

		return result;

	}

	private void storeChecksum(String checksum) throws IOException {

		File hashFile = new File(".xprova.hash");

		PrintWriter writer2 = new PrintWriter(hashFile);

		writer2.print(checksum);

		writer2.close();

	}

	private String getFileChecksum(String file) throws Exception {

		MessageDigest md = MessageDigest.getInstance("SHA1");

		FileInputStream fis = new FileInputStream(file);

		byte[] dataBytes = new byte[1024];

		int nread = 0;

		while ((nread = fis.read(dataBytes)) != -1)
			md.update(dataBytes, 0, nread);

		fis.close();

		byte[] mdbytes = md.digest();

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < mdbytes.length; i++)
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));

		return sb.toString();

	}

}
