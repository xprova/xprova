package net.xprova.xprova;

import java.util.ArrayList;

public class AssertionTest {

	public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {

		int[] count_0_ = new int[cycles];
		int[] count_1_ = new int[cycles];
		int[] count_2_ = new int[cycles];
		int[] count_3_ = new int[cycles];
		int[] n28 = new int[cycles];

		count_0_[0] = initial[0];
		count_1_[0] = initial[1];
		count_2_[0] = initial[2];
		count_3_[0] = initial[3];
		n28[0] = initial[4];

		int ena[] = inputs.get(0);

		int[] n1 = new int[cycles];
		int[] n10 = new int[cycles];
		int[] n11 = new int[cycles];
		int[] n12 = new int[cycles];
		int[] n13 = new int[cycles];
		int[] n14 = new int[cycles];
		int[] n15 = new int[cycles];
		int[] n16 = new int[cycles];
		int[] n17 = new int[cycles];
		int[] n18 = new int[cycles];
		int[] n19 = new int[cycles];
		int[] n2 = new int[cycles];
		int[] n20 = new int[cycles];
		int[] n21 = new int[cycles];
		int[] n22 = new int[cycles];
		int[] n23 = new int[cycles];
		int[] n24 = new int[cycles];
		int[] n25 = new int[cycles];
		int[] n26 = new int[cycles];
		int[] n27 = new int[cycles];
		int[] n3 = new int[cycles];
		int[] n4 = new int[cycles];
		int[] n5 = new int[cycles];
		int[] n6 = new int[cycles];
		int[] n7 = new int[cycles];
		int[] n8 = new int[cycles];
		int[] n9 = new int[cycles];
		int[] valid = new int[cycles];

		for (int i=0; i<cycles; i++) {

			n10[i] = ~count_1_[i];
			n9[i] = count_1_[i] | count_0_[i];
			n11[i] = n7[i] | n10[i];
			n18[i] = count_1_[i] & count_0_[i];
			n25[i] = ~n9[i];
			n16[i] = n11[i] | n5[i];
			n23[i] = ~count_3_[i];
			n7[i] = ~count_0_[i];
			n12[i] = n11[i] & n9[i];
			n19[i] = n18[i] & ena[i];
			n5[i] = ~ena[i];
			n21[i] = n19[i] & count_2_[i];
			n15[i] = ~count_2_[i];
			n6[i] = n5[i] & count_0_[i];
			n24[i] = n17[i] | n23[i];
			n26[i] = n15[i] | n25[i];
			n27[i] = n28[i] & n23[i];
			n13[i] = n5[i] | n12[i];
			n14[i] = count_1_[i] | ena[i];
			n17[i] = n15[i] | n16[i];
			n22[i] = n21[i] | count_3_[i];
			n8[i] = n7[i] & ena[i];
			n20[i] = n19[i] | count_2_[i];
			n4[i] = n22[i] & n24[i];
			valid[i] = n27[i] & n26[i];
			n1[i] = n8[i] | n6[i];
			n2[i] = n14[i] & n13[i];
			n3[i] = n20[i] & n17[i];

			if (i < cycles-1) {

				n28[i+1] = ena[i];
				count_1_[i+1] = n2[i];
				count_3_[i+1] = n4[i];
				count_2_[i+1] = n3[i];
				count_0_[i+1] = n1[i];
			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		waveforms.add(count_0_);
		waveforms.add(count_1_);
		waveforms.add(count_2_);
		waveforms.add(count_3_);
		waveforms.add(n28);

		waveforms.add(ena);

		waveforms.add(n1);
		waveforms.add(n10);
		waveforms.add(n11);
		waveforms.add(n12);
		waveforms.add(n13);
		waveforms.add(n14);
		waveforms.add(n15);
		waveforms.add(n16);
		waveforms.add(n17);
		waveforms.add(n18);
		waveforms.add(n19);
		waveforms.add(n2);
		waveforms.add(n20);
		waveforms.add(n21);
		waveforms.add(n22);
		waveforms.add(n23);
		waveforms.add(n24);
		waveforms.add(n25);
		waveforms.add(n26);
		waveforms.add(n27);
		waveforms.add(n3);
		waveforms.add(n4);
		waveforms.add(n5);
		waveforms.add(n6);
		waveforms.add(n7);
		waveforms.add(n8);
		waveforms.add(n9);
		waveforms.add(valid);

		return waveforms;
	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		result.add("count_0_");
		result.add("count_1_");
		result.add("count_2_");
		result.add("count_3_");
		result.add("n28");

		result.add("ena");

		result.add("n1");
		result.add("n10");
		result.add("n11");
		result.add("n12");
		result.add("n13");
		result.add("n14");
		result.add("n15");
		result.add("n16");
		result.add("n17");
		result.add("n18");
		result.add("n19");
		result.add("n2");
		result.add("n20");
		result.add("n21");
		result.add("n22");
		result.add("n23");
		result.add("n24");
		result.add("n25");
		result.add("n26");
		result.add("n27");
		result.add("n3");
		result.add("n4");
		result.add("n5");
		result.add("n6");
		result.add("n7");
		result.add("n8");
		result.add("n9");
		result.add("valid");

		return result;
	}

	public int getStateBitCount() {

		return 5;
	}

	public int getInputBitCount() {

		return 1;
	}




}
