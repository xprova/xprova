module top (a , b);

	input a;
	output [1:0] b;

	assign b[0] = a;

	NOT n1 (  b [1]  , a);

endmodule
