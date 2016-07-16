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

		CodeSimulator sim1 = new CodeSimulator();

		int initial = sim1.getResetState();

		boolean generateCounterExample = true;

		System.out.println(generateCounterExample);

		int[] counterExample = sim1.exploreSpace(initial);

		if (counterExample != null && generateCounterExample)
			sim1.simulate(initial, counterExample);

	}

	public int getResetState() {

		// return {RESET_STATE};
		return 0x0; // {EXPANDED}

	}

	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		final int STATE_BUF_SIZE = 1 << 9; // {EXPANDED}
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 9; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 4; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int n_1_x_19 = -(initial >> 0 & 1); // {EXPANDED}
		int n_2_x_10 = -(initial >> 1 & 1); // {EXPANDED}
		int nff2_isT_25 = -(initial >> 2 & 1); // {EXPANDED}
		int nff3_isM_13 = -(initial >> 3 & 1); // {EXPANDED}
		int nn1_17 = -(initial >> 4 & 1); // {EXPANDED}
		int nn2_14 = -(initial >> 5 & 1); // {EXPANDED}
		int nn3_23 = -(initial >> 6 & 1); // {EXPANDED}
		int nn4_18 = -(initial >> 7 & 1); // {EXPANDED}
		int ny_16 = -(initial >> 8 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int nff2_inpT_11; // {EXPANDED}
		int nff2_x_20; // {EXPANDED}
		int nff3_inpD_1; // {EXPANDED}
		int nff3_inpM_9; // {EXPANDED}
		int nff3_isV_4; // {EXPANDED}
		int nff3_x_6; // {EXPANDED}
		int nff4_inpD_5; // {EXPANDED}
		int nff4_isV_12; // {EXPANDED}
		int nn2_dup1_24; // {EXPANDED}
		int nn3_dup1_3; // {EXPANDED}
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
				n_1_x_19 = -(state >> 0 & 1); // {EXPANDED}
				n_2_x_10 = -(state >> 1 & 1); // {EXPANDED}
				nff2_isT_25 = -(state >> 2 & 1); // {EXPANDED}
				nff3_isM_13 = -(state >> 3 & 1); // {EXPANDED}
				nn1_17 = -(state >> 4 & 1); // {EXPANDED}
				nn2_14 = -(state >> 5 & 1); // {EXPANDED}
				nn3_23 = -(state >> 6 & 1); // {EXPANDED}
				nn4_18 = -(state >> 7 & 1); // {EXPANDED}
				ny_16 = -(state >> 8 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int nr_0__15 = -(in >> 0 & 1); // {EXPANDED}
					int nr_1__22 = -(in >> 1 & 1); // {EXPANDED}
					int nr_2__0 = -(in >> 2 & 1); // {EXPANDED}
					int nx_7 = -(in >> 3 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					nff2_x_20 = 0xf0f0f0f0; // {EXPANDED}
					nff3_x_6 = 0xf0f0f0f0; // {EXPANDED}
					nn3_dup1_3 = (nn3_23 & ~nff3_isM_13) | (nff3_x_6 & nff3_isM_13); // {EXPANDED}
					nn2_dup1_24 = (nn2_14 & ~nff2_isT_25) | (nff2_x_20 & nff2_isT_25); // {EXPANDED}
					nff2_inpT_11 = (nn2_14 ^ nn1_17); // {EXPANDED}
					nff4_isV_12 = ((nn3_dup1_3 != 0) & (nn3_dup1_3 != -1)) ? -1 : 0 ; // {EXPANDED}
					nff3_isV_4 = ((nn2_dup1_24 != 0) & (nn2_dup1_24 != -1)) ? -1 : 0 ; // {EXPANDED}
					nff4_inpD_5 = (nn3_23 & ~nff4_isV_12) | (nr_2__0 & nff4_isV_12); // {EXPANDED}
					nff3_inpD_1 = (nn2_14 & ~nff3_isV_4) | (nr_1__22 & nff3_isV_4); // {EXPANDED}
					nff3_inpM_9 = nff3_isV_4 & nr_0__15; // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= nx_7 & (1 << 0); // {EXPANDED}
					nxState |= n_1_x_19 & (1 << 1); // {EXPANDED}
					nxState |= nff2_inpT_11 & (1 << 2); // {EXPANDED}
					nxState |= nff3_inpM_9 & (1 << 3); // {EXPANDED}
					nxState |= nx_7 & (1 << 4); // {EXPANDED}
					nxState |= nn1_17 & (1 << 5); // {EXPANDED}
					nxState |= nff3_inpD_1 & (1 << 6); // {EXPANDED}
					nxState |= nff4_inpD_5 & (1 << 7); // {EXPANDED}
					nxState |= nn4_18 & (1 << 8); // {EXPANDED}
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
					// all_assertions &= {ASSERTION};
					all_assertions &= (~n_2_x_10 | (~ny_16)); // {EXPANDED}

					if (all_assumptions == -1 && all_assertions == 0) {

						violationState = state;

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
		// result.add("{STATE_BIT_ORG}");
		result.add("@1 x"); // {EXPANDED}
		result.add("@2 x"); // {EXPANDED}
		result.add("ff2_isT"); // {EXPANDED}
		result.add("ff3_isM"); // {EXPANDED}
		result.add("n1"); // {EXPANDED}
		result.add("n2"); // {EXPANDED}
		result.add("n3"); // {EXPANDED}
		result.add("n4"); // {EXPANDED}
		result.add("y"); // {EXPANDED}

		// result.add("{INPUT_BIT_ORG}");
		result.add("r[0]"); // {EXPANDED}
		result.add("r[1]"); // {EXPANDED}
		result.add("r[2]"); // {EXPANDED}
		result.add("x"); // {EXPANDED}

		// result.add("{NON_STATE_BIT_ORG}");
		result.add("ff2_inpT"); // {EXPANDED}
		result.add("ff2_x"); // {EXPANDED}
		result.add("ff3_inpD"); // {EXPANDED}
		result.add("ff3_inpM"); // {EXPANDED}
		result.add("ff3_isV"); // {EXPANDED}
		result.add("ff3_x"); // {EXPANDED}
		result.add("ff4_inpD"); // {EXPANDED}
		result.add("ff4_isV"); // {EXPANDED}
		result.add("n2_dup1"); // {EXPANDED}
		result.add("n3_dup1"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 9; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 4; // {EXPANDED}
		//@formatter:on
	}

	public void simulate(int initial, int[] inputs) throws FileNotFoundException {

		ArrayList<String> sigNames = getSignalNames();

		int cycles = inputs.length;

		// determine longest signal name
		// (for pretty printing)

		int maxL = 0;

		for (String s : sigNames)
			maxL = s.length() > maxL ? s.length() : maxL;

		String strFmt = String.format("%%%ds : ", maxL + 2);

		// run simulation

		ArrayList<int[]> waveforms = simulate_internal(initial, inputs);

		System.out.printf(strFmt, "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		System.out.println();

		System.out.println();

		for (int j = 0; j < waveforms.size(); j++) {

			if (j == getStateBitCount())
				System.out.println();

			int[] sig = waveforms.get(j);

			System.out.printf(strFmt, sigNames.get(j));

			for (int i = 0; i < cycles; i++) {

				if (sig[i] == H)
					System.out.printf("1");
				else if (sig[i] == L)
					System.out.printf("0");
				else
					System.out.printf("X");

			}

			System.out.println();

		}

		generateVCD(sigNames, waveforms);

	}

	private ArrayList<int[]> simulate_internal(int initial, int[] inputs) {

		int cycles = inputs.length;

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] n_1_x_19 = new int[cycles]; // {EXPANDED}
		int[] n_2_x_10 = new int[cycles]; // {EXPANDED}
		int[] nff2_isT_25 = new int[cycles]; // {EXPANDED}
		int[] nff3_isM_13 = new int[cycles]; // {EXPANDED}
		int[] nn1_17 = new int[cycles]; // {EXPANDED}
		int[] nn2_14 = new int[cycles]; // {EXPANDED}
		int[] nn3_23 = new int[cycles]; // {EXPANDED}
		int[] nn4_18 = new int[cycles]; // {EXPANDED}
		int[] ny_16 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] nr_0__15 = new int[cycles]; // {EXPANDED}
		int[] nr_1__22 = new int[cycles]; // {EXPANDED}
		int[] nr_2__0 = new int[cycles]; // {EXPANDED}
		int[] nx_7 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		n_1_x_19[0] = -(initial >> 0 & 1); // {EXPANDED}
		n_2_x_10[0] = -(initial >> 1 & 1); // {EXPANDED}
		nff2_isT_25[0] = -(initial >> 2 & 1); // {EXPANDED}
		nff3_isM_13[0] = -(initial >> 3 & 1); // {EXPANDED}
		nn1_17[0] = -(initial >> 4 & 1); // {EXPANDED}
		nn2_14[0] = -(initial >> 5 & 1); // {EXPANDED}
		nn3_23[0] = -(initial >> 6 & 1); // {EXPANDED}
		nn4_18[0] = -(initial >> 7 & 1); // {EXPANDED}
		ny_16[0] = -(initial >> 8 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] nff2_inpT_11 = new int[cycles]; // {EXPANDED}
		int[] nff2_x_20 = new int[cycles]; // {EXPANDED}
		int[] nff3_inpD_1 = new int[cycles]; // {EXPANDED}
		int[] nff3_inpM_9 = new int[cycles]; // {EXPANDED}
		int[] nff3_isV_4 = new int[cycles]; // {EXPANDED}
		int[] nff3_x_6 = new int[cycles]; // {EXPANDED}
		int[] nff4_inpD_5 = new int[cycles]; // {EXPANDED}
		int[] nff4_isV_12 = new int[cycles]; // {EXPANDED}
		int[] nn2_dup1_24 = new int[cycles]; // {EXPANDED}
		int[] nn3_dup1_3 = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			nr_0__15[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			nr_1__22[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}
			nr_2__0[i] = -(inputs[i] >> 2 & 1); // {EXPANDED}
			nx_7[i] = -(inputs[i] >> 3 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			nff2_x_20[i] = 0xf0f0f0f0; // {EXPANDED}
			nff3_x_6[i] = 0xf0f0f0f0; // {EXPANDED}
			nn3_dup1_3[i] = (nn3_23[i] & ~nff3_isM_13[i]) | (nff3_x_6[i] & nff3_isM_13[i]); // {EXPANDED}
			nn2_dup1_24[i] = (nn2_14[i] & ~nff2_isT_25[i]) | (nff2_x_20[i] & nff2_isT_25[i]); // {EXPANDED}
			nff2_inpT_11[i] = (nn2_14[i] ^ nn1_17[i]); // {EXPANDED}
			nff4_isV_12[i] = ((nn3_dup1_3[i] != 0) & (nn3_dup1_3[i] != -1)) ? -1 : 0 ; // {EXPANDED}
			nff3_isV_4[i] = ((nn2_dup1_24[i] != 0) & (nn2_dup1_24[i] != -1)) ? -1 : 0 ; // {EXPANDED}
			nff4_inpD_5[i] = (nn3_23[i] & ~nff4_isV_12[i]) | (nr_2__0[i] & nff4_isV_12[i]); // {EXPANDED}
			nff3_inpD_1[i] = (nn2_14[i] & ~nff3_isV_4[i]) | (nr_1__22[i] & nff3_isV_4[i]); // {EXPANDED}
			nff3_inpM_9[i] = nff3_isV_4[i] & nr_0__15[i]; // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				n_1_x_19[i+1] |= nx_7[i]; // {EXPANDED}
				n_2_x_10[i+1] |= n_1_x_19[i]; // {EXPANDED}
				nff2_isT_25[i+1] |= nff2_inpT_11[i]; // {EXPANDED}
				nff3_isM_13[i+1] |= nff3_inpM_9[i]; // {EXPANDED}
				nn1_17[i+1] |= nx_7[i]; // {EXPANDED}
				nn2_14[i+1] |= nn1_17[i]; // {EXPANDED}
				nn3_23[i+1] |= nff3_inpD_1[i]; // {EXPANDED}
				nn4_18[i+1] |= nff4_inpD_5[i]; // {EXPANDED}
				ny_16[i+1] |= nn4_18[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(n_1_x_19); // {EXPANDED}
		waveforms.add(n_2_x_10); // {EXPANDED}
		waveforms.add(nff2_isT_25); // {EXPANDED}
		waveforms.add(nff3_isM_13); // {EXPANDED}
		waveforms.add(nn1_17); // {EXPANDED}
		waveforms.add(nn2_14); // {EXPANDED}
		waveforms.add(nn3_23); // {EXPANDED}
		waveforms.add(nn4_18); // {EXPANDED}
		waveforms.add(ny_16); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(nr_0__15); // {EXPANDED}
		waveforms.add(nr_1__22); // {EXPANDED}
		waveforms.add(nr_2__0); // {EXPANDED}
		waveforms.add(nx_7); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(nff2_inpT_11); // {EXPANDED}
		waveforms.add(nff2_x_20); // {EXPANDED}
		waveforms.add(nff3_inpD_1); // {EXPANDED}
		waveforms.add(nff3_inpM_9); // {EXPANDED}
		waveforms.add(nff3_isV_4); // {EXPANDED}
		waveforms.add(nff3_x_6); // {EXPANDED}
		waveforms.add(nff4_inpD_5); // {EXPANDED}
		waveforms.add(nff4_isV_12); // {EXPANDED}
		waveforms.add(nn2_dup1_24); // {EXPANDED}
		waveforms.add(nn3_dup1_3); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

	private void generateVCD(ArrayList<String> sigNames, ArrayList<int[]> waveforms) throws FileNotFoundException {

		ArrayList<String> lines = new ArrayList<String>();

		PrintStream out = System.out;

		lines.add("$timescale 1ns $end");

		lines.add("$scope module logic $end");

		for (int i = 0; i < sigNames.size(); i++)
			lines.add(String.format("$var wire 1 %s %s $end", (char) ('a' + i), sigNames.get(i).replace(" ", "-")));

		lines.add("$upscope $end");

		lines.add("$enddefinitions $end");

		lines.add("$dumpvars");

		for (int i = 0; i < sigNames.size(); i++)
			lines.add(String.format("x%s\n", (char) ('a' + i)));

		lines.add("$end");

		int cycles = waveforms.get(0).length;

		for (int j = 0; j < cycles; j++) {

			lines.add(String.format("#%d", j));

			for (int i = 0; i < sigNames.size(); i++) {

				int newVal = waveforms.get(i)[j];

				int oldVal = j > 0 ? waveforms.get(i)[j - 1] : newVal + 1;

				if (newVal != oldVal) {

					lines.add(String.format("%d%s", newVal == -1 ? 1 : 0, (char) ('a' + i)));

				}

			}

		}

		lines.add(String.format("#%d", cycles));
		lines.add(String.format("#%d", cycles + 1));

		// writing to file

//		File tempDir = new File(System.getProperty("java.io.tmpdir"));

		File tempDir = new File("C:\\gtkwave-3.3.71-bin-win32\\gtkwave\\bin");

		File javaFile = new File(tempDir, "counter.vcd");

		out.println("Saving code to " + javaFile.getAbsolutePath() + " ...");

		PrintStream fout = new PrintStream(javaFile);

		for (String l : lines)
			fout.println(l);

		fout.close();

		// write tcl script for gtkwave:

//		set clk48 [list]
//		lappend clk48 "n1"
//		lappend clk48 "n2"
//		set num_added [ gtkwave::addSignalsFromList $clk48 ]

		//  ./gtkwave.exe counter.vcd -S test.tcl



	}

}
