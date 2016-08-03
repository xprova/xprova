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
		return 0x0; // {EXPANDED}

	}

	@SuppressWarnings("unused")
	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		final int STATE_BUF_SIZE = 1 << 1; // {EXPANDED}
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 1; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 2; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int nn9_6 = -(initial >> 0 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int nn4_4; // {EXPANDED}
		int nn6_0; // {EXPANDED}
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
				nn9_6 = -(state >> 0 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int na_2 = -(in >> 0 & 1); // {EXPANDED}
					int nc_3 = -(in >> 1 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					nn6_0 = nc_3 & nn9_6; // {EXPANDED}
					nn4_4 = ~nn6_0; // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= na_2 & (1 << 0); // {EXPANDED}
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
					all_assertions &= nn4_4; // {EXPANDED}

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
		result.add("n9"); // {EXPANDED}

		// result.add("{INPUT_BIT_ORG}");
		result.add("a"); // {EXPANDED}
		result.add("c"); // {EXPANDED}

		// result.add("{NON_STATE_BIT_ORG}");
		result.add("n4"); // {EXPANDED}
		result.add("n6"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 1; // {EXPANDED}
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
		int[] nn9_6 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] na_2 = new int[cycles]; // {EXPANDED}
		int[] nc_3 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		nn9_6[0] = -(initial >> 0 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] nn4_4 = new int[cycles]; // {EXPANDED}
		int[] nn6_0 = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			na_2[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			nc_3[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			nn6_0[i] = nc_3[i] & nn9_6[i]; // {EXPANDED}
			nn4_4[i] = ~nn6_0[i]; // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				nn9_6[i+1] |= na_2[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(nn9_6); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(na_2); // {EXPANDED}
		waveforms.add(nc_3); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(nn4_4); // {EXPANDED}
		waveforms.add(nn6_0); // {EXPANDED}
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
