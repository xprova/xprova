import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class CodeSimulatorDFS {

	public static final int L = 0;
	public static final int H = -1;

	public static void main(String args[]) throws Exception {

		// usage:
		// codesimulator [--txt <file>]

		CodeSimulatorDFS sim1 = new CodeSimulatorDFS();

		int initial = 0; // TODO

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

	public int[] exploreSpace(int initial) {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 5; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 1; // {EXPANDED}
		//@formatter:on

		final int MAX_SIZE = 1 << stateBitCount;

		final int UNDISCOVERED = -1;

		final int VISITED = -2;

		final int inputCombinationCount = 1 << inputBitCount;

		int stateStack[] = new int[MAX_SIZE];

		int inputVectors[] = new int[MAX_SIZE];

		Arrays.fill(inputVectors, UNDISCOVERED);

		int stateStackPtr = 0; // next available empty slot

		stateStack[stateStackPtr++] = initial; // initial state

		inputVectors[initial] = 0; // initial state

		int all_assumptions;
		int all_assertions;
		int all_live_assertions;

		//@formatter:off
		// int {STATE_BIT};
		int n_n52_21; // {EXPANDED}
		int n_n53_29; // {EXPANDED}
		int nstate_0__28; // {EXPANDED}
		int nstate_1__17; // {EXPANDED}
		int nstate_2__24; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n_n54_31; // {EXPANDED}
		int n_n55_32; // {EXPANDED}
		int n_n56_25; // {EXPANDED}
		int n_n59_11; // {EXPANDED}
		int n_00__0__8; // {EXPANDED}
		int n_00__1__12; // {EXPANDED}
		int n_00__2__23; // {EXPANDED}
		int n_01__14; // {EXPANDED}
		int n_02__2; // {EXPANDED}
		int n_03__4; // {EXPANDED}
		int n_04__18; // {EXPANDED}
		int n_05__13; // {EXPANDED}
		int n_06__26; // {EXPANDED}
		int n_07__19; // {EXPANDED}
		int n_08__9; // {EXPANDED}
		int n_09__20; // {EXPANDED}
		int n_10__27; // {EXPANDED}
		int n_11__15; // {EXPANDED}
		int n_12__1; // {EXPANDED}
		int n_13__16; // {EXPANDED}
		int n_14__3; // {EXPANDED}
		int n_15__22; // {EXPANDED}
		//@formatter:on

		while (stateStackPtr > 0) {

			int currentState = stateStack[stateStackPtr - 1]; // peek

			int currentInputVec = inputVectors[currentState];

			if (currentInputVec < inputCombinationCount) {

				printState(stateStackPtr, String.format("(inpVec = %d) currentState", currentInputVec), currentState);

				// there is at least one more nextState to explore

				// int nextState = graph[currentState][currentInputVec];

				//@formatter:off
				// {STATE_BIT} = -(currentState >> {STATE_BIT_INDEX} & 1);
				n_n52_21 = -(currentState >> 0 & 1); // {EXPANDED}
				n_n53_29 = -(currentState >> 1 & 1); // {EXPANDED}
				nstate_0__28 = -(currentState >> 2 & 1); // {EXPANDED}
				nstate_1__17 = -(currentState >> 3 & 1); // {EXPANDED}
				nstate_2__24 = -(currentState >> 4 & 1); // {EXPANDED}
				//@formatter:on

				//@formatter:off
				// int {INPUT_BIT} = -(currentInputVec >> {INPUT_BIT_INDEX} & 1);
				int na_10 = -(currentInputVec >> 0 & 1); // {EXPANDED}
				//@formatter:on

				// increment inputVectors afterwards so that the first input
				// vector is 0

				inputVectors[currentState] = currentInputVec + 1;

				//@formatter:off
				// {COMB_ASSIGN}
				n_15__22 = 0; // {EXPANDED}
				n_14__3 = ~nstate_1__17; // {EXPANDED}
				n_03__4 = ~(na_10 & n_14__3); // {EXPANDED}
				n_08__9 = nstate_1__17 | na_10; // {EXPANDED}
				n_n56_25 = ~n_n53_29; // {EXPANDED}
				n_n59_11 = n_n56_25 & n_n52_21; // {EXPANDED}
				n_n55_32 = nstate_2__24 | n_n53_29; // {EXPANDED}
				n_n54_31 = na_10 | n_n52_21; // {EXPANDED}
				n_01__14 = ~(n_14__3 & nstate_0__28); // {EXPANDED}
				n_13__16 = ~nstate_2__24; // {EXPANDED}
				n_06__26 = ~(nstate_0__28 & n_13__16); // {EXPANDED}
				n_04__18 = n_14__3 | nstate_0__28; // {EXPANDED}
				n_02__2 = ~(n_01__14 | n_13__16); // {EXPANDED}
				n_10__27 = ~(nstate_2__24 & n_01__14); // {EXPANDED}
				n_09__20 = ~(n_08__9 & n_01__14); // {EXPANDED}
				n_05__13 = ~(n_04__18 & n_03__4); // {EXPANDED}
				n_11__15 = n_06__26 | n_03__4; // {EXPANDED}
				n_00__0__8 = ~(n_02__2 | n_05__13); // {EXPANDED}
				n_12__1 = n_04__18 & n_11__15; // {EXPANDED}
				n_07__19 = n_06__26 | na_10; // {EXPANDED}
				n_00__1__12 = ~(n_07__19 & n_09__20); // {EXPANDED}
				n_00__2__23 = ~(n_12__1 & n_10__27); // {EXPANDED}
				//@formatter:on

				int nextState = 0;

				//@formatter:off
				// nextState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
				nextState |= n_n54_31 & (1 << 0); // {EXPANDED}
				nextState |= n_n55_32 & (1 << 1); // {EXPANDED}
				nextState |= n_00__0__8 & (1 << 2); // {EXPANDED}
				nextState |= n_00__1__12 & (1 << 3); // {EXPANDED}
				nextState |= n_00__2__23 & (1 << 4); // {EXPANDED}
				//@formatter:on

				all_assumptions = -1;
				all_assertions = -1;

				// In the code below we logically AND all assumptions
				// and assertions.

				// We don't want any property to evaluate to false if until
				// we're at least {MAXDELAY} away from the initial state. This
				// is {MAXDELAY} is the max depth of flip-flop chains within
				// the property.

				//@formatter:off
				// all_assumptions &= {ASSUMPTION} | (distance > {MAXDELAY} ? 0 : -1);
				// all_assertions &= {ASSERTION} | (distance > {MAXDELAY} ? 0 : -1);
				//@formatter:on

				if (all_assumptions == -1 && all_assertions == 0) {

					// TODO: expand this stub

					System.out.println("violation of (non-liveness) property");

					break;

				}

				if (inputVectors[nextState] == UNDISCOVERED) {

					// push to stack

					stateStack[stateStackPtr++] = nextState;

					inputVectors[nextState] = 0;

				} else if (inputVectors[nextState] != VISITED) {

					// found a cycle

					all_live_assertions = -1;

					// In the code below we logically AND all live assertions

					// TODO: add code to handle delays in properties (ignored
					// for now)

					//@formatter:off
					// all_live_assertions &= {LIVE_ASSERTION};
					all_live_assertions &= n_n59_11; // {EXPANDED}
					//@formatter:on

					if (all_live_assertions == 0) {

						System.out.println("found violation of live assertion:");

						for (int i = 0; i < stateStackPtr; i++)
							printState(stateStackPtr + 1, "State", stateStack[i]);

						printState(stateStackPtr + 1, "State", nextState);

					}

					break;

				}

			} else {

				// explored all input vectors of currentState

				// currentState is now marked as visited

				inputVectors[currentState] = VISITED;

				stateStackPtr--; // pop

			}

		}

		if (stateStackPtr != 0) {

			int[] counter = new int[stateStackPtr];

			for (int j = 0; j < stateStackPtr; j++)
				counter[j] = inputVectors[stateStack[j]];

			return counter;

		} else {

			return null;

		}

	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT_ORG}");
		result.add("*n52"); // {EXPANDED}
		result.add("*n53"); // {EXPANDED}
		result.add("state[0]"); // {EXPANDED}
		result.add("state[1]"); // {EXPANDED}
		result.add("state[2]"); // {EXPANDED}

		// result.add("{INPUT_BIT_ORG}");
		result.add("a"); // {EXPANDED}

		// result.add("{NON_STATE_BIT_ORG}");
		result.add("*n54"); // {EXPANDED}
		result.add("*n55"); // {EXPANDED}
		result.add("*n56"); // {EXPANDED}
		result.add("*n59"); // {EXPANDED}
		result.add("_00_[0]"); // {EXPANDED}
		result.add("_00_[1]"); // {EXPANDED}
		result.add("_00_[2]"); // {EXPANDED}
		result.add("_01_"); // {EXPANDED}
		result.add("_02_"); // {EXPANDED}
		result.add("_03_"); // {EXPANDED}
		result.add("_04_"); // {EXPANDED}
		result.add("_05_"); // {EXPANDED}
		result.add("_06_"); // {EXPANDED}
		result.add("_07_"); // {EXPANDED}
		result.add("_08_"); // {EXPANDED}
		result.add("_09_"); // {EXPANDED}
		result.add("_10_"); // {EXPANDED}
		result.add("_11_"); // {EXPANDED}
		result.add("_12_"); // {EXPANDED}
		result.add("_13_"); // {EXPANDED}
		result.add("_14_"); // {EXPANDED}
		result.add("_15_"); // {EXPANDED}
		//@formatter:on

		return result;
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
		int[] n_n52_21 = new int[cycles]; // {EXPANDED}
		int[] n_n53_29 = new int[cycles]; // {EXPANDED}
		int[] nstate_0__28 = new int[cycles]; // {EXPANDED}
		int[] nstate_1__17 = new int[cycles]; // {EXPANDED}
		int[] nstate_2__24 = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] na_10 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		n_n52_21[0] = -(initial >> 0 & 1); // {EXPANDED}
		n_n53_29[0] = -(initial >> 1 & 1); // {EXPANDED}
		nstate_0__28[0] = -(initial >> 2 & 1); // {EXPANDED}
		nstate_1__17[0] = -(initial >> 3 & 1); // {EXPANDED}
		nstate_2__24[0] = -(initial >> 4 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] n_n54_31 = new int[cycles]; // {EXPANDED}
		int[] n_n55_32 = new int[cycles]; // {EXPANDED}
		int[] n_n56_25 = new int[cycles]; // {EXPANDED}
		int[] n_n59_11 = new int[cycles]; // {EXPANDED}
		int[] n_00__0__8 = new int[cycles]; // {EXPANDED}
		int[] n_00__1__12 = new int[cycles]; // {EXPANDED}
		int[] n_00__2__23 = new int[cycles]; // {EXPANDED}
		int[] n_01__14 = new int[cycles]; // {EXPANDED}
		int[] n_02__2 = new int[cycles]; // {EXPANDED}
		int[] n_03__4 = new int[cycles]; // {EXPANDED}
		int[] n_04__18 = new int[cycles]; // {EXPANDED}
		int[] n_05__13 = new int[cycles]; // {EXPANDED}
		int[] n_06__26 = new int[cycles]; // {EXPANDED}
		int[] n_07__19 = new int[cycles]; // {EXPANDED}
		int[] n_08__9 = new int[cycles]; // {EXPANDED}
		int[] n_09__20 = new int[cycles]; // {EXPANDED}
		int[] n_10__27 = new int[cycles]; // {EXPANDED}
		int[] n_11__15 = new int[cycles]; // {EXPANDED}
		int[] n_12__1 = new int[cycles]; // {EXPANDED}
		int[] n_13__16 = new int[cycles]; // {EXPANDED}
		int[] n_14__3 = new int[cycles]; // {EXPANDED}
		int[] n_15__22 = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			na_10[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n_15__22[i] = 0; // {EXPANDED}
			n_14__3[i] = ~nstate_1__17[i]; // {EXPANDED}
			n_03__4[i] = ~(na_10[i] & n_14__3[i]); // {EXPANDED}
			n_08__9[i] = nstate_1__17[i] | na_10[i]; // {EXPANDED}
			n_n56_25[i] = ~n_n53_29[i]; // {EXPANDED}
			n_n59_11[i] = n_n56_25[i] & n_n52_21[i]; // {EXPANDED}
			n_n55_32[i] = nstate_2__24[i] | n_n53_29[i]; // {EXPANDED}
			n_n54_31[i] = na_10[i] | n_n52_21[i]; // {EXPANDED}
			n_01__14[i] = ~(n_14__3[i] & nstate_0__28[i]); // {EXPANDED}
			n_13__16[i] = ~nstate_2__24[i]; // {EXPANDED}
			n_06__26[i] = ~(nstate_0__28[i] & n_13__16[i]); // {EXPANDED}
			n_04__18[i] = n_14__3[i] | nstate_0__28[i]; // {EXPANDED}
			n_02__2[i] = ~(n_01__14[i] | n_13__16[i]); // {EXPANDED}
			n_10__27[i] = ~(nstate_2__24[i] & n_01__14[i]); // {EXPANDED}
			n_09__20[i] = ~(n_08__9[i] & n_01__14[i]); // {EXPANDED}
			n_05__13[i] = ~(n_04__18[i] & n_03__4[i]); // {EXPANDED}
			n_11__15[i] = n_06__26[i] | n_03__4[i]; // {EXPANDED}
			n_00__0__8[i] = ~(n_02__2[i] | n_05__13[i]); // {EXPANDED}
			n_12__1[i] = n_04__18[i] & n_11__15[i]; // {EXPANDED}
			n_07__19[i] = n_06__26[i] | na_10[i]; // {EXPANDED}
			n_00__1__12[i] = ~(n_07__19[i] & n_09__20[i]); // {EXPANDED}
			n_00__2__23[i] = ~(n_12__1[i] & n_10__27[i]); // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				n_n52_21[i+1] |= n_n54_31[i]; // {EXPANDED}
				n_n53_29[i+1] |= n_n55_32[i]; // {EXPANDED}
				nstate_0__28[i+1] |= n_00__0__8[i]; // {EXPANDED}
				nstate_1__17[i+1] |= n_00__1__12[i]; // {EXPANDED}
				nstate_2__24[i+1] |= n_00__2__23[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(n_n52_21); // {EXPANDED}
		waveforms.add(n_n53_29); // {EXPANDED}
		waveforms.add(nstate_0__28); // {EXPANDED}
		waveforms.add(nstate_1__17); // {EXPANDED}
		waveforms.add(nstate_2__24); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(na_10); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n_n54_31); // {EXPANDED}
		waveforms.add(n_n55_32); // {EXPANDED}
		waveforms.add(n_n56_25); // {EXPANDED}
		waveforms.add(n_n59_11); // {EXPANDED}
		waveforms.add(n_00__0__8); // {EXPANDED}
		waveforms.add(n_00__1__12); // {EXPANDED}
		waveforms.add(n_00__2__23); // {EXPANDED}
		waveforms.add(n_01__14); // {EXPANDED}
		waveforms.add(n_02__2); // {EXPANDED}
		waveforms.add(n_03__4); // {EXPANDED}
		waveforms.add(n_04__18); // {EXPANDED}
		waveforms.add(n_05__13); // {EXPANDED}
		waveforms.add(n_06__26); // {EXPANDED}
		waveforms.add(n_07__19); // {EXPANDED}
		waveforms.add(n_08__9); // {EXPANDED}
		waveforms.add(n_09__20); // {EXPANDED}
		waveforms.add(n_10__27); // {EXPANDED}
		waveforms.add(n_11__15); // {EXPANDED}
		waveforms.add(n_12__1); // {EXPANDED}
		waveforms.add(n_13__16); // {EXPANDED}
		waveforms.add(n_14__3); // {EXPANDED}
		waveforms.add(n_15__22); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	static void printState(int depth, String label, int state) {

		for (int j = 0; j < depth - 1; j++)
			System.out.printf("  ");

		System.out.println(label + ": " + state + " (" + (state >> 2) + ")");
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
