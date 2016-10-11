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
		// int inputBitCount = {INPUT_BIT_COUNT};
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

		int currentState = initial;

		//@formatter:off
		// int {STATE_BIT};
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		//@formatter:on

		while (stateStackPtr > 0) {

			currentState = stateStack[stateStackPtr - 1]; // peek

			int currentInputVec = inputVectors[currentState];

			if (currentInputVec < inputCombinationCount) {

				printState(stateStackPtr, String.format("(inpVec = %d) currentState", currentInputVec), currentState);

				// there is at least one more nextState to explore

				// int nextState = graph[currentState][currentInputVec];

				//@formatter:off
				// {STATE_BIT} = -(currentState >> {STATE_BIT_INDEX} & 1);
				//@formatter:on

				//@formatter:off
				// int {INPUT_BIT} = -(currentInputVec >> {INPUT_BIT_INDEX} & 1);
				//@formatter:on

				// increment inputVectors afterwards so that the first input
				// vector is 0

				inputVectors[currentState] = currentInputVec + 1;

				//@formatter:off
				// {COMB_ASSIGN}
				//@formatter:on

				int nextState = 0;

				//@formatter:off
				// nextState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
				//@formatter:on

				all_assumptions = H;
				all_assertions = H;

				// In the code below we logically AND all assumptions
				// and assertions.

				// We don't want any property to evaluate to false if until
				// we're at least {MAXDELAY} away from the initial state. This
				// is {MAXDELAY} is the max depth of flip-flop chains within
				// the property.

				int distance = stateStackPtr - 1;

				//@formatter:off
				// all_assumptions &= {ASSUMPTION} | (stateStackPtr > {MAXDELAY} ? L : H);
				// all_assertions &= {ASSERTION} | (stateStackPtr > {MAXDELAY} ? L : H);
				//@formatter:on

				if (all_assumptions == H && all_assertions == L) {

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

			int[] counter = new int[stateStackPtr+1];

			for (int j = 0; j < stateStackPtr; j++)
				counter[j] = inputVectors[stateStack[j]] - 1;

			counter[stateStackPtr] = inputVectors[currentState] - 1;

			return counter;

		} else {

			return null;

		}

	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT_ORG}");

		// result.add("{INPUT_BIT_ORG}");

		// result.add("{NON_STATE_BIT_ORG}");
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

		// int[] {INPUT_BIT} = new int[cycles];

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);

		// int[] {NON_STATE_BIT} = new int[cycles];
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});

		// waveforms.add({INPUT_BIT});

		// waveforms.add({NON_STATE_BIT});
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
