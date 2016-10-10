package net.xprova.xprova;

import java.util.Arrays;

public class CodeSimulatorDFS {

	public static final int L = 0;
	public static final int H = -1;

	static final int[][] graph = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	public static void main(String args[]) throws Exception {

		CodeSimulatorDFS sim1 = new CodeSimulatorDFS();

		int[] counterExample = sim1.exploreSpace();

		if (counterExample != null) {

			// TODO: implement sim1.simulate()
			// sim1.simulate(initial, counterExample, txtFile);

			System.out.println("Found counter example");

			// 100 is a special return code for finding a counter-example but
			// terminating successfully

			System.exit(100);

		}

	}

	public int[] exploreSpace() {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 5; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 1; // {EXPANDED}
		//@formatter:on

		final int MAX_SIZE = 1 << stateBitCount;

		final int INITIAL_STATE = 0;

		final int UNDISCOVERED = -1;

		final int VISITED = -2;

		final int inputCombinationCount = 1 << inputBitCount;

		int stateStack[] = new int[MAX_SIZE];

		int inputVectors[] = new int[MAX_SIZE];

		Arrays.fill(inputVectors, UNDISCOVERED);

		int stateStackPtr = 0; // next available empty slot

		stateStack[stateStackPtr++] = INITIAL_STATE; // initial state

		inputVectors[INITIAL_STATE] = 0; // initial state

		int all_assumptions;
		int all_assertions;
		int all_live_assertions;

		//@formatter:off
		// int {STATE_BIT};
		int n_n52_21; // {EXPANDED}
		int n_n53_29; // {EXPANDED}
		int nstate_0__28; // {EXPANDED}
		int nstate_1__17; // {EXPANDED}
		int nstate_2__24; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n_n54_31; // {EXPANDED}
		int n_n55_32; // {EXPANDED}
		int n_n56_25; // {EXPANDED}
		int n_n59_11; // {EXPANDED}
		int n_00__0__8; // {EXPANDED}
		int n_00__1__12; // {EXPANDED}
		int n_00__2__23; // {EXPANDED}
		int n_01__14; // {EXPANDED}
		int n_02__2; // {EXPANDED}
		int n_03__4; // {EXPANDED}
		int n_04__18; // {EXPANDED}
		int n_05__13; // {EXPANDED}
		int n_06__26; // {EXPANDED}
		int n_07__19; // {EXPANDED}
		int n_08__9; // {EXPANDED}
		int n_09__20; // {EXPANDED}
		int n_10__27; // {EXPANDED}
		int n_11__15; // {EXPANDED}
		int n_12__1; // {EXPANDED}
		int n_13__16; // {EXPANDED}
		int n_14__3; // {EXPANDED}
		int n_15__22; // {EXPANDED}
		//@formatter:on

		while (stateStackPtr > 0) {

			int currentState = stateStack[stateStackPtr - 1]; // peek

			int currentInputVec = inputVectors[currentState];

			if (currentInputVec < inputCombinationCount) {

				printState(stateStackPtr, String.format("(inpVec = %d) currentState", currentInputVec), currentState);

				// there is at least one more nextState to explore

				// int nextState = graph[currentState][currentInputVec];

				//@formatter:off
				// {STATE_BIT} = -(currentState >> {STATE_BIT_INDEX} & 1);
				n_n52_21 = -(currentState >> 0 & 1); // {EXPANDED}
				n_n53_29 = -(currentState >> 1 & 1); // {EXPANDED}
				nstate_0__28 = -(currentState >> 2 & 1); // {EXPANDED}
				nstate_1__17 = -(currentState >> 3 & 1); // {EXPANDED}
				nstate_2__24 = -(currentState >> 4 & 1); // {EXPANDED}
				//@formatter:on

				//@formatter:off
				// int {INPUT_BIT} = -(currentInputVec >> {INPUT_BIT_INDEX} & 1);
				int na_10 = -(currentInputVec >> 0 & 1); // {EXPANDED}
				//@formatter:on

				// increment inputVectors afterwards so that the first input
				// vector is 0

				inputVectors[currentState] = currentInputVec + 1;

				//@formatter:off
				// {COMB_ASSIGN}
				n_15__22 = 0; // {EXPANDED}
				n_14__3 = ~nstate_1__17; // {EXPANDED}
				n_03__4 = ~(na_10 & n_14__3); // {EXPANDED}
				n_08__9 = nstate_1__17 | na_10; // {EXPANDED}
				n_n56_25 = ~n_n53_29; // {EXPANDED}
				n_n59_11 = n_n56_25 & n_n52_21; // {EXPANDED}
				n_n55_32 = nstate_2__24 | n_n53_29; // {EXPANDED}
				n_n54_31 = na_10 | n_n52_21; // {EXPANDED}
				n_01__14 = ~(n_14__3 & nstate_0__28); // {EXPANDED}
				n_13__16 = ~nstate_2__24; // {EXPANDED}
				n_06__26 = ~(nstate_0__28 & n_13__16); // {EXPANDED}
				n_04__18 = n_14__3 | nstate_0__28; // {EXPANDED}
				n_02__2 = ~(n_01__14 | n_13__16); // {EXPANDED}
				n_10__27 = ~(nstate_2__24 & n_01__14); // {EXPANDED}
				n_09__20 = ~(n_08__9 & n_01__14); // {EXPANDED}
				n_05__13 = ~(n_04__18 & n_03__4); // {EXPANDED}
				n_11__15 = n_06__26 | n_03__4; // {EXPANDED}
				n_00__0__8 = ~(n_02__2 | n_05__13); // {EXPANDED}
				n_12__1 = n_04__18 & n_11__15; // {EXPANDED}
				n_07__19 = n_06__26 | na_10; // {EXPANDED}
				n_00__1__12 = ~(n_07__19 & n_09__20); // {EXPANDED}
				n_00__2__23 = ~(n_12__1 & n_10__27); // {EXPANDED}
				//@formatter:on

				int nextState = 0;

				//@formatter:off
				// nextState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
				nextState |= n_n54_31 & (1 << 0); // {EXPANDED}
				nextState |= n_n55_32 & (1 << 1); // {EXPANDED}
				nextState |= n_00__0__8 & (1 << 2); // {EXPANDED}
				nextState |= n_00__1__12 & (1 << 3); // {EXPANDED}
				nextState |= n_00__2__23 & (1 << 4); // {EXPANDED}
				//@formatter:on

				all_assumptions = -1;
				all_assertions = -1;

				// In the code below we logically AND all assumptions
				// and assertions.

				// We don't want any property to evaluate to false if
				// until we're at least {MAXDELAY} away from the initial state.
				// This is {MAXDELAY} is the max depth of flip-flop chains
				// within
				// the property.

				// all_assumptions &= {ASSUMPTION} | (distance > {MAXDELAY} ? 0
				// : -1);
				// all_assertions &= {ASSERTION} | (distance > {MAXDELAY} ? 0 :
				// -1);

				if (all_assumptions == -1 && all_assertions == 0) {

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
					all_live_assertions &= n_n59_11; // {EXPANDED}
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

			int[] counter = new int[stateStackPtr];

			for (int j = 0; j < stateStackPtr; j++)
				counter[j] = inputVectors[stateStack[j]];

			return counter;

		} else {

			return null;

		}

	}

	static void printState(int depth, String label, int state) {

		for (int j = 0; j < depth - 1; j++)
			System.out.printf("  ");

		System.out.println(label + ": " + state + " (" + (state >> 2) + ")");
	}

}
