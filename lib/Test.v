module Test (p1, p2, p3, p4);

	input p1, p2;
	output p3, p4;

	AND a1 (p3, p1, p2);
	OR o1 (p4, p1, p2);

endmodule