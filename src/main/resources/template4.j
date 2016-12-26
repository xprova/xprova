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
		return 0x0; // {EXPANDED}

	}

	public long hash64shift(long key)
	{
	  key = (~key) + (key << 21); // key = (key << 21) - key - 1;
	  key = key ^ (key >>> 24);
	  key = (key + (key << 3)) + (key << 8); // key * 265
	  key = key ^ (key >>> 14);
	  key = (key + (key << 2)) + (key << 4); // key * 21
	  key = key ^ (key >>> 28);
	  key = key + (key << 31);
	  return key;
	}

	public int getHash(long key, int bank) {

		return bank == 0 ? (int) key : (int) (key>>16);

	}

	public int getHash_old(long key, int bank) {

//		long hash = hash64shift(value);

		if (bank == 0) {

			return (int) key;

		} else {

			key = (~key) + (key << 21); // key = (key << 21) - key - 1;
			key = key ^ (key >>> 24);
			key = (key + (key << 3)) + (key << 8); // key * 265
			key = key ^ (key >>> 14);
			key = (key + (key << 2)) + (key << 4); // key * 21
			key = key ^ (key >>> 28);
			key = key + (key << 31);

			return (int) key;

		}
//		return (bank == 0) ? (int) key : (int) (key>>32);

	}

	public int calIndex(int bank, int hash, int field) {

		return hash * (3 * 2) + (bank * 3) + field;

	}

	@SuppressWarnings("unused")
	public long[] exploreSpace(long initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		final int STATE_BUF_SIZE = 1 << 22; // {EXPANDED}
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = STATE_BUF_SIZE;

		boolean printStateList = false;

		final long UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 22; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 2; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// long {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		long ncount2_0__8 = -(initial >> 0 & 1); // {EXPANDED}
		long ncount2_10__15 = -(initial >> 1 & 1); // {EXPANDED}
		long ncount2_1__12 = -(initial >> 2 & 1); // {EXPANDED}
		long ncount2_2__24 = -(initial >> 3 & 1); // {EXPANDED}
		long ncount2_3__34 = -(initial >> 4 & 1); // {EXPANDED}
		long ncount2_4__97 = -(initial >> 5 & 1); // {EXPANDED}
		long ncount2_5__80 = -(initial >> 6 & 1); // {EXPANDED}
		long ncount2_6__4 = -(initial >> 7 & 1); // {EXPANDED}
		long ncount2_7__87 = -(initial >> 8 & 1); // {EXPANDED}
		long ncount2_8__2 = -(initial >> 9 & 1); // {EXPANDED}
		long ncount2_9__39 = -(initial >> 10 & 1); // {EXPANDED}
		long ncount_0__51 = -(initial >> 11 & 1); // {EXPANDED}
		long ncount_10__52 = -(initial >> 12 & 1); // {EXPANDED}
		long ncount_1__86 = -(initial >> 13 & 1); // {EXPANDED}
		long ncount_2__91 = -(initial >> 14 & 1); // {EXPANDED}
		long ncount_3__75 = -(initial >> 15 & 1); // {EXPANDED}
		long ncount_4__96 = -(initial >> 16 & 1); // {EXPANDED}
		long ncount_5__99 = -(initial >> 17 & 1); // {EXPANDED}
		long ncount_6__89 = -(initial >> 18 & 1); // {EXPANDED}
		long ncount_7__48 = -(initial >> 19 & 1); // {EXPANDED}
		long ncount_8__61 = -(initial >> 20 & 1); // {EXPANDED}
		long ncount_9__45 = -(initial >> 21 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// long {NON_STATE_BIT};
		long n_n196_50; // {EXPANDED}
		long n_000__0__43; // {EXPANDED}
		long n_000__10__70; // {EXPANDED}
		long n_000__1__17; // {EXPANDED}
		long n_000__2__11; // {EXPANDED}
		long n_000__3__29; // {EXPANDED}
		long n_000__4__59; // {EXPANDED}
		long n_000__5__3; // {EXPANDED}
		long n_000__6__10; // {EXPANDED}
		long n_000__7__56; // {EXPANDED}
		long n_000__8__1; // {EXPANDED}
		long n_000__9__30; // {EXPANDED}
		long n_001__0__71; // {EXPANDED}
		long n_001__10__67; // {EXPANDED}
		long n_001__1__98; // {EXPANDED}
		long n_001__2__101; // {EXPANDED}
		long n_001__3__76; // {EXPANDED}
		long n_001__4__6; // {EXPANDED}
		long n_001__5__72; // {EXPANDED}
		long n_001__6__9; // {EXPANDED}
		long n_001__7__19; // {EXPANDED}
		long n_001__8__32; // {EXPANDED}
		long n_001__9__33; // {EXPANDED}
		long n_002__23; // {EXPANDED}
		long n_003__65; // {EXPANDED}
		long n_004__26; // {EXPANDED}
		long n_005__47; // {EXPANDED}
		long n_006__0; // {EXPANDED}
		long n_007__7; // {EXPANDED}
		long n_008__92; // {EXPANDED}
		long n_009__83; // {EXPANDED}
		long n_010__54; // {EXPANDED}
		long n_011__60; // {EXPANDED}
		long n_012__31; // {EXPANDED}
		long n_013__37; // {EXPANDED}
		long n_014__35; // {EXPANDED}
		long n_015__69; // {EXPANDED}
		long n_016__79; // {EXPANDED}
		long n_017__84; // {EXPANDED}
		long n_018__88; // {EXPANDED}
		long n_019__38; // {EXPANDED}
		long n_020__49; // {EXPANDED}
		long n_021__94; // {EXPANDED}
		long n_022__74; // {EXPANDED}
		long n_023__82; // {EXPANDED}
		long n_024__57; // {EXPANDED}
		long n_025__55; // {EXPANDED}
		long n_026__40; // {EXPANDED}
		long n_027__78; // {EXPANDED}
		long n_028__25; // {EXPANDED}
		long n_029__81; // {EXPANDED}
		long n_030__5; // {EXPANDED}
		long n_031__100; // {EXPANDED}
		long n_032__44; // {EXPANDED}
		long n_033__66; // {EXPANDED}
		long n_034__68; // {EXPANDED}
		long n_035__63; // {EXPANDED}
		long n_036__20; // {EXPANDED}
		long n_037__42; // {EXPANDED}
		long n_038__64; // {EXPANDED}
		long n_039__62; // {EXPANDED}
		long n_040__93; // {EXPANDED}
		long n_041__18; // {EXPANDED}
		long n_042__21; // {EXPANDED}
		long n_043__28; // {EXPANDED}
		long n_044__46; // {EXPANDED}
		long n_045__85; // {EXPANDED}
		long n_046__13; // {EXPANDED}
		long n_047__53; // {EXPANDED}
		long n_048__36; // {EXPANDED}
		long n_049__41; // {EXPANDED}
		long n_050__22; // {EXPANDED}
		long nerr_73; // {EXPANDED}
		//@formatter:on

		long[] toVisitArr = new long[1];
		toVisitArr[0] = initial;
		long toVisitArrOccupied = 1;

		int distance = 0;

		long in; // input vector

		boolean use_direct_lut = false;

		long[] parentState = null;
		long[] inputVector = null;

		if (use_direct_lut) {

			parentState = new long[STATE_BUF_SIZE];
			inputVector = new long[STATE_BUF_SIZE];

			Arrays.fill(parentState, UNDISCOVERED);

		}

		int cucko_swap_maximum = 50;

		long total_cuckoo_swaps = 0;
		long total_cuckoo_insertions = 0;

		int BUF_SIZE_2_LOG2 = 26;

		int BUF_SIZE_2 = 1 << BUF_SIZE_2_LOG2;

		long DMASK = 1 << 63;

//		long [][] table_0 = new long [2][BUF_SIZE_2];
//		long [][] table_1 = new long [2][BUF_SIZE_2];
//		long [][] table_2 = new long [2][BUF_SIZE_2];

		int TAB_SIZE = 6 * BUF_SIZE_2;

		long[] TAB = new long[TAB_SIZE];


//		Arrays.fill(table, 0);

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

		long found_counter = 0;

		long notfound_counter = 0;

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
				ncount2_0__8 = -(state >> 0 & 1); // {EXPANDED}
				ncount2_10__15 = -(state >> 1 & 1); // {EXPANDED}
				ncount2_1__12 = -(state >> 2 & 1); // {EXPANDED}
				ncount2_2__24 = -(state >> 3 & 1); // {EXPANDED}
				ncount2_3__34 = -(state >> 4 & 1); // {EXPANDED}
				ncount2_4__97 = -(state >> 5 & 1); // {EXPANDED}
				ncount2_5__80 = -(state >> 6 & 1); // {EXPANDED}
				ncount2_6__4 = -(state >> 7 & 1); // {EXPANDED}
				ncount2_7__87 = -(state >> 8 & 1); // {EXPANDED}
				ncount2_8__2 = -(state >> 9 & 1); // {EXPANDED}
				ncount2_9__39 = -(state >> 10 & 1); // {EXPANDED}
				ncount_0__51 = -(state >> 11 & 1); // {EXPANDED}
				ncount_10__52 = -(state >> 12 & 1); // {EXPANDED}
				ncount_1__86 = -(state >> 13 & 1); // {EXPANDED}
				ncount_2__91 = -(state >> 14 & 1); // {EXPANDED}
				ncount_3__75 = -(state >> 15 & 1); // {EXPANDED}
				ncount_4__96 = -(state >> 16 & 1); // {EXPANDED}
				ncount_5__99 = -(state >> 17 & 1); // {EXPANDED}
				ncount_6__89 = -(state >> 18 & 1); // {EXPANDED}
				ncount_7__48 = -(state >> 19 & 1); // {EXPANDED}
				ncount_8__61 = -(state >> 20 & 1); // {EXPANDED}
				ncount_9__45 = -(state >> 21 & 1); // {EXPANDED}
				//@formatter:on

				long inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// long {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					long nena1_14 = -(in >> 0 & 1); // {EXPANDED}
					long nena2_58 = -(in >> 1 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n_050__22 = 0; // {EXPANDED}
					n_047__53 = ~(ncount_4__96 & ncount_5__99); // {EXPANDED}
					n_010__54 = ~ncount2_10__15; // {EXPANDED}
					n_030__5 = ~(ncount2_6__4 & ncount2_7__87); // {EXPANDED}
					n_007__7 = ~(ncount_9__45 & ncount_8__61); // {EXPANDED}
					n_039__62 = ~(ncount_1__86 & ncount_0__51); // {EXPANDED}
					n_035__63 = ~(ncount2_8__2 & ncount2_9__39); // {EXPANDED}
					n_038__64 = ~nena1_14; // {EXPANDED}
					n_042__21 = ~(ncount_3__75 & ncount_2__91); // {EXPANDED}
					n_002__23 = ~(ncount_6__89 & ncount_7__48); // {EXPANDED}
					n_001__0__71 = (nena1_14 ^ ncount_0__51); // {EXPANDED}
					n_022__74 = ~(ncount2_2__24 & ncount2_3__34); // {EXPANDED}
					n_027__78 = ~(ncount2_4__97 & ncount2_5__80); // {EXPANDED}
					n_014__35 = ~ncount_10__52; // {EXPANDED}
					n_017__84 = nena2_58 & ncount2_0__8; // {EXPANDED}
					n_019__38 = ~(ncount2_0__8 & ncount2_1__12); // {EXPANDED}
					n_018__88 = ~nena2_58; // {EXPANDED}
					n_037__42 = nena1_14 & ncount_0__51; // {EXPANDED}
					n_000__0__43 = (nena2_58 ^ ncount2_0__8); // {EXPANDED}
					n_001__1__98 = (ncount_1__86 ^ n_037__42); // {EXPANDED}
					n_023__82 = n_019__38 | n_022__74; // {EXPANDED}
					n_026__40 = n_023__82 | n_018__88; // {EXPANDED}
					n_028__25 = ~(n_026__40 | n_027__78); // {EXPANDED}
					n_043__28 = n_042__21 | n_039__62; // {EXPANDED}
					n_040__93 = ~(n_038__64 | n_039__62); // {EXPANDED}
					n_000__1__17 = (n_017__84 ^ ncount2_1__12); // {EXPANDED}
					n_024__57 = ~(n_023__82 | n_018__88); // {EXPANDED}
					n_044__46 = ~(n_038__64 | n_043__28); // {EXPANDED}
					n_003__65 = n_002__23 | n_047__53; // {EXPANDED}
					n_011__60 = ~(n_010__54 | n_035__63); // {EXPANDED}
					n_015__69 = ~(n_007__7 | n_014__35); // {EXPANDED}
					n_020__49 = ~(n_019__38 | n_018__88); // {EXPANDED}
					n_031__100 = n_030__5 | n_027__78; // {EXPANDED}
					n_009__83 = ~(n_023__82 | n_031__100); // {EXPANDED}
					n_046__13 = n_038__64 | n_043__28; // {EXPANDED}
					n_006__0 = n_046__13 | n_003__65; // {EXPANDED}
					n_045__85 = ncount_4__96 & n_044__46; // {EXPANDED}
					n_013__37 = ~(n_003__65 | n_043__28); // {EXPANDED}
					n_004__26 = ~(n_046__13 | n_003__65); // {EXPANDED}
					n_025__55 = n_024__57 & ncount2_4__97; // {EXPANDED}
					n_021__94 = ncount2_2__24 & n_020__49; // {EXPANDED}
					n_032__44 = ~(n_026__40 | n_031__100); // {EXPANDED}
					n_000__4__59 = (n_024__57 ^ ncount2_4__97); // {EXPANDED}
					n_001__4__6 = (ncount_4__96 ^ n_044__46); // {EXPANDED}
					n_012__31 = ~(n_009__83 & n_011__60); // {EXPANDED}
					n_041__18 = n_040__93 & ncount_2__91; // {EXPANDED}
					n_000__6__10 = (ncount2_6__4 ^ n_028__25); // {EXPANDED}
					n_034__68 = n_026__40 | n_031__100; // {EXPANDED}
					n_000__2__11 = (ncount2_2__24 ^ n_020__49); // {EXPANDED}
					n_016__79 = ~(n_013__37 & n_015__69); // {EXPANDED}
					n_029__81 = ncount2_6__4 & n_028__25; // {EXPANDED}
					n_001__2__101 = (n_040__93 ^ ncount_2__91); // {EXPANDED}
					n_000__8__1 = (n_032__44 ^ ncount2_8__2); // {EXPANDED}
					n_001__5__72 = (n_045__85 ^ ncount_5__99); // {EXPANDED}
					n_000__5__3 = (ncount2_5__80 ^ n_025__55); // {EXPANDED}
					nerr_73 = ~(n_012__31 & n_016__79); // {EXPANDED}
					n_008__92 = ~(n_006__0 | n_007__7); // {EXPANDED}
					n_000__7__56 = (ncount2_7__87 ^ n_029__81); // {EXPANDED}
					n_000__3__29 = (n_021__94 ^ ncount2_3__34); // {EXPANDED}
					n_001__3__76 = (ncount_3__75 ^ n_041__18); // {EXPANDED}
					n_001__8__32 = (n_004__26 ^ ncount_8__61); // {EXPANDED}
					n_005__47 = n_004__26 & ncount_8__61; // {EXPANDED}
					n_033__66 = n_032__44 & ncount2_8__2; // {EXPANDED}
					n_036__20 = ~(n_034__68 | n_035__63); // {EXPANDED}
					n_048__36 = ~(n_046__13 | n_047__53); // {EXPANDED}
					n_049__41 = ncount_6__89 & n_048__36; // {EXPANDED}
					n_000__9__30 = (ncount2_9__39 ^ n_033__66); // {EXPANDED}
					n_001__9__33 = (ncount_9__45 ^ n_005__47); // {EXPANDED}
					n_001__10__67 = (n_008__92 ^ ncount_10__52); // {EXPANDED}
					n_001__6__9 = (ncount_6__89 ^ n_048__36); // {EXPANDED}
					n_n196_50 = ~nerr_73; // {EXPANDED}
					n_000__10__70 = (ncount2_10__15 ^ n_036__20); // {EXPANDED}
					n_001__7__19 = (n_049__41 ^ ncount_7__48); // {EXPANDED}
					//@formatter:on

					long nxState2 = 0;

					//@formatter:off
					// nxState2 |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState2 |= n_000__0__43 & (1 << 0); // {EXPANDED}
					nxState2 |= n_000__10__70 & (1 << 1); // {EXPANDED}
					nxState2 |= n_000__1__17 & (1 << 2); // {EXPANDED}
					nxState2 |= n_000__2__11 & (1 << 3); // {EXPANDED}
					nxState2 |= n_000__3__29 & (1 << 4); // {EXPANDED}
					nxState2 |= n_000__4__59 & (1 << 5); // {EXPANDED}
					nxState2 |= n_000__5__3 & (1 << 6); // {EXPANDED}
					nxState2 |= n_000__6__10 & (1 << 7); // {EXPANDED}
					nxState2 |= n_000__7__56 & (1 << 8); // {EXPANDED}
					nxState2 |= n_000__8__1 & (1 << 9); // {EXPANDED}
					nxState2 |= n_000__9__30 & (1 << 10); // {EXPANDED}
					nxState2 |= n_001__0__71 & (1 << 11); // {EXPANDED}
					nxState2 |= n_001__10__67 & (1 << 12); // {EXPANDED}
					nxState2 |= n_001__1__98 & (1 << 13); // {EXPANDED}
					nxState2 |= n_001__2__101 & (1 << 14); // {EXPANDED}
					nxState2 |= n_001__3__76 & (1 << 15); // {EXPANDED}
					nxState2 |= n_001__4__6 & (1 << 16); // {EXPANDED}
					nxState2 |= n_001__5__72 & (1 << 17); // {EXPANDED}
					nxState2 |= n_001__6__9 & (1 << 18); // {EXPANDED}
					nxState2 |= n_001__7__19 & (1 << 19); // {EXPANDED}
					nxState2 |= n_001__8__32 & (1 << 20); // {EXPANDED}
					nxState2 |= n_001__9__33 & (1 << 21); // {EXPANDED}
					//@formatter:on



					boolean use_cuckoo = true;



					if (use_direct_lut) {

						int nxState = (int) nxState2;

						if (parentState[nxState] == UNDISCOVERED) {

							toVisitNextArr[toVisitNextArrOccupied] = nxState;

							toVisitNextArrOccupied += 1;

							parentState[nxState] = state;

							inputVector[nxState] = in;

						}

					} else if (use_cuckoo) {

						boolean found = false;

						for (int bank=0; bank<2; bank++) {

							int hash = getHash(nxState2, bank);

							hash &= (1 << BUF_SIZE_2_LOG2) - 1;

//							long recorded_inpVec = table_2[bank][hash];
							long recorded_inpVec = TAB[calIndex(bank, hash, 2)];

							boolean occupied = (recorded_inpVec & DMASK) != 0;

							if (occupied) {

//								long recorded_state = table_0[bank][hash];
								long recorded_state = TAB[calIndex(bank, hash, 0)];

								found = (recorded_state == nxState2);

								if (found)
									break;

							}

						}

						found_counter += found ? 1 : 0;
						notfound_counter += found ? 0 : 1;

						if (!found) {

							toVisitNextArr[toVisitNextArrOccupied] = nxState2;

							toVisitNextArrOccupied += 1;

							// attempt to insert record in `table`

							int bank = 0;

							long insert_record_state = nxState2;
							long insert_record_parent = state;
							long insert_record_inpVec = in;

							boolean occupied = true;

							int swap_counter = 0;

							total_cuckoo_insertions += 1;

							while (occupied) {

								int hash = getHash(insert_record_state, bank);

								hash &= (1 << BUF_SIZE_2_LOG2) - 1;

//								long recorded_inpVec = table_2[bank][hash];
								long recorded_inpVec = TAB[calIndex(bank, hash, 2)];

								occupied = (recorded_inpVec & DMASK) != 0;

								if (occupied) {

									long temp_insert_record_state = TAB[calIndex(bank, hash, 0)];
									long temp_insert_record_parent = TAB[calIndex(bank, hash, 1)];
									long temp_insert_record_inpVec = TAB[calIndex(bank, hash, 2)];
//									long temp_insert_record_state = table_0[bank][hash];
//									long temp_insert_record_parent = table_1[bank][hash];
//									long temp_insert_record_inpVec = table_2[bank][hash];

									TAB[calIndex(bank, hash, 0)] = insert_record_state;;
									TAB[calIndex(bank, hash, 1)] = insert_record_parent;
									TAB[calIndex(bank, hash, 2)] = insert_record_inpVec | DMASK;;
//									table_0[bank][hash] = insert_record_state;
//									table_1[bank][hash] = insert_record_parent;
//									table_2[bank][hash] = insert_record_inpVec | DMASK;

									insert_record_state = temp_insert_record_state;
									insert_record_parent = temp_insert_record_parent;
									insert_record_inpVec = temp_insert_record_inpVec;

									bank = 1 - bank;

								} else {

//									table_0[bank][hash] = insert_record_state;
//									table_1[bank][hash] = insert_record_parent;
//									table_2[bank][hash] = insert_record_inpVec | DMASK;

									TAB[calIndex(bank, hash, 0)] = insert_record_state;;
									TAB[calIndex(bank, hash, 1)] = insert_record_parent;
									TAB[calIndex(bank, hash, 2)] = insert_record_inpVec | DMASK;;

								}

							swap_counter += 1;
							total_cuckoo_swaps += 1;

							if (swap_counter > cucko_swap_maximum)
								throw new Exception("maximum number of cuckoo swaps reached");
	//						else
	//							System.out.println("inserted record after " + swap_counter + " trials");

							}

						}

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
					all_assertions &= n_n196_50 | (distance >= 0 ? 0 : -1); // {EXPANDED}

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

		System.out.printf("Total cuckoo swaps = %d\n", total_cuckoo_swaps);

		System.out.printf("Total cuckoo insertions = %d\n", total_cuckoo_insertions);

		System.out.printf("average cuckoo swaps = %f\n", 1.0 * total_cuckoo_swaps / total_cuckoo_insertions);

		System.out.printf("found_counter= %d\n", found_counter);
		System.out.printf("notfound_counter= %d\n", notfound_counter);

		if (violationState != UNDISCOVERED) {

			System.out.printf("Counter-example found (distance = %d)!\n", distance);

			int currentState = (int) violationState;

			int transitions = distance;

			while (transitions > 0) {

				if (printStateList)
					System.out.println("currentState = " + getBinary(currentState, stateBitCount)
							+ ", reached from parent using input vector "
							+ getBinary(inputVector[currentState], inputBitCount));

				// lookup parent state and input vector using the cuckoo hash table

				boolean found = false;
				boolean compare = false;

				for (int bank=0; bank<2; bank++) {

					int hash = getHash(currentState, bank);

					hash &= (1 << BUF_SIZE_2_LOG2) - 1;

//					long recorded_state = table_0[bank][hash];
					long recorded_state = TAB[calIndex(bank, hash, 0)];

					found = recorded_state == currentState;

					if (found) {

//						long next_current_state = table_1[bank][hash];
//						long inp_vec = table_2[bank][hash] & (~DMASK);

						long next_current_state = TAB[calIndex(bank, hash, 1)];
						long inp_vec = TAB[calIndex(bank, hash, 2)] & (~DMASK);

						rList.add(inp_vec);

						currentState = (int) next_current_state;

						if (compare) {
							boolean c1 = next_current_state == parentState[currentState];
							boolean c2 = inp_vec == inputVector[currentState];

							if (!c1 | !c2)
								throw new Exception("comparison error");

						}

						break;

					}

				}

				// end of cuckoo section

				if (use_direct_lut) {

					rList.add(inputVector[currentState]);

					currentState = (int) parentState[currentState];

				}

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
		result.add("count2[0]"); // {EXPANDED}
		result.add("count2[10]"); // {EXPANDED}
		result.add("count2[1]"); // {EXPANDED}
		result.add("count2[2]"); // {EXPANDED}
		result.add("count2[3]"); // {EXPANDED}
		result.add("count2[4]"); // {EXPANDED}
		result.add("count2[5]"); // {EXPANDED}
		result.add("count2[6]"); // {EXPANDED}
		result.add("count2[7]"); // {EXPANDED}
		result.add("count2[8]"); // {EXPANDED}
		result.add("count2[9]"); // {EXPANDED}
		result.add("count[0]"); // {EXPANDED}
		result.add("count[10]"); // {EXPANDED}
		result.add("count[1]"); // {EXPANDED}
		result.add("count[2]"); // {EXPANDED}
		result.add("count[3]"); // {EXPANDED}
		result.add("count[4]"); // {EXPANDED}
		result.add("count[5]"); // {EXPANDED}
		result.add("count[6]"); // {EXPANDED}
		result.add("count[7]"); // {EXPANDED}
		result.add("count[8]"); // {EXPANDED}
		result.add("count[9]"); // {EXPANDED}

		// result.add("{INPUT_BIT_ORG}");
		result.add("ena1"); // {EXPANDED}
		result.add("ena2"); // {EXPANDED}

		// result.add("{NON_STATE_BIT_ORG}");
		result.add("*n196"); // {EXPANDED}
		result.add("_000_[0]"); // {EXPANDED}
		result.add("_000_[10]"); // {EXPANDED}
		result.add("_000_[1]"); // {EXPANDED}
		result.add("_000_[2]"); // {EXPANDED}
		result.add("_000_[3]"); // {EXPANDED}
		result.add("_000_[4]"); // {EXPANDED}
		result.add("_000_[5]"); // {EXPANDED}
		result.add("_000_[6]"); // {EXPANDED}
		result.add("_000_[7]"); // {EXPANDED}
		result.add("_000_[8]"); // {EXPANDED}
		result.add("_000_[9]"); // {EXPANDED}
		result.add("_001_[0]"); // {EXPANDED}
		result.add("_001_[10]"); // {EXPANDED}
		result.add("_001_[1]"); // {EXPANDED}
		result.add("_001_[2]"); // {EXPANDED}
		result.add("_001_[3]"); // {EXPANDED}
		result.add("_001_[4]"); // {EXPANDED}
		result.add("_001_[5]"); // {EXPANDED}
		result.add("_001_[6]"); // {EXPANDED}
		result.add("_001_[7]"); // {EXPANDED}
		result.add("_001_[8]"); // {EXPANDED}
		result.add("_001_[9]"); // {EXPANDED}
		result.add("_002_"); // {EXPANDED}
		result.add("_003_"); // {EXPANDED}
		result.add("_004_"); // {EXPANDED}
		result.add("_005_"); // {EXPANDED}
		result.add("_006_"); // {EXPANDED}
		result.add("_007_"); // {EXPANDED}
		result.add("_008_"); // {EXPANDED}
		result.add("_009_"); // {EXPANDED}
		result.add("_010_"); // {EXPANDED}
		result.add("_011_"); // {EXPANDED}
		result.add("_012_"); // {EXPANDED}
		result.add("_013_"); // {EXPANDED}
		result.add("_014_"); // {EXPANDED}
		result.add("_015_"); // {EXPANDED}
		result.add("_016_"); // {EXPANDED}
		result.add("_017_"); // {EXPANDED}
		result.add("_018_"); // {EXPANDED}
		result.add("_019_"); // {EXPANDED}
		result.add("_020_"); // {EXPANDED}
		result.add("_021_"); // {EXPANDED}
		result.add("_022_"); // {EXPANDED}
		result.add("_023_"); // {EXPANDED}
		result.add("_024_"); // {EXPANDED}
		result.add("_025_"); // {EXPANDED}
		result.add("_026_"); // {EXPANDED}
		result.add("_027_"); // {EXPANDED}
		result.add("_028_"); // {EXPANDED}
		result.add("_029_"); // {EXPANDED}
		result.add("_030_"); // {EXPANDED}
		result.add("_031_"); // {EXPANDED}
		result.add("_032_"); // {EXPANDED}
		result.add("_033_"); // {EXPANDED}
		result.add("_034_"); // {EXPANDED}
		result.add("_035_"); // {EXPANDED}
		result.add("_036_"); // {EXPANDED}
		result.add("_037_"); // {EXPANDED}
		result.add("_038_"); // {EXPANDED}
		result.add("_039_"); // {EXPANDED}
		result.add("_040_"); // {EXPANDED}
		result.add("_041_"); // {EXPANDED}
		result.add("_042_"); // {EXPANDED}
		result.add("_043_"); // {EXPANDED}
		result.add("_044_"); // {EXPANDED}
		result.add("_045_"); // {EXPANDED}
		result.add("_046_"); // {EXPANDED}
		result.add("_047_"); // {EXPANDED}
		result.add("_048_"); // {EXPANDED}
		result.add("_049_"); // {EXPANDED}
		result.add("_050_"); // {EXPANDED}
		result.add("err"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 22; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 2; // {EXPANDED}
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
		long[] ncount2_0__8 = new long[cycles]; // {EXPANDED}
		long[] ncount2_10__15 = new long[cycles]; // {EXPANDED}
		long[] ncount2_1__12 = new long[cycles]; // {EXPANDED}
		long[] ncount2_2__24 = new long[cycles]; // {EXPANDED}
		long[] ncount2_3__34 = new long[cycles]; // {EXPANDED}
		long[] ncount2_4__97 = new long[cycles]; // {EXPANDED}
		long[] ncount2_5__80 = new long[cycles]; // {EXPANDED}
		long[] ncount2_6__4 = new long[cycles]; // {EXPANDED}
		long[] ncount2_7__87 = new long[cycles]; // {EXPANDED}
		long[] ncount2_8__2 = new long[cycles]; // {EXPANDED}
		long[] ncount2_9__39 = new long[cycles]; // {EXPANDED}
		long[] ncount_0__51 = new long[cycles]; // {EXPANDED}
		long[] ncount_10__52 = new long[cycles]; // {EXPANDED}
		long[] ncount_1__86 = new long[cycles]; // {EXPANDED}
		long[] ncount_2__91 = new long[cycles]; // {EXPANDED}
		long[] ncount_3__75 = new long[cycles]; // {EXPANDED}
		long[] ncount_4__96 = new long[cycles]; // {EXPANDED}
		long[] ncount_5__99 = new long[cycles]; // {EXPANDED}
		long[] ncount_6__89 = new long[cycles]; // {EXPANDED}
		long[] ncount_7__48 = new long[cycles]; // {EXPANDED}
		long[] ncount_8__61 = new long[cycles]; // {EXPANDED}
		long[] ncount_9__45 = new long[cycles]; // {EXPANDED}

		// long[] {INPUT_BIT} = new long[cycles];
		long[] nena1_14 = new long[cycles]; // {EXPANDED}
		long[] nena2_58 = new long[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		ncount2_0__8[0] = -(initial >> 0 & 1); // {EXPANDED}
		ncount2_10__15[0] = -(initial >> 1 & 1); // {EXPANDED}
		ncount2_1__12[0] = -(initial >> 2 & 1); // {EXPANDED}
		ncount2_2__24[0] = -(initial >> 3 & 1); // {EXPANDED}
		ncount2_3__34[0] = -(initial >> 4 & 1); // {EXPANDED}
		ncount2_4__97[0] = -(initial >> 5 & 1); // {EXPANDED}
		ncount2_5__80[0] = -(initial >> 6 & 1); // {EXPANDED}
		ncount2_6__4[0] = -(initial >> 7 & 1); // {EXPANDED}
		ncount2_7__87[0] = -(initial >> 8 & 1); // {EXPANDED}
		ncount2_8__2[0] = -(initial >> 9 & 1); // {EXPANDED}
		ncount2_9__39[0] = -(initial >> 10 & 1); // {EXPANDED}
		ncount_0__51[0] = -(initial >> 11 & 1); // {EXPANDED}
		ncount_10__52[0] = -(initial >> 12 & 1); // {EXPANDED}
		ncount_1__86[0] = -(initial >> 13 & 1); // {EXPANDED}
		ncount_2__91[0] = -(initial >> 14 & 1); // {EXPANDED}
		ncount_3__75[0] = -(initial >> 15 & 1); // {EXPANDED}
		ncount_4__96[0] = -(initial >> 16 & 1); // {EXPANDED}
		ncount_5__99[0] = -(initial >> 17 & 1); // {EXPANDED}
		ncount_6__89[0] = -(initial >> 18 & 1); // {EXPANDED}
		ncount_7__48[0] = -(initial >> 19 & 1); // {EXPANDED}
		ncount_8__61[0] = -(initial >> 20 & 1); // {EXPANDED}
		ncount_9__45[0] = -(initial >> 21 & 1); // {EXPANDED}

		// long[] {NON_STATE_BIT} = new long[cycles];
		long[] n_n196_50 = new long[cycles]; // {EXPANDED}
		long[] n_000__0__43 = new long[cycles]; // {EXPANDED}
		long[] n_000__10__70 = new long[cycles]; // {EXPANDED}
		long[] n_000__1__17 = new long[cycles]; // {EXPANDED}
		long[] n_000__2__11 = new long[cycles]; // {EXPANDED}
		long[] n_000__3__29 = new long[cycles]; // {EXPANDED}
		long[] n_000__4__59 = new long[cycles]; // {EXPANDED}
		long[] n_000__5__3 = new long[cycles]; // {EXPANDED}
		long[] n_000__6__10 = new long[cycles]; // {EXPANDED}
		long[] n_000__7__56 = new long[cycles]; // {EXPANDED}
		long[] n_000__8__1 = new long[cycles]; // {EXPANDED}
		long[] n_000__9__30 = new long[cycles]; // {EXPANDED}
		long[] n_001__0__71 = new long[cycles]; // {EXPANDED}
		long[] n_001__10__67 = new long[cycles]; // {EXPANDED}
		long[] n_001__1__98 = new long[cycles]; // {EXPANDED}
		long[] n_001__2__101 = new long[cycles]; // {EXPANDED}
		long[] n_001__3__76 = new long[cycles]; // {EXPANDED}
		long[] n_001__4__6 = new long[cycles]; // {EXPANDED}
		long[] n_001__5__72 = new long[cycles]; // {EXPANDED}
		long[] n_001__6__9 = new long[cycles]; // {EXPANDED}
		long[] n_001__7__19 = new long[cycles]; // {EXPANDED}
		long[] n_001__8__32 = new long[cycles]; // {EXPANDED}
		long[] n_001__9__33 = new long[cycles]; // {EXPANDED}
		long[] n_002__23 = new long[cycles]; // {EXPANDED}
		long[] n_003__65 = new long[cycles]; // {EXPANDED}
		long[] n_004__26 = new long[cycles]; // {EXPANDED}
		long[] n_005__47 = new long[cycles]; // {EXPANDED}
		long[] n_006__0 = new long[cycles]; // {EXPANDED}
		long[] n_007__7 = new long[cycles]; // {EXPANDED}
		long[] n_008__92 = new long[cycles]; // {EXPANDED}
		long[] n_009__83 = new long[cycles]; // {EXPANDED}
		long[] n_010__54 = new long[cycles]; // {EXPANDED}
		long[] n_011__60 = new long[cycles]; // {EXPANDED}
		long[] n_012__31 = new long[cycles]; // {EXPANDED}
		long[] n_013__37 = new long[cycles]; // {EXPANDED}
		long[] n_014__35 = new long[cycles]; // {EXPANDED}
		long[] n_015__69 = new long[cycles]; // {EXPANDED}
		long[] n_016__79 = new long[cycles]; // {EXPANDED}
		long[] n_017__84 = new long[cycles]; // {EXPANDED}
		long[] n_018__88 = new long[cycles]; // {EXPANDED}
		long[] n_019__38 = new long[cycles]; // {EXPANDED}
		long[] n_020__49 = new long[cycles]; // {EXPANDED}
		long[] n_021__94 = new long[cycles]; // {EXPANDED}
		long[] n_022__74 = new long[cycles]; // {EXPANDED}
		long[] n_023__82 = new long[cycles]; // {EXPANDED}
		long[] n_024__57 = new long[cycles]; // {EXPANDED}
		long[] n_025__55 = new long[cycles]; // {EXPANDED}
		long[] n_026__40 = new long[cycles]; // {EXPANDED}
		long[] n_027__78 = new long[cycles]; // {EXPANDED}
		long[] n_028__25 = new long[cycles]; // {EXPANDED}
		long[] n_029__81 = new long[cycles]; // {EXPANDED}
		long[] n_030__5 = new long[cycles]; // {EXPANDED}
		long[] n_031__100 = new long[cycles]; // {EXPANDED}
		long[] n_032__44 = new long[cycles]; // {EXPANDED}
		long[] n_033__66 = new long[cycles]; // {EXPANDED}
		long[] n_034__68 = new long[cycles]; // {EXPANDED}
		long[] n_035__63 = new long[cycles]; // {EXPANDED}
		long[] n_036__20 = new long[cycles]; // {EXPANDED}
		long[] n_037__42 = new long[cycles]; // {EXPANDED}
		long[] n_038__64 = new long[cycles]; // {EXPANDED}
		long[] n_039__62 = new long[cycles]; // {EXPANDED}
		long[] n_040__93 = new long[cycles]; // {EXPANDED}
		long[] n_041__18 = new long[cycles]; // {EXPANDED}
		long[] n_042__21 = new long[cycles]; // {EXPANDED}
		long[] n_043__28 = new long[cycles]; // {EXPANDED}
		long[] n_044__46 = new long[cycles]; // {EXPANDED}
		long[] n_045__85 = new long[cycles]; // {EXPANDED}
		long[] n_046__13 = new long[cycles]; // {EXPANDED}
		long[] n_047__53 = new long[cycles]; // {EXPANDED}
		long[] n_048__36 = new long[cycles]; // {EXPANDED}
		long[] n_049__41 = new long[cycles]; // {EXPANDED}
		long[] n_050__22 = new long[cycles]; // {EXPANDED}
		long[] nerr_73 = new long[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			nena1_14[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			nena2_58[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n_050__22[i] = 0; // {EXPANDED}
			n_047__53[i] = ~(ncount_4__96[i] & ncount_5__99[i]); // {EXPANDED}
			n_010__54[i] = ~ncount2_10__15[i]; // {EXPANDED}
			n_030__5[i] = ~(ncount2_6__4[i] & ncount2_7__87[i]); // {EXPANDED}
			n_007__7[i] = ~(ncount_9__45[i] & ncount_8__61[i]); // {EXPANDED}
			n_039__62[i] = ~(ncount_1__86[i] & ncount_0__51[i]); // {EXPANDED}
			n_035__63[i] = ~(ncount2_8__2[i] & ncount2_9__39[i]); // {EXPANDED}
			n_038__64[i] = ~nena1_14[i]; // {EXPANDED}
			n_042__21[i] = ~(ncount_3__75[i] & ncount_2__91[i]); // {EXPANDED}
			n_002__23[i] = ~(ncount_6__89[i] & ncount_7__48[i]); // {EXPANDED}
			n_001__0__71[i] = (nena1_14[i] ^ ncount_0__51[i]); // {EXPANDED}
			n_022__74[i] = ~(ncount2_2__24[i] & ncount2_3__34[i]); // {EXPANDED}
			n_027__78[i] = ~(ncount2_4__97[i] & ncount2_5__80[i]); // {EXPANDED}
			n_014__35[i] = ~ncount_10__52[i]; // {EXPANDED}
			n_017__84[i] = nena2_58[i] & ncount2_0__8[i]; // {EXPANDED}
			n_019__38[i] = ~(ncount2_0__8[i] & ncount2_1__12[i]); // {EXPANDED}
			n_018__88[i] = ~nena2_58[i]; // {EXPANDED}
			n_037__42[i] = nena1_14[i] & ncount_0__51[i]; // {EXPANDED}
			n_000__0__43[i] = (nena2_58[i] ^ ncount2_0__8[i]); // {EXPANDED}
			n_001__1__98[i] = (ncount_1__86[i] ^ n_037__42[i]); // {EXPANDED}
			n_023__82[i] = n_019__38[i] | n_022__74[i]; // {EXPANDED}
			n_026__40[i] = n_023__82[i] | n_018__88[i]; // {EXPANDED}
			n_028__25[i] = ~(n_026__40[i] | n_027__78[i]); // {EXPANDED}
			n_043__28[i] = n_042__21[i] | n_039__62[i]; // {EXPANDED}
			n_040__93[i] = ~(n_038__64[i] | n_039__62[i]); // {EXPANDED}
			n_000__1__17[i] = (n_017__84[i] ^ ncount2_1__12[i]); // {EXPANDED}
			n_024__57[i] = ~(n_023__82[i] | n_018__88[i]); // {EXPANDED}
			n_044__46[i] = ~(n_038__64[i] | n_043__28[i]); // {EXPANDED}
			n_003__65[i] = n_002__23[i] | n_047__53[i]; // {EXPANDED}
			n_011__60[i] = ~(n_010__54[i] | n_035__63[i]); // {EXPANDED}
			n_015__69[i] = ~(n_007__7[i] | n_014__35[i]); // {EXPANDED}
			n_020__49[i] = ~(n_019__38[i] | n_018__88[i]); // {EXPANDED}
			n_031__100[i] = n_030__5[i] | n_027__78[i]; // {EXPANDED}
			n_009__83[i] = ~(n_023__82[i] | n_031__100[i]); // {EXPANDED}
			n_046__13[i] = n_038__64[i] | n_043__28[i]; // {EXPANDED}
			n_006__0[i] = n_046__13[i] | n_003__65[i]; // {EXPANDED}
			n_045__85[i] = ncount_4__96[i] & n_044__46[i]; // {EXPANDED}
			n_013__37[i] = ~(n_003__65[i] | n_043__28[i]); // {EXPANDED}
			n_004__26[i] = ~(n_046__13[i] | n_003__65[i]); // {EXPANDED}
			n_025__55[i] = n_024__57[i] & ncount2_4__97[i]; // {EXPANDED}
			n_021__94[i] = ncount2_2__24[i] & n_020__49[i]; // {EXPANDED}
			n_032__44[i] = ~(n_026__40[i] | n_031__100[i]); // {EXPANDED}
			n_000__4__59[i] = (n_024__57[i] ^ ncount2_4__97[i]); // {EXPANDED}
			n_001__4__6[i] = (ncount_4__96[i] ^ n_044__46[i]); // {EXPANDED}
			n_012__31[i] = ~(n_009__83[i] & n_011__60[i]); // {EXPANDED}
			n_041__18[i] = n_040__93[i] & ncount_2__91[i]; // {EXPANDED}
			n_000__6__10[i] = (ncount2_6__4[i] ^ n_028__25[i]); // {EXPANDED}
			n_034__68[i] = n_026__40[i] | n_031__100[i]; // {EXPANDED}
			n_000__2__11[i] = (ncount2_2__24[i] ^ n_020__49[i]); // {EXPANDED}
			n_016__79[i] = ~(n_013__37[i] & n_015__69[i]); // {EXPANDED}
			n_029__81[i] = ncount2_6__4[i] & n_028__25[i]; // {EXPANDED}
			n_001__2__101[i] = (n_040__93[i] ^ ncount_2__91[i]); // {EXPANDED}
			n_000__8__1[i] = (n_032__44[i] ^ ncount2_8__2[i]); // {EXPANDED}
			n_001__5__72[i] = (n_045__85[i] ^ ncount_5__99[i]); // {EXPANDED}
			n_000__5__3[i] = (ncount2_5__80[i] ^ n_025__55[i]); // {EXPANDED}
			nerr_73[i] = ~(n_012__31[i] & n_016__79[i]); // {EXPANDED}
			n_008__92[i] = ~(n_006__0[i] | n_007__7[i]); // {EXPANDED}
			n_000__7__56[i] = (ncount2_7__87[i] ^ n_029__81[i]); // {EXPANDED}
			n_000__3__29[i] = (n_021__94[i] ^ ncount2_3__34[i]); // {EXPANDED}
			n_001__3__76[i] = (ncount_3__75[i] ^ n_041__18[i]); // {EXPANDED}
			n_001__8__32[i] = (n_004__26[i] ^ ncount_8__61[i]); // {EXPANDED}
			n_005__47[i] = n_004__26[i] & ncount_8__61[i]; // {EXPANDED}
			n_033__66[i] = n_032__44[i] & ncount2_8__2[i]; // {EXPANDED}
			n_036__20[i] = ~(n_034__68[i] | n_035__63[i]); // {EXPANDED}
			n_048__36[i] = ~(n_046__13[i] | n_047__53[i]); // {EXPANDED}
			n_049__41[i] = ncount_6__89[i] & n_048__36[i]; // {EXPANDED}
			n_000__9__30[i] = (ncount2_9__39[i] ^ n_033__66[i]); // {EXPANDED}
			n_001__9__33[i] = (ncount_9__45[i] ^ n_005__47[i]); // {EXPANDED}
			n_001__10__67[i] = (n_008__92[i] ^ ncount_10__52[i]); // {EXPANDED}
			n_001__6__9[i] = (ncount_6__89[i] ^ n_048__36[i]); // {EXPANDED}
			n_n196_50[i] = ~nerr_73[i]; // {EXPANDED}
			n_000__10__70[i] = (ncount2_10__15[i] ^ n_036__20[i]); // {EXPANDED}
			n_001__7__19[i] = (n_049__41[i] ^ ncount_7__48[i]); // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				ncount2_0__8[i+1] |= n_000__0__43[i]; // {EXPANDED}
				ncount2_10__15[i+1] |= n_000__10__70[i]; // {EXPANDED}
				ncount2_1__12[i+1] |= n_000__1__17[i]; // {EXPANDED}
				ncount2_2__24[i+1] |= n_000__2__11[i]; // {EXPANDED}
				ncount2_3__34[i+1] |= n_000__3__29[i]; // {EXPANDED}
				ncount2_4__97[i+1] |= n_000__4__59[i]; // {EXPANDED}
				ncount2_5__80[i+1] |= n_000__5__3[i]; // {EXPANDED}
				ncount2_6__4[i+1] |= n_000__6__10[i]; // {EXPANDED}
				ncount2_7__87[i+1] |= n_000__7__56[i]; // {EXPANDED}
				ncount2_8__2[i+1] |= n_000__8__1[i]; // {EXPANDED}
				ncount2_9__39[i+1] |= n_000__9__30[i]; // {EXPANDED}
				ncount_0__51[i+1] |= n_001__0__71[i]; // {EXPANDED}
				ncount_10__52[i+1] |= n_001__10__67[i]; // {EXPANDED}
				ncount_1__86[i+1] |= n_001__1__98[i]; // {EXPANDED}
				ncount_2__91[i+1] |= n_001__2__101[i]; // {EXPANDED}
				ncount_3__75[i+1] |= n_001__3__76[i]; // {EXPANDED}
				ncount_4__96[i+1] |= n_001__4__6[i]; // {EXPANDED}
				ncount_5__99[i+1] |= n_001__5__72[i]; // {EXPANDED}
				ncount_6__89[i+1] |= n_001__6__9[i]; // {EXPANDED}
				ncount_7__48[i+1] |= n_001__7__19[i]; // {EXPANDED}
				ncount_8__61[i+1] |= n_001__8__32[i]; // {EXPANDED}
				ncount_9__45[i+1] |= n_001__9__33[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<long[]> waveforms = new ArrayList<long[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(ncount2_0__8); // {EXPANDED}
		waveforms.add(ncount2_10__15); // {EXPANDED}
		waveforms.add(ncount2_1__12); // {EXPANDED}
		waveforms.add(ncount2_2__24); // {EXPANDED}
		waveforms.add(ncount2_3__34); // {EXPANDED}
		waveforms.add(ncount2_4__97); // {EXPANDED}
		waveforms.add(ncount2_5__80); // {EXPANDED}
		waveforms.add(ncount2_6__4); // {EXPANDED}
		waveforms.add(ncount2_7__87); // {EXPANDED}
		waveforms.add(ncount2_8__2); // {EXPANDED}
		waveforms.add(ncount2_9__39); // {EXPANDED}
		waveforms.add(ncount_0__51); // {EXPANDED}
		waveforms.add(ncount_10__52); // {EXPANDED}
		waveforms.add(ncount_1__86); // {EXPANDED}
		waveforms.add(ncount_2__91); // {EXPANDED}
		waveforms.add(ncount_3__75); // {EXPANDED}
		waveforms.add(ncount_4__96); // {EXPANDED}
		waveforms.add(ncount_5__99); // {EXPANDED}
		waveforms.add(ncount_6__89); // {EXPANDED}
		waveforms.add(ncount_7__48); // {EXPANDED}
		waveforms.add(ncount_8__61); // {EXPANDED}
		waveforms.add(ncount_9__45); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(nena1_14); // {EXPANDED}
		waveforms.add(nena2_58); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n_n196_50); // {EXPANDED}
		waveforms.add(n_000__0__43); // {EXPANDED}
		waveforms.add(n_000__10__70); // {EXPANDED}
		waveforms.add(n_000__1__17); // {EXPANDED}
		waveforms.add(n_000__2__11); // {EXPANDED}
		waveforms.add(n_000__3__29); // {EXPANDED}
		waveforms.add(n_000__4__59); // {EXPANDED}
		waveforms.add(n_000__5__3); // {EXPANDED}
		waveforms.add(n_000__6__10); // {EXPANDED}
		waveforms.add(n_000__7__56); // {EXPANDED}
		waveforms.add(n_000__8__1); // {EXPANDED}
		waveforms.add(n_000__9__30); // {EXPANDED}
		waveforms.add(n_001__0__71); // {EXPANDED}
		waveforms.add(n_001__10__67); // {EXPANDED}
		waveforms.add(n_001__1__98); // {EXPANDED}
		waveforms.add(n_001__2__101); // {EXPANDED}
		waveforms.add(n_001__3__76); // {EXPANDED}
		waveforms.add(n_001__4__6); // {EXPANDED}
		waveforms.add(n_001__5__72); // {EXPANDED}
		waveforms.add(n_001__6__9); // {EXPANDED}
		waveforms.add(n_001__7__19); // {EXPANDED}
		waveforms.add(n_001__8__32); // {EXPANDED}
		waveforms.add(n_001__9__33); // {EXPANDED}
		waveforms.add(n_002__23); // {EXPANDED}
		waveforms.add(n_003__65); // {EXPANDED}
		waveforms.add(n_004__26); // {EXPANDED}
		waveforms.add(n_005__47); // {EXPANDED}
		waveforms.add(n_006__0); // {EXPANDED}
		waveforms.add(n_007__7); // {EXPANDED}
		waveforms.add(n_008__92); // {EXPANDED}
		waveforms.add(n_009__83); // {EXPANDED}
		waveforms.add(n_010__54); // {EXPANDED}
		waveforms.add(n_011__60); // {EXPANDED}
		waveforms.add(n_012__31); // {EXPANDED}
		waveforms.add(n_013__37); // {EXPANDED}
		waveforms.add(n_014__35); // {EXPANDED}
		waveforms.add(n_015__69); // {EXPANDED}
		waveforms.add(n_016__79); // {EXPANDED}
		waveforms.add(n_017__84); // {EXPANDED}
		waveforms.add(n_018__88); // {EXPANDED}
		waveforms.add(n_019__38); // {EXPANDED}
		waveforms.add(n_020__49); // {EXPANDED}
		waveforms.add(n_021__94); // {EXPANDED}
		waveforms.add(n_022__74); // {EXPANDED}
		waveforms.add(n_023__82); // {EXPANDED}
		waveforms.add(n_024__57); // {EXPANDED}
		waveforms.add(n_025__55); // {EXPANDED}
		waveforms.add(n_026__40); // {EXPANDED}
		waveforms.add(n_027__78); // {EXPANDED}
		waveforms.add(n_028__25); // {EXPANDED}
		waveforms.add(n_029__81); // {EXPANDED}
		waveforms.add(n_030__5); // {EXPANDED}
		waveforms.add(n_031__100); // {EXPANDED}
		waveforms.add(n_032__44); // {EXPANDED}
		waveforms.add(n_033__66); // {EXPANDED}
		waveforms.add(n_034__68); // {EXPANDED}
		waveforms.add(n_035__63); // {EXPANDED}
		waveforms.add(n_036__20); // {EXPANDED}
		waveforms.add(n_037__42); // {EXPANDED}
		waveforms.add(n_038__64); // {EXPANDED}
		waveforms.add(n_039__62); // {EXPANDED}
		waveforms.add(n_040__93); // {EXPANDED}
		waveforms.add(n_041__18); // {EXPANDED}
		waveforms.add(n_042__21); // {EXPANDED}
		waveforms.add(n_043__28); // {EXPANDED}
		waveforms.add(n_044__46); // {EXPANDED}
		waveforms.add(n_045__85); // {EXPANDED}
		waveforms.add(n_046__13); // {EXPANDED}
		waveforms.add(n_047__53); // {EXPANDED}
		waveforms.add(n_048__36); // {EXPANDED}
		waveforms.add(n_049__41); // {EXPANDED}
		waveforms.add(n_050__22); // {EXPANDED}
		waveforms.add(nerr_73); // {EXPANDED}
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
