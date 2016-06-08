package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public int[] exploreSpace(int initial) throws Exception {

		// method parameters:

		final int STATE_BUF_SIZE = 20000000;

		final int DISCOVERED_BUF_SIZE = 100000;

		boolean printStateList = false;

		final int UNDISCOVERED = 0x55555555;

		// method body:

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 24; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 2; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = -(initial >> {STATE_BIT_INDEX} & 1);
		int count_0_ = -(initial >> 0 & 1); // {EXPANDED}
		int count_10_ = -(initial >> 1 & 1); // {EXPANDED}
		int count_11_ = -(initial >> 2 & 1); // {EXPANDED}
		int count_1_ = -(initial >> 3 & 1); // {EXPANDED}
		int count_2_ = -(initial >> 4 & 1); // {EXPANDED}
		int count_3_ = -(initial >> 5 & 1); // {EXPANDED}
		int count_4_ = -(initial >> 6 & 1); // {EXPANDED}
		int count_5_ = -(initial >> 7 & 1); // {EXPANDED}
		int count_6_ = -(initial >> 8 & 1); // {EXPANDED}
		int count_7_ = -(initial >> 9 & 1); // {EXPANDED}
		int count_8_ = -(initial >> 10 & 1); // {EXPANDED}
		int count_9_ = -(initial >> 11 & 1); // {EXPANDED}
		int n78 = -(initial >> 12 & 1); // {EXPANDED}
		int n79 = -(initial >> 13 & 1); // {EXPANDED}
		int n80 = -(initial >> 14 & 1); // {EXPANDED}
		int n81 = -(initial >> 15 & 1); // {EXPANDED}
		int n82 = -(initial >> 16 & 1); // {EXPANDED}
		int n83 = -(initial >> 17 & 1); // {EXPANDED}
		int n84 = -(initial >> 18 & 1); // {EXPANDED}
		int n85 = -(initial >> 19 & 1); // {EXPANDED}
		int n86 = -(initial >> 20 & 1); // {EXPANDED}
		int n87 = -(initial >> 21 & 1); // {EXPANDED}
		int n88 = -(initial >> 22 & 1); // {EXPANDED}
		int n89 = -(initial >> 23 & 1); // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n1; // {EXPANDED}
		int n10; // {EXPANDED}
		int n11; // {EXPANDED}
		int n12; // {EXPANDED}
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
		int n8; // {EXPANDED}
		int n9; // {EXPANDED}
		int valid; // {EXPANDED}
		//@formatter:on

		int[] toVisitArr = new int[1];
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
				count_0_ = -(state >> 0 & 1); // {EXPANDED}
				count_10_ = -(state >> 1 & 1); // {EXPANDED}
				count_11_ = -(state >> 2 & 1); // {EXPANDED}
				count_1_ = -(state >> 3 & 1); // {EXPANDED}
				count_2_ = -(state >> 4 & 1); // {EXPANDED}
				count_3_ = -(state >> 5 & 1); // {EXPANDED}
				count_4_ = -(state >> 6 & 1); // {EXPANDED}
				count_5_ = -(state >> 7 & 1); // {EXPANDED}
				count_6_ = -(state >> 8 & 1); // {EXPANDED}
				count_7_ = -(state >> 9 & 1); // {EXPANDED}
				count_8_ = -(state >> 10 & 1); // {EXPANDED}
				count_9_ = -(state >> 11 & 1); // {EXPANDED}
				n78 = -(state >> 12 & 1); // {EXPANDED}
				n79 = -(state >> 13 & 1); // {EXPANDED}
				n80 = -(state >> 14 & 1); // {EXPANDED}
				n81 = -(state >> 15 & 1); // {EXPANDED}
				n82 = -(state >> 16 & 1); // {EXPANDED}
				n83 = -(state >> 17 & 1); // {EXPANDED}
				n84 = -(state >> 18 & 1); // {EXPANDED}
				n85 = -(state >> 19 & 1); // {EXPANDED}
				n86 = -(state >> 20 & 1); // {EXPANDED}
				n87 = -(state >> 21 & 1); // {EXPANDED}
				n88 = -(state >> 22 & 1); // {EXPANDED}
				n89 = -(state >> 23 & 1); // {EXPANDED}
				//@formatter:on

				int inputPermutes = 1 << (inputBitCount);

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int ena1 = -(in >> 0 & 1); // {EXPANDED}
					int ena2 = -(in >> 1 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n1 = (n78 ^ ena2); // {EXPANDED}
					n48 = ~(n78 & n81); // {EXPANDED}
					n29 = ~(count_6_ & count_7_); // {EXPANDED}
					n59 = ~(n87 & n86); // {EXPANDED}
					n34 = ~(count_8_ & count_9_); // {EXPANDED}
					n51 = ~(n83 & n82); // {EXPANDED}
					n26 = ~(count_5_ & count_4_); // {EXPANDED}
					n74 = ~(count_3_ & count_2_); // {EXPANDED}
					n44 = ~(count_11_ & n80); // {EXPANDED}
					n46 = n78 & ena2; // {EXPANDED}
					n64 = ~(n89 & n88); // {EXPANDED}
					n47 = ~ena2; // {EXPANDED}
					n13 = (ena1 ^ count_0_); // {EXPANDED}
					n56 = ~(n84 & n85); // {EXPANDED}
					n73 = ~(count_1_ & count_0_); // {EXPANDED}
					n4 = (n81 ^ n46); // {EXPANDED}
					n33 = ~count_10_; // {EXPANDED}
					n70 = ena1 & count_0_; // {EXPANDED}
					n38 = ~(n73 | n33); // {EXPANDED}
					n30 = n29 | n26; // {EXPANDED}
					n60 = n59 | n56; // {EXPANDED}
					n49 = ~(n48 | n47); // {EXPANDED}
					n71 = count_1_ & n70; // {EXPANDED}
					n16 = (count_1_ ^ n70); // {EXPANDED}
					n39 = ~(n74 | n34); // {EXPANDED}
					n35 = ~n34; // {EXPANDED}
					n52 = n48 | n51; // {EXPANDED}
					n75 = ~(n73 | n74); // {EXPANDED}
					n40 = ~(n38 & n39); // {EXPANDED}
					n63 = n60 | n52; // {EXPANDED}
					n76 = ~(ena1 & n75); // {EXPANDED}
					n53 = n47 | n52; // {EXPANDED}
					n17 = (count_2_ ^ n71); // {EXPANDED}
					n61 = ~(n60 | n53); // {EXPANDED}
					n72 = count_2_ & n71; // {EXPANDED}
					n41 = ~(n40 | n30); // {EXPANDED}
					n5 = (n49 ^ n82); // {EXPANDED}
					n50 = n49 & n82; // {EXPANDED}
					n62 = n61 & n88; // {EXPANDED}
					n42 = n41 & ena1; // {EXPANDED}
					n31 = ~(n30 | n76); // {EXPANDED}
					n18 = (count_3_ ^ n72); // {EXPANDED}
					n11 = (n61 ^ n88); // {EXPANDED}
					n6 = (n83 ^ n50); // {EXPANDED}
					n65 = ~(n64 | n63); // {EXPANDED}
					n77 = ~n76; // {EXPANDED}
					n57 = ~(n53 | n56); // {EXPANDED}
					n54 = ~n53; // {EXPANDED}
					n27 = ~(n76 | n26); // {EXPANDED}
					n12 = (n62 ^ n89); // {EXPANDED}
					n32 = n31 & count_8_; // {EXPANDED}
					n19 = (n77 ^ count_4_); // {EXPANDED}
					n9 = (n57 ^ n86); // {EXPANDED}
					n55 = n84 & n54; // {EXPANDED}
					n23 = (n31 ^ count_8_); // {EXPANDED}
					n36 = ~(n31 & n35); // {EXPANDED}
					n58 = n57 & n86; // {EXPANDED}
					n28 = count_6_ & n27; // {EXPANDED}
					n21 = (count_6_ ^ n27); // {EXPANDED}
					n66 = ena2 & n65; // {EXPANDED}
					n25 = n77 & count_4_; // {EXPANDED}
					n68 = ~(n65 & n79); // {EXPANDED}
					n7 = (n84 ^ n54); // {EXPANDED}
					n37 = n33 & n36; // {EXPANDED}
					n67 = ~(n66 | n79); // {EXPANDED}
					n10 = (n87 ^ n58); // {EXPANDED}
					n24 = (n32 ^ count_9_); // {EXPANDED}
					n8 = (n55 ^ n85); // {EXPANDED}
					n22 = (n28 ^ count_7_); // {EXPANDED}
					n69 = ~(n47 | n68); // {EXPANDED}
					n45 = ~(n68 | n44); // {EXPANDED}
					n20 = (n25 ^ count_5_); // {EXPANDED}
					n43 = ~(n33 | n36); // {EXPANDED}
					n3 = (n69 ^ n80); // {EXPANDED}
					n2 = ~(n69 | n67); // {EXPANDED}
					valid = ~(n45 & n41); // {EXPANDED}
					n15 = (n43 ^ count_11_); // {EXPANDED}
					n14 = ~(n42 | n37); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {STATE_ASSIGN} {PREFIX1=int next_}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
					nxState |= n13 & (1 << 0); // {EXPANDED}
					nxState |= n14 & (1 << 1); // {EXPANDED}
					nxState |= n15 & (1 << 2); // {EXPANDED}
					nxState |= n16 & (1 << 3); // {EXPANDED}
					nxState |= n17 & (1 << 4); // {EXPANDED}
					nxState |= n18 & (1 << 5); // {EXPANDED}
					nxState |= n19 & (1 << 6); // {EXPANDED}
					nxState |= n20 & (1 << 7); // {EXPANDED}
					nxState |= n21 & (1 << 8); // {EXPANDED}
					nxState |= n22 & (1 << 9); // {EXPANDED}
					nxState |= n23 & (1 << 10); // {EXPANDED}
					nxState |= n24 & (1 << 11); // {EXPANDED}
					nxState |= n1 & (1 << 12); // {EXPANDED}
					nxState |= n2 & (1 << 13); // {EXPANDED}
					nxState |= n3 & (1 << 14); // {EXPANDED}
					nxState |= n4 & (1 << 15); // {EXPANDED}
					nxState |= n5 & (1 << 16); // {EXPANDED}
					nxState |= n6 & (1 << 17); // {EXPANDED}
					nxState |= n7 & (1 << 18); // {EXPANDED}
					nxState |= n8 & (1 << 19); // {EXPANDED}
					nxState |= n9 & (1 << 20); // {EXPANDED}
					nxState |= n10 & (1 << 21); // {EXPANDED}
					nxState |= n11 & (1 << 22); // {EXPANDED}
					nxState |= n12 & (1 << 23); // {EXPANDED}
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
		result.add("count_0_"); // {EXPANDED}
		result.add("count_10_"); // {EXPANDED}
		result.add("count_11_"); // {EXPANDED}
		result.add("count_1_"); // {EXPANDED}
		result.add("count_2_"); // {EXPANDED}
		result.add("count_3_"); // {EXPANDED}
		result.add("count_4_"); // {EXPANDED}
		result.add("count_5_"); // {EXPANDED}
		result.add("count_6_"); // {EXPANDED}
		result.add("count_7_"); // {EXPANDED}
		result.add("count_8_"); // {EXPANDED}
		result.add("count_9_"); // {EXPANDED}
		result.add("n78"); // {EXPANDED}
		result.add("n79"); // {EXPANDED}
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

		// result.add("{INPUT_BIT}");
		result.add("ena1"); // {EXPANDED}
		result.add("ena2"); // {EXPANDED}

		// result.add("{NON_STATE_BIT}");
		result.add("n1"); // {EXPANDED}
		result.add("n10"); // {EXPANDED}
		result.add("n11"); // {EXPANDED}
		result.add("n12"); // {EXPANDED}
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
		result.add("n8"); // {EXPANDED}
		result.add("n9"); // {EXPANDED}
		result.add("valid"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 24; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 2; // {EXPANDED}
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
		int[] count_0_ = new int[cycles]; // {EXPANDED}
		int[] count_10_ = new int[cycles]; // {EXPANDED}
		int[] count_11_ = new int[cycles]; // {EXPANDED}
		int[] count_1_ = new int[cycles]; // {EXPANDED}
		int[] count_2_ = new int[cycles]; // {EXPANDED}
		int[] count_3_ = new int[cycles]; // {EXPANDED}
		int[] count_4_ = new int[cycles]; // {EXPANDED}
		int[] count_5_ = new int[cycles]; // {EXPANDED}
		int[] count_6_ = new int[cycles]; // {EXPANDED}
		int[] count_7_ = new int[cycles]; // {EXPANDED}
		int[] count_8_ = new int[cycles]; // {EXPANDED}
		int[] count_9_ = new int[cycles]; // {EXPANDED}
		int[] n78 = new int[cycles]; // {EXPANDED}
		int[] n79 = new int[cycles]; // {EXPANDED}
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

		// int[] {INPUT_BIT} = new int[cycles];
		int[] ena1 = new int[cycles]; // {EXPANDED}
		int[] ena2 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = -(initial >> {STATE_BIT_INDEX} & 1);
		count_0_[0] = -(initial >> 0 & 1); // {EXPANDED}
		count_10_[0] = -(initial >> 1 & 1); // {EXPANDED}
		count_11_[0] = -(initial >> 2 & 1); // {EXPANDED}
		count_1_[0] = -(initial >> 3 & 1); // {EXPANDED}
		count_2_[0] = -(initial >> 4 & 1); // {EXPANDED}
		count_3_[0] = -(initial >> 5 & 1); // {EXPANDED}
		count_4_[0] = -(initial >> 6 & 1); // {EXPANDED}
		count_5_[0] = -(initial >> 7 & 1); // {EXPANDED}
		count_6_[0] = -(initial >> 8 & 1); // {EXPANDED}
		count_7_[0] = -(initial >> 9 & 1); // {EXPANDED}
		count_8_[0] = -(initial >> 10 & 1); // {EXPANDED}
		count_9_[0] = -(initial >> 11 & 1); // {EXPANDED}
		n78[0] = -(initial >> 12 & 1); // {EXPANDED}
		n79[0] = -(initial >> 13 & 1); // {EXPANDED}
		n80[0] = -(initial >> 14 & 1); // {EXPANDED}
		n81[0] = -(initial >> 15 & 1); // {EXPANDED}
		n82[0] = -(initial >> 16 & 1); // {EXPANDED}
		n83[0] = -(initial >> 17 & 1); // {EXPANDED}
		n84[0] = -(initial >> 18 & 1); // {EXPANDED}
		n85[0] = -(initial >> 19 & 1); // {EXPANDED}
		n86[0] = -(initial >> 20 & 1); // {EXPANDED}
		n87[0] = -(initial >> 21 & 1); // {EXPANDED}
		n88[0] = -(initial >> 22 & 1); // {EXPANDED}
		n89[0] = -(initial >> 23 & 1); // {EXPANDED}

		// int[] {NON_STATE_BIT} = new int[cycles];
		int[] n1 = new int[cycles]; // {EXPANDED}
		int[] n10 = new int[cycles]; // {EXPANDED}
		int[] n11 = new int[cycles]; // {EXPANDED}
		int[] n12 = new int[cycles]; // {EXPANDED}
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
		int[] n8 = new int[cycles]; // {EXPANDED}
		int[] n9 = new int[cycles]; // {EXPANDED}
		int[] valid = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i = 0; i < cycles; i++) {

			//@formatter:off
			// {INPUT_BIT}[i] = -(inputs[i] >> {INPUT_BIT_INDEX} & 1);
			ena1[i] = -(inputs[i] >> 0 & 1); // {EXPANDED}
			ena2[i] = -(inputs[i] >> 1 & 1); // {EXPANDED}

			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n1[i] = (n78[i] ^ ena2[i]); // {EXPANDED}
			n48[i] = ~(n78[i] & n81[i]); // {EXPANDED}
			n29[i] = ~(count_6_[i] & count_7_[i]); // {EXPANDED}
			n59[i] = ~(n87[i] & n86[i]); // {EXPANDED}
			n34[i] = ~(count_8_[i] & count_9_[i]); // {EXPANDED}
			n51[i] = ~(n83[i] & n82[i]); // {EXPANDED}
			n26[i] = ~(count_5_[i] & count_4_[i]); // {EXPANDED}
			n74[i] = ~(count_3_[i] & count_2_[i]); // {EXPANDED}
			n44[i] = ~(count_11_[i] & n80[i]); // {EXPANDED}
			n46[i] = n78[i] & ena2[i]; // {EXPANDED}
			n64[i] = ~(n89[i] & n88[i]); // {EXPANDED}
			n47[i] = ~ena2[i]; // {EXPANDED}
			n13[i] = (ena1[i] ^ count_0_[i]); // {EXPANDED}
			n56[i] = ~(n84[i] & n85[i]); // {EXPANDED}
			n73[i] = ~(count_1_[i] & count_0_[i]); // {EXPANDED}
			n4[i] = (n81[i] ^ n46[i]); // {EXPANDED}
			n33[i] = ~count_10_[i]; // {EXPANDED}
			n70[i] = ena1[i] & count_0_[i]; // {EXPANDED}
			n38[i] = ~(n73[i] | n33[i]); // {EXPANDED}
			n30[i] = n29[i] | n26[i]; // {EXPANDED}
			n60[i] = n59[i] | n56[i]; // {EXPANDED}
			n49[i] = ~(n48[i] | n47[i]); // {EXPANDED}
			n71[i] = count_1_[i] & n70[i]; // {EXPANDED}
			n16[i] = (count_1_[i] ^ n70[i]); // {EXPANDED}
			n39[i] = ~(n74[i] | n34[i]); // {EXPANDED}
			n35[i] = ~n34[i]; // {EXPANDED}
			n52[i] = n48[i] | n51[i]; // {EXPANDED}
			n75[i] = ~(n73[i] | n74[i]); // {EXPANDED}
			n40[i] = ~(n38[i] & n39[i]); // {EXPANDED}
			n63[i] = n60[i] | n52[i]; // {EXPANDED}
			n76[i] = ~(ena1[i] & n75[i]); // {EXPANDED}
			n53[i] = n47[i] | n52[i]; // {EXPANDED}
			n17[i] = (count_2_[i] ^ n71[i]); // {EXPANDED}
			n61[i] = ~(n60[i] | n53[i]); // {EXPANDED}
			n72[i] = count_2_[i] & n71[i]; // {EXPANDED}
			n41[i] = ~(n40[i] | n30[i]); // {EXPANDED}
			n5[i] = (n49[i] ^ n82[i]); // {EXPANDED}
			n50[i] = n49[i] & n82[i]; // {EXPANDED}
			n62[i] = n61[i] & n88[i]; // {EXPANDED}
			n42[i] = n41[i] & ena1[i]; // {EXPANDED}
			n31[i] = ~(n30[i] | n76[i]); // {EXPANDED}
			n18[i] = (count_3_[i] ^ n72[i]); // {EXPANDED}
			n11[i] = (n61[i] ^ n88[i]); // {EXPANDED}
			n6[i] = (n83[i] ^ n50[i]); // {EXPANDED}
			n65[i] = ~(n64[i] | n63[i]); // {EXPANDED}
			n77[i] = ~n76[i]; // {EXPANDED}
			n57[i] = ~(n53[i] | n56[i]); // {EXPANDED}
			n54[i] = ~n53[i]; // {EXPANDED}
			n27[i] = ~(n76[i] | n26[i]); // {EXPANDED}
			n12[i] = (n62[i] ^ n89[i]); // {EXPANDED}
			n32[i] = n31[i] & count_8_[i]; // {EXPANDED}
			n19[i] = (n77[i] ^ count_4_[i]); // {EXPANDED}
			n9[i] = (n57[i] ^ n86[i]); // {EXPANDED}
			n55[i] = n84[i] & n54[i]; // {EXPANDED}
			n23[i] = (n31[i] ^ count_8_[i]); // {EXPANDED}
			n36[i] = ~(n31[i] & n35[i]); // {EXPANDED}
			n58[i] = n57[i] & n86[i]; // {EXPANDED}
			n28[i] = count_6_[i] & n27[i]; // {EXPANDED}
			n21[i] = (count_6_[i] ^ n27[i]); // {EXPANDED}
			n66[i] = ena2[i] & n65[i]; // {EXPANDED}
			n25[i] = n77[i] & count_4_[i]; // {EXPANDED}
			n68[i] = ~(n65[i] & n79[i]); // {EXPANDED}
			n7[i] = (n84[i] ^ n54[i]); // {EXPANDED}
			n37[i] = n33[i] & n36[i]; // {EXPANDED}
			n67[i] = ~(n66[i] | n79[i]); // {EXPANDED}
			n10[i] = (n87[i] ^ n58[i]); // {EXPANDED}
			n24[i] = (n32[i] ^ count_9_[i]); // {EXPANDED}
			n8[i] = (n55[i] ^ n85[i]); // {EXPANDED}
			n22[i] = (n28[i] ^ count_7_[i]); // {EXPANDED}
			n69[i] = ~(n47[i] | n68[i]); // {EXPANDED}
			n45[i] = ~(n68[i] | n44[i]); // {EXPANDED}
			n20[i] = (n25[i] ^ count_5_[i]); // {EXPANDED}
			n43[i] = ~(n33[i] | n36[i]); // {EXPANDED}
			n3[i] = (n69[i] ^ n80[i]); // {EXPANDED}
			n2[i] = ~(n69[i] | n67[i]); // {EXPANDED}
			valid[i] = ~(n45[i] & n41[i]); // {EXPANDED}
			n15[i] = (n43[i] ^ count_11_[i]); // {EXPANDED}
			n14[i] = ~(n42[i] | n37[i]); // {EXPANDED}

			if (i < cycles-1) {

				// {STATE_ASSIGN} {POSTFIX1=[i+1]} {POSTFIX2=[i]}

			}
			//@formatter:on

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(count_0_); // {EXPANDED}
		waveforms.add(count_10_); // {EXPANDED}
		waveforms.add(count_11_); // {EXPANDED}
		waveforms.add(count_1_); // {EXPANDED}
		waveforms.add(count_2_); // {EXPANDED}
		waveforms.add(count_3_); // {EXPANDED}
		waveforms.add(count_4_); // {EXPANDED}
		waveforms.add(count_5_); // {EXPANDED}
		waveforms.add(count_6_); // {EXPANDED}
		waveforms.add(count_7_); // {EXPANDED}
		waveforms.add(count_8_); // {EXPANDED}
		waveforms.add(count_9_); // {EXPANDED}
		waveforms.add(n78); // {EXPANDED}
		waveforms.add(n79); // {EXPANDED}
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

		// waveforms.add({INPUT_BIT});
		waveforms.add(ena1); // {EXPANDED}
		waveforms.add(ena2); // {EXPANDED}

		// waveforms.add({NON_STATE_BIT});
		waveforms.add(n1); // {EXPANDED}
		waveforms.add(n10); // {EXPANDED}
		waveforms.add(n11); // {EXPANDED}
		waveforms.add(n12); // {EXPANDED}
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
		waveforms.add(n8); // {EXPANDED}
		waveforms.add(n9); // {EXPANDED}
		waveforms.add(valid); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

}

