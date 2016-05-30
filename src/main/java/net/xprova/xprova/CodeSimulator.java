package net.xprova.xprova;

public class CodeSimulator {

	public void simulate() {

		boolean n1, n2, n3, n4, n5, n6, n7, n8;

		boolean ena = true;

		boolean[] count = { false, false };

		for (int i = 0; i < 50; i++) {

			ena = (i % 3) == 0;

			String enaStr = ena ? "1" : "0";

			String c0Str = count[0] ? "1" : "0";
			String c1Str = count[1] ? "1" : "0";

			System.out.println("tick " + i + ", count = " + c0Str + c1Str + ", ena = " + enaStr);

			// calculate

			n7 = !count[1];
			n5 = !ena;
			n3 = n5 & count[1];
			n6 = count[0] & n5;
			n8 = ena & n7;
			n4 = count[0] & ena;
			n1 = n8 | n6;
			n2 = n4 | n3;

			count[1] = n2;
			count[0] = n1;

		}

		// state bit : count[0]
		// state bit : ena
		// state bit : clk
		// state bit : count[1]
		// state bit : rst

	}

}
