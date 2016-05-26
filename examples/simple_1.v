module top (reset, clk1, clk2, x, y);

	input reset, clk1, clk2, x;
	output y;

	DFF ff1 (clk1, reset, x, n1);
	DFF ff2 (clk1, reset, n1, n2);
	DFF ff3 (clk2, reset, n2, n3);
	DFF ff4 (clk2, reset, n3, n4);
	DFF ff5 (clk2, reset, n4, y);

endmodule