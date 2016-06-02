package net.xprova.xprova;

import java.util.ArrayList;

public class GrayCounter {

	public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {

		int[] count_0_ = new int[cycles];
		int[] count_1_ = new int[cycles];

		count_0_[0] = initial[0];
		count_1_[0] = initial[1];

		int ena[] = inputs.get(0);

		int[] n1 = new int[cycles];
		int[] n2 = new int[cycles];
		int[] n3 = new int[cycles];
		int[] n4 = new int[cycles];
		int[] n5 = new int[cycles];
		int[] n6 = new int[cycles];
		int[] n7 = new int[cycles];
		int[] n8 = new int[cycles];

		for (int i = 0; i < cycles; i++) {

			n7[i] = ~count_1_[i];
			n5[i] = ~ena[i];
			n6[i] = n5[i] & count_0_[i];
			n8[i] = ena[i] & n7[i];
			n4[i] = ena[i] & count_0_[i];
			n3[i] = n5[i] & count_1_[i];
			n1[i] = n8[i] | n6[i];
			n2[i] = n3[i] | n4[i];

			if (i < cycles - 1) {

				count_0_[i + 1] = n1[i];
				count_1_[i + 1] = n2[i];
			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		waveforms.add(count_0_);
		waveforms.add(count_1_);

		waveforms.add(ena);

		waveforms.add(n1);
		waveforms.add(n2);
		waveforms.add(n3);
		waveforms.add(n4);
		waveforms.add(n5);
		waveforms.add(n6);
		waveforms.add(n7);
		waveforms.add(n8);

		return waveforms;
	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		result.add("count_0_");
		result.add("count_1_");

		result.add("ena");

		result.add("n1");
		result.add("n2");
		result.add("n3");
		result.add("n4");
		result.add("n5");
		result.add("n6");
		result.add("n7");
		result.add("n8");

		return result;
	}

	public int getStateBitCount() {

		return 2;
	}

	public int getInputBitCount() {

		return 1;
	}

}
