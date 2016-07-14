module DFFx (CK, RS, D, Q, T, M, V, rD, rV);

	input CK, RS, D, V, rD, rV;

	output Q, T, M;

	TIEX tiex1 (.y(x)); // when M or T

	MUX2 mux1 (.y(inpD), .a(D), .b(rD), .s(isV)); // when V
	MUX2 mux2 (.y(M), .a(Q), .b(x), .s(isM)); // when M
	MUX2 mux3 (.y(T), .a(Q), .b(x), .s(isT)); // when T

	DFF d (.CK(CK), .RS(RS), .D(inpD), .Q(Q)); // always
	DFF m (.CK(CK), .RS(RS), .D(inpM), .Q(isM)); // when M

	// note: M -> V

	X2H x2h (.y(isV), .a(V)); // when V

	AND and1 (.y(inpM), .a(isV), .b(rV)); // when M

	XOR xor1 (.y(isT), .a(inpD), .b(Q)); // when T

endmodule