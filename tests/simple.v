module top (clk, rst, a, z);

	input clk, rst, a;
	output z;



	// OR u3 (z, a, b);
	NOT u1 (.y(z), .a(a));
	// AND u4 (y, z, c);

endmodule

module inverter (inp, outp);

	input inp;
	output outp;

	NOT u1 (outp, inp);

endmodule