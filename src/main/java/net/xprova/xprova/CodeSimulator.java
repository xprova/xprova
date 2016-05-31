package net.xprova.xprova;

public class CodeSimulator {

	public void simulate() {

		boolean n1, n2, n3, n4, n5, n6, n7, n8;

		boolean ena = true;

		boolean count_0_ = false;
		boolean count_1_ = false;

		for (int i = 0; i < 20; i++) {

			ena = (i % 3) == 0;

			String enaStr = ena ? "1" : "0";

			String c0Str = count_0_ ? "1" : "0";
			String c1Str = count_1_ ? "1" : "0";

			System.out.println("tick " + i + ", count = " + c0Str + c1Str + ", ena = " + enaStr);

			// calculate

			n7 = !count_1_;
			n5 = !ena;
			n3 = n5 & count_1_;
			n6 = count_0_ & n5;
			n8 = ena & n7;
			n4 = count_0_ & ena;
			n1 = n8 | n6;
			n2 = n4 | n3;

			count_1_ = n2;
			count_0_ = n1;

		}

		// state bit : count_0_
		// state bit : ena
		// state bit : clk
		// state bit : count_1_
		// state bit : rst

	}

}
