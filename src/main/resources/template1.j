import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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

	@SuppressWarnings("unused")
	public int[] exploreSpace(int initial) throws Exception {

		int stateBitCount = getStateBitCount();

		int inputBitCount = getInputBitCount();

		int DMASK = 1 << 31;

		// method parameters:

		final int STATE_BUF_SIZE = 1 << stateBitCount;

		final int DISCOVERED_BUF_SIZE = 1 << stateBitCount - 2;

		boolean printStateList = false;

		// memory usage checks:

		if (stateBitCount > 29)
			throw new Exception(String.format("Memory requirements exceed 4 GB (state bits = %d)", stateBitCount));

		if (inputBitCount > 32)
			throw new Exception(String.format("Input vector not representable as int type (input bits = %d)", inputBitCount));

		// search variables

		int[] toVisitArr = { initial };

		int toVisitArrOccupied = 1;

		int distance = 0;

		int in = 0; // input vector

		int statesDiscovered = 1;

		int statesVisited = 0;

		boolean counter_example_found = false;

		int bufSelector = 0;

		int state = initial;

		int union_assumptions;

		int union_assertions;

		// state LUT and discovery arrays

		int[] parentState = new int[STATE_BUF_SIZE];

		int[] inputVector = new int[STATE_BUF_SIZE];

		int[][] buf = new int[2][DISCOVERED_BUF_SIZE];

		// net declarations

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		//@formatter:on

		System.out.println("Starting search ...");

		long startTime = System.nanoTime();

		search_loop: while (toVisitArrOccupied > 0) {

			bufSelector = 1 - bufSelector;

			int[] toVisitNextArr = buf[bufSelector];

			int toVisitNextArrOccupied = 0;

			for (int i1 = 0; i1 < toVisitArrOccupied; i1++) {

				state = toVisitArr[i1];

				statesVisited++;

				//@formatter:off
				// {STATE_BIT} = -(state >> {STATE_BIT_INDEX} & 1);
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					//@formatter:on

					if ((parentState[nxState] & DMASK) == 0) {

						statesDiscovered++;

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied++;

						parentState[nxState] = state | DMASK;

						inputVector[nxState] = in;

					}

					union_assumptions = H;

					union_assertions = H;

					// In the code below we logically AND all assumptions
					// and assertions.

					// We don't want any property to evaluate to false until
					// we're at least {MAXDELAY} transitions away from the
					// initial state, where {MAXDELAY} is the max depth of
					// flip-flop chains within the property.

					//@formatter:off
					// union_assumptions &= {ASSUMPTION} | (distance >= {MAXDELAY} ? L : H);
					// union_assertions &= {ASSERTION} | (distance >= {MAXDELAY} ? L : H);
					//@formatter:on

					if (union_assumptions == H && union_assertions == L) {

						counter_example_found = true;

						break search_loop;

					}

				}

			}

			toVisitArr = toVisitNextArr;

			toVisitArrOccupied = toVisitNextArrOccupied;

			distance++;

		}

		long endTime = System.nanoTime();

		double searchTime = (endTime - startTime) / 1e9;

		System.out.printf("Completed search in %f seconds\n", searchTime);

		System.out.printf("State bits                     : %d\n", getStateBitCount());

		System.out.printf("Input bits                     : %d\n", getInputBitCount());

		System.out.printf("State LUT                      : %s\n", getByteSize(8 * ((long) STATE_BUF_SIZE)));

		System.out.printf("State discovery buffer         : %s\n", getByteSize(8 * ((long) DISCOVERED_BUF_SIZE)));

		System.out.printf("States visited                 : %d\n", statesVisited);

		System.out.printf("States discovered              : %d\n", statesDiscovered);

		if (counter_example_found) {

			System.out.printf("Counter-example found in %d cycles\n", distance);

			int currentState = state; // violation state

			int transitions = distance;

			Stack<Integer> rList = new Stack<Integer>();

			rList.push(in);

			while (transitions > 0) {

				if (printStateList)
					System.out.println("currentState = " + getBinary(currentState, stateBitCount)
							+ ", reached from parent using input vector "
							+ getBinary(inputVector[currentState], inputBitCount));

				rList.add(inputVector[currentState]);

				currentState = parentState[currentState] & ~DMASK;

				transitions--;
			}

			int[] result = new int[distance + 1];

			for (int j = 0; j < distance + 1; j++)
				result[j] = rList.pop();

			return result;

		} else {

			System.out.println("Assertion proven, no counter-examples were found.");

			return null;

		}

	}

	public List<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

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

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
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

}
