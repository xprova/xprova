package net.xprova.xprova;

public class CodeSimulator {

	private void simulate1(boolean[] count_0_, boolean[] count_1_, boolean[] ena, int cycles) {

		boolean n4;
		boolean n1;
		boolean n8;
		boolean n6;
		boolean n5;
		boolean n3;
		boolean n2;
		boolean n7;

		for (int i = 1; i <= cycles; i++) {

			n7 = !count_1_[i - 1];
			n5 = !ena[i - 1];
			n6 = n5 & count_0_[i - 1];
			n8 = ena[i - 1] & n7;
			n4 = ena[i - 1] & count_0_[i - 1];
			n3 = n5 & count_1_[i - 1];
			n1 = n8 | n6;
			n2 = n3 | n4;
			count_0_[i] = n1;
			count_1_[i] = n2;

		}
	}

	public void simulate() {

		boolean[] count_0_ = new boolean[20];
		boolean[] count_1_ = new boolean[20];
		boolean[] ena = { false, false, true, false, false, true, false, true, false, false };

		int cycles = ena.length;

		simulate1(count_0_, count_1_, ena, cycles);

		for (int i = 0; i < cycles; i++) {

			String enaStr = ena[i] ? "1" : "0";
			String c0Str = count_0_[i] ? "1" : "0";
			String c1Str = count_1_[i] ? "1" : "0";

			System.out.println("tick " + i + ", count = " + c0Str + c1Str + ", ena = " + enaStr);

		}

	}

}
