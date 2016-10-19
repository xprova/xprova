module top (a, b, c, y);

	input a, b, c;
	output [2:0] y;

	assign y = {a, b,c};

endmodule