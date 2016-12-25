import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final long L = 0;
	public static final long H = -1;

	public static void main(String args[]) throws Exception {

		// usage:
		// codesimulator [--txt <file>]

		CodeSimulator sim1 = new CodeSimulator();

		long initial = sim1.getResetState();

		File  txtFile = null;

		for (int i = 0; i < args.length; i++) {

			String a = args[i];

			if ("--txt".equals(a))
				txtFile = new File(args[i + 1]);

		}

		long[] counterExample = sim1.exploreSpace(initial);

		if (counterExample != null) {

			sim1.simulate(initial, counterExample, txtFile);

			// 100 is a special return code for finding a counter-example but
			// terminating successfully

			System.exit(100);

		}

	}

	public long getResetState() {

		// return {RESET_STATE};

	}

	@SuppressWarnings("unused")
	public long[] exploreSpace(long initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = STATE_BUF_SIZE;

		boolean printStateList = false;

		final long UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		// int inputBitCount = {INPUT_BIT_COUNT};
		//@formatter:on

		//@formatter:off
		// long {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		//@formatter:on

		//@formatter:off
		// long {NON_STATE_BIT};
		//@formatter:on

		long[] toVisitArr = new long[1];
		toVisitArr[0] = initial;
		long toVisitArrOccupied = 1;

		int distance = 0;

		long in; // input vector

		long[] parentState = new long[STATE_BUF_SIZE];
		long[] inputVector = new long[STATE_BUF_SIZE];

		Arrays.fill(parentState, UNDISCOVERED);

		// note: a state having the value UNDISCOVERED
		// will pose an issue to the algorithm below.
		// This possibility is here (unwisely?) ignored

		// note: an actual state of Integer.

		long statesDiscovered = 0;

		long violationState = UNDISCOVERED;

		long[][] buf = new long[2][DISCOVERED_BUF_SIZE];

		int bufSelector = 0;

		long state = initial;

		long all_assumptions;
		long all_assertions;

		Stack<Long> rList = new Stack<Long>();

		System.out.println("Starting search ...");

		long startTime = System.nanoTime();

		loop1: while (toVisitArrOccupied > 0) {

			bufSelector = 1 - bufSelector;

			long[] toVisitNextArr = buf[bufSelector];

			int toVisitNextArrOccupied = 0;

			for (int i1 = 0; i1 < toVisitArrOccupied; i1++) {

				state = toVisitArr[i1];

				statesDiscovered += 1;

				//@formatter:off
				// {STATE_BIT} = -(state >> {STATE_BIT_INDEX} & 1);
				//@formatter:on

				long inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// long {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					//@formatter:on

					long nxState2 = 0;

					//@formatter:off
					// nxState2 |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					//@formatter:on

					int nxState = (int) nxState2;

					if (parentState[nxState] == UNDISCOVERED) {

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied += 1;

						parentState[nxState] = state;

						inputVector[nxState] = in;

					}

					all_assumptions = -1;
					all_assertions = -1;

					// In the code below we logically AND all assumptions
					// and assertions.

					// We don't want any property to evaluate to false until
					// we're at least {MAXDELAY} transitions away from the
					// initial state, where {MAXDELAY} is the max depth of
					// flip-flop chains within the property.

					// all_assumptions &= {ASSUMPTION} | (distance >= {MAXDELAY} ? 0 : -1);
					// all_assertions &= {ASSERTION} | (distance >= {MAXDELAY} ? 0 : -1);

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

			int currentState = (int) violationState;

			int transitions = distance;

			while (transitions > 0) {

				if (printStateList)
					System.out.println("currentState = " + getBinary(currentState, stateBitCount)
							+ ", reached from parent using input vector "
							+ getBinary(inputVector[currentState], inputBitCount));

				rList.add(inputVector[currentState]);

				currentState = (int) parentState[currentState];

				transitions--;
			}

			long[] result = new long[distance + 1];

			for (int j = 0; j < distance + 1; j++)
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

		// result.add("{INPUT_BIT_ORG}");

		// result.add("{NON_STATE_BIT_ORG}");
		//@formatter:on

		return result;
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

	public void simulate(long initial, long[] inputs, File txtFile) throws Exception {

		ArrayList<String> sigNames = getSignalNames();

		ArrayList<long[]> waveforms = simulate_internal(initial, inputs);

		if (txtFile != null)
			generateTextFile(sigNames, waveforms, txtFile);

	}

	private ArrayList<long[]> simulate_internal(long initial, long[] inputs) {

		int cycles = inputs.length;

		//@formatter:off
		// long[] {STATE_BIT} = new long[cycles];

		// long[] {INPUT_BIT} = new long[cycles];

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);

		// long[] {NON_STATE_BIT} = new long[cycles];
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

		ArrayList<long[]> waveforms = new ArrayList<long[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});

		// waveforms.add({INPUT_BIT});

		// waveforms.add({NON_STATE_BIT});
		//@formatter:on

		return waveforms;
	}

	private String getBinary(long num, long digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Long.toBinaryString(num)).replace(' ', '0');
	}

	private void generateTextFile(ArrayList<String> sigNames, ArrayList<long[]> waveforms, File txtFile)
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

			long[] sigWaveform = waveforms.get(i);

			for (int j = 0; j < sigWaveform.length; j++) {

				long v = sigWaveform[j];

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
