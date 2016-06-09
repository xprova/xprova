module AND (y, a, b);
	input a, b;
	output y;
endmodule

module NAND (y, a, b);
	input a, b;
	output y;
endmodule

module OR (y, a, b);
	input a, b;
	output y;
endmodule

module NOR (y, a, b);
	input a, b;
	output y;
endmodule

module NOT (y, a);
	input a;
	output y;
endmodule

module XOR (y, a, b);
	input a, b;
	output y;
endmodule

module DFF (CK, RS, ST, D, Q);
	input CK, RS, ST, D;
	output Q;
endmodule

module TIE0(y);
	output y;
endmodule

module TIE1(y);
	output y;
endmodule
