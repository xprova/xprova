package net.xprova.xprova;

import java.util.Arrays;

public class CodeSimulator2 {

	public static final int L = 0;
	public static final int H = -1;

	static final int[][] graph = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	public static void main(String args[]) throws Exception {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		int stateBitCount = 3; // {EXPANDED}
		// int inputBitCount = {INPUT_BIT_COUNT};
		int inputBitCount = 1; // {EXPANDED}
		//@formatter:on

		final int MAX_SIZE = 1 << stateBitCount;;

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
		int nstate_0__24; // {EXPANDED}
		int nstate_1__13; // {EXPANDED}
		int nstate_2__23; // {EXPANDED}
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
		int n_00__0__8; // {EXPANDED}
		int n_00__1__0; // {EXPANDED}
		int n_00__2__1; // {EXPANDED}
		int n_01__2; // {EXPANDED}
		int n_02__6; // {EXPANDED}
		int n_03__22; // {EXPANDED}
		int n_04__3; // {EXPANDED}
		int n_05__26; // {EXPANDED}
		int n_06__9; // {EXPANDED}
		int n_07__17; // {EXPANDED}
		int n_08__19; // {EXPANDED}
		int n_09__20; // {EXPANDED}
		int n_10__15; // {EXPANDED}
		int n_11__7; // {EXPANDED}
		int n_12__18; // {EXPANDED}
		int n_13__25; // {EXPANDED}
		int n_14__12; // {EXPANDED}
		int n_15__10; // {EXPANDED}
		//@formatter:on

		while (stateStackPtr > 0) {

			int currentState = stateStack[stateStackPtr - 1]; // peek

			int currentInputVec = inputVectors[currentState];

			if (currentInputVec < inputCombinationCount) {

				// there is at least one more nextState to explore

				inputVectors[currentState] = currentInputVec + 1;

				//@formatter:off
				// {STATE_BIT} = -(currentState >> {STATE_BIT_INDEX} & 1);
				nstate_0__24 = -(currentState >> 0 & 1); // {EXPANDED}
				nstate_1__13 = -(currentState >> 1 & 1); // {EXPANDED}
				nstate_2__23 = -(currentState >> 2 & 1); // {EXPANDED}
				//@formatter:on

				//@formatter:off
				// int {INPUT_BIT} = -(currentInputVec >> {INPUT_BIT_INDEX} & 1);
				int na_16 = -(currentInputVec >> 0 & 1); // {EXPANDED}
				//@formatter:on

				//@formatter:off
				// {COMB_ASSIGN}
				n_15__10 = 0; // {EXPANDED}
				n_13__25 = ~nstate_2__23; // {EXPANDED}
				n_06__9 = ~(nstate_0__24 & n_13__25); // {EXPANDED}
				n_08__19 = na_16 | nstate_1__13; // {EXPANDED}
				n_14__12 = ~nstate_1__13; // {EXPANDED}
				n_03__22 = ~(na_16 & n_14__12); // {EXPANDED}
				n_01__2 = ~(nstate_0__24 & n_14__12); // {EXPANDED}
				n_04__3 = nstate_0__24 | n_14__12; // {EXPANDED}
				n_07__17 = na_16 | n_06__9; // {EXPANDED}
				n_02__6 = ~(n_01__2 | n_13__25); // {EXPANDED}
				n_09__20 = ~(n_01__2 & n_08__19); // {EXPANDED}
				n_11__7 = n_03__22 | n_06__9; // {EXPANDED}
				n_00__1__0 = ~(n_07__17 & n_09__20); // {EXPANDED}
				n_10__15 = ~(n_01__2 & nstate_2__23); // {EXPANDED}
				n_12__18 = n_11__7 & n_04__3; // {EXPANDED}
				n_05__26 = ~(n_03__22 & n_04__3); // {EXPANDED}
				n_00__2__1 = ~(n_10__15 & n_12__18); // {EXPANDED}
				n_00__0__8 = ~(n_02__6 | n_05__26); // {EXPANDED}
				//@formatter:on

				int nextState = 0;

				//@formatter:off
				// nextState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
				nextState |= n_00__0__8 & (1 << 0); // {EXPANDED}
				nextState |= n_00__1__0 & (1 << 1); // {EXPANDED}
				nextState |= n_00__2__1 & (1 << 2); // {EXPANDED}
				//@formatter:on

				System.out.println("found path: " + currentState + " -> " + nextState);

				if (inputVectors[nextState] == UNDISCOVERED) {

					// push to stack

					stateStack[stateStackPtr++] = nextState;

					inputVectors[nextState] = 0;

				} else if (inputVectors[nextState] != VISITED) {

					// found a cycle

					System.out.println("found a cycle");

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

}
