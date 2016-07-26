package net.xprova.xprova;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.verilogparser.VerilogParser;

public class TransformerTest  {

	private static ArrayList<Netlist> gates;

	private static HashMap<String, FlipFlop> defsFF;

	private static GateLibrary library;

	@BeforeClass
	public static void setUp() throws Exception {

		String gateStrs = "module AND (y, a, b); input a, b; output y; endmodule\n"
				+ "module OR (y, a, b); input a, b; output y; endmodule\n"
				+ "module NOT (y, a); input a; output y; endmodule\n"
				+ "module DFF (CK, RS, D, Q); input CK, RS, D; output Q; endmodule\n";

		gates = VerilogParser.parseString(gateStrs, null);

		defsFF = new HashMap<String, FlipFlop>();

		defsFF.put("DFF", new FlipFlop("DFF", "CK", "RS", "D"));

		defsFF.put("DFFx", new FlipFlop("DFFx", "CK", "RS", "D"));

		defsFF.put("DFFx2", new FlipFlop("DFFx", "CK", "RS", "D"));

		library = new GateLibrary(gates);

	}

	@Test
	public void testTransformer1() throws Exception {

		// Test Design 1
		//
		// This netlist contains five flip-flops, two in clock domain clk1 and
		// three in clk2, all connected in a chain.
		//
		// When augmented the 3rd and 4th flops (clk2) must be identified as
		// hazardous and the appropriate connections made between flops 2->3 and
		// also 3->4.
		//
		// If done correctly this will add two nets to the netlist.

		String designStr = "module top (reset, clk1, clk2, x, y);" + "input reset, clk1, clk2, x;" + "output y;"
				+ "DFF ff1 (clk1, reset, x, n1);" + "DFF ff2 (clk1, reset, n1, n2);" + "DFF ff3 (clk2, reset, n2, n3);"
				+ "DFF ff4 (clk2, reset, n3, n4);" + "DFF ff5 (clk2, reset, n4, y);" + "endmodule";

		Netlist design = VerilogParser.parseString(designStr, library).get(0);

		// parse

		NetlistGraph graph = new NetlistGraph(design);

		// create transformer

		Transformer t = new Transformer(graph, defsFF);

		// basic checks before augmenting the design:

		assertEquals(graph.getModules().size(), 5);

		assertEquals(graph.getNets().size(), 9);

		assertEquals(t.getClocks().size(), 2);

		assertEquals(graph.getModulesByType("DFF").size(), 5);

		// augment design

		t.transformCDC(false);

		// check augmented netlist
		// should contain two more nets

		assertEquals(graph.getModules().size(), 5);

		assertEquals(graph.getNets().size(), 11);

		assertEquals(graph.getModulesByType("DFF").size(), 2); // flops 1 and 5

		assertEquals(graph.getModulesByType("DFFx").size(), 3); // flops 2, 3 and 4

	}

	@Test
	public void testTransformer2() throws Exception {

		// Test Design 2
		//
		// This is similar to Test Design 1 except that now there's an inverter
		// in the crossover path.
		//
		// If augmented correctly, the inverter will be duplicated and the total
		// number of nets will increase by 3.

		String designStr = "module top (reset, clk1, clk2, x, y);" + "input reset, clk1, clk2, x;" + "output y;"
				+ "DFF ff1 (clk1, reset, x, n1);" + "DFF ff2 (clk1, reset, n1, n2);" + "NOT u1 (n2_inv, n2);"
				+ "DFF ff3 (clk2, reset, n2_inv, n3);" + "DFF ff4 (clk2, reset, n3, n4);"
				+ "DFF ff5 (clk2, reset, n4, y);" + "endmodule";

		Netlist design = VerilogParser.parseString(designStr, library).get(0);

		// parse

		NetlistGraph graph = new NetlistGraph(design);

		// create transformer

		Transformer t = new Transformer(graph, defsFF);

		// basic checks before augmenting the design:

		assertEquals(graph.getModules().size(), 6);

		assertEquals(graph.getNets().size(), 10);

		assertEquals(t.getClocks().size(), 2);

		assertEquals(graph.getModulesByType("NOT").size(), 1);

		// augment design

		t.transformCDC(false);

		// check augmented netlist

		assertEquals(graph.getModules().size(), 7);

		assertEquals(graph.getNets().size(), 13);

		assertEquals(graph.getModulesByType("NOT").size(), 2);

		assertEquals(graph.getModulesByType("DFF").size(), 2); // flops 1 and 5

		assertEquals(graph.getModulesByType("DFFx").size(), 3); // flops 2, 3 and 4

	}

}
