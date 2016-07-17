package net.xprova.xprova;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

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
import net.xprova.simulations.CodeGenerator;
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

	private void assertDesignLoaded() throws Exception {

		if (current == null)
			throw new Exception("no design is loaded");

	}

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
			"  read [-m module] <verilog_file>",
			"",
			"Options:",
			"  -m module : specify top module (first in file be default)"
		}
	)
	//@formatter:on
	public void read(String args[]) throws Exception {

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

		// read verilog file

		if (lib == null) {

			throw new Exception("No library is currently loaded");

		} else {

			ArrayList<Netlist> nls = VerilogParser.parseFile(verilogFile, lib);

			HashMap<String, Netlist> newNLs = new HashMap<String, Netlist>();

			for (Netlist nl : nls)
				newNLs.put(nl.name, nl);

			String moduleName = line.getOptionValue("m", nls.get(0).name);

			if (!newNLs.containsKey(moduleName))
				throw new Exception(String.format("Cannot find module <%s> in file <%s>", moduleName, verilogFile));

			ArrayList<NetlistGraph> newDesigns = new ArrayList<NetlistGraph>();

			for (Netlist nl : nls) {

				out.printf("Parsing design <%s> in file <%s> ...\n", nl.name, verilogFile);

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
			"      [--pdf] <output_file>",
			"",
			"Options:",
			"  -e --ignore-edges e1,e2,...     exclude edges from graph",
			"  -v --ignore-vertices v1,v2,...  exclude vertices from graph",
			"  --to <flipflop>                 export sub-graph of flipflop and its combinational logic",
			"  -t --type fng                   include (f)lip-flops, (n)ets and/or (g)ates",
			"  -c --combine v1,v2,...          combine group of vertices into a single vertex",
			"  --pdf                           output a PDF file directly (requires DOT)"
		}
	)
	//@formatter:on
	public void dot(String[] args) throws Exception {

		// parse command input

		Option optIgnoreEdges = Option.builder("e").hasArg().argName("IGNORE_EDGES").required(false)
				.longOpt("ignore-edges").build();

		Option optIgnoreVertices = Option.builder("v").hasArg().argName("IGNORE_VERTICES").required(false)
				.longOpt("ignore-vertices").build();

		Option optType = Option.builder("t").longOpt("type").hasArg().build();

		Option optTo = Option.builder().longOpt("to").hasArg().build();

		Option optCombine = Option.builder("c").longOpt("combine").hasArg().build();

		Option optPDF = Option.builder().longOpt("pdf").build();

		Options options = new Options();

		options.addOption(optIgnoreEdges);

		options.addOption(optIgnoreVertices);

		options.addOption(optType);

		options.addOption(optTo);

		options.addOption(optCombine);

		options.addOption(optPDF);

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
						String.format("netlist <%s> does not contain flip-flip <%s>", current.getName(), vName));

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

		NetlistGraphDotFormatter formatter = new NetlistGraphDotFormatter(current);

		if (line.hasOption("ignore-edges")) {

			formatter.setIgnoredEdges(line.getOptionValue("ignore-edges").split(","));

		}

		if (line.hasOption("ignore-vertices")) {

			formatter.setIgnoredVertices(line.getOptionValue("ignore-vertices").split(","));

		}

		if (line.hasOption("--pdf")) {

			String dotFileStr = "netlist.dot";

			File tempDir = new File(System.getProperty("java.io.tmpdir"));

			File javaFile = new File(tempDir, dotFileStr);

			String cmd = "dot -Tpdf " + javaFile.getAbsolutePath() + " -o " + outFile;

			// write temp dot file

			GraphDotPrinter.printGraph(javaFile.getAbsolutePath(), sg, formatter, selectedVertices);

			// invoke DOT

			final Runtime rt = Runtime.getRuntime();

			Process proc = rt.exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			String s;

			while ((s = stdError.readLine()) != null)
				out.println(s);

			while ((s = stdInput.readLine()) != null)
				out.println(s);

			stdInput.close();

			stdError.close();

			proc.waitFor();

			if (proc.exitValue() != 0) {

				throw new Exception("Error while attempting to convert dot file to pdf using DOT");

			}

		} else {

			GraphDotPrinter.printGraph(outFile, sg, formatter, selectedVertices);
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

				out.print(String.format(strFormat, "Flip-flip Module", "Clock Port", "Reset Port", "Data Port"));
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
			"  design rm <name>"
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

			} else if (cmd.equals("rm") && args.length > 1) {

				String designName = args[1];

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

	@Command(description = "rename nets or modules")
	public void rename(String args[]) throws Exception {

		// parse input

		Option optIgnore = Option.builder().desc("list of nets/modules to ignore").hasArg().argName("IGNORE")
				.required(false).longOpt("ignore").build();

		Option optFormat = Option.builder().desc("naming format").hasArg().argName("FORMAT").required(false)
				.longOpt("format").build();

		Options options = new Options();

		options.addOption(optIgnore);
		options.addOption(optFormat);

		options.addOption(optIgnore);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		String ignoreStr = line.getOptionValue("ignore", "");

		HashSet<String> ignored = new HashSet<String>(Arrays.asList(ignoreStr.split(",")));

		if (args.length > 0) {

			String cmd = args[0];

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

	@Command(description = "attempt to prove assertions")
	public void prove(String args[]) throws Exception {

		// options:
		// --printcode
		// --norun

		// parse command input

		Option optPrintCode = Option.builder().desc("print generated code").required(false).longOpt("printcode")
				.build();

		Option optNoRun = Option.builder().desc("do not execute generated code").required(false).longOpt("norun")
				.build();

		Option optNoCounter = Option.builder().desc("do not print counter-example (if found)").required(false)
				.longOpt("nocounter").build();

		Options options = new Options();

		options.addOption(optPrintCode);

		options.addOption(optNoRun);

		options.addOption(optNoCounter);

		CommandLineParser parser = new DefaultParser();

		CommandLine line = parser.parse(options, args);

		// code:

		assertDesignLoaded();

		NetlistGraph current2 = new NetlistGraph(current);

		(new Transformer(current2, defsFF)).expandDFFx();

		String invokeArgs = line.hasOption("nocounter") ? "" : "gen-counter-example";

		String templateResourceFile = "template1.j";

		String javaFileStr = "CodeSimulator.java";

		String classFileStr = "CodeSimulator";

		File tempDir = new File(System.getProperty("java.io.tmpdir"));

		File javaFile = new File(tempDir, javaFileStr);

		String cmd = "javac " + javaFile.getAbsolutePath();

		String cmd2 = String.format("java -classpath %s %s %s", tempDir.getAbsolutePath(), classFileStr, invokeArgs);

		// generate code

		String templateCode = loadResourceString(templateResourceFile);

		CodeGenerator cg = new CodeGenerator(current2, assumptions, assertions);

		ArrayList<String> lines = cg.generate(templateCode);

		if (line.hasOption("printcode")) {

			for (String l : lines)
				out.println(l);

		}

		if (!line.hasOption("norun")) {

			out.println("Saving code to " + javaFile.getAbsolutePath() + " ...");

			PrintStream fout = new PrintStream(javaFile);

			for (String l : lines)
				fout.println(l);

			fout.close();

			// compile using javac

			out.println("Compiling ...");

			final Runtime rt = Runtime.getRuntime();

			Process proc = rt.exec(cmd);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			String s;

			while ((s = stdError.readLine()) != null)
				out.println(s);

			while ((s = stdInput.readLine()) != null)
				out.println(s);

			stdInput.close();

			stdError.close();

			proc.waitFor();

			if (proc.exitValue() != 0) {

				throw new Exception("Compilation failed");

			}

			// run code

			out.println("Executing compiled code ...");

			Process proc2 = rt.exec(cmd2);

			BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(proc2.getInputStream()));

			BufferedReader stdError2 = new BufferedReader(new InputStreamReader(proc2.getErrorStream()));

			while (proc2.isAlive()) {

				while ((s = stdInput2.readLine()) != null)
					out.println(s);

				while ((s = stdError2.readLine()) != null)
					out.println(s);

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

	@Command(aliases = { "synth" }, description = "synthesize behavioral verilog design using yosys")
	public void synth(String args[]) throws Exception {

		// TODO: implement the following switches
		// -v : verbose mode, show output of yosys
		// --lib myfile.lib: specify custom library for yosys

		String behavioralDesign = args[0];

		String synthDesign = args[1];

		(new SynthesisEngine()).synthesis(behavioralDesign, synthDesign, out);

	}

	@Command(description = "add an assumption for formal verification")
	public void assume(String args[]) throws Exception {

		String pStr = String.join(" ", args);

		assumptions.add(new Property(pStr));
	}

	@Command(aliases = { "assert" }, description = "add an assertion for formal verification")
	public void assertp(String args[]) throws Exception {

		String pStr = String.join(" ", args);

		assertions.add(new Property(pStr));
	}

	//@formatter:off
	@Command(
		description = "print a list of design properties",
		help = {
			"Usage:",
			"  props",
		}
	)
	//@formatter:on
	public void props(String args[]) throws Exception {

		if (assumptions.size() + assertions.size() > 0) {

			out.println("Properties:");

			for (Property p : assumptions)
				out.println("* assumption : " + p);

			for (Property p : assertions)
				out.println("* assertion  : " + p);

		} else {

			out.println("no properties defined");

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

}
