module Test (p1, p2, p3, p4, p5);

	input p1, p2;
	output p3, p4, p5;

	AND a1 (p3, p1, p2);
	OR o1 (p4, p1, p2);
	XOR x1 (p5, p3, p4);

endmodule