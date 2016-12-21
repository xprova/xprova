module multibit (clk, rst, x, y);

	input clk, rst;

	input [7:0] x;
	output [7:0] y;

	assign y = x;

endmodule
