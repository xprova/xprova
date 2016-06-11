package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public static void main(String args[]) throws Exception {

		CodeSimulator sim1 = new CodeSimulator();

		int initial = sim1.getResetState();

		boolean generateCounterExample = args.length > 0 && "gen-counter-example".equals(args[0]);

		int[] counterExample = sim1.exploreSpace(initial);

		if (counterExample != null && generateCounterExample)
			sim1.simulate(initial, counterExample);

	}

	public int getResetState() {

		// return {RESET_STATE};
		return 0x8001; // {EXPANDED}

	}

	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		//@formatter:off
		// final int STATE_BUF_SIZE = 1 << {STATE_BIT_COUNT};
		final int STATE_BUF_SIZE = 1 << 27; // {EXPANDED}
		//@formatter:on

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 27; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 15; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int secret_0_ = -(initial >> 0 & 1); // {EXPANDED}
		int secret_10_ = -(initial >> 1 & 1); // {EXPANDED}
		int secret_11_ = -(initial >> 2 & 1); // {EXPANDED}
		int secret_12_ = -(initial >> 3 & 1); // {EXPANDED}
		int secret_13_ = -(initial >> 4 & 1); // {EXPANDED}
		int secret_14_ = -(initial >> 5 & 1); // {EXPANDED}
		int secret_1_ = -(initial >> 6 & 1); // {EXPANDED}
		int secret_2_ = -(initial >> 7 & 1); // {EXPANDED}
		int secret_3_ = -(initial >> 8 & 1); // {EXPANDED}
		int secret_4_ = -(initial >> 9 & 1); // {EXPANDED}
		int secret_5_ = -(initial >> 10 & 1); // {EXPANDED}
		int secret_6_ = -(initial >> 11 & 1); // {EXPANDED}
		int secret_7_ = -(initial >> 12 & 1); // {EXPANDED}
		int secret_8_ = -(initial >> 13 & 1); // {EXPANDED}
		int secret_9_ = -(initial >> 14 & 1); // {EXPANDED}
		int state_0_ = -(initial >> 15 & 1); // {EXPANDED}
		int state_10_ = -(initial >> 16 & 1); // {EXPANDED}
		int state_11_ = -(initial >> 17 & 1); // {EXPANDED}
		int state_1_ = -(initial >> 18 & 1); // {EXPANDED}
		int state_2_ = -(initial >> 19 & 1); // {EXPANDED}
		int state_3_ = -(initial >> 20 & 1); // {EXPANDED}
		int state_4_ = -(initial >> 21 & 1); // {EXPANDED}
		int state_5_ = -(initial >> 22 & 1); // {EXPANDED}
		int state_6_ = -(initial >> 23 & 1); // {EXPANDED}
		int state_7_ = -(initial >> 24 & 1); // {EXPANDED}
		int state_8_ = -(initial >> 25 & 1); // {EXPANDED}
		int state_9_ = -(initial >> 26 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n1; // {EXPANDED}
		int n10; // {EXPANDED}
		int n100; // {EXPANDED}
		int n101; // {EXPANDED}
		int n102; // {EXPANDED}
		int n103; // {EXPANDED}
		int n104; // {EXPANDED}
		int n105; // {EXPANDED}
		int n106; // {EXPANDED}
		int n107; // {EXPANDED}
		int n108; // {EXPANDED}
		int n109; // {EXPANDED}
		int n11; // {EXPANDED}
		int n110; // {EXPANDED}
		int n111; // {EXPANDED}
		int n112; // {EXPANDED}
		int n113; // {EXPANDED}
		int n114; // {EXPANDED}
		int n115; // {EXPANDED}
		int n116; // {EXPANDED}
		int n117; // {EXPANDED}
		int n118; // {EXPANDED}
		int n119; // {EXPANDED}
		int n12; // {EXPANDED}
		int n120; // {EXPANDED}
		int n121; // {EXPANDED}
		int n122; // {EXPANDED}
		int n123; // {EXPANDED}
		int n124; // {EXPANDED}
		int n125; // {EXPANDED}
		int n126; // {EXPANDED}
		int n13; // {EXPANDED}
		int n14; // {EXPANDED}
		int n15; // {EXPANDED}
		int n16; // {EXPANDED}
		int n17; // {EXPANDED}
		int n18; // {EXPANDED}
		int n19; // {EXPANDED}
		int n2; // {EXPANDED}
		int n20; // {EXPANDED}
		int n21; // {EXPANDED}
		int n22; // {EXPANDED}
		int n23; // {EXPANDED}
		int n24; // {EXPANDED}
		int n25; // {EXPANDED}
		int n26; // {EXPANDED}
		int n27; // {EXPANDED}
		int n28; // {EXPANDED}
		int n29; // {EXPANDED}
		int n3; // {EXPANDED}
		int n30; // {EXPANDED}
		int n31; // {EXPANDED}
		int n32; // {EXPANDED}
		int n33; // {EXPANDED}
		int n34; // {EXPANDED}
		int n35; // {EXPANDED}
		int n36; // {EXPANDED}
		int n37; // {EXPANDED}
		int n38; // {EXPANDED}
		int n39; // {EXPANDED}
		int n4; // {EXPANDED}
		int n40; // {EXPANDED}
		int n41; // {EXPANDED}
		int n42; // {EXPANDED}
		int n43; // {EXPANDED}
		int n44; // {EXPANDED}
		int n45; // {EXPANDED}
		int n46; // {EXPANDED}
		int n47; // {EXPANDED}
		int n48; // {EXPANDED}
		int n49; // {EXPANDED}
		int n5; // {EXPANDED}
		int n50; // {EXPANDED}
		int n51; // {EXPANDED}
		int n52; // {EXPANDED}
		int n53; // {EXPANDED}
		int n54; // {EXPANDED}
		int n55; // {EXPANDED}
		int n56; // {EXPANDED}
		int n57; // {EXPANDED}
		int n58; // {EXPANDED}
		int n59; // {EXPANDED}
		int n6; // {EXPANDED}
		int n60; // {EXPANDED}
		int n61; // {EXPANDED}
		int n62; // {EXPANDED}
		int n63; // {EXPANDED}
		int n64; // {EXPANDED}
		int n65; // {EXPANDED}
		int n66; // {EXPANDED}
		int n67; // {EXPANDED}
		int n68; // {EXPANDED}
		int n69; // {EXPANDED}
		int n7; // {EXPANDED}
		int n70; // {EXPANDED}
		int n71; // {EXPANDED}
		int n72; // {EXPANDED}
		int n73; // {EXPANDED}
		int n74; // {EXPANDED}
		int n75; // {EXPANDED}
		int n76; // {EXPANDED}
		int n77; // {EXPANDED}
		int n78; // {EXPANDED}
		int n79; // {EXPANDED}
		int n8; // {EXPANDED}
		int n80; // {EXPANDED}
		int n81; // {EXPANDED}
		int n82; // {EXPANDED}
		int n83; // {EXPANDED}
		int n84; // {EXPANDED}
		int n85; // {EXPANDED}
		int n86; // {EXPANDED}
		int n87; // {EXPANDED}
		int n88; // {EXPANDED}
		int n89; // {EXPANDED}
		int n9; // {EXPANDED}
		int n90; // {EXPANDED}
		int n91; // {EXPANDED}
		int n92; // {EXPANDED}
		int n93; // {EXPANDED}
		int n94; // {EXPANDED}
		int n95; // {EXPANDED}
		int n96; // {EXPANDED}
		int n97; // {EXPANDED}
		int n98; // {EXPANDED}
		int n99; // {EXPANDED}
		int valid; // {EXPANDED}
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
				secret_0_ = -(state >> 0 & 1); // {EXPANDED}
				secret_10_ = -(state >> 1 & 1); // {EXPANDED}
				secret_11_ = -(state >> 2 & 1); // {EXPANDED}
				secret_12_ = -(state >> 3 & 1); // {EXPANDED}
				secret_13_ = -(state >> 4 & 1); // {EXPANDED}
				secret_14_ = -(state >> 5 & 1); // {EXPANDED}
				secret_1_ = -(state >> 6 & 1); // {EXPANDED}
				secret_2_ = -(state >> 7 & 1); // {EXPANDED}
				secret_3_ = -(state >> 8 & 1); // {EXPANDED}
				secret_4_ = -(state >> 9 & 1); // {EXPANDED}
				secret_5_ = -(state >> 10 & 1); // {EXPANDED}
				secret_6_ = -(state >> 11 & 1); // {EXPANDED}
				secret_7_ = -(state >> 12 & 1); // {EXPANDED}
				secret_8_ = -(state >> 13 & 1); // {EXPANDED}
				secret_9_ = -(state >> 14 & 1); // {EXPANDED}
				state_0_ = -(state >> 15 & 1); // {EXPANDED}
				state_10_ = -(state >> 16 & 1); // {EXPANDED}
				state_11_ = -(state >> 17 & 1); // {EXPANDED}
				state_1_ = -(state >> 18 & 1); // {EXPANDED}
				state_2_ = -(state >> 19 & 1); // {EXPANDED}
				state_3_ = -(state >> 20 & 1); // {EXPANDED}
				state_4_ = -(state >> 21 & 1); // {EXPANDED}
				state_5_ = -(state >> 22 & 1); // {EXPANDED}
				state_6_ = -(state >> 23 & 1); // {EXPANDED}
				state_7_ = -(state >> 24 & 1); // {EXPANDED}
				state_8_ = -(state >> 25 & 1); // {EXPANDED}
				state_9_ = -(state >> 26 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int code_0_ = -(in >> 0 & 1); // {EXPANDED}
					int code_10_ = -(in >> 1 & 1); // {EXPANDED}
					int code_11_ = -(in >> 2 & 1); // {EXPANDED}
					int code_12_ = -(in >> 3 & 1); // {EXPANDED}
					int code_13_ = -(in >> 4 & 1); // {EXPANDED}
					int code_14_ = -(in >> 5 & 1); // {EXPANDED}
					int code_1_ = -(in >> 6 & 1); // {EXPANDED}
					int code_2_ = -(in >> 7 & 1); // {EXPANDED}
					int code_3_ = -(in >> 8 & 1); // {EXPANDED}
					int code_4_ = -(in >> 9 & 1); // {EXPANDED}
					int code_5_ = -(in >> 10 & 1); // {EXPANDED}
					int code_6_ = -(in >> 11 & 1); // {EXPANDED}
					int code_7_ = -(in >> 12 & 1); // {EXPANDED}
					int code_8_ = -(in >> 13 & 1); // {EXPANDED}
					int code_9_ = -(in >> 14 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n114 = (code_3_ ^ secret_3_); // {EXPANDED}
					n100 = (secret_14_ ^ code_14_); // {EXPANDED}
					n87 = ~(state_4_ | state_3_); // {EXPANDED}
					n8 = (secret_5_ ^ code_5_); // {EXPANDED}
					n82 = state_8_ | state_7_; // {EXPANDED}
					n119 = (secret_11_ ^ code_11_); // {EXPANDED}
					n1 = (secret_10_ ^ code_10_); // {EXPANDED}
					n116 = (secret_4_ ^ code_4_); // {EXPANDED}
					n125 = (code_9_ ^ secret_9_); // {EXPANDED}
					n5 = (secret_8_ ^ code_8_); // {EXPANDED}
					n106 = (code_2_ ^ secret_2_); // {EXPANDED}
					n4 = (code_7_ ^ secret_7_); // {EXPANDED}
					n42 = (secret_14_ ^ secret_13_); // {EXPANDED}
					n91 = ~state_10_; // {EXPANDED}
					n94 = ~(state_0_ | state_1_); // {EXPANDED}
					n126 = (code_0_ ^ secret_0_); // {EXPANDED}
					n88 = ~(state_5_ | state_2_); // {EXPANDED}
					n84 = state_6_ | state_9_; // {EXPANDED}
					n46 = (state_3_ ^ state_9_); // {EXPANDED}
					n108 = (code_6_ ^ secret_6_); // {EXPANDED}
					n121 = (secret_12_ ^ code_12_); // {EXPANDED}
					n7 = (code_1_ ^ secret_1_); // {EXPANDED}
					n102 = (code_13_ ^ secret_13_); // {EXPANDED}
					n90 = ~(n87 & n88); // {EXPANDED}
					n104 = n100 | n102; // {EXPANDED}
					n122 = n121 | n119; // {EXPANDED}
					n118 = n114 | n116; // {EXPANDED}
					n93 = n91 & state_11_; // {EXPANDED}
					n96 = ~(n93 & n94); // {EXPANDED}
					n85 = ~(n82 | n84); // {EXPANDED}
					n110 = n106 | n108; // {EXPANDED}
					n2 = n126 | n1; // {EXPANDED}
					n6 = n5 | n4; // {EXPANDED}
					n47 = (n46 ^ n91); // {EXPANDED}
					n9 = n8 | n7; // {EXPANDED}
					n3 = n2 | n125; // {EXPANDED}
					n123 = n118 | n122; // {EXPANDED}
					n48 = (state_11_ ^ n47); // {EXPANDED}
					n112 = n110 | n104; // {EXPANDED}
					n10 = n6 | n9; // {EXPANDED}
					n97 = ~(n90 | n96); // {EXPANDED}
					n11 = n3 | n10; // {EXPANDED}
					valid = ~(n85 & n97); // {EXPANDED}
					n124 = n123 | n112; // {EXPANDED}
					n14 = n11 | n124; // {EXPANDED}
					n12 = ~(n11 | n124); // {EXPANDED}
					n24 = ~(n12 | secret_6_); // {EXPANDED}
					n33 = ~(n14 | secret_9_); // {EXPANDED}
					n34 = ~(secret_11_ | n12); // {EXPANDED}
					n51 = ~(state_0_ | n14); // {EXPANDED}
					n15 = ~(n14 | secret_0_); // {EXPANDED}
					n71 = ~(n14 | state_10_); // {EXPANDED}
					n52 = ~(n12 | state_2_); // {EXPANDED}
					n19 = ~(n14 | secret_2_); // {EXPANDED}
					n57 = ~(n14 | state_3_); // {EXPANDED}
					n40 = ~(secret_14_ | n12); // {EXPANDED}
					n32 = ~(secret_10_ | n12); // {EXPANDED}
					n70 = ~(state_11_ | n12); // {EXPANDED}
					n26 = ~(n12 | secret_7_); // {EXPANDED}
					n62 = ~(n12 | state_7_); // {EXPANDED}
					n56 = ~(n12 | state_4_); // {EXPANDED}
					n17 = ~(n14 | secret_1_); // {EXPANDED}
					n45 = ~(state_0_ | n12); // {EXPANDED}
					n55 = ~(n14 | state_2_); // {EXPANDED}
					n31 = ~(n14 | secret_8_); // {EXPANDED}
					n43 = ~(n42 & n12); // {EXPANDED}
					n58 = ~(state_5_ | n12); // {EXPANDED}
					n27 = ~(n14 | secret_6_); // {EXPANDED}
					n29 = ~(n14 | secret_7_); // {EXPANDED}
					n66 = ~(n12 | state_9_); // {EXPANDED}
					n18 = ~(secret_3_ | n12); // {EXPANDED}
					n23 = ~(secret_4_ | n14); // {EXPANDED}
					n68 = ~(n12 | state_10_); // {EXPANDED}
					n13 = ~(n12 | secret_1_); // {EXPANDED}
					n21 = ~(n14 | secret_3_); // {EXPANDED}
					n22 = ~(secret_5_ | n12); // {EXPANDED}
					n69 = ~(n14 | state_9_); // {EXPANDED}
					n65 = ~(n14 | state_7_); // {EXPANDED}
					n30 = ~(n12 | secret_9_); // {EXPANDED}
					n39 = ~(n14 | secret_12_); // {EXPANDED}
					n28 = ~(secret_8_ | n12); // {EXPANDED}
					n38 = ~(n12 | secret_13_); // {EXPANDED}
					n41 = ~(n14 | secret_13_); // {EXPANDED}
					n59 = ~(n14 | state_4_); // {EXPANDED}
					n64 = ~(n12 | state_8_); // {EXPANDED}
					n67 = ~(n14 | state_8_); // {EXPANDED}
					n37 = ~(n14 | secret_11_); // {EXPANDED}
					n53 = ~(state_1_ | n14); // {EXPANDED}
					n50 = ~(state_1_ | n12); // {EXPANDED}
					n25 = ~(secret_5_ | n14); // {EXPANDED}
					n60 = ~(state_6_ | n12); // {EXPANDED}
					n54 = ~(n12 | state_3_); // {EXPANDED}
					n35 = ~(secret_10_ | n14); // {EXPANDED}
					n44 = ~(n14 & secret_0_); // {EXPANDED}
					n63 = ~(n14 | state_6_); // {EXPANDED}
					n20 = ~(secret_4_ | n12); // {EXPANDED}
					n61 = ~(n14 | state_5_); // {EXPANDED}
					n36 = ~(n12 | secret_12_); // {EXPANDED}
					n49 = n48 & n12; // {EXPANDED}
					n16 = ~(secret_2_ | n12); // {EXPANDED}
					n89 = ~(n39 | n38); // {EXPANDED}
					n77 = ~(n24 | n25); // {EXPANDED}
					n95 = ~(n44 & n43); // {EXPANDED}
					n92 = ~(n41 | n40); // {EXPANDED}
					n105 = ~(n57 | n56); // {EXPANDED}
					n75 = ~(n20 | n21); // {EXPANDED}
					n113 = ~(n65 | n64); // {EXPANDED}
					n83 = ~(n35 | n34); // {EXPANDED}
					n76 = ~(n23 | n22); // {EXPANDED}
					n73 = ~(n16 | n17); // {EXPANDED}
					n80 = ~(n30 | n31); // {EXPANDED}
					n115 = ~(n66 | n67); // {EXPANDED}
					n120 = ~(n70 | n71); // {EXPANDED}
					n103 = ~(n55 | n54); // {EXPANDED}
					n86 = ~(n36 | n37); // {EXPANDED}
					n98 = ~(n45 | n49); // {EXPANDED}
					n109 = ~(n61 | n60); // {EXPANDED}
					n72 = ~(n13 | n15); // {EXPANDED}
					n111 = ~(n63 | n62); // {EXPANDED}
					n74 = ~(n18 | n19); // {EXPANDED}
					n78 = ~(n27 | n26); // {EXPANDED}
					n81 = ~(n33 | n32); // {EXPANDED}
					n117 = ~(n68 | n69); // {EXPANDED}
					n79 = ~(n29 | n28); // {EXPANDED}
					n107 = ~(n59 | n58); // {EXPANDED}
					n99 = ~(n50 | n51); // {EXPANDED}
					n101 = ~(n52 | n53); // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= n95 & (1 << 0); // {EXPANDED}
					nxState |= n81 & (1 << 1); // {EXPANDED}
					nxState |= n83 & (1 << 2); // {EXPANDED}
					nxState |= n86 & (1 << 3); // {EXPANDED}
					nxState |= n89 & (1 << 4); // {EXPANDED}
					nxState |= n92 & (1 << 5); // {EXPANDED}
					nxState |= n72 & (1 << 6); // {EXPANDED}
					nxState |= n73 & (1 << 7); // {EXPANDED}
					nxState |= n74 & (1 << 8); // {EXPANDED}
					nxState |= n75 & (1 << 9); // {EXPANDED}
					nxState |= n76 & (1 << 10); // {EXPANDED}
					nxState |= n77 & (1 << 11); // {EXPANDED}
					nxState |= n78 & (1 << 12); // {EXPANDED}
					nxState |= n79 & (1 << 13); // {EXPANDED}
					nxState |= n80 & (1 << 14); // {EXPANDED}
					nxState |= n98 & (1 << 15); // {EXPANDED}
					nxState |= n117 & (1 << 16); // {EXPANDED}
					nxState |= n120 & (1 << 17); // {EXPANDED}
					nxState |= n99 & (1 << 18); // {EXPANDED}
					nxState |= n101 & (1 << 19); // {EXPANDED}
					nxState |= n103 & (1 << 20); // {EXPANDED}
					nxState |= n105 & (1 << 21); // {EXPANDED}
					nxState |= n107 & (1 << 22); // {EXPANDED}
					nxState |= n109 & (1 << 23); // {EXPANDED}
					nxState |= n111 & (1 << 24); // {EXPANDED}
					nxState |= n113 & (1 << 25); // {EXPANDED}
					nxState |= n115 & (1 << 26); // {EXPANDED}
					//@formatter:on

					if (parentState[nxState] == UNDISCOVERED) {

						toVisitNextArr[toVisitNextArrOccupied] = nxState;

						toVisitNextArrOccupied += 1;

						parentState[nxState] = state;

						inputVector[nxState] = in;

					}

					if (valid == 0) {

						violationState = nxState;

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
		// result.add("{STATE_BIT}");
		result.add("secret_0_"); // {EXPANDED}
		result.add("secret_10_"); // {EXPANDED}
		result.add("secret_11_"); // {EXPANDED}
		result.add("secret_12_"); // {EXPANDED}
		result.add("secret_13_"); // {EXPANDED}
		result.add("secret_14_"); // {EXPANDED}
		result.add("secret_1_"); // {EXPANDED}
		result.add("secret_2_"); // {EXPANDED}
		result.add("secret_3_"); // {EXPANDED}
		result.add("secret_4_"); // {EXPANDED}
		result.add("secret_5_"); // {EXPANDED}
		result.add("secret_6_"); // {EXPANDED}
		result.add("secret_7_"); // {EXPANDED}
		result.add("secret_8_"); // {EXPANDED}
		result.add("secret_9_"); // {EXPANDED}
		result.add("state_0_"); // {EXPANDED}
		result.add("state_10_"); // {EXPANDED}
		result.add("state_11_"); // {EXPANDED}
		result.add("state_1_"); // {EXPANDED}
		result.add("state_2_"); // {EXPANDED}
		result.add("state_3_"); // {EXPANDED}
		result.add("state_4_"); // {EXPANDED}
		result.add("state_5_"); // {EXPANDED}
		result.add("state_6_"); // {EXPANDED}
		result.add("state_7_"); // {EXPANDED}
		result.add("state_8_"); // {EXPANDED}
		result.add("state_9_"); // {EXPANDED}

		// result.add("{INPUT_BIT}");
		result.add("code_0_"); // {EXPANDED}
		result.add("code_10_"); // {EXPANDED}
		result.add("code_11_"); // {EXPANDED}
		result.add("code_12_"); // {EXPANDED}
		result.add("code_13_"); // {EXPANDED}
		result.add("code_14_"); // {EXPANDED}
		result.add("code_1_"); // {EXPANDED}
		result.add("code_2_"); // {EXPANDED}
		result.add("code_3_"); // {EXPANDED}
		result.add("code_4_"); // {EXPANDED}
		result.add("code_5_"); // {EXPANDED}
		result.add("code_6_"); // {EXPANDED}
		result.add("code_7_"); // {EXPANDED}
		result.add("code_8_"); // {EXPANDED}
		result.add("code_9_"); // {EXPANDED}

		// result.add("{NON_STATE_BIT}");
		result.add("n1"); // {EXPANDED}
		result.add("n10"); // {EXPANDED}
		result.add("n100"); // {EXPANDED}
		result.add("n101"); // {EXPANDED}
		result.add("n102"); // {EXPANDED}
		result.add("n103"); // {EXPANDED}
		result.add("n104"); // {EXPANDED}
		result.add("n105"); // {EXPANDED}
		result.add("n106"); // {EXPANDED}
		result.add("n107"); // {EXPANDED}
		result.add("n108"); // {EXPANDED}
		result.add("n109"); // {EXPANDED}
		result.add("n11"); // {EXPANDED}
		result.add("n110"); // {EXPANDED}
		result.add("n111"); // {EXPANDED}
		result.add("n112"); // {EXPANDED}
		result.add("n113"); // {EXPANDED}
		result.add("n114"); // {EXPANDED}
		result.add("n115"); // {EXPANDED}
		result.add("n116"); // {EXPANDED}
		result.add("n117"); // {EXPANDED}
		result.add("n118"); // {EXPANDED}
		result.add("n119"); // {EXPANDED}
		result.add("n12"); // {EXPANDED}
		result.add("n120"); // {EXPANDED}
		result.add("n121"); // {EXPANDED}
		result.add("n122"); // {EXPANDED}
		result.add("n123"); // {EXPANDED}
		result.add("n124"); // {EXPANDED}
		result.add("n125"); // {EXPANDED}
		result.add("n126"); // {EXPANDED}
		result.add("n13"); // {EXPANDED}
		result.add("n14"); // {EXPANDED}
		result.add("n15"); // {EXPANDED}
		result.add("n16"); // {EXPANDED}
		result.add("n17"); // {EXPANDED}
		result.add("n18"); // {EXPANDED}
		result.add("n19"); // {EXPANDED}
		result.add("n2"); // {EXPANDED}
		result.add("n20"); // {EXPANDED}
		result.add("n21"); // {EXPANDED}
		result.add("n22"); // {EXPANDED}
		result.add("n23"); // {EXPANDED}
		result.add("n24"); // {EXPANDED}
		result.add("n25"); // {EXPANDED}
		result.add("n26"); // {EXPANDED}
		result.add("n27"); // {EXPANDED}
		result.add("n28"); // {EXPANDED}
		result.add("n29"); // {EXPANDED}
		result.add("n3"); // {EXPANDED}
		result.add("n30"); // {EXPANDED}
		result.add("n31"); // {EXPANDED}
		result.add("n32"); // {EXPANDED}
		result.add("n33"); // {EXPANDED}
		result.add("n34"); // {EXPANDED}
		result.add("n35"); // {EXPANDED}
		result.add("n36"); // {EXPANDED}
		result.add("n37"); // {EXPANDED}
		result.add("n38"); // {EXPANDED}
		result.add("n39"); // {EXPANDED}
		result.add("n4"); // {EXPANDED}
		result.add("n40"); // {EXPANDED}
		result.add("n41"); // {EXPANDED}
		result.add("n42"); // {EXPANDED}
		result.add("n43"); // {EXPANDED}
		result.add("n44"); // {EXPANDED}
		result.add("n45"); // {EXPANDED}
		result.add("n46"); // {EXPANDED}
		result.add("n47"); // {EXPANDED}
		result.add("n48"); // {EXPANDED}
		result.add("n49"); // {EXPANDED}
		result.add("n5"); // {EXPANDED}
		result.add("n50"); // {EXPANDED}
		result.add("n51"); // {EXPANDED}
		result.add("n52"); // {EXPANDED}
		result.add("n53"); // {EXPANDED}
		result.add("n54"); // {EXPANDED}
		result.add("n55"); // {EXPANDED}
		result.add("n56"); // {EXPANDED}
		result.add("n57"); // {EXPANDED}
		result.add("n58"); // {EXPANDED}
		result.add("n59"); // {EXPANDED}
		result.add("n6"); // {EXPANDED}
		result.add("n60"); // {EXPANDED}
		result.add("n61"); // {EXPANDED}
		result.add("n62"); // {EXPANDED}
		result.add("n63"); // {EXPANDED}
		result.add("n64"); // {EXPANDED}
		result.add("n65"); // {EXPANDED}
		result.add("n66"); // {EXPANDED}
		result.add("n67"); // {EXPANDED}
		result.add("n68"); // {EXPANDED}
		result.add("n69"); // {EXPANDED}
		result.add("n7"); // {EXPANDED}
		result.add("n70"); // {EXPANDED}
		result.add("n71"); // {EXPANDED}
		result.add("n72"); // {EXPANDED}
		result.add("n73"); // {EXPANDED}
		result.add("n74"); // {EXPANDED}
		result.add("n75"); // {EXPANDED}
		result.add("n76"); // {EXPANDED}
		result.add("n77"); // {EXPANDED}
		result.add("n78"); // {EXPANDED}
		result.add("n79"); // {EXPANDED}
		result.add("n8"); // {EXPANDED}
		result.add("n80"); // {EXPANDED}
		result.add("n81"); // {EXPANDED}
		result.add("n82"); // {EXPANDED}
		result.add("n83"); // {EXPANDED}
		result.add("n84"); // {EXPANDED}
		result.add("n85"); // {EXPANDED}
		result.add("n86"); // {EXPANDED}
		result.add("n87"); // {EXPANDED}
		result.add("n88"); // {EXPANDED}
		result.add("n89"); // {EXPANDED}
		result.add("n9"); // {EXPANDED}
		result.add("n90"); // {EXPANDED}
		result.add("n91"); // {EXPANDED}
		result.add("n92"); // {EXPANDED}
		result.add("n93"); // {EXPANDED}
		result.add("n94"); // {EXPANDED}
		result.add("n95"); // {EXPANDED}
		result.add("n96"); // {EXPANDED}
		result.add("n97"); // {EXPANDED}
		result.add("n98"); // {EXPANDED}
		result.add("n99"); // {EXPANDED}
		result.add("valid"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 27; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 15; // {EXPANDED}
		//@formatter:on
	}

	public void simulate(int initial, int[] inputs) {

		ArrayList<String> sigNames = getSignalNames();

		int cycles = inputs.length;

		ArrayList<int[]> waveforms = simulate_internal(initial, inputs);

		System.out.printf("%10s : ", "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		System.out.println();

		System.out.println();

		for (int j = 0; j < waveforms.size(); j++) {

			if (j == getStateBitCount())
				System.out.println();

			int[] sig = waveforms.get(j);

			System.out.printf("%10s : ", sigNames.get(j));

			for (int i = 0; i < cycles; i++) {

				System.out.printf((sig[i] == H) ? "1" : "0");

			}

			System.out.println();

		}

	}

	private ArrayList<int[]> simulate_internal(int initial, int[] inputs) {

		int cycles = inputs.length;

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] secret_0_ = new int[cycles]; // {EXPANDED}
		int[] secret_10_ = new int[cycles]; // {EXPANDED}
		int[] secret_11_ = new int[cycles]; // {EXPANDED}
		int[] secret_12_ = new int[cycles]; // {EXPANDED}
		int[] secret_13_ = new int[cycles]; // {EXPANDED}
		int[] secret_14_ = new int[cycles]; // {EXPANDED}
		int[] secret_1_ = new int[cycles]; // {EXPANDED}
		int[] secret_2_ = new int[cycles]; // {EXPANDED}
		int[] secret_3_ = new int[cycles]; // {EXPANDED}
		int[] secret_4_ = new int[cycles]; // {EXPANDED}
		int[] secret_5_ = new int[cycles]; // {EXPANDED}
		int[] secret_6_ = new int[cycles]; // {EXPANDED}
		int[] secret_7_ = new int[cycles]; // {EXPANDED}
		int[] secret_8_ = new int[cycles]; // {EXPANDED}
		int[] secret_9_ = new int[cycles]; // {EXPANDED}
		int[] state_0_ = new int[cycles]; // {EXPANDED}
		int[] state_10_ = new int[cycles]; // {EXPANDED}
		int[] state_11_ = new int[cycles]; // {EXPANDED}
		int[] state_1_ = new int[cycles]; // {EXPANDED}
		int[] state_2_ = new int[cycles]; // {EXPANDED}
		int[] state_3_ = new int[cycles]; // {EXPANDED}
		int[] state_4_ = new int[cycles]; // {EXPANDED}
		int[] state_5_ = new int[cycles]; // {EXPANDED}
		int[] state_6_ = new int[cycles]; // {EXPANDED}
		int[] state_7_ = new int[cycles]; // {EXPANDED}
		int[] state_8_ = new int[cycles]; // {EXPANDED}
		int[] state_9_ = new int[cycles]; // {EXPANDED}

		// int[] {INPUT_BIT} = new int[cycles];
		int[] code_0_ = new int[cycles]; // {EXPANDED}
		int[] code_10_ = new int[cycles]; // {EXPANDED}
		int[] code_11_ = new int[cycles]; // {EXPANDED}
		int[] code_12_ = new int[cycles]; // {EXPANDED}
		int[] code_13_ = new int[cycles]; // {EXPANDED}
		int[] code_14_ = new int[cycles]; // {EXPANDED}
		int[] code_1_ = new int[cycles]; // {EXPANDED}
		int[] code_2_ = new int[cycles]; // {EXPANDED}
		int[] code_3_ = new int[cycles]; // {EXPANDED}
		int[] code_4_ = new int[cycles]; // {EXPANDED}
		int[] code_5_ = new int[cycles]; // {EXPANDED}
		int[] code_6_ = new int[cycles]; // {EXPANDED}
		int[] code_7_ = new int[cycles]; // {EXPANDED}
		int[] code_8_ = new int[cycles]; // {EXPANDED}
		int[] code_9_ = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		secret_0_[0] = -(initial >> 0 & 1); // {EXPANDED}
		secret_10_[0] = -(initial >> 1 & 1); // {EXPANDED}
		secret_11_[0] = -(initial >> 2 & 1); // {EXPANDED}
		secret_12_[0] = -(initial >> 3 & 1); // {EXPANDED}
		secret_13_[0] = -(initial >> 4 & 1); // {EXPANDED}
		secret_14_[0] = -(initial >> 5 & 1); // {EXPANDED}
		secret_1_[0] = -(initial >> 6 & 1); // {EXPANDED}
		secret_2_[0] = -(initial >> 7 & 1); // {EXPANDED}
		secret_3_[0] = -(initial >> 8 & 1); // {EXPANDED}
		secret_4_[0] = -(initial >> 9 & 1); // {EXPANDED}
		secret_5_[0] = -(initial >> 10 & 1); // {EXPANDED}
		secret_6_[0] = -(initial >> 11 & 1); // {EXPANDED}
		secret_7_[0] = -(initial >> 12 & 1); // {EXPANDED}
		secret_8_[0] = -(initial >> 13 & 1); // {EXPANDED}
		secret_9_[0] = -(initial >> 14 & 1); // {EXPANDED}
		state_0_[0] = -(initial >> 15 & 1); // {EXPANDED}
		state_10_[0] = -(initial >> 16 & 1); // {EXPANDED}
		state_11_[0] = -(initial >> 17 & 1); // {EXPANDED}
		state_1_[0] = -(initial >> 18 & 1); // {EXPANDED}
		state_2_[0] = -(initial >> 19 & 1); // {EXPANDED}
		state_3_[0] = -(initial >> 20 & 1); // {EXPANDED}
		state_4_[0] = -(initial >> 21 & 1); // {EXPANDED}
		state_5_[0] = -(initial >> 22 & 1); // {EXPANDED}
		state_6_[0] = -(initial >> 23 & 1); // {EXPANDED}
		state_7_[0] = -(initial >> 24 & 1); // {EXPANDED}
		state_8_[0] = -(initial >> 25 & 1); // {EXPANDED}
		state_9_[0] = -(initial >> 26 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] n1 = new int[cycles]; // {EXPANDED}
		int[] n10 = new int[cycles]; // {EXPANDED}
		int[] n100 = new int[cycles]; // {EXPANDED}
		int[] n101 = new int[cycles]; // {EXPANDED}
		int[] n102 = new int[cycles]; // {EXPANDED}
		int[] n103 = new int[cycles]; // {EXPANDED}
		int[] n104 = new int[cycles]; // {EXPANDED}
		int[] n105 = new int[cycles]; // {EXPANDED}
		int[] n106 = new int[cycles]; // {EXPANDED}
		int[] n107 = new int[cycles]; // {EXPANDED}
		int[] n108 = new int[cycles]; // {EXPANDED}
		int[] n109 = new int[cycles]; // {EXPANDED}
		int[] n11 = new int[cycles]; // {EXPANDED}
		int[] n110 = new int[cycles]; // {EXPANDED}
		int[] n111 = new int[cycles]; // {EXPANDED}
		int[] n112 = new int[cycles]; // {EXPANDED}
		int[] n113 = new int[cycles]; // {EXPANDED}
		int[] n114 = new int[cycles]; // {EXPANDED}
		int[] n115 = new int[cycles]; // {EXPANDED}
		int[] n116 = new int[cycles]; // {EXPANDED}
		int[] n117 = new int[cycles]; // {EXPANDED}
		int[] n118 = new int[cycles]; // {EXPANDED}
		int[] n119 = new int[cycles]; // {EXPANDED}
		int[] n12 = new int[cycles]; // {EXPANDED}
		int[] n120 = new int[cycles]; // {EXPANDED}
		int[] n121 = new int[cycles]; // {EXPANDED}
		int[] n122 = new int[cycles]; // {EXPANDED}
		int[] n123 = new int[cycles]; // {EXPANDED}
		int[] n124 = new int[cycles]; // {EXPANDED}
		int[] n125 = new int[cycles]; // {EXPANDED}
		int[] n126 = new int[cycles]; // {EXPANDED}
		int[] n13 = new int[cycles]; // {EXPANDED}
		int[] n14 = new int[cycles]; // {EXPANDED}
		int[] n15 = new int[cycles]; // {EXPANDED}
		int[] n16 = new int[cycles]; // {EXPANDED}
		int[] n17 = new int[cycles]; // {EXPANDED}
		int[] n18 = new int[cycles]; // {EXPANDED}
		int[] n19 = new int[cycles]; // {EXPANDED}
		int[] n2 = new int[cycles]; // {EXPANDED}
		int[] n20 = new int[cycles]; // {EXPANDED}
		int[] n21 = new int[cycles]; // {EXPANDED}
		int[] n22 = new int[cycles]; // {EXPANDED}
		int[] n23 = new int[cycles]; // {EXPANDED}
		int[] n24 = new int[cycles]; // {EXPANDED}
		int[] n25 = new int[cycles]; // {EXPANDED}
		int[] n26 = new int[cycles]; // {EXPANDED}
		int[] n27 = new int[cycles]; // {EXPANDED}
		int[] n28 = new int[cycles]; // {EXPANDED}
		int[] n29 = new int[cycles]; // {EXPANDED}
		int[] n3 = new int[cycles]; // {EXPANDED}
		int[] n30 = new int[cycles]; // {EXPANDED}
		int[] n31 = new int[cycles]; // {EXPANDED}
		int[] n32 = new int[cycles]; // {EXPANDED}
		int[] n33 = new int[cycles]; // {EXPANDED}
		int[] n34 = new int[cycles]; // {EXPANDED}
		int[] n35 = new int[cycles]; // {EXPANDED}
		int[] n36 = new int[cycles]; // {EXPANDED}
		int[] n37 = new int[cycles]; // {EXPANDED}
		int[] n38 = new int[cycles]; // {EXPANDED}
		int[] n39 = new int[cycles]; // {EXPANDED}
		int[] n4 = new int[cycles]; // {EXPANDED}
		int[] n40 = new int[cycles]; // {EXPANDED}
		int[] n41 = new int[cycles]; // {EXPANDED}
		int[] n42 = new int[cycles]; // {EXPANDED}
		int[] n43 = new int[cycles]; // {EXPANDED}
		int[] n44 = new int[cycles]; // {EXPANDED}
		int[] n45 = new int[cycles]; // {EXPANDED}
		int[] n46 = new int[cycles]; // {EXPANDED}
		int[] n47 = new int[cycles]; // {EXPANDED}
		int[] n48 = new int[cycles]; // {EXPANDED}
		int[] n49 = new int[cycles]; // {EXPANDED}
		int[] n5 = new int[cycles]; // {EXPANDED}
		int[] n50 = new int[cycles]; // {EXPANDED}
		int[] n51 = new int[cycles]; // {EXPANDED}
		int[] n52 = new int[cycles]; // {EXPANDED}
		int[] n53 = new int[cycles]; // {EXPANDED}
		int[] n54 = new int[cycles]; // {EXPANDED}
		int[] n55 = new int[cycles]; // {EXPANDED}
		int[] n56 = new int[cycles]; // {EXPANDED}
		int[] n57 = new int[cycles]; // {EXPANDED}
		int[] n58 = new int[cycles]; // {EXPANDED}
		int[] n59 = new int[cycles]; // {EXPANDED}
		int[] n6 = new int[cycles]; // {EXPANDED}
		int[] n60 = new int[cycles]; // {EXPANDED}
		int[] n61 = new int[cycles]; // {EXPANDED}
		int[] n62 = new int[cycles]; // {EXPANDED}
		int[] n63 = new int[cycles]; // {EXPANDED}
		int[] n64 = new int[cycles]; // {EXPANDED}
		int[] n65 = new int[cycles]; // {EXPANDED}
		int[] n66 = new int[cycles]; // {EXPANDED}
		int[] n67 = new int[cycles]; // {EXPANDED}
		int[] n68 = new int[cycles]; // {EXPANDED}
		int[] n69 = new int[cycles]; // {EXPANDED}
		int[] n7 = new int[cycles]; // {EXPANDED}
		int[] n70 = new int[cycles]; // {EXPANDED}
		int[] n71 = new int[cycles]; // {EXPANDED}
		int[] n72 = new int[cycles]; // {EXPANDED}
		int[] n73 = new int[cycles]; // {EXPANDED}
		int[] n74 = new int[cycles]; // {EXPANDED}
		int[] n75 = new int[cycles]; // {EXPANDED}
		int[] n76 = new int[cycles]; // {EXPANDED}
		int[] n77 = new int[cycles]; // {EXPANDED}
		int[] n78 = new int[cycles]; // {EXPANDED}
		int[] n79 = new int[cycles]; // {EXPANDED}
		int[] n8 = new int[cycles]; // {EXPANDED}
		int[] n80 = new int[cycles]; // {EXPANDED}
		int[] n81 = new int[cycles]; // {EXPANDED}
		int[] n82 = new int[cycles]; // {EXPANDED}
		int[] n83 = new int[cycles]; // {EXPANDED}
		int[] n84 = new int[cycles]; // {EXPANDED}
		int[] n85 = new int[cycles]; // {EXPANDED}
		int[] n86 = new int[cycles]; // {EXPANDED}
		int[] n87 = new int[cycles]; // {EXPANDED}
		int[] n88 = new int[cycles]; // {EXPANDED}
		int[] n89 = new int[cycles]; // {EXPANDED}
		int[] n9 = new int[cycles]; // {EXPANDED}
		int[] n90 = new int[cycles]; // {EXPANDED}
		int[] n91 = new int[cycles]; // {EXPANDED}
		int[] n92 = new int[cycles]; // {EXPANDED}
		int[] n93 = new int[cycles]; // {EXPANDED}
		int[] n94 = new int[cycles]; // {EXPANDED}
		int[] n95 = new int[cycles]; // {EXPANDED}
		int[] n96 = new int[cycles]; // {EXPANDED}
		int[] n97 = new int[cycles]; // {EXPANDED}
		int[] n98 = new int[cycles]; // {EXPANDED}
		int[] n99 = new int[cycles]; // {EXPANDED}
		int[] valid = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			code_0_[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			code_10_[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}
			code_11_[i] = -(inputs[i] >> 2 & 1); // {EXPANDED}
			code_12_[i] = -(inputs[i] >> 3 & 1); // {EXPANDED}
			code_13_[i] = -(inputs[i] >> 4 & 1); // {EXPANDED}
			code_14_[i] = -(inputs[i] >> 5 & 1); // {EXPANDED}
			code_1_[i] = -(inputs[i] >> 6 & 1); // {EXPANDED}
			code_2_[i] = -(inputs[i] >> 7 & 1); // {EXPANDED}
			code_3_[i] = -(inputs[i] >> 8 & 1); // {EXPANDED}
			code_4_[i] = -(inputs[i] >> 9 & 1); // {EXPANDED}
			code_5_[i] = -(inputs[i] >> 10 & 1); // {EXPANDED}
			code_6_[i] = -(inputs[i] >> 11 & 1); // {EXPANDED}
			code_7_[i] = -(inputs[i] >> 12 & 1); // {EXPANDED}
			code_8_[i] = -(inputs[i] >> 13 & 1); // {EXPANDED}
			code_9_[i] = -(inputs[i] >> 14 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n114[i] = (code_3_[i] ^ secret_3_[i]); // {EXPANDED}
			n100[i] = (secret_14_[i] ^ code_14_[i]); // {EXPANDED}
			n87[i] = ~(state_4_[i] | state_3_[i]); // {EXPANDED}
			n8[i] = (secret_5_[i] ^ code_5_[i]); // {EXPANDED}
			n82[i] = state_8_[i] | state_7_[i]; // {EXPANDED}
			n119[i] = (secret_11_[i] ^ code_11_[i]); // {EXPANDED}
			n1[i] = (secret_10_[i] ^ code_10_[i]); // {EXPANDED}
			n116[i] = (secret_4_[i] ^ code_4_[i]); // {EXPANDED}
			n125[i] = (code_9_[i] ^ secret_9_[i]); // {EXPANDED}
			n5[i] = (secret_8_[i] ^ code_8_[i]); // {EXPANDED}
			n106[i] = (code_2_[i] ^ secret_2_[i]); // {EXPANDED}
			n4[i] = (code_7_[i] ^ secret_7_[i]); // {EXPANDED}
			n42[i] = (secret_14_[i] ^ secret_13_[i]); // {EXPANDED}
			n91[i] = ~state_10_[i]; // {EXPANDED}
			n94[i] = ~(state_0_[i] | state_1_[i]); // {EXPANDED}
			n126[i] = (code_0_[i] ^ secret_0_[i]); // {EXPANDED}
			n88[i] = ~(state_5_[i] | state_2_[i]); // {EXPANDED}
			n84[i] = state_6_[i] | state_9_[i]; // {EXPANDED}
			n46[i] = (state_3_[i] ^ state_9_[i]); // {EXPANDED}
			n108[i] = (code_6_[i] ^ secret_6_[i]); // {EXPANDED}
			n121[i] = (secret_12_[i] ^ code_12_[i]); // {EXPANDED}
			n7[i] = (code_1_[i] ^ secret_1_[i]); // {EXPANDED}
			n102[i] = (code_13_[i] ^ secret_13_[i]); // {EXPANDED}
			n90[i] = ~(n87[i] & n88[i]); // {EXPANDED}
			n104[i] = n100[i] | n102[i]; // {EXPANDED}
			n122[i] = n121[i] | n119[i]; // {EXPANDED}
			n118[i] = n114[i] | n116[i]; // {EXPANDED}
			n93[i] = n91[i] & state_11_[i]; // {EXPANDED}
			n96[i] = ~(n93[i] & n94[i]); // {EXPANDED}
			n85[i] = ~(n82[i] | n84[i]); // {EXPANDED}
			n110[i] = n106[i] | n108[i]; // {EXPANDED}
			n2[i] = n126[i] | n1[i]; // {EXPANDED}
			n6[i] = n5[i] | n4[i]; // {EXPANDED}
			n47[i] = (n46[i] ^ n91[i]); // {EXPANDED}
			n9[i] = n8[i] | n7[i]; // {EXPANDED}
			n3[i] = n2[i] | n125[i]; // {EXPANDED}
			n123[i] = n118[i] | n122[i]; // {EXPANDED}
			n48[i] = (state_11_[i] ^ n47[i]); // {EXPANDED}
			n112[i] = n110[i] | n104[i]; // {EXPANDED}
			n10[i] = n6[i] | n9[i]; // {EXPANDED}
			n97[i] = ~(n90[i] | n96[i]); // {EXPANDED}
			n11[i] = n3[i] | n10[i]; // {EXPANDED}
			valid[i] = ~(n85[i] & n97[i]); // {EXPANDED}
			n124[i] = n123[i] | n112[i]; // {EXPANDED}
			n14[i] = n11[i] | n124[i]; // {EXPANDED}
			n12[i] = ~(n11[i] | n124[i]); // {EXPANDED}
			n24[i] = ~(n12[i] | secret_6_[i]); // {EXPANDED}
			n33[i] = ~(n14[i] | secret_9_[i]); // {EXPANDED}
			n34[i] = ~(secret_11_[i] | n12[i]); // {EXPANDED}
			n51[i] = ~(state_0_[i] | n14[i]); // {EXPANDED}
			n15[i] = ~(n14[i] | secret_0_[i]); // {EXPANDED}
			n71[i] = ~(n14[i] | state_10_[i]); // {EXPANDED}
			n52[i] = ~(n12[i] | state_2_[i]); // {EXPANDED}
			n19[i] = ~(n14[i] | secret_2_[i]); // {EXPANDED}
			n57[i] = ~(n14[i] | state_3_[i]); // {EXPANDED}
			n40[i] = ~(secret_14_[i] | n12[i]); // {EXPANDED}
			n32[i] = ~(secret_10_[i] | n12[i]); // {EXPANDED}
			n70[i] = ~(state_11_[i] | n12[i]); // {EXPANDED}
			n26[i] = ~(n12[i] | secret_7_[i]); // {EXPANDED}
			n62[i] = ~(n12[i] | state_7_[i]); // {EXPANDED}
			n56[i] = ~(n12[i] | state_4_[i]); // {EXPANDED}
			n17[i] = ~(n14[i] | secret_1_[i]); // {EXPANDED}
			n45[i] = ~(state_0_[i] | n12[i]); // {EXPANDED}
			n55[i] = ~(n14[i] | state_2_[i]); // {EXPANDED}
			n31[i] = ~(n14[i] | secret_8_[i]); // {EXPANDED}
			n43[i] = ~(n42[i] & n12[i]); // {EXPANDED}
			n58[i] = ~(state_5_[i] | n12[i]); // {EXPANDED}
			n27[i] = ~(n14[i] | secret_6_[i]); // {EXPANDED}
			n29[i] = ~(n14[i] | secret_7_[i]); // {EXPANDED}
			n66[i] = ~(n12[i] | state_9_[i]); // {EXPANDED}
			n18[i] = ~(secret_3_[i] | n12[i]); // {EXPANDED}
			n23[i] = ~(secret_4_[i] | n14[i]); // {EXPANDED}
			n68[i] = ~(n12[i] | state_10_[i]); // {EXPANDED}
			n13[i] = ~(n12[i] | secret_1_[i]); // {EXPANDED}
			n21[i] = ~(n14[i] | secret_3_[i]); // {EXPANDED}
			n22[i] = ~(secret_5_[i] | n12[i]); // {EXPANDED}
			n69[i] = ~(n14[i] | state_9_[i]); // {EXPANDED}
			n65[i] = ~(n14[i] | state_7_[i]); // {EXPANDED}
			n30[i] = ~(n12[i] | secret_9_[i]); // {EXPANDED}
			n39[i] = ~(n14[i] | secret_12_[i]); // {EXPANDED}
			n28[i] = ~(secret_8_[i] | n12[i]); // {EXPANDED}
			n38[i] = ~(n12[i] | secret_13_[i]); // {EXPANDED}
			n41[i] = ~(n14[i] | secret_13_[i]); // {EXPANDED}
			n59[i] = ~(n14[i] | state_4_[i]); // {EXPANDED}
			n64[i] = ~(n12[i] | state_8_[i]); // {EXPANDED}
			n67[i] = ~(n14[i] | state_8_[i]); // {EXPANDED}
			n37[i] = ~(n14[i] | secret_11_[i]); // {EXPANDED}
			n53[i] = ~(state_1_[i] | n14[i]); // {EXPANDED}
			n50[i] = ~(state_1_[i] | n12[i]); // {EXPANDED}
			n25[i] = ~(secret_5_[i] | n14[i]); // {EXPANDED}
			n60[i] = ~(state_6_[i] | n12[i]); // {EXPANDED}
			n54[i] = ~(n12[i] | state_3_[i]); // {EXPANDED}
			n35[i] = ~(secret_10_[i] | n14[i]); // {EXPANDED}
			n44[i] = ~(n14[i] & secret_0_[i]); // {EXPANDED}
			n63[i] = ~(n14[i] | state_6_[i]); // {EXPANDED}
			n20[i] = ~(secret_4_[i] | n12[i]); // {EXPANDED}
			n61[i] = ~(n14[i] | state_5_[i]); // {EXPANDED}
			n36[i] = ~(n12[i] | secret_12_[i]); // {EXPANDED}
			n49[i] = n48[i] & n12[i]; // {EXPANDED}
			n16[i] = ~(secret_2_[i] | n12[i]); // {EXPANDED}
			n89[i] = ~(n39[i] | n38[i]); // {EXPANDED}
			n77[i] = ~(n24[i] | n25[i]); // {EXPANDED}
			n95[i] = ~(n44[i] & n43[i]); // {EXPANDED}
			n92[i] = ~(n41[i] | n40[i]); // {EXPANDED}
			n105[i] = ~(n57[i] | n56[i]); // {EXPANDED}
			n75[i] = ~(n20[i] | n21[i]); // {EXPANDED}
			n113[i] = ~(n65[i] | n64[i]); // {EXPANDED}
			n83[i] = ~(n35[i] | n34[i]); // {EXPANDED}
			n76[i] = ~(n23[i] | n22[i]); // {EXPANDED}
			n73[i] = ~(n16[i] | n17[i]); // {EXPANDED}
			n80[i] = ~(n30[i] | n31[i]); // {EXPANDED}
			n115[i] = ~(n66[i] | n67[i]); // {EXPANDED}
			n120[i] = ~(n70[i] | n71[i]); // {EXPANDED}
			n103[i] = ~(n55[i] | n54[i]); // {EXPANDED}
			n86[i] = ~(n36[i] | n37[i]); // {EXPANDED}
			n98[i] = ~(n45[i] | n49[i]); // {EXPANDED}
			n109[i] = ~(n61[i] | n60[i]); // {EXPANDED}
			n72[i] = ~(n13[i] | n15[i]); // {EXPANDED}
			n111[i] = ~(n63[i] | n62[i]); // {EXPANDED}
			n74[i] = ~(n18[i] | n19[i]); // {EXPANDED}
			n78[i] = ~(n27[i] | n26[i]); // {EXPANDED}
			n81[i] = ~(n33[i] | n32[i]); // {EXPANDED}
			n117[i] = ~(n68[i] | n69[i]); // {EXPANDED}
			n79[i] = ~(n29[i] | n28[i]); // {EXPANDED}
			n107[i] = ~(n59[i] | n58[i]); // {EXPANDED}
			n99[i] = ~(n50[i] | n51[i]); // {EXPANDED}
			n101[i] = ~(n52[i] | n53[i]); // {EXPANDED}

			if (i < cycles-1) {

				//@formatter:off
				// {STATE_BIT}[i+1] |= {NEXT_STATE_BIT}[i];
				secret_0_[i+1] |= n95[i]; // {EXPANDED}
				secret_10_[i+1] |= n81[i]; // {EXPANDED}
				secret_11_[i+1] |= n83[i]; // {EXPANDED}
				secret_12_[i+1] |= n86[i]; // {EXPANDED}
				secret_13_[i+1] |= n89[i]; // {EXPANDED}
				secret_14_[i+1] |= n92[i]; // {EXPANDED}
				secret_1_[i+1] |= n72[i]; // {EXPANDED}
				secret_2_[i+1] |= n73[i]; // {EXPANDED}
				secret_3_[i+1] |= n74[i]; // {EXPANDED}
				secret_4_[i+1] |= n75[i]; // {EXPANDED}
				secret_5_[i+1] |= n76[i]; // {EXPANDED}
				secret_6_[i+1] |= n77[i]; // {EXPANDED}
				secret_7_[i+1] |= n78[i]; // {EXPANDED}
				secret_8_[i+1] |= n79[i]; // {EXPANDED}
				secret_9_[i+1] |= n80[i]; // {EXPANDED}
				state_0_[i+1] |= n98[i]; // {EXPANDED}
				state_10_[i+1] |= n117[i]; // {EXPANDED}
				state_11_[i+1] |= n120[i]; // {EXPANDED}
				state_1_[i+1] |= n99[i]; // {EXPANDED}
				state_2_[i+1] |= n101[i]; // {EXPANDED}
				state_3_[i+1] |= n103[i]; // {EXPANDED}
				state_4_[i+1] |= n105[i]; // {EXPANDED}
				state_5_[i+1] |= n107[i]; // {EXPANDED}
				state_6_[i+1] |= n109[i]; // {EXPANDED}
				state_7_[i+1] |= n111[i]; // {EXPANDED}
				state_8_[i+1] |= n113[i]; // {EXPANDED}
				state_9_[i+1] |= n115[i]; // {EXPANDED}
				//@formatter:on

			}

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(secret_0_); // {EXPANDED}
		waveforms.add(secret_10_); // {EXPANDED}
		waveforms.add(secret_11_); // {EXPANDED}
		waveforms.add(secret_12_); // {EXPANDED}
		waveforms.add(secret_13_); // {EXPANDED}
		waveforms.add(secret_14_); // {EXPANDED}
		waveforms.add(secret_1_); // {EXPANDED}
		waveforms.add(secret_2_); // {EXPANDED}
		waveforms.add(secret_3_); // {EXPANDED}
		waveforms.add(secret_4_); // {EXPANDED}
		waveforms.add(secret_5_); // {EXPANDED}
		waveforms.add(secret_6_); // {EXPANDED}
		waveforms.add(secret_7_); // {EXPANDED}
		waveforms.add(secret_8_); // {EXPANDED}
		waveforms.add(secret_9_); // {EXPANDED}
		waveforms.add(state_0_); // {EXPANDED}
		waveforms.add(state_10_); // {EXPANDED}
		waveforms.add(state_11_); // {EXPANDED}
		waveforms.add(state_1_); // {EXPANDED}
		waveforms.add(state_2_); // {EXPANDED}
		waveforms.add(state_3_); // {EXPANDED}
		waveforms.add(state_4_); // {EXPANDED}
		waveforms.add(state_5_); // {EXPANDED}
		waveforms.add(state_6_); // {EXPANDED}
		waveforms.add(state_7_); // {EXPANDED}
		waveforms.add(state_8_); // {EXPANDED}
		waveforms.add(state_9_); // {EXPANDED}

		// waveforms.add({INPUT_BIT});
		waveforms.add(code_0_); // {EXPANDED}
		waveforms.add(code_10_); // {EXPANDED}
		waveforms.add(code_11_); // {EXPANDED}
		waveforms.add(code_12_); // {EXPANDED}
		waveforms.add(code_13_); // {EXPANDED}
		waveforms.add(code_14_); // {EXPANDED}
		waveforms.add(code_1_); // {EXPANDED}
		waveforms.add(code_2_); // {EXPANDED}
		waveforms.add(code_3_); // {EXPANDED}
		waveforms.add(code_4_); // {EXPANDED}
		waveforms.add(code_5_); // {EXPANDED}
		waveforms.add(code_6_); // {EXPANDED}
		waveforms.add(code_7_); // {EXPANDED}
		waveforms.add(code_8_); // {EXPANDED}
		waveforms.add(code_9_); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n1); // {EXPANDED}
		waveforms.add(n10); // {EXPANDED}
		waveforms.add(n100); // {EXPANDED}
		waveforms.add(n101); // {EXPANDED}
		waveforms.add(n102); // {EXPANDED}
		waveforms.add(n103); // {EXPANDED}
		waveforms.add(n104); // {EXPANDED}
		waveforms.add(n105); // {EXPANDED}
		waveforms.add(n106); // {EXPANDED}
		waveforms.add(n107); // {EXPANDED}
		waveforms.add(n108); // {EXPANDED}
		waveforms.add(n109); // {EXPANDED}
		waveforms.add(n11); // {EXPANDED}
		waveforms.add(n110); // {EXPANDED}
		waveforms.add(n111); // {EXPANDED}
		waveforms.add(n112); // {EXPANDED}
		waveforms.add(n113); // {EXPANDED}
		waveforms.add(n114); // {EXPANDED}
		waveforms.add(n115); // {EXPANDED}
		waveforms.add(n116); // {EXPANDED}
		waveforms.add(n117); // {EXPANDED}
		waveforms.add(n118); // {EXPANDED}
		waveforms.add(n119); // {EXPANDED}
		waveforms.add(n12); // {EXPANDED}
		waveforms.add(n120); // {EXPANDED}
		waveforms.add(n121); // {EXPANDED}
		waveforms.add(n122); // {EXPANDED}
		waveforms.add(n123); // {EXPANDED}
		waveforms.add(n124); // {EXPANDED}
		waveforms.add(n125); // {EXPANDED}
		waveforms.add(n126); // {EXPANDED}
		waveforms.add(n13); // {EXPANDED}
		waveforms.add(n14); // {EXPANDED}
		waveforms.add(n15); // {EXPANDED}
		waveforms.add(n16); // {EXPANDED}
		waveforms.add(n17); // {EXPANDED}
		waveforms.add(n18); // {EXPANDED}
		waveforms.add(n19); // {EXPANDED}
		waveforms.add(n2); // {EXPANDED}
		waveforms.add(n20); // {EXPANDED}
		waveforms.add(n21); // {EXPANDED}
		waveforms.add(n22); // {EXPANDED}
		waveforms.add(n23); // {EXPANDED}
		waveforms.add(n24); // {EXPANDED}
		waveforms.add(n25); // {EXPANDED}
		waveforms.add(n26); // {EXPANDED}
		waveforms.add(n27); // {EXPANDED}
		waveforms.add(n28); // {EXPANDED}
		waveforms.add(n29); // {EXPANDED}
		waveforms.add(n3); // {EXPANDED}
		waveforms.add(n30); // {EXPANDED}
		waveforms.add(n31); // {EXPANDED}
		waveforms.add(n32); // {EXPANDED}
		waveforms.add(n33); // {EXPANDED}
		waveforms.add(n34); // {EXPANDED}
		waveforms.add(n35); // {EXPANDED}
		waveforms.add(n36); // {EXPANDED}
		waveforms.add(n37); // {EXPANDED}
		waveforms.add(n38); // {EXPANDED}
		waveforms.add(n39); // {EXPANDED}
		waveforms.add(n4); // {EXPANDED}
		waveforms.add(n40); // {EXPANDED}
		waveforms.add(n41); // {EXPANDED}
		waveforms.add(n42); // {EXPANDED}
		waveforms.add(n43); // {EXPANDED}
		waveforms.add(n44); // {EXPANDED}
		waveforms.add(n45); // {EXPANDED}
		waveforms.add(n46); // {EXPANDED}
		waveforms.add(n47); // {EXPANDED}
		waveforms.add(n48); // {EXPANDED}
		waveforms.add(n49); // {EXPANDED}
		waveforms.add(n5); // {EXPANDED}
		waveforms.add(n50); // {EXPANDED}
		waveforms.add(n51); // {EXPANDED}
		waveforms.add(n52); // {EXPANDED}
		waveforms.add(n53); // {EXPANDED}
		waveforms.add(n54); // {EXPANDED}
		waveforms.add(n55); // {EXPANDED}
		waveforms.add(n56); // {EXPANDED}
		waveforms.add(n57); // {EXPANDED}
		waveforms.add(n58); // {EXPANDED}
		waveforms.add(n59); // {EXPANDED}
		waveforms.add(n6); // {EXPANDED}
		waveforms.add(n60); // {EXPANDED}
		waveforms.add(n61); // {EXPANDED}
		waveforms.add(n62); // {EXPANDED}
		waveforms.add(n63); // {EXPANDED}
		waveforms.add(n64); // {EXPANDED}
		waveforms.add(n65); // {EXPANDED}
		waveforms.add(n66); // {EXPANDED}
		waveforms.add(n67); // {EXPANDED}
		waveforms.add(n68); // {EXPANDED}
		waveforms.add(n69); // {EXPANDED}
		waveforms.add(n7); // {EXPANDED}
		waveforms.add(n70); // {EXPANDED}
		waveforms.add(n71); // {EXPANDED}
		waveforms.add(n72); // {EXPANDED}
		waveforms.add(n73); // {EXPANDED}
		waveforms.add(n74); // {EXPANDED}
		waveforms.add(n75); // {EXPANDED}
		waveforms.add(n76); // {EXPANDED}
		waveforms.add(n77); // {EXPANDED}
		waveforms.add(n78); // {EXPANDED}
		waveforms.add(n79); // {EXPANDED}
		waveforms.add(n8); // {EXPANDED}
		waveforms.add(n80); // {EXPANDED}
		waveforms.add(n81); // {EXPANDED}
		waveforms.add(n82); // {EXPANDED}
		waveforms.add(n83); // {EXPANDED}
		waveforms.add(n84); // {EXPANDED}
		waveforms.add(n85); // {EXPANDED}
		waveforms.add(n86); // {EXPANDED}
		waveforms.add(n87); // {EXPANDED}
		waveforms.add(n88); // {EXPANDED}
		waveforms.add(n89); // {EXPANDED}
		waveforms.add(n9); // {EXPANDED}
		waveforms.add(n90); // {EXPANDED}
		waveforms.add(n91); // {EXPANDED}
		waveforms.add(n92); // {EXPANDED}
		waveforms.add(n93); // {EXPANDED}
		waveforms.add(n94); // {EXPANDED}
		waveforms.add(n95); // {EXPANDED}
		waveforms.add(n96); // {EXPANDED}
		waveforms.add(n97); // {EXPANDED}
		waveforms.add(n98); // {EXPANDED}
		waveforms.add(n99); // {EXPANDED}
		waveforms.add(valid); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

}
