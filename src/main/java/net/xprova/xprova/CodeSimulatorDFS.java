package net.xprova.xprova;

import java.util.Arrays;

public class CodeSimulatorDFS {

	public static final int L = 0;
	public static final int H = -1;

	static final int[][] graph = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	public static void main(String args[]) throws Exception {

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

		//@formatter:off
		// int {STATE_BIT};
		int n_n52_26; // {EXPANDED}
		int n_n53_2; // {EXPANDED}
		int nstate_0__25; // {EXPANDED}
		int nstate_1__22; // {EXPANDED}
		int nstate_2__30; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n_n54_11; // {EXPANDED}
		int n_n55_5; // {EXPANDED}
		int n_n56_6; // {EXPANDED}
		int n_n59_20; // {EXPANDED}
		int n_00__0__21; // {EXPANDED}
		int n_00__1__0; // {EXPANDED}
		int n_00__2__31; // {EXPANDED}
		int n_01__16; // {EXPANDED}
		int n_02__13; // {EXPANDED}
		int n_03__4; // {EXPANDED}
		int n_04__32; // {EXPANDED}
		int n_05__15; // {EXPANDED}
		int n_06__7; // {EXPANDED}
		int n_07__23; // {EXPANDED}
		int n_08__28; // {EXPANDED}
		int n_09__14; // {EXPANDED}
		int n_10__17; // {EXPANDED}
		int n_11__8; // {EXPANDED}
		int n_12__29; // {EXPANDED}
		int n_13__12; // {EXPANDED}
		int n_14__18; // {EXPANDED}
		int n_15__24; // {EXPANDED}
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
				n_n52_26 = -(currentState >> 0 & 1); // {EXPANDED}
				n_n53_2 = -(currentState >> 1 & 1); // {EXPANDED}
				nstate_0__25 = -(currentState >> 2 & 1); // {EXPANDED}
				nstate_1__22 = -(currentState >> 3 & 1); // {EXPANDED}
				nstate_2__30 = -(currentState >> 4 & 1); // {EXPANDED}
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
				n_15__24 = 0; // {EXPANDED}
				n_n55_5 = n_n53_2 | nstate_2__30; // {EXPANDED}
				n_14__18 = ~nstate_1__22; // {EXPANDED}
				n_n56_6 = ~n_n53_2; // {EXPANDED}
				n_n54_11 = na_10 | n_n52_26; // {EXPANDED}
				n_13__12 = ~nstate_2__30; // {EXPANDED}
				n_08__28 = na_10 | nstate_1__22; // {EXPANDED}
				n_01__16 = ~(n_14__18 & nstate_0__25); // {EXPANDED}
				n_04__32 = n_14__18 | nstate_0__25; // {EXPANDED}
				n_03__4 = ~(na_10 & n_14__18); // {EXPANDED}
				n_10__17 = ~(n_01__16 & nstate_2__30); // {EXPANDED}
				n_n59_20 = n_n56_6 & n_n52_26; // {EXPANDED}
				n_02__13 = ~(n_13__12 | n_01__16); // {EXPANDED}
				n_09__14 = ~(n_08__28 & n_01__16); // {EXPANDED}
				n_06__7 = ~(n_13__12 & nstate_0__25); // {EXPANDED}
				n_05__15 = ~(n_03__4 & n_04__32); // {EXPANDED}
				n_11__8 = n_03__4 | n_06__7; // {EXPANDED}
				n_00__0__21 = ~(n_02__13 | n_05__15); // {EXPANDED}
				n_07__23 = na_10 | n_06__7; // {EXPANDED}
				n_00__1__0 = ~(n_09__14 & n_07__23); // {EXPANDED}
				n_12__29 = n_11__8 & n_04__32; // {EXPANDED}
				n_00__2__31 = ~(n_10__17 & n_12__29); // {EXPANDED}
				//@formatter:on

				int nextState = 0;

				//@formatter:off
				// nextState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
				nextState |= n_n54_11 & (1 << 0); // {EXPANDED}
				nextState |= n_n55_5 & (1 << 1); // {EXPANDED}
				nextState |= n_00__0__21 & (1 << 2); // {EXPANDED}
				nextState |= n_00__1__0 & (1 << 3); // {EXPANDED}
				nextState |= n_00__2__31 & (1 << 4); // {EXPANDED}
				//@formatter:on

				if (inputVectors[nextState] == UNDISCOVERED) {

					// push to stack

					stateStack[stateStackPtr++] = nextState;

					inputVectors[nextState] = 0;

				} else if (inputVectors[nextState] != VISITED) {

					// found a cycle

					// System.out.println(" found a cycle ");

					if (n_n59_20 == -1) {

						// System.out.println(" violation of liveness property,
						// stack (bottom to top):");

						for (int i = 0; i < stateStackPtr; i++)
							printState(stateStackPtr + 1, "State", stateStack[i]);

						printState(stateStackPtr + 1, "State", nextState);

					}

					// TODO:

				}

			} else {

				// explored all input vectors of currentState

				// currentState is now marked as visited

				inputVectors[currentState] = VISITED;

				stateStackPtr--; // pop

			}

		}

	}

	static void printState(int depth, String label, int state) {

		for (int j = 0; j < depth - 1; j++)
			System.out.printf("  ");

		System.out.println(label + ": " + state + " (" + (state >> 2) + ")");
	}

}
