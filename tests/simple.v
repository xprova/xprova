module top (clk, rst, a, b, c, y);

	input clk, rst, a, b, c;
	output y;

	NOT u1 (x, n1);
	AND u2 (n2, x, a);
	OR u3 (n3, n2, b);
	AND u4 (y, n3, c);

endmodule

module inverter (inp, outp);

	input inp;
	output outp;

	NOT u1 (outp, inp);

endmodule