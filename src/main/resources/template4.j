import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
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

		File txtFile = null;

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

	public int getHash(long key, int bank) {

		// the mix and hashing functions below are taken from:
		// https://gist.github.com/badboy/6267743

		// mixing

		key = (~key) + (key << 21); // key = (key << 21) - key - 1;
		key = key ^ (key >>> 24);
		key = (key + (key << 3)) + (key << 8); // key * 265
		key = key ^ (key >>> 14);
		key = (key + (key << 2)) + (key << 4); // key * 21
		key = key ^ (key >>> 28);
		key = key + (key << 31);

		if (bank == 0) {

			return Long.hashCode(key);

		} else {

			key = (~key) + (key << 18); // key = (key << 18) - key - 1;
			key = key ^ (key >>> 31);
			key = key * 21; // key = (key + (key << 2)) + (key << 4);
			key = key ^ (key >>> 11);
			key = key + (key << 6);
			key = key ^ (key >>> 22);

			return (int) key;

		}

		// key = (~key) + (key << 18); // key = (key << 18) - key - 1;
		// key = key ^ (key >>> 31);
		// key = key * 21; // key = (key + (key << 2)) + (key << 4);
		// key = key ^ (key >>> 11);
		// key = key + (key << 6);
		// key = key ^ (key >>> 22);

		// return bank == 0 ? (int) key : (int) (key >> 25);

	}

	public int calIndex(int bank, int hash, int field) {

		return hash * (3 * 2) + (bank * 3) + field;

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

	@SuppressWarnings("unused")
	public long[] exploreSpace(long initial) throws Exception {

		// method parameters:

		final int DISCOVERED_BUF_SIZE = 1 << 20;

		final double bytes_available = (double) 0.25/4 * 1024 * 1024 * 1024; // 2 GB

		final long record_size_bytes = 3 * 8;

		final double BUF_SIZE = bytes_available / record_size_bytes;

		final int BUF_SIZE_LOG2 = (int) (Math.floor(Math.log(BUF_SIZE) / Math.log(2)));

		final boolean printStateList = false;

		// method body:

		final int BUF_SIZE_HALF = 1 << (BUF_SIZE_LOG2-1);

		final int cucko_swap_maximum = 1 << BUF_SIZE_LOG2;

		int stateBitCount = getStateBitCount();

		int inputBitCount = getInputBitCount();

		//@formatter:off
		// long {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		//@formatter:on

		//@formatter:off
		// long {NON_STATE_BIT};
		//@formatter:on

		// initialize hash table

		int TAB_SIZE = 2 * 3 * BUF_SIZE_HALF;

		long[] TAB = new long[TAB_SIZE];

		Arrays.fill(TAB, 0);

		// search arrays, pointers and indices

		long[] toVisitArr = { initial };

		long toVisitArrOccupied = 1;

		long[][] buf = new long[2][DISCOVERED_BUF_SIZE];

		int bufSelector = 0;

		// other search variables

		int distance = 0;

		long statesVisited = -1; // to offset the two visits to `initial`

		boolean counter_example_found = false;

		long statesDiscovered = 0;

		long state = 0;

		long in = 0;

		// constants

		final long DMASK = 1 << 63;

		// performance counters

		long cache_hit_counter = 0;

		long cache_miss_counter = 0;

		long total_cuckoo_swaps = 0;

		long total_cuckoo_insertions = 0;

		// body:

		System.out.println("Starting search ...");

		long startTime = System.nanoTime();

		search_loop: while (toVisitArrOccupied > 0) {

			bufSelector = 1 - bufSelector;

			long[] toVisitNextArr = buf[bufSelector];

			int toVisitNextArrOccupied = 0;

			for (int i1 = 0; i1 < toVisitArrOccupied; i1++) {

				state = toVisitArr[i1];

				statesVisited++;

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

					long nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & ((long) 1 << {STATE_BIT_INDEX});
					//@formatter:on

					// check assumptions

					long assumptions = H; // intersection of assumptions

					//@formatter:off
					// if (distance >= {MAXDELAY}) assumptions &= {ASSUMPTION};
					//@formatter:on

					if (assumptions == H) {

						boolean found = false;

						for (int bank = 0; bank < 2; bank++) {

							int hash = getHash(nxState, bank);

							hash &= BUF_SIZE_HALF - 1;

							long recorded_inpVec = TAB[calIndex(bank, hash, 2)];

							boolean occupied = (recorded_inpVec & DMASK) != 0;

							if (occupied) {

								long recorded_state = TAB[calIndex(bank, hash, 0)];

								found = (recorded_state == nxState);

								if (found)
									break;

							}

						}

						if (found) {

							cache_hit_counter++;

						} else {

							cache_miss_counter++;

							statesDiscovered++;

							toVisitNextArr[toVisitNextArrOccupied] = nxState;

							toVisitNextArrOccupied++;

							// attempt to insert record in `table`

							int bank = 0;

							long insert_record_state = nxState;
							long insert_record_parent = state;
							long insert_record_inpVec = in;

							boolean occupied = true;

							int swap_counter = 0;

							total_cuckoo_insertions++;

							while (occupied) {

								int hash = getHash(insert_record_state, bank);

								hash &= BUF_SIZE_HALF - 1;

								long recorded_inpVec = TAB[calIndex(bank, hash, 2)];

								occupied = (recorded_inpVec & DMASK) != 0;

								if (occupied) {

									long temp_insert_record_state = TAB[calIndex(bank, hash, 0)];
									long temp_insert_record_parent = TAB[calIndex(bank, hash, 1)];
									long temp_insert_record_inpVec = TAB[calIndex(bank, hash, 2)];

									TAB[calIndex(bank, hash, 0)] = insert_record_state;
									TAB[calIndex(bank, hash, 1)] = insert_record_parent;
									TAB[calIndex(bank, hash, 2)] = insert_record_inpVec | DMASK;

									insert_record_state = temp_insert_record_state;
									insert_record_parent = temp_insert_record_parent;
									insert_record_inpVec = temp_insert_record_inpVec;

									bank = 1 - bank;

									total_cuckoo_swaps++;

								} else {

									TAB[calIndex(bank, hash, 0)] = insert_record_state;
									TAB[calIndex(bank, hash, 1)] = insert_record_parent;
									TAB[calIndex(bank, hash, 2)] = insert_record_inpVec | DMASK;

								}

								swap_counter++;

								if (swap_counter > cucko_swap_maximum) {

									System.out.printf("Cuckoo insertions (total)     : %d\n", total_cuckoo_insertions);

									System.out.printf("Cache size                    : %s\n", getByteSize(2 * 3 * 8 * BUF_SIZE_HALF));

									System.out.printf("Cache load factor             : %1.3f\n", 0.5 * total_cuckoo_insertions / BUF_SIZE_HALF);

									System.out.printf("Cuckoo swaps (mean / insert)  : %f\n", 1.0 * total_cuckoo_swaps / total_cuckoo_insertions);

									throw new Exception("maximum number of cuckoo swaps reached");
								}

							}

						}

						long assertions = H; // intersection of assertions

						//@formatter:off
						// assertions &= {ASSERTION} | (distance < {MAXDELAY} ? H : L);
						//@formatter:on

						if (assertions == L) {

							counter_example_found = true;

							break search_loop;

						}

					}

				}

			}

			toVisitArr = toVisitNextArr;

			toVisitArrOccupied = toVisitNextArrOccupied;

			distance++;

		}

		long endTime = System.nanoTime();

		double searchTime = (endTime - startTime) / 1e9;

		long cache_accesses = cache_hit_counter + cache_miss_counter;

		double found_perc = 1.0 * cache_hit_counter / cache_accesses * 100;

		double notfound_perc = 1.0 * cache_miss_counter / cache_accesses * 100;

		System.out.printf("Completed search in %1.2f sec\n\n", searchTime);

		System.out.printf("State bits                    : %d\n", getStateBitCount());

		System.out.printf("Input bits                    : %d\n", getInputBitCount());

		System.out.printf("Cache size                    : %s\n", getByteSize(2 * 3 * 8 * BUF_SIZE_HALF));

		System.out.printf("State array size              : %s\n", getByteSize(2 * 8 * ((long) DISCOVERED_BUF_SIZE)));

		System.out.printf("States visited                : %d\n", statesVisited);

		System.out.printf("States discovered             : %d\n", statesDiscovered);

		System.out.printf("Cuckoo swaps (total)          : %d\n", total_cuckoo_swaps);

		System.out.printf("Cuckoo swaps (mean / insert)  : %f\n", 1.0 * total_cuckoo_swaps / total_cuckoo_insertions);

		System.out.printf("Cuckoo insertions (total)     : %d\n", total_cuckoo_insertions);

		System.out.printf("Cache load factor             : %1.3f\n", 0.5 * total_cuckoo_insertions / BUF_SIZE_HALF);

		System.out.printf("Cache hits                    : %d (%1.1f%%)\n", cache_hit_counter, found_perc);

		System.out.printf("Cache misses                  : %d (%1.1f%%)\n", cache_miss_counter, notfound_perc);

		System.out.println("");

		if (counter_example_found) {

			System.out.printf("Counter-example found (distance = %d)!\n", distance);

			long currentState = state;

			int transitions = distance;

			Stack<Long> rList = new Stack<Long>();

			rList.push(in);

			while (transitions > 0) {

				boolean found = false;

				for (int bank = 0; bank < 2; bank++) {

					int hash = getHash(currentState, bank);

					hash &= BUF_SIZE_HALF - 1;

					long recorded_state = TAB[calIndex(bank, hash, 0)];

					found = recorded_state == currentState;

					if (found) {

						long next_current_state = TAB[calIndex(bank, hash, 1)];
						long inp_vec = TAB[calIndex(bank, hash, 2)] & (~DMASK);

						if (printStateList)
							System.out.println("currentState = " + getBinary(currentState, stateBitCount)
									+ ", reached from parent using input vector " + getBinary(inp_vec, inputBitCount));

						rList.add(inp_vec);

						currentState = (int) next_current_state;

						break;

					}

				}

				if (!found)
					throw new Exception("Error while generating counter-example: state not present in cache");

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

	public void simulate(long initial, long[] inputs, File txtFile) throws Exception {

		List<String> sigNames = getSignalNames();

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

	private void generateTextFile(List<String> sigNames, ArrayList<long[]> waveforms, File txtFile)
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
