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

module DFFx (CK, RS, ST, D, Q, M, V, T, rD, rV);
	input CK, RS, ST, D;
	output Q;
	input V;
	output M, T;
	input rD, rV;
endmodule

module TIE0(y);
	output y;
endmodule

module TIE1(y);
	output y;
endmodule

module TIEX(y);
	output y;
endmodule

module MUX2 (y, a, b, s);
	input a, b, s;
	output y;
endmodule

module X2H (y, a);
	input a;
	output y;
endmodule

module H2X (y, a);
	input a;
	output y;
endmodule

module Test (p1, p2, p3, p4);

	input p1, p2;
	output p3, p4;

endmodule