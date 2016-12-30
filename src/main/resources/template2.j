import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public static void main(String args[]) throws Exception {

		// usage:
		// codesimulator [--txt <file>]

		CodeSimulator sim1 = new CodeSimulator();

		int initial = sim1.getResetState();

		File txtFile = null;

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

	}

	public int[] exploreSpace(int initial) throws Exception {

		int stateBitCount = getStateBitCount();

		int inputBitCount = getInputBitCount();

		final int MAX_SIZE = 1 << stateBitCount;

		final int UNDISCOVERED = -1;

		final int VISITED = -2;

		// note, MSB of inputVector values is reserved

		if (inputBitCount > 31)
			throw new Exception("Number of input bits exceeds 31");

		if (stateBitCount > 29)
			throw new Exception(String.format("Memory requirements exceed 4 GB (state bits = %d)", stateBitCount));

		int stateStack[] = new int[MAX_SIZE];

		int inputVectors[] = new int[MAX_SIZE];

		Arrays.fill(inputVectors, UNDISCOVERED);

		int stateStackPtr = 0; // next available empty slot

		stateStack[stateStackPtr++] = initial; // initial state

		inputVectors[initial] = 0; // initial state

		int assumptions;

		int assertions;

		int any_liveness_armed;

		int currentState = initial;

		boolean livenessViolation = false;

		int statesDiscovered = 1;

		int statesVisited = 0;

		//@formatter:off
		// int {STATE_BIT};
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		//@formatter:on

		long startTime = System.nanoTime();

		search_loop: while (stateStackPtr > 0) {

			currentState = stateStack[stateStackPtr - 1]; // peek

			int currentInputVec = inputVectors[currentState];

			if (currentInputVec < (1 << inputBitCount)) {

				// there is at least one more nextState to explore

				// printState(stateStackPtr, String.format("(inpVec = %d) currentState", currentInputVec), currentState);

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

				assumptions = H;

				// In the code below we logically AND all assumptions
				// and assertions.

				// We don't want any property to evaluate to false if until
				// we're at least {MAXDELAY} away from the initial state. This
				// is {MAXDELAY} is the max depth of flip-flop chains within
				// the property.

				int distance = stateStackPtr - 1;

				//@formatter:off
				// if (distance >= {MAXDELAY}) assumptions &= {ASSUMPTION};
				//@formatter:on

				if (assumptions == H) {

					assertions = H;

					//@formatter:off
					// if (distance >= {MAXDELAY}) assertions &= {ASSERTION};
					//@formatter:on

					if (assertions == L) {

						// TODO: expand this stub

						System.out.println("violation of (non-liveness) property");

						break;

					}

					if (inputVectors[nextState] == UNDISCOVERED) {

						// push to stack

						statesDiscovered++;

						stateStack[stateStackPtr++] = nextState;

						inputVectors[nextState] = 0;

					} else if (inputVectors[nextState] != VISITED) {

						// found a cycle in the state graph

						any_liveness_armed = L;

						// The expression LIVE_ASSERTION indicates whether the
						// assertion is in an armed state. Violations are reported
						// if state graph loops are discovered where the assertion
						// is armed. For example, the property $eventually(a, y)
						// will get armed once the trigger `a` is asserted and
						// before the release expression `y` is asserted. The
						// existence of a loop in which this assertion is
						// triggered means that there is a case in which a is
						// asserted but y never gets asserted eventually, i.e. a
						// violation of the property.

						// TODO: add code to handle delays in properties (ignored
						// for now)

						//@formatter:off
						// any_liveness_armed |= {LIVE_ASSERTION};
						//@formatter:on

						if (any_liveness_armed == H) {

							System.out.println("found violation of live assertion:");

							// for (int i = 0; i < stateStackPtr; i++)
							// 	printState(stateStackPtr + 1, "State", stateStack[i]);

							// printState(stateStackPtr + 1, "State", nextState);

							livenessViolation = true;

							break;

						}

					}

				}

			} else {

				// explored all input vectors of currentState

				// currentState is now marked as visited

				statesVisited++;

				inputVectors[currentState] = VISITED;

				stateStackPtr--; // pop

			}

		}

		long endTime = System.nanoTime();

		double searchTime = (endTime - startTime) / 1e9;

		System.out.printf("Completed search in %f seconds\n", searchTime);

		System.out.printf("State bits                     : %d\n", getStateBitCount());

		System.out.printf("Input bits                     : %d\n", getInputBitCount());

		System.out.printf("States visited                 : %d\n", statesVisited);

		System.out.printf("States discovered              : %d\n", statesDiscovered);

		System.out.printf("State stack                    : %s\n", getByteSize(4 * ((long) MAX_SIZE)));

		System.out.printf("State LUT                      : %s\n", getByteSize(4 * ((long) MAX_SIZE)));

		if (stateStackPtr != 0) {

			int counterExampleCycles = livenessViolation ? stateStackPtr+1 : stateStackPtr;

			int[] counter = new int[counterExampleCycles];

			for (int j = 0; j < stateStackPtr; j++)
				counter[j] = inputVectors[stateStack[j]] - 1;

			if (livenessViolation)
				counter[stateStackPtr] = inputVectors[currentState] - 1;

			return counter;

		} else {

			System.out.println("Assertion proven, no counter-examples were found.");

			return null;

		}

	}

	public List<String> getSignalNames() {

		String[] signalNames = {

			// "{STATE_BIT_ORG}",

			// "{INPUT_BIT_ORG}",

			// "{NON_STATE_BIT_ORG}",

		};

		return Arrays.asList(signalNames);

	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		//@formatter:on
	}

	public String getByteSize(long bytes) {

		if (bytes < 1024) {

			return String.format("%d bytes", bytes);

		} else if (bytes < 1024 * 1024) {

			return String.format("%1.2f KB", (float) bytes / 1024);

		} else if (bytes < 1024 * 1024 * 1024) {

			return String.format("%1.2f MB", (float) bytes / 1024 / 1024);

		} else {

			return String.format("%1.2f GB", (float) bytes / 1024 / 1024 / 1024);

		}

	}

	public void simulate(int initial, int[] inputs, File txtFile) throws Exception {

		List<String> sigNames = getSignalNames();

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

	private void generateTextFile(List<String> sigNames, ArrayList<int[]> waveforms, File txtFile)
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
