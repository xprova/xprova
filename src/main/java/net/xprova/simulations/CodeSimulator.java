package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		final int STATE_BUF_SIZE = 20000000;

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 4; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 1; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int n19 = -(initial >> 0 & 1); // {EXPANDED}
		int n20 = -(initial >> 1 & 1); // {EXPANDED}
		int n21 = -(initial >> 2 & 1); // {EXPANDED}
		int n22 = -(initial >> 3 & 1); // {EXPANDED}
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
		int n2; // {EXPANDED}
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
		toVisitArr[0] = initial;
		int toVisitArrOccupied = 1;

		int distance = 1;

		int in; // input vector

		int[] parentState = new int[STATE_BUF_SIZE];
		int[] inputVector = new int[STATE_BUF_SIZE];

		Arrays.fill(parentState, UNDISCOVERED);

		// note: a state having the value UNDISCOVERED
		// will pose an issue to the algorithm below.
		// This possibility is here (unwisely?) ignored

		// note: an actual state of Integer.

		parentState[initial] = -1; // marked parentState as visited

		long statesDiscovered = 0;

		int violationState = UNDISCOVERED;

		int[][] buf = new int[2][DISCOVERED_BUF_SIZE];

		int bufSelector = 0;

		int state = initial;

		System.out.println("Starting search ...");

		long startTime = System.nanoTime();

		loop1: while (toVisitArrOccupied > 0) {

			bufSelector = 1 - bufSelector;

			int[] toVisitNextArr = buf[bufSelector];

			int toVisitNextArrOccupied = 0;

			for (int i1 = 0; i1 < toVisitArrOccupied; i1++) {

				state = toVisitArr[i1];

				statesDiscovered += 1;

				//@formatter:off
				// {STATE_BIT} = -(state >> {STATE_BIT_INDEX} & 1);
				n19 = -(state >> 0 & 1); // {EXPANDED}
				n20 = -(state >> 1 & 1); // {EXPANDED}
				n21 = -(state >> 2 & 1); // {EXPANDED}
				n22 = -(state >> 3 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int ena = -(in >> 0 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n10 = ~(ena & n20); // {EXPANDED}
					n14 = (n21 ^ n22); // {EXPANDED}
					n15 = ~(ena & n14); // {EXPANDED}
					n8 = ~(ena & n19); // {EXPANDED}
					n6 = ~ena; // {EXPANDED}
					n5 = ~(n20 | n19); // {EXPANDED}
					n13 = ~(n6 & n19); // {EXPANDED}
					n12 = ~(n21 & ena); // {EXPANDED}
					n11 = ~(n6 & n22); // {EXPANDED}
					n2 = ~n22; // {EXPANDED}
					n3 = ~(n21 | n2); // {EXPANDED}
					n7 = ~(n6 & n20); // {EXPANDED}
					valid = ~(n5 & n3); // {EXPANDED}
					n16 = ~(n8 & n7); // {EXPANDED}
					n9 = ~(n21 & n6); // {EXPANDED}
					n1 = ~(n12 & n11); // {EXPANDED}
					n4 = ~(n13 & n15); // {EXPANDED}
					n17 = ~(n10 & n9); // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= n4 & (1 << 0); // {EXPANDED}
					nxState |= n16 & (1 << 1); // {EXPANDED}
					nxState |= n17 & (1 << 2); // {EXPANDED}
					nxState |= n1 & (1 << 3); // {EXPANDED}
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

		System.out.printf("Completed search in %f seconds\n", searchTime);

		System.out.printf("States discovered = %d\n", statesDiscovered);

		Stack<Integer> rList = new Stack<Integer>();

		if (violationState != UNDISCOVERED) {

			System.out.printf("Counter-example found (distance = %d)!\n", distance);

			int currentState = violationState;

			while (currentState != initial) {

				if (printStateList)
					System.out.println("currentState = " + getBinary(currentState, stateBitCount)
							+ ", reached from parent using input vector "
							+ getBinary(inputVector[currentState], inputBitCount));

				rList.add(inputVector[currentState]);

				currentState = parentState[currentState];
			}

			int[] result = new int[distance];

			for (int j = 0; j < distance - 1; j++)
				result[j] = rList.pop();

			return result;

		} else {

			System.out.println("Assertion proven, no counter-examples were found.");

			return null;

		}

	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT}");
		result.add("n19"); // {EXPANDED}
		result.add("n20"); // {EXPANDED}
		result.add("n21"); // {EXPANDED}
		result.add("n22"); // {EXPANDED}

		// result.add("{INPUT_BIT}");
		result.add("ena"); // {EXPANDED}

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
		result.add("n2"); // {EXPANDED}
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
		return 4; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 1; // {EXPANDED}
		//@formatter:on
	}

	public void simulate(int initial, int[] inputs) {

		ArrayList<String> sigNames = getSignalNames();

		int cycles = inputs.length;

		ArrayList<int[]> waveforms = simulate_internal(initial, inputs);

		System.out.printf("%10s : ", "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		System.out.println();

		System.out.println();

		for (int j = 0; j < waveforms.size(); j++) {

			if (j == getStateBitCount())
				System.out.println();

			int[] sig = waveforms.get(j);

			System.out.printf("%10s : ", sigNames.get(j));

			for (int i = 0; i < cycles; i++) {

				System.out.printf((sig[i] == H) ? "1" : "0");

			}

			System.out.println();

		}

	}

	private ArrayList<int[]> simulate_internal(int initial, int[] inputs) {

		int cycles = inputs.length;

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] n19 = new int[cycles]; // {EXPANDED}
		int[] n20 = new int[cycles]; // {EXPANDED}
		int[] n21 = new int[cycles]; // {EXPANDED}
		int[] n22 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] ena = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		n19[0] = -(initial >> 0 & 1); // {EXPANDED}
		n20[0] = -(initial >> 1 & 1); // {EXPANDED}
		n21[0] = -(initial >> 2 & 1); // {EXPANDED}
		n22[0] = -(initial >> 3 & 1); // {EXPANDED}

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
		int[] n2 = new int[cycles]; // {EXPANDED}
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
			ena[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n10[i] = ~(ena[i] & n20[i]); // {EXPANDED}
			n14[i] = (n21[i] ^ n22[i]); // {EXPANDED}
			n15[i] = ~(ena[i] & n14[i]); // {EXPANDED}
			n8[i] = ~(ena[i] & n19[i]); // {EXPANDED}
			n6[i] = ~ena[i]; // {EXPANDED}
			n5[i] = ~(n20[i] | n19[i]); // {EXPANDED}
			n13[i] = ~(n6[i] & n19[i]); // {EXPANDED}
			n12[i] = ~(n21[i] & ena[i]); // {EXPANDED}
			n11[i] = ~(n6[i] & n22[i]); // {EXPANDED}
			n2[i] = ~n22[i]; // {EXPANDED}
			n3[i] = ~(n21[i] | n2[i]); // {EXPANDED}
			n7[i] = ~(n6[i] & n20[i]); // {EXPANDED}
			valid[i] = ~(n5[i] & n3[i]); // {EXPANDED}
			n16[i] = ~(n8[i] & n7[i]); // {EXPANDED}
			n9[i] = ~(n21[i] & n6[i]); // {EXPANDED}
			n1[i] = ~(n12[i] & n11[i]); // {EXPANDED}
			n4[i] = ~(n13[i] & n15[i]); // {EXPANDED}
			n17[i] = ~(n10[i] & n9[i]); // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				n19[i+1] |= n4[i]; // {EXPANDED}
				n20[i+1] |= n16[i]; // {EXPANDED}
				n21[i+1] |= n17[i]; // {EXPANDED}
				n22[i+1] |= n1[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(n19); // {EXPANDED}
		waveforms.add(n20); // {EXPANDED}
		waveforms.add(n21); // {EXPANDED}
		waveforms.add(n22); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(ena); // {EXPANDED}

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
		waveforms.add(n2); // {EXPANDED}
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

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

}

