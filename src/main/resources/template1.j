package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public int[] exploreSpace(int initial) throws Exception {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 5; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 2; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int count_0_ = -(initial >> 0 & 1); // {EXPANDED}
		int count_1_ = -(initial >> 1 & 1); // {EXPANDED}
		int count_2_ = -(initial >> 2 & 1); // {EXPANDED}
		int count_3_ = -(initial >> 3 & 1); // {EXPANDED}
		int n26 = -(initial >> 4 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n1; // {EXPANDED}
		int n10; // {EXPANDED}
		int n11; // {EXPANDED}
		int n12; // {EXPANDED}
		int n13; // {EXPANDED}
		int n14; // {EXPANDED}
		int n15; // {EXPANDED}
		int n16; // {EXPANDED}
		int n17; // {EXPANDED}
		int n18; // {EXPANDED}
		int n19; // {EXPANDED}
		int n2; // {EXPANDED}
		int n20; // {EXPANDED}
		int n21; // {EXPANDED}
		int n22; // {EXPANDED}
		int n23; // {EXPANDED}
		int n24; // {EXPANDED}
		int n25; // {EXPANDED}
		int n3; // {EXPANDED}
		int n4; // {EXPANDED}
		int n5; // {EXPANDED}
		int n6; // {EXPANDED}
		int n7; // {EXPANDED}
		int n8; // {EXPANDED}
		int n9; // {EXPANDED}
		int valid; // {EXPANDED}
		//@formatter:on

		int[] toVisitArr = new int[1];
		int toVisitArrOccupied = 1;

		int distance = 1;

		Integer in = null; // input vector

		final int UNDISCOVERED = 0x55555555;

		int[] parentState = new int[20000000];
		int[] inputVector = new int[20000000];

		Arrays.fill(parentState, UNDISCOVERED);

		// note: a state having the value UNDISCOVERED
		// will pose an issue to the algorithm below.
		// This possibility is here (unwisely?) ignored

		// note: an actual state of Integer.

		parentState[initial] = -1;

		long statesDiscovered = 0;

		Integer violationState = null;

		long maxToVisitSize = 0;

		int[][] buf = new int[2][100000];

		int bufSelector = 0;

		System.out.println("Starting search ...");

		long startTime = System.nanoTime();

		loop1: while (toVisitArrOccupied > 0) {

			bufSelector = 1 - bufSelector;

			int[] toVisitNextArr = buf[bufSelector];

			int toVisitNextArrOccupied = 0;

			if (toVisitArrOccupied > maxToVisitSize)
				maxToVisitSize = toVisitArrOccupied;

			for (int i1 = 0; i1 < toVisitArrOccupied; i1++) {

				int state = toVisitArr[i1];

				statesDiscovered += 1;

				if (statesDiscovered % 1e6 == 0)
					System.out.printf("Discovered %d states ...\n", statesDiscovered);

				//@formatter:off
				// {STATE_BIT} = -(state >> {STATE_BIT_INDEX} & 1);
				count_0_ = -(state >> 0 & 1); // {EXPANDED}
				count_1_ = -(state >> 1 & 1); // {EXPANDED}
				count_2_ = -(state >> 2 & 1); // {EXPANDED}
				count_3_ = -(state >> 3 & 1); // {EXPANDED}
				n26 = -(state >> 4 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << inputBitCount;

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int ena1 = -(in >> 0 & 1); // {EXPANDED}
					int ena2 = -(in >> 1 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n8 = count_3_ | count_2_; // {EXPANDED}
					n12 = ~ena2; // {EXPANDED}
					n22 = ~count_2_; // {EXPANDED}
					n5 = ~count_0_; // {EXPANDED}
					n17 = (count_0_ ^ count_1_); // {EXPANDED}
					n19 = ~(count_0_ & count_1_); // {EXPANDED}
					n6 = ~(count_3_ | count_1_); // {EXPANDED}
					n9 = n26 & n8; // {EXPANDED}
					n10 = ~(count_1_ | n8); // {EXPANDED}
					n23 = ~(n19 | n22); // {EXPANDED}
					n13 = ~(n12 | n10); // {EXPANDED}
					n11 = ena1 & n10; // {EXPANDED}
					n7 = ~(n6 & n5); // {EXPANDED}
					valid = ~(n7 & n9); // {EXPANDED}
					n24 = (n23 ^ count_3_); // {EXPANDED}
					n14 = ~(n13 | n11); // {EXPANDED}
					n16 = n13 | n11; // {EXPANDED}
					n1 = (n14 ^ n5); // {EXPANDED}
					n20 = ~(n14 | n19); // {EXPANDED}
					n25 = ~(n24 & n16); // {EXPANDED}
					n21 = ~(count_3_ & n14); // {EXPANDED}
					n15 = ~(count_1_ & n14); // {EXPANDED}
					n18 = ~(n17 & n16); // {EXPANDED}
					n3 = (n20 ^ count_2_); // {EXPANDED}
					n2 = ~(n15 & n18); // {EXPANDED}
					n4 = ~(n25 & n21); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {STATE_ASSIGN} {PREFIX1=int next_}
					int next_count_0_ = n1; // {EXPANDED}
					int next_count_1_ = n2; // {EXPANDED}
					int next_count_2_ = n3; // {EXPANDED}
					int next_count_3_ = n4; // {EXPANDED}
					int next_n26 = ena1; // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState += (next_{STATE_BIT} & 1) << {STATE_BIT_INDEX};
					nxState += (next_count_0_ & 1) << 0; // {EXPANDED}
					nxState += (next_count_1_ & 1) << 1; // {EXPANDED}
					nxState += (next_count_2_ & 1) << 2; // {EXPANDED}
					nxState += (next_count_3_ & 1) << 3; // {EXPANDED}
					nxState += (next_n26 & 1) << 4; // {EXPANDED}
					//@formatter:on

					if (parentState[nxState] == UNDISCOVERED) {

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied += 1;

						parentState[nxState] = state;

						inputVector[nxState] = in;

					}

					if (valid == 0) {

						violationState = nxState;

						break loop1;

					}

				}

			}

			toVisitArr = toVisitNextArr;

			toVisitArrOccupied = toVisitNextArrOccupied;

			distance = distance + 1;

		}

		long endTime = System.nanoTime();

		double searchTime = (endTime - startTime) / 1e9;

		System.out.printf("completed search in %f seconds\n", searchTime);

		System.out.printf("maxToVisitSize = %d\n", maxToVisitSize);

		Stack<Integer> rList = new Stack<Integer>();

		if (violationState != null) {

			System.out.printf("Counter-example found (distance = %d)!\n", distance);

			int currentState = violationState;

			while (currentState != initial) {

				System.out.println("currentState = " + getBinary(currentState, stateBitCount)
						+ ", reached from parent using input vector "
						+ getBinary(inputVector[currentState], inputBitCount));

				rList.push(inputVector[currentState]);

				currentState = parentState[currentState];

			}

			System.out.println("currentState = " + getBinary(currentState, stateBitCount));

			int[] result = new int[distance];

			for (int j = 0; j < distance-1; j++)
				result[j] = rList.pop();

			return result;

		} else {

			// no counter-example was found

			return null;

		}

	}

	public ArrayList<int[]> simulate(int initial, int[] inputs, int cycles) {

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] count_0_ = new int[cycles]; // {EXPANDED}
		int[] count_1_ = new int[cycles]; // {EXPANDED}
		int[] count_2_ = new int[cycles]; // {EXPANDED}
		int[] count_3_ = new int[cycles]; // {EXPANDED}
		int[] n26 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] ena1 = new int[cycles]; // {EXPANDED}
		int[] ena2 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		count_0_[0] = -(initial >> 0 & 1); // {EXPANDED}
		count_1_[0] = -(initial >> 1 & 1); // {EXPANDED}
		count_2_[0] = -(initial >> 2 & 1); // {EXPANDED}
		count_3_[0] = -(initial >> 3 & 1); // {EXPANDED}
		n26[0] = -(initial >> 4 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] n1 = new int[cycles]; // {EXPANDED}
		int[] n10 = new int[cycles]; // {EXPANDED}
		int[] n11 = new int[cycles]; // {EXPANDED}
		int[] n12 = new int[cycles]; // {EXPANDED}
		int[] n13 = new int[cycles]; // {EXPANDED}
		int[] n14 = new int[cycles]; // {EXPANDED}
		int[] n15 = new int[cycles]; // {EXPANDED}
		int[] n16 = new int[cycles]; // {EXPANDED}
		int[] n17 = new int[cycles]; // {EXPANDED}
		int[] n18 = new int[cycles]; // {EXPANDED}
		int[] n19 = new int[cycles]; // {EXPANDED}
		int[] n2 = new int[cycles]; // {EXPANDED}
		int[] n20 = new int[cycles]; // {EXPANDED}
		int[] n21 = new int[cycles]; // {EXPANDED}
		int[] n22 = new int[cycles]; // {EXPANDED}
		int[] n23 = new int[cycles]; // {EXPANDED}
		int[] n24 = new int[cycles]; // {EXPANDED}
		int[] n25 = new int[cycles]; // {EXPANDED}
		int[] n3 = new int[cycles]; // {EXPANDED}
		int[] n4 = new int[cycles]; // {EXPANDED}
		int[] n5 = new int[cycles]; // {EXPANDED}
		int[] n6 = new int[cycles]; // {EXPANDED}
		int[] n7 = new int[cycles]; // {EXPANDED}
		int[] n8 = new int[cycles]; // {EXPANDED}
		int[] n9 = new int[cycles]; // {EXPANDED}
		int[] valid = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			ena1[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			ena2[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n8[i] = count_3_[i] | count_2_[i]; // {EXPANDED}
			n12[i] = ~ena2[i]; // {EXPANDED}
			n22[i] = ~count_2_[i]; // {EXPANDED}
			n5[i] = ~count_0_[i]; // {EXPANDED}
			n17[i] = (count_0_[i] ^ count_1_[i]); // {EXPANDED}
			n19[i] = ~(count_0_[i] & count_1_[i]); // {EXPANDED}
			n6[i] = ~(count_3_[i] | count_1_[i]); // {EXPANDED}
			n9[i] = n26[i] & n8[i]; // {EXPANDED}
			n10[i] = ~(count_1_[i] | n8[i]); // {EXPANDED}
			n23[i] = ~(n19[i] | n22[i]); // {EXPANDED}
			n13[i] = ~(n12[i] | n10[i]); // {EXPANDED}
			n11[i] = ena1[i] & n10[i]; // {EXPANDED}
			n7[i] = ~(n6[i] & n5[i]); // {EXPANDED}
			valid[i] = ~(n7[i] & n9[i]); // {EXPANDED}
			n24[i] = (n23[i] ^ count_3_[i]); // {EXPANDED}
			n14[i] = ~(n13[i] | n11[i]); // {EXPANDED}
			n16[i] = n13[i] | n11[i]; // {EXPANDED}
			n1[i] = (n14[i] ^ n5[i]); // {EXPANDED}
			n20[i] = ~(n14[i] | n19[i]); // {EXPANDED}
			n25[i] = ~(n24[i] & n16[i]); // {EXPANDED}
			n21[i] = ~(count_3_[i] & n14[i]); // {EXPANDED}
			n15[i] = ~(count_1_[i] & n14[i]); // {EXPANDED}
			n18[i] = ~(n17[i] & n16[i]); // {EXPANDED}
			n3[i] = (n20[i] ^ count_2_[i]); // {EXPANDED}
			n2[i] = ~(n15[i] & n18[i]); // {EXPANDED}
			n4[i] = ~(n25[i] & n21[i]); // {EXPANDED}

			if (i < cycles-1) {

				// {STATE_ASSIGN} {POSTFIX1=[i+1]} {POSTFIX2=[i]}
				count_0_[i+1] = n1[i]; // {EXPANDED}
				count_1_[i+1] = n2[i]; // {EXPANDED}
				count_2_[i+1] = n3[i]; // {EXPANDED}
				count_3_[i+1] = n4[i]; // {EXPANDED}
				n26[i+1] = ena1[i]; // {EXPANDED}

			}
			//@formatter:on

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(count_0_); // {EXPANDED}
		waveforms.add(count_1_); // {EXPANDED}
		waveforms.add(count_2_); // {EXPANDED}
		waveforms.add(count_3_); // {EXPANDED}
		waveforms.add(n26); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(ena1); // {EXPANDED}
		waveforms.add(ena2); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n1); // {EXPANDED}
		waveforms.add(n10); // {EXPANDED}
		waveforms.add(n11); // {EXPANDED}
		waveforms.add(n12); // {EXPANDED}
		waveforms.add(n13); // {EXPANDED}
		waveforms.add(n14); // {EXPANDED}
		waveforms.add(n15); // {EXPANDED}
		waveforms.add(n16); // {EXPANDED}
		waveforms.add(n17); // {EXPANDED}
		waveforms.add(n18); // {EXPANDED}
		waveforms.add(n19); // {EXPANDED}
		waveforms.add(n2); // {EXPANDED}
		waveforms.add(n20); // {EXPANDED}
		waveforms.add(n21); // {EXPANDED}
		waveforms.add(n22); // {EXPANDED}
		waveforms.add(n23); // {EXPANDED}
		waveforms.add(n24); // {EXPANDED}
		waveforms.add(n25); // {EXPANDED}
		waveforms.add(n3); // {EXPANDED}
		waveforms.add(n4); // {EXPANDED}
		waveforms.add(n5); // {EXPANDED}
		waveforms.add(n6); // {EXPANDED}
		waveforms.add(n7); // {EXPANDED}
		waveforms.add(n8); // {EXPANDED}
		waveforms.add(n9); // {EXPANDED}
		waveforms.add(valid); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT}");
		result.add("count_0_"); // {EXPANDED}
		result.add("count_1_"); // {EXPANDED}
		result.add("count_2_"); // {EXPANDED}
		result.add("count_3_"); // {EXPANDED}
		result.add("n26"); // {EXPANDED}

		// result.add("{INPUT_BIT}");
		result.add("ena1"); // {EXPANDED}
		result.add("ena2"); // {EXPANDED}

		// result.add("{NON_STATE_BIT}");
		result.add("n1"); // {EXPANDED}
		result.add("n10"); // {EXPANDED}
		result.add("n11"); // {EXPANDED}
		result.add("n12"); // {EXPANDED}
		result.add("n13"); // {EXPANDED}
		result.add("n14"); // {EXPANDED}
		result.add("n15"); // {EXPANDED}
		result.add("n16"); // {EXPANDED}
		result.add("n17"); // {EXPANDED}
		result.add("n18"); // {EXPANDED}
		result.add("n19"); // {EXPANDED}
		result.add("n2"); // {EXPANDED}
		result.add("n20"); // {EXPANDED}
		result.add("n21"); // {EXPANDED}
		result.add("n22"); // {EXPANDED}
		result.add("n23"); // {EXPANDED}
		result.add("n24"); // {EXPANDED}
		result.add("n25"); // {EXPANDED}
		result.add("n3"); // {EXPANDED}
		result.add("n4"); // {EXPANDED}
		result.add("n5"); // {EXPANDED}
		result.add("n6"); // {EXPANDED}
		result.add("n7"); // {EXPANDED}
		result.add("n8"); // {EXPANDED}
		result.add("n9"); // {EXPANDED}
		result.add("valid"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 5; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 2; // {EXPANDED}
		//@formatter:on
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

	public void runSim2(int initial, int[] inputs) {

		ArrayList<String> sigNames = getSignalNames();

		int cycles = inputs.length;

		ArrayList<int[]> results = simulate(initial, inputs, cycles);

		System.out.printf("%10s : ", "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		System.out.println();

		System.out.println();

		for (int j = 0; j < results.size(); j++) {

			if (j == getStateBitCount())
				System.out.println();

			int[] sig = results.get(j);

			System.out.printf("%10s : ", sigNames.get(j));

			for (int i = 0; i < cycles; i++) {

				System.out.printf((sig[i] == H) ? "1" : "0");

			}

			System.out.println();

		}

	}

}
