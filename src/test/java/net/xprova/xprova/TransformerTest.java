package net.xprova.xprova;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;
import net.xprova.netlist.GateLibrary;
import net.xprova.netlist.Netlist;
import net.xprova.netlistgraph.NetlistGraph;
import net.xprova.verilogparser.VerilogParser;

public class TransformerTest extends TestCase {

	public void testTransformer() throws Exception {

		String gateStrs = "module AND (y, a, b); input a, b; output y; endmodule\n"
				+ "module OR (y, a, b); input a, b; output y; endmodule\n"
				+ "module NOT (y, a); input a; output y; endmodule\n"
				+ "module DFF (CK, RS, D, Q); input CK, RS, D; output Q; endmodule\n";

		String designStr = "module top (reset, clk1, clk2, x, y);" + "input reset, clk1, clk2, x;" + "output y;"
				+ "DFF ff1 (clk1, reset, x, n1);" + "DFF ff2 (clk1, reset, n1, n2);" + "DFF ff3 (clk2, reset, n2, n3);"
				+ "DFF ff4 (clk2, reset, n3, n4);" + "DFF ff5 (clk2, reset, n4, y);" + "endmodule";

		ArrayList<Netlist> gates = VerilogParser.parseString(gateStrs);

		ArrayList<Netlist> designs = VerilogParser.parseString(designStr, new GateLibrary(gates));

		Netlist design = designs.get(0);

		// parse

		NetlistGraph graph = new NetlistGraph(design);

		HashMap<String, FlipFlop> defsFF = new HashMap<String, FlipFlop>();

		defsFF.put("DFF", new FlipFlop("DFF", "CK", "RS", "D"));
		defsFF.put("DFFx", new FlipFlop("DFF", "CK", "RS", "D"));

		// create transformer

		Transformer t = new Transformer(graph, defsFF);

		// basic checks before augmenting the design:

		assertEquals(graph.getModules().size(), 5);

		assertEquals(graph.getNets().size(), 9);

		assertEquals(t.getClocks().size(), 2);

		// augment design

		t.transformCDC();

		// check augmented netlist
		// should contain two more nets

		assertEquals(graph.getModules().size(), 5);

		assertEquals(graph.getNets().size(), 11);

	}

}
