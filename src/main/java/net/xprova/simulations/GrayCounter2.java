package net.xprova.simulations;

import java.util.ArrayList;

public class GrayCounter2 {


	public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {

		int[] count_0_ = new int[cycles];
		int[] count_1_ = new int[cycles];

		count_0_[0] = initial[0];
		count_1_[0] = initial[1];

		int ena1[] = inputs.get(0);
		int ena2[] = inputs.get(1);

		int[] n1 = new int[cycles];
		int[] n10 = new int[cycles];
		int[] n11 = new int[cycles];
		int[] n12 = new int[cycles];
		int[] n13 = new int[cycles];
		int[] n14 = new int[cycles];
		int[] n15 = new int[cycles];
		int[] n2 = new int[cycles];
		int[] n3 = new int[cycles];
		int[] n4 = new int[cycles];
		int[] n5 = new int[cycles];
		int[] n6 = new int[cycles];
		int[] n7 = new int[cycles];
		int[] n8 = new int[cycles];
		int[] n9 = new int[cycles];

		for (int i = 0; i < cycles; i++) {

			n15[i] = ~ena1[i];
			n13[i] = ~ena2[i];
			n7[i] = ena2[i] & n15[i];
			n8[i] = n13[i] & ena1[i];
			n3[i] = ena2[i] | n15[i];
			n14[i] = n13[i] | ena1[i];
			n4[i] = n3[i] & n14[i];
			n6[i] = ~count_1_[i];
			n9[i] = n8[i] | n7[i];
			n11[i] = count_1_[i] & n4[i];
			n5[i] = count_0_[i] & n4[i];
			n10[i] = n9[i] & n6[i];
			n12[i] = n9[i] & count_0_[i];
			n2[i] = n12[i] | n11[i];
			n1[i] = n10[i] | n5[i];

			if (i < cycles - 1) {

				count_0_[i + 1] = n1[i];
				count_1_[i + 1] = n2[i];
			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		waveforms.add(count_0_);
		waveforms.add(count_1_);

		waveforms.add(ena1);
		waveforms.add(ena2);

		waveforms.add(n1);
		waveforms.add(n10);
		waveforms.add(n11);
		waveforms.add(n12);
		waveforms.add(n13);
		waveforms.add(n14);
		waveforms.add(n15);
		waveforms.add(n2);
		waveforms.add(n3);
		waveforms.add(n4);
		waveforms.add(n5);
		waveforms.add(n6);
		waveforms.add(n7);
		waveforms.add(n8);
		waveforms.add(n9);

		return waveforms;
	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		result.add("count_0_");
		result.add("count_1_");

		result.add("ena1");
		result.add("ena2");

		result.add("n1");
		result.add("n10");
		result.add("n11");
		result.add("n12");
		result.add("n13");
		result.add("n14");
		result.add("n15");
		result.add("n2");
		result.add("n3");
		result.add("n4");
		result.add("n5");
		result.add("n6");
		result.add("n7");
		result.add("n8");
		result.add("n9");

		return result;
	}

	public int getStateBitCount() {

		return 2;
	}

	public int getInputBitCount() {

		return 2;
	}

}
