package net.xprova.xprova;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public static void main(String args[]) throws Exception {

		// usage:
		// codesimulator [--txt <file>]

		CodeSimulator sim1 = new CodeSimulator();

		int initial = sim1.getResetState();

		File  txtFile = null;

		for (int i = 0; i < args.length; i++) {

			String a = args[i];

			if ("--txt".equals(a))
				txtFile = new File(args[i + 1]);

		}

		int[] counterExample = sim1.exploreSpace(initial);

		if (counterExample != null) {

			sim1.simulate(initial, counterExample, txtFile);

			// 100 is a special return code for finding a counter-example but
			// terminating successfully

			System.exit(100);

		}

	}

	public int getResetState() {

		// return {RESET_STATE};
		return 0x9; // {EXPANDED}

	}

	@SuppressWarnings("unused")
	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		final int STATE_BUF_SIZE = 1 << 11; // {EXPANDED}
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 11; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 2; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int n_n38_16 = -(initial >> 0 & 1); // {EXPANDED}
		int n_n44_10 = -(initial >> 1 & 1); // {EXPANDED}
		int n_n48_0 = -(initial >> 2 & 1); // {EXPANDED}
		int n_n52_21 = -(initial >> 3 & 1); // {EXPANDED}
		int n_n56_19 = -(initial >> 4 & 1); // {EXPANDED}
		int nc_28 = -(initial >> 5 & 1); // {EXPANDED}
		int ncount_0__31 = -(initial >> 6 & 1); // {EXPANDED}
		int ncount_1__11 = -(initial >> 7 & 1); // {EXPANDED}
		int ncount_2__17 = -(initial >> 8 & 1); // {EXPANDED}
		int ncount_3__12 = -(initial >> 9 & 1); // {EXPANDED}
		int nd_30 = -(initial >> 10 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n_n36_29; // {EXPANDED}
		int n_n40_1; // {EXPANDED}
		int n_n42_13; // {EXPANDED}
		int n_n46_20; // {EXPANDED}
		int n_n50_7; // {EXPANDED}
		int n_n54_22; // {EXPANDED}
		int n_n58_32; // {EXPANDED}
		int n_rst_prop_9; // {EXPANDED}
		int n_set_prop_6; // {EXPANDED}
		int n_00__0__14; // {EXPANDED}
		int n_00__1__5; // {EXPANDED}
		int n_00__2__26; // {EXPANDED}
		int n_00__3__18; // {EXPANDED}
		int n_01__3; // {EXPANDED}
		int n_02__25; // {EXPANDED}
		int n_03__8; // {EXPANDED}
		int n_04__24; // {EXPANDED}
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

		int all_assumptions;
		int all_assertions;

		Stack<Integer> rList = new Stack<Integer>();

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
				n_n38_16 = -(state >> 0 & 1); // {EXPANDED}
				n_n44_10 = -(state >> 1 & 1); // {EXPANDED}
				n_n48_0 = -(state >> 2 & 1); // {EXPANDED}
				n_n52_21 = -(state >> 3 & 1); // {EXPANDED}
				n_n56_19 = -(state >> 4 & 1); // {EXPANDED}
				nc_28 = -(state >> 5 & 1); // {EXPANDED}
				ncount_0__31 = -(state >> 6 & 1); // {EXPANDED}
				ncount_1__11 = -(state >> 7 & 1); // {EXPANDED}
				ncount_2__17 = -(state >> 8 & 1); // {EXPANDED}
				ncount_3__12 = -(state >> 9 & 1); // {EXPANDED}
				nd_30 = -(state >> 10 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int na_4 = -(in >> 0 & 1); // {EXPANDED}
					int nb_2 = -(in >> 1 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n_04__24 = 0; // {EXPANDED}
					n_n46_20 = (n_n48_0 ^ nb_2); // {EXPANDED}
					n_n54_22 = (n_n56_19 ^ na_4); // {EXPANDED}
					n_01__3 = ncount_0__31 & na_4; // {EXPANDED}
					n_00__1__5 = (ncount_1__11 ^ n_01__3); // {EXPANDED}
					n_n50_7 = n_n52_21 & n_n54_22; // {EXPANDED}
					n_02__25 = ncount_1__11 & n_01__3; // {EXPANDED}
					n_00__2__26 = (n_02__25 ^ ncount_2__17); // {EXPANDED}
					n_n58_32 = ~ncount_3__12; // {EXPANDED}
					n_n42_13 = ~na_4; // {EXPANDED}
					n_00__0__14 = (ncount_0__31 ^ na_4); // {EXPANDED}
					n_03__8 = n_02__25 & ncount_2__17; // {EXPANDED}
					n_n40_1 = n_n44_10 | n_n46_20; // {EXPANDED}
					n_00__3__18 = (n_03__8 ^ ncount_3__12); // {EXPANDED}
					n_n36_29 = n_n38_16 & n_n40_1; // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= n_n36_29 & (1 << 0); // {EXPANDED}
					nxState |= n_n42_13 & (1 << 1); // {EXPANDED}
					nxState |= nb_2 & (1 << 2); // {EXPANDED}
					nxState |= n_n50_7 & (1 << 3); // {EXPANDED}
					nxState |= na_4 & (1 << 4); // {EXPANDED}
					nxState |= na_4 & (1 << 5); // {EXPANDED}
					nxState |= n_00__0__14 & (1 << 6); // {EXPANDED}
					nxState |= n_00__1__5 & (1 << 7); // {EXPANDED}
					nxState |= n_00__2__26 & (1 << 8); // {EXPANDED}
					nxState |= n_00__3__18 & (1 << 9); // {EXPANDED}
					nxState |= nb_2 & (1 << 10); // {EXPANDED}
					//@formatter:on

					if (parentState[nxState] == UNDISCOVERED) {

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied += 1;

						parentState[nxState] = state;

						inputVector[nxState] = in;

					}

					all_assumptions = -1;
					all_assertions = -1;

					// all_assumptions &= {ASSUMPTION};
					all_assumptions &= n_n36_29; // {EXPANDED}
					all_assumptions &= n_n50_7; // {EXPANDED}
					// all_assertions &= {ASSERTION};
					all_assertions &= n_n58_32; // {EXPANDED}

					if (all_assumptions == -1 && all_assertions == 0) {

						violationState = state;

						rList.push(in);

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

			for (int j = 0; j < distance; j++)
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
		// result.add("{STATE_BIT_ORG}");
		result.add("*n38"); // {EXPANDED}
		result.add("*n44"); // {EXPANDED}
		result.add("*n48"); // {EXPANDED}
		result.add("*n52"); // {EXPANDED}
		result.add("*n56"); // {EXPANDED}
		result.add("c"); // {EXPANDED}
		result.add("count[0]"); // {EXPANDED}
		result.add("count[1]"); // {EXPANDED}
		result.add("count[2]"); // {EXPANDED}
		result.add("count[3]"); // {EXPANDED}
		result.add("d"); // {EXPANDED}

		// result.add("{INPUT_BIT_ORG}");
		result.add("a"); // {EXPANDED}
		result.add("b"); // {EXPANDED}

		// result.add("{NON_STATE_BIT_ORG}");
		result.add("*n36"); // {EXPANDED}
		result.add("*n40"); // {EXPANDED}
		result.add("*n42"); // {EXPANDED}
		result.add("*n46"); // {EXPANDED}
		result.add("*n50"); // {EXPANDED}
		result.add("*n54"); // {EXPANDED}
		result.add("*n58"); // {EXPANDED}
		result.add("*rst_prop"); // {EXPANDED}
		result.add("*set_prop"); // {EXPANDED}
		result.add("_00_[0]"); // {EXPANDED}
		result.add("_00_[1]"); // {EXPANDED}
		result.add("_00_[2]"); // {EXPANDED}
		result.add("_00_[3]"); // {EXPANDED}
		result.add("_01_"); // {EXPANDED}
		result.add("_02_"); // {EXPANDED}
		result.add("_03_"); // {EXPANDED}
		result.add("_04_"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 11; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 2; // {EXPANDED}
		//@formatter:on
	}

	public void simulate(int initial, int[] inputs, File txtFile) throws Exception {

		ArrayList<String> sigNames = getSignalNames();

		ArrayList<int[]> waveforms = simulate_internal(initial, inputs);

		if (txtFile != null)
			generateTextFile(sigNames, waveforms, txtFile);

	}

	private ArrayList<int[]> simulate_internal(int initial, int[] inputs) {

		int cycles = inputs.length;

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] n_n38_16 = new int[cycles]; // {EXPANDED}
		int[] n_n44_10 = new int[cycles]; // {EXPANDED}
		int[] n_n48_0 = new int[cycles]; // {EXPANDED}
		int[] n_n52_21 = new int[cycles]; // {EXPANDED}
		int[] n_n56_19 = new int[cycles]; // {EXPANDED}
		int[] nc_28 = new int[cycles]; // {EXPANDED}
		int[] ncount_0__31 = new int[cycles]; // {EXPANDED}
		int[] ncount_1__11 = new int[cycles]; // {EXPANDED}
		int[] ncount_2__17 = new int[cycles]; // {EXPANDED}
		int[] ncount_3__12 = new int[cycles]; // {EXPANDED}
		int[] nd_30 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] na_4 = new int[cycles]; // {EXPANDED}
		int[] nb_2 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		n_n38_16[0] = -(initial >> 0 & 1); // {EXPANDED}
		n_n44_10[0] = -(initial >> 1 & 1); // {EXPANDED}
		n_n48_0[0] = -(initial >> 2 & 1); // {EXPANDED}
		n_n52_21[0] = -(initial >> 3 & 1); // {EXPANDED}
		n_n56_19[0] = -(initial >> 4 & 1); // {EXPANDED}
		nc_28[0] = -(initial >> 5 & 1); // {EXPANDED}
		ncount_0__31[0] = -(initial >> 6 & 1); // {EXPANDED}
		ncount_1__11[0] = -(initial >> 7 & 1); // {EXPANDED}
		ncount_2__17[0] = -(initial >> 8 & 1); // {EXPANDED}
		ncount_3__12[0] = -(initial >> 9 & 1); // {EXPANDED}
		nd_30[0] = -(initial >> 10 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] n_n36_29 = new int[cycles]; // {EXPANDED}
		int[] n_n40_1 = new int[cycles]; // {EXPANDED}
		int[] n_n42_13 = new int[cycles]; // {EXPANDED}
		int[] n_n46_20 = new int[cycles]; // {EXPANDED}
		int[] n_n50_7 = new int[cycles]; // {EXPANDED}
		int[] n_n54_22 = new int[cycles]; // {EXPANDED}
		int[] n_n58_32 = new int[cycles]; // {EXPANDED}
		int[] n_rst_prop_9 = new int[cycles]; // {EXPANDED}
		int[] n_set_prop_6 = new int[cycles]; // {EXPANDED}
		int[] n_00__0__14 = new int[cycles]; // {EXPANDED}
		int[] n_00__1__5 = new int[cycles]; // {EXPANDED}
		int[] n_00__2__26 = new int[cycles]; // {EXPANDED}
		int[] n_00__3__18 = new int[cycles]; // {EXPANDED}
		int[] n_01__3 = new int[cycles]; // {EXPANDED}
		int[] n_02__25 = new int[cycles]; // {EXPANDED}
		int[] n_03__8 = new int[cycles]; // {EXPANDED}
		int[] n_04__24 = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			na_4[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			nb_2[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n_04__24[i] = 0; // {EXPANDED}
			n_n46_20[i] = (n_n48_0[i] ^ nb_2[i]); // {EXPANDED}
			n_n54_22[i] = (n_n56_19[i] ^ na_4[i]); // {EXPANDED}
			n_01__3[i] = ncount_0__31[i] & na_4[i]; // {EXPANDED}
			n_00__1__5[i] = (ncount_1__11[i] ^ n_01__3[i]); // {EXPANDED}
			n_n50_7[i] = n_n52_21[i] & n_n54_22[i]; // {EXPANDED}
			n_02__25[i] = ncount_1__11[i] & n_01__3[i]; // {EXPANDED}
			n_00__2__26[i] = (n_02__25[i] ^ ncount_2__17[i]); // {EXPANDED}
			n_n58_32[i] = ~ncount_3__12[i]; // {EXPANDED}
			n_n42_13[i] = ~na_4[i]; // {EXPANDED}
			n_00__0__14[i] = (ncount_0__31[i] ^ na_4[i]); // {EXPANDED}
			n_03__8[i] = n_02__25[i] & ncount_2__17[i]; // {EXPANDED}
			n_n40_1[i] = n_n44_10[i] | n_n46_20[i]; // {EXPANDED}
			n_00__3__18[i] = (n_03__8[i] ^ ncount_3__12[i]); // {EXPANDED}
			n_n36_29[i] = n_n38_16[i] & n_n40_1[i]; // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				n_n38_16[i+1] |= n_n36_29[i]; // {EXPANDED}
				n_n44_10[i+1] |= n_n42_13[i]; // {EXPANDED}
				n_n48_0[i+1] |= nb_2[i]; // {EXPANDED}
				n_n52_21[i+1] |= n_n50_7[i]; // {EXPANDED}
				n_n56_19[i+1] |= na_4[i]; // {EXPANDED}
				nc_28[i+1] |= na_4[i]; // {EXPANDED}
				ncount_0__31[i+1] |= n_00__0__14[i]; // {EXPANDED}
				ncount_1__11[i+1] |= n_00__1__5[i]; // {EXPANDED}
				ncount_2__17[i+1] |= n_00__2__26[i]; // {EXPANDED}
				ncount_3__12[i+1] |= n_00__3__18[i]; // {EXPANDED}
				nd_30[i+1] |= nb_2[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(n_n38_16); // {EXPANDED}
		waveforms.add(n_n44_10); // {EXPANDED}
		waveforms.add(n_n48_0); // {EXPANDED}
		waveforms.add(n_n52_21); // {EXPANDED}
		waveforms.add(n_n56_19); // {EXPANDED}
		waveforms.add(nc_28); // {EXPANDED}
		waveforms.add(ncount_0__31); // {EXPANDED}
		waveforms.add(ncount_1__11); // {EXPANDED}
		waveforms.add(ncount_2__17); // {EXPANDED}
		waveforms.add(ncount_3__12); // {EXPANDED}
		waveforms.add(nd_30); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(na_4); // {EXPANDED}
		waveforms.add(nb_2); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n_n36_29); // {EXPANDED}
		waveforms.add(n_n40_1); // {EXPANDED}
		waveforms.add(n_n42_13); // {EXPANDED}
		waveforms.add(n_n46_20); // {EXPANDED}
		waveforms.add(n_n50_7); // {EXPANDED}
		waveforms.add(n_n54_22); // {EXPANDED}
		waveforms.add(n_n58_32); // {EXPANDED}
		waveforms.add(n_rst_prop_9); // {EXPANDED}
		waveforms.add(n_set_prop_6); // {EXPANDED}
		waveforms.add(n_00__0__14); // {EXPANDED}
		waveforms.add(n_00__1__5); // {EXPANDED}
		waveforms.add(n_00__2__26); // {EXPANDED}
		waveforms.add(n_00__3__18); // {EXPANDED}
		waveforms.add(n_01__3); // {EXPANDED}
		waveforms.add(n_02__25); // {EXPANDED}
		waveforms.add(n_03__8); // {EXPANDED}
		waveforms.add(n_04__24); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

	private void generateTextFile(ArrayList<String> sigNames, ArrayList<int[]> waveforms, File txtFile)
			throws FileNotFoundException {

		// prepare file content

		ArrayList<String> lines = new ArrayList<String>();

		int maxSigName = 0;

		for (String s : sigNames)
			maxSigName = s.length() > maxSigName ? s.length() : maxSigName;

		String strFmt = String.format("%%%ds : ", maxSigName);

		for (int i = 0; i < sigNames.size(); i++) {

			String l = String.format(strFmt, sigNames.get(i));

			StringBuilder sb = new StringBuilder(l);

			int[] sigWaveform = waveforms.get(i);

			for (int j = 0; j < sigWaveform.length; j++) {

				int v = sigWaveform[j];

				if (v == -1)
					sb.append("1");
				else if (v == 0)
					sb.append("0");
				else
					sb.append("x");

			}

			lines.add(sb.toString());

		}

		// write to file

		System.out
				.println("Saving counter-example waveform data (plain-text) to " + txtFile.getAbsolutePath() + " ...");

		PrintStream fout = new PrintStream(txtFile);

		for (String l : lines)
			fout.println(l);

		fout.close();

	}

}
