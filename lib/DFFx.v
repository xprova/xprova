module DFFx (CK, RS, D, Q, T, M, V, rD, rV);

	input CK, RS, D, V, rD, rV;

	output Q, T, M;

	TIEX t1 (.y(x));

	MUX2 m1 (.y(ffd_d), .a(D), .b(rD), .s(isV));
	MUX2 m2 (.y(M), .a(Q), .b(x), .s(isM));
	MUX2 m3 (.y(T), .a(Q), .b(x), .s(isT));

	DFF ffd (.CK(CK), .RS(RS), .D(ffd_d), .Q(Q));
	DFF ffm (.CK(CK), .RS(RS), .D(ffm_d), .Q(isM));

	X2H x1 (.y(isV), .a(V));

	AND a1 (.y(ffm_d), .a(isV), .b(rV));

	XOR xor1 (.y(isT), .a(ffd_d), .b(Q));

endmodule