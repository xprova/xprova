package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import net.xprova.dot.GraphDotPrinter;
import net.xprova.graph.Graph;
import net.xprova.graph.MultiMap;

public class CodeSimulator {

	public static final int L = 0;
	public static final int H = -1;

	public ArrayList<int[]> exploreSpace(int initial[]) throws Exception {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 5; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 2; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {STATE_BIT} = initial[{STATE_BIT_INDEX}];
		int count_0_ = initial[0]; // {EXPANDED}
		int count_1_ = initial[1]; // {EXPANDED}
		int count_2_ = initial[2]; // {EXPANDED}
		int count_3_ = initial[3]; // {EXPANDED}
		int n26 = initial[4]; // {EXPANDED}
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
		int n3; // {EXPANDED}
		int n4; // {EXPANDED}
		int n5; // {EXPANDED}
		int n6; // {EXPANDED}
		int n7; // {EXPANDED}
		int n8; // {EXPANDED}
		int n9; // {EXPANDED}
		int valid; // {EXPANDED}
		//@formatter:on

		Graph<Integer> stateGraph = new Graph<Integer>();

		HashSet<Integer> toVisit = new HashSet<Integer>();

		HashSet<Integer> visited = new HashSet<Integer>();

		HashMap<Integer, Integer> distanceFromInitial = new HashMap<Integer, Integer>();

		// key: (state, next_state)
		// value: input vector
		MultiMap<Integer, Integer, Integer> iVectors = new MultiMap<Integer, Integer, Integer>();

		distanceFromInitial.put(0, 0);

		int distance = 1;

		Integer in = null; // input vector

		int initialState = 0;

		//@formatter:off
		// initialState += ({STATE_BIT} & 1) << {STATE_BIT_INDEX};
		initialState += (count_0_ & 1) << 0; // {EXPANDED}
		initialState += (count_1_ & 1) << 1; // {EXPANDED}
		initialState += (count_2_ & 1) << 2; // {EXPANDED}
		initialState += (count_3_ & 1) << 3; // {EXPANDED}
		initialState += (n26 & 1) << 4; // {EXPANDED}
		initialState += (count_0_ & 1) << 0;
		//@formatter:on

		toVisit.add(0);

		Integer violationState = null;

		loop1: while (!toVisit.isEmpty()) {

			HashSet<Integer> toVisitNext = new HashSet<Integer>();

			for (Integer state : toVisit) {

				//@formatter:off
				// {STATE_BIT} = (state >> {STATE_BIT_INDEX} & 1) * -1;
				count_0_ = (state >> 0 & 1) * -1; // {EXPANDED}
				count_1_ = (state >> 1 & 1) * -1; // {EXPANDED}
				count_2_ = (state >> 2 & 1) * -1; // {EXPANDED}
				count_3_ = (state >> 3 & 1) * -1; // {EXPANDED}
				n26 = (state >> 4 & 1) * -1; // {EXPANDED}
				//@formatter:on

				String stateBin = getBinary(state, stateBitCount);

				stateGraph.addVertex(state);

				int inputPermutes = 2 ^ 5;

				for (in = 0; in < inputPermutes; in++) {

					//@formatter:off
					// int {INPUT_BIT} = -(in >> {INPUT_BIT_INDEX} & 1);
					int ena1 = -(in >> 0 & 1); // {EXPANDED}
					int ena2 = -(in >> 1 & 1); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {COMB_ASSIGN}
					n8 = count_3_ | count_2_; // {EXPANDED}
					n12 = ~ena2; // {EXPANDED}
					n22 = ~count_2_; // {EXPANDED}
					n5 = ~count_0_; // {EXPANDED}
					n17 = (count_0_ ^ count_1_); // {EXPANDED}
					n19 = ~(count_0_ & count_1_); // {EXPANDED}
					n6 = ~(count_3_ | count_1_); // {EXPANDED}
					n9 = n26 & n8; // {EXPANDED}
					n10 = ~(count_1_ | n8); // {EXPANDED}
					n23 = ~(n19 | n22); // {EXPANDED}
					n13 = ~(n12 | n10); // {EXPANDED}
					n11 = ena1 & n10; // {EXPANDED}
					n7 = ~(n6 & n5); // {EXPANDED}
					valid = ~(n7 & n9); // {EXPANDED}
					n24 = (n23 ^ count_3_); // {EXPANDED}
					n14 = ~(n13 | n11); // {EXPANDED}
					n16 = n13 | n11; // {EXPANDED}
					n1 = (n14 ^ n5); // {EXPANDED}
					n20 = ~(n14 | n19); // {EXPANDED}
					n25 = ~(n24 & n16); // {EXPANDED}
					n21 = ~(count_3_ & n14); // {EXPANDED}
					n15 = ~(count_1_ & n14); // {EXPANDED}
					n18 = ~(n17 & n16); // {EXPANDED}
					n3 = (n20 ^ count_2_); // {EXPANDED}
					n2 = ~(n15 & n18); // {EXPANDED}
					n4 = ~(n25 & n21); // {EXPANDED}
					//@formatter:on

					//@formatter:off
					// {STATE_ASSIGN} {PREFIX1=int next_}
					int next_count_0_ = n1; // {EXPANDED}
					int next_count_1_ = n2; // {EXPANDED}
					int next_count_2_ = n3; // {EXPANDED}
					int next_count_3_ = n4; // {EXPANDED}
					int next_n26 = ena1; // {EXPANDED}
					//@formatter:on

					int nxState = 0;

					//@formatter:off
					// nxState += (next_{STATE_BIT} & 1) << {STATE_BIT_INDEX};
					nxState += (next_count_0_ & 1) << 0; // {EXPANDED}
					nxState += (next_count_1_ & 1) << 1; // {EXPANDED}
					nxState += (next_count_2_ & 1) << 2; // {EXPANDED}
					nxState += (next_count_3_ & 1) << 3; // {EXPANDED}
					nxState += (next_n26 & 1) << 4; // {EXPANDED}
					//@formatter:on

					int inputVector = 0;

					//@formatter:off
					// inputVector += ({INPUT_BIT} & 1) << {INPUT_BIT_INDEX};
					inputVector += (ena1 & 1) << 0; // {EXPANDED}
					inputVector += (ena2 & 1) << 1; // {EXPANDED}
					//@formatter:on

					toVisitNext.add(nxState);

					Integer currentDistance = distanceFromInitial.getOrDefault(nxState, Integer.MAX_VALUE);

					distanceFromInitial.put(nxState, distance < currentDistance ? distance : currentDistance);

					iVectors.put(state, nxState, inputVector);

					String nextStateBin = getBinary(nxState, stateBitCount);

					String inputVecBin = getBinary(inputVector, inputBitCount);

					System.out.printf("discovered %s (%2d) -> %s (%2d) (distance = %d), input vector = %s\n", stateBin,
							state, nextStateBin, nxState, distance, inputVecBin);

					stateGraph.addVertex(nxState);

					stateGraph.addConnection(state, nxState);

					if (valid == 0) {

						violationState = state;

						break loop1;

					}

				}

			}

			visited.addAll(toVisit);

			toVisitNext.removeAll(visited);

			toVisit = toVisitNext;

			distance = distance + 1;

		}

		GraphDotPrinter.printGraph("output/state.dot", stateGraph);

		if (violationState != null) {

			System.out.println("Counter-example found!");

			// now back track to find shortest path from violationState to
			// initial state

			ArrayList<Integer> counterExampleState = new ArrayList<Integer>();

			ArrayList<Integer> counterExampleInputVectors = new ArrayList<Integer>();

			HashSet<Integer> visitedStates = new HashSet<Integer>();

			Integer current = violationState;

			int shortestDistance = distance + 1;

			while (current != 0) {

				counterExampleState.add(current);

				HashSet<Integer> prevStates = stateGraph.bfs(current, 1, true);

				prevStates.removeAll(visitedStates);

				if (prevStates.isEmpty())
					throw new Exception("could not generate counter example.");

				for (Integer previousState : prevStates) {

					int d = distanceFromInitial.get(previousState);

					if ((d < shortestDistance) || (previousState == 0)) {

						current = previousState;

						shortestDistance = d;

					}

				}

				visitedStates.addAll(prevStates);

			}

			counterExampleState.add(0);

			Collections.reverse(counterExampleState);

			int nCounter = counterExampleState.size();

			for (int i = 0; i < nCounter - 1; i++) {

				int s1 = counterExampleState.get(i);
				int s2 = counterExampleState.get(i + 1);

				counterExampleInputVectors.add(iVectors.get(s1, s2));

			}

			counterExampleInputVectors.add(in);

			for (int i = 0; i < nCounter; i++) {

				Integer inpVec = counterExampleInputVectors.get(i);

				int state = counterExampleState.get(i);

				System.out.printf("state: %s, input vector %s\n", getBinary(state, stateBitCount),
						getBinary(inpVec, inputBitCount));

			}

			//formatter:off
			// int[] counterExample_{INPUT_BIT} = new int[nCounter];
			int[] counterExample_ena1 = new int[nCounter]; // {EXPANDED}
			int[] counterExample_ena2 = new int[nCounter]; // {EXPANDED}
			//formatter:on

			for (int i = 0; i < nCounter; i++) {

				Integer inpVec = counterExampleInputVectors.get(i);

				//formatter:off
				// counterExample_{INPUT_BIT}[i] = -(inpVec >> {INPUT_BIT_INDEX} & 1);
				counterExample_ena1[i] = -(inpVec >> 0 & 1); // {EXPANDED}
				counterExample_ena2[i] = -(inpVec >> 1 & 1); // {EXPANDED}
				//formatter:on

			}

			ArrayList<int[]> counterExampleInputs = new ArrayList<int[]>();

			//formatter:off
			// counterExampleInputs.add(counterExample_{INPUT_BIT});
			counterExampleInputs.add(counterExample_ena1); // {EXPANDED}
			counterExampleInputs.add(counterExample_ena2); // {EXPANDED}
			//formatter:on

			return counterExampleInputs;

		} else {

			// no counter-example found

			return null;

		}

	}

	public ArrayList<int[]> simulate(int[] initial, ArrayList<int[]> inputs, int cycles) {

		//@formatter:off
		// int[] {STATE_BIT} = new int[cycles];
		int[] count_0_ = new int[cycles]; // {EXPANDED}
		int[] count_1_ = new int[cycles]; // {EXPANDED}
		int[] count_2_ = new int[cycles]; // {EXPANDED}
		int[] count_3_ = new int[cycles]; // {EXPANDED}
		int[] n26 = new int[cycles]; // {EXPANDED}

		// {STATE_BIT}[0] = initial[{STATE_BIT_INDEX}];
		count_0_[0] = initial[0]; // {EXPANDED}
		count_1_[0] = initial[1]; // {EXPANDED}
		count_2_[0] = initial[2]; // {EXPANDED}
		count_3_[0] = initial[3]; // {EXPANDED}
		n26[0] = initial[4]; // {EXPANDED}

		// int {INPUT_BIT}[] = inputs.get({INPUT_BIT_INDEX});
		int ena1[] = inputs.get(0); // {EXPANDED}
		int ena2[] = inputs.get(1); // {EXPANDED}

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
		int[] n3 = new int[cycles]; // {EXPANDED}
		int[] n4 = new int[cycles]; // {EXPANDED}
		int[] n5 = new int[cycles]; // {EXPANDED}
		int[] n6 = new int[cycles]; // {EXPANDED}
		int[] n7 = new int[cycles]; // {EXPANDED}
		int[] n8 = new int[cycles]; // {EXPANDED}
		int[] n9 = new int[cycles]; // {EXPANDED}
		int[] valid = new int[cycles]; // {EXPANDED}
		//@formatter:on

		for (int i=0; i<cycles; i++) {

			//@formatter:off
			// {COMB_ASSIGN} {POSTFIX1=[i]} {POSTFIX2=[i]}
			n8[i] = count_3_[i] | count_2_[i]; // {EXPANDED}
			n12[i] = ~ena2[i]; // {EXPANDED}
			n22[i] = ~count_2_[i]; // {EXPANDED}
			n5[i] = ~count_0_[i]; // {EXPANDED}
			n17[i] = (count_0_[i] ^ count_1_[i]); // {EXPANDED}
			n19[i] = ~(count_0_[i] & count_1_[i]); // {EXPANDED}
			n6[i] = ~(count_3_[i] | count_1_[i]); // {EXPANDED}
			n9[i] = n26[i] & n8[i]; // {EXPANDED}
			n10[i] = ~(count_1_[i] | n8[i]); // {EXPANDED}
			n23[i] = ~(n19[i] | n22[i]); // {EXPANDED}
			n13[i] = ~(n12[i] | n10[i]); // {EXPANDED}
			n11[i] = ena1[i] & n10[i]; // {EXPANDED}
			n7[i] = ~(n6[i] & n5[i]); // {EXPANDED}
			valid[i] = ~(n7[i] & n9[i]); // {EXPANDED}
			n24[i] = (n23[i] ^ count_3_[i]); // {EXPANDED}
			n14[i] = ~(n13[i] | n11[i]); // {EXPANDED}
			n16[i] = n13[i] | n11[i]; // {EXPANDED}
			n1[i] = (n14[i] ^ n5[i]); // {EXPANDED}
			n20[i] = ~(n14[i] | n19[i]); // {EXPANDED}
			n25[i] = ~(n24[i] & n16[i]); // {EXPANDED}
			n21[i] = ~(count_3_[i] & n14[i]); // {EXPANDED}
			n15[i] = ~(count_1_[i] & n14[i]); // {EXPANDED}
			n18[i] = ~(n17[i] & n16[i]); // {EXPANDED}
			n3[i] = (n20[i] ^ count_2_[i]); // {EXPANDED}
			n2[i] = ~(n15[i] & n18[i]); // {EXPANDED}
			n4[i] = ~(n25[i] & n21[i]); // {EXPANDED}

			if (i < cycles-1) {

				// {STATE_ASSIGN} {POSTFIX1=[i+1]} {POSTFIX2=[i]}
				count_0_[i+1] = n1[i]; // {EXPANDED}
				count_1_[i+1] = n2[i]; // {EXPANDED}
				count_2_[i+1] = n3[i]; // {EXPANDED}
				count_3_[i+1] = n4[i]; // {EXPANDED}
				n26[i+1] = ena1[i]; // {EXPANDED}

			}
			//@formatter:on

		}

		ArrayList<int[]> waveforms = new ArrayList<int[]>();

		//@formatter:off
		// waveforms.add({STATE_BIT});
		waveforms.add(count_0_); // {EXPANDED}
		waveforms.add(count_1_); // {EXPANDED}
		waveforms.add(count_2_); // {EXPANDED}
		waveforms.add(count_3_); // {EXPANDED}
		waveforms.add(n26); // {EXPANDED}

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
		waveforms.add(n3); // {EXPANDED}
		waveforms.add(n4); // {EXPANDED}
		waveforms.add(n5); // {EXPANDED}
		waveforms.add(n6); // {EXPANDED}
		waveforms.add(n7); // {EXPANDED}
		waveforms.add(n8); // {EXPANDED}
		waveforms.add(n9); // {EXPANDED}
		waveforms.add(valid); // {EXPANDED}
		//@formatter:on

		return waveforms;
	}

	public ArrayList<String> getSignalNames() {

		ArrayList<String> result = new ArrayList<String>();

		//@formatter:off
		// result.add("{STATE_BIT}");
		result.add("count_0_"); // {EXPANDED}
		result.add("count_1_"); // {EXPANDED}
		result.add("count_2_"); // {EXPANDED}
		result.add("count_3_"); // {EXPANDED}
		result.add("n26"); // {EXPANDED}

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
		result.add("n3"); // {EXPANDED}
		result.add("n4"); // {EXPANDED}
		result.add("n5"); // {EXPANDED}
		result.add("n6"); // {EXPANDED}
		result.add("n7"); // {EXPANDED}
		result.add("n8"); // {EXPANDED}
		result.add("n9"); // {EXPANDED}
		result.add("valid"); // {EXPANDED}
		//@formatter:on

		return result;
	}

	public int getStateBitCount() {

		//@formatter:off
		// return {STATE_BIT_COUNT};
		return 5; // {EXPANDED}
		//@formatter:on
	}

	public int getInputBitCount() {

		//@formatter:off
		// return {INPUT_BIT_COUNT};
		return 2; // {EXPANDED}
		//@formatter:on
	}

	private String getBinary(int num, int digits) {

		String bitFmt = String.format("%%%ds", digits);

		return String.format(bitFmt, Integer.toBinaryString(num)).replace(' ', '0');
	}

	public void runSim2(int[] initial, ArrayList<int[]> inputs) {

		ArrayList<String> sigNames = getSignalNames();

		int cycles = inputs.get(0).length;

		ArrayList<int[]> results = simulate(initial, inputs, cycles);

		System.out.printf("%10s : ", "Cycle");

		for (int i = 0; i < cycles; i++)
			System.out.printf("%d", i % 10);

		System.out.println();

		System.out.println();

		for (int j = 0; j < results.size(); j++) {

			if (j == getStateBitCount())
				System.out.println();

			int[] sig = results.get(j);

			System.out.printf("%10s : ", sigNames.get(j));

			for (int i = 0; i < cycles; i++) {

				System.out.printf((sig[i] == H) ? "1" : "0");

			}

			System.out.println();

		}

	}

}

