import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public static void main(String args[]) throws Exception {

		// usage:
		// codesimulator [-no-counter]
		// codesimulator [-vcd <file>] [-gtkwave]

		CodeSimulator sim1 = new CodeSimulator();

		int initial = sim1.getResetState();

		boolean generateCounterExample = true;

		boolean runGtkwave = false;

		File vcdFile = null;

		for (int i = 0; i < args.length; i++) {

			String a = args[i];

			if ("--no-counter".equals(a))
				generateCounterExample = false;

			if ("--vcd".equals(a))
				vcdFile = new File(args[i + 1]);

			if ("--gtkwave".equals(a))
				runGtkwave = true;

		}

		if (runGtkwave && vcdFile == null) {

			File tempDir = new File(System.getProperty("java.io.tmpdir"));

			vcdFile = new File(tempDir, "counter-example.vcd");
		}

		if (!generateCounterExample && (vcdFile != null)) {

			throw new Exception("Command line argument -no-counter is incompatible with -vcd and -gtkwave");

		}

		int[] counterExample = sim1.exploreSpace(initial);

		if (counterExample != null && generateCounterExample)
			sim1.simulate(initial, counterExample, vcdFile, runGtkwave);

	}

	public int getResetState() {

		// return {RESET_STATE};

	}

	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		// int inputBitCount = {INPUT_BIT_COUNT};
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
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

	public void simulate(int initial, int[] inputs, File vcdFile, boolean runGtkwave) throws Exception {

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

		System.out.println();

		System.out.flush();

		if (vcdFile != null) {

			generateVCD(sigNames, waveforms, vcdFile, runGtkwave);

		}

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

	private void generateVCD(ArrayList<String> sigNames, ArrayList<int[]> waveforms, File vcdFile, boolean runGtkwave)
			throws Exception {

		ArrayList<String> vcdLines = new ArrayList<String>();

		ArrayList<String> tclLines = new ArrayList<String>();

		// rename signals with spaces

		for (int i = 0; i < sigNames.size(); i++) {

			String s = sigNames.get(i);

			if (s.startsWith("@"))
				s = "prop-" + s.replace(" ", "-");

			if (s.startsWith("\\"))
				s = s.substring(1);

			sigNames.set(i, s);

		}

		// prepare vcd file content

		vcdLines.add("$timescale 1ns $end");

		vcdLines.add("$scope module logic $end");

		for (int i = 0; i < sigNames.size(); i++)
			vcdLines.add(String.format("$var wire 1 %s %s $end", (char) ('a' + i), sigNames.get(i)));

		vcdLines.add("$upscope $end");

		vcdLines.add("$enddefinitions $end");

		vcdLines.add("$dumpvars");

		for (int i = 0; i < sigNames.size(); i++)
			vcdLines.add(String.format("x%s", (char) ('a' + i)));

		vcdLines.add("$end");

		int cycles = waveforms.get(0).length;

		for (int j = 0; j < cycles; j++) {

			vcdLines.add(String.format("#%d", j));

			for (int i = 0; i < sigNames.size(); i++) {

				int newVal = waveforms.get(i)[j];

				int oldVal = j > 0 ? waveforms.get(i)[j - 1] : newVal + 1;

				if (newVal != oldVal) {

					String newValStr;

					if (newVal == -1)
						newValStr = "1";
					else if (newVal == 0)
						newValStr = "0";
					else
						newValStr = "x";

					vcdLines.add(String.format("%s%s", newValStr, (char) ('a' + i)));

				}

			}

		}

		vcdLines.add(String.format("#%d", cycles));

		// writing vcd file

		File tempDir = new File(System.getProperty("java.io.tmpdir"));

		System.out.println("Saving waveform data to " + vcdFile.getAbsolutePath() + " ...");

		PrintStream fout = new PrintStream(vcdFile);

		for (String l : vcdLines)
			fout.println(l);

		fout.close();

		if (runGtkwave) {

			// prepare gtkwave tcl script content

			// gtkwave has an issue with adding array bits
			// as a walk-around add the array name instead
			// of the individual bits

			tclLines.add("set sigList [list]");

			for (String s : sigNames) {

				if (!s.startsWith("\\") && s.contains("[")) {

					if (s.contains("[0]")) {

						s = s.replaceAll("\\[\\d+\\]", "");

					} else {

						continue;

					}

				}


				tclLines.add(String.format("lappend sigList {%s}", s));
			}

			tclLines.add("set num_added [ gtkwave::addSignalsFromList $sigList ]");

			// write tcl script

			File tclFile = new File(tempDir, "counter-example-show.tcl");

			System.out.println("Saving gtkwave tcl script to " + tclFile.getAbsolutePath() + " ...");

			PrintStream fout2 = new PrintStream(tclFile);

			for (String l : tclLines)
				fout2.println(l);

			fout2.close();

			// run gtkwave

			String cmd = String.format("gtkwave \"%s\" -S \"%s\"", vcdFile.getAbsolutePath(),
					tclFile.getAbsolutePath());

			final Runtime rt = Runtime.getRuntime();

			try {

				rt.exec(cmd);

			} catch (IOException e) {

				e.printStackTrace();

				throw new Exception("unable to run gtkwave, make sure it is installed and setup in PATH");

			}

		}

	}

}
