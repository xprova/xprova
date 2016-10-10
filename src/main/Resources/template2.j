import java.util.Arrays;

public class CodeSimulatorDFS {

	public static final int L = 0;
	public static final int H = -1;

	static final int[][] graph = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	public static void main(String args[]) throws Exception {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		// int inputBitCount = {INPUT_BIT_COUNT};
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
		//@formatter:on

		//@formatter:off
		// int {NON_STATE_BIT};
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
				//@formatter:on

				//@formatter:off
				// int {INPUT_BIT} = -(currentInputVec >> {INPUT_BIT_INDEX} & 1);
				//@formatter:on

				// increment inputVectors afterwards so that the first input
				// vector is 0

				inputVectors[currentState] = currentInputVec + 1;

				//@formatter:off
				// {COMB_ASSIGN}
				//@formatter:on

				int nextState = 0;

				//@formatter:off
				// nextState |= {NEXT_STATE_BIT} & (1 << {STATE_BIT_INDEX});
				//@formatter:on

				if (inputVectors[nextState] == UNDISCOVERED) {

					// push to stack

					stateStack[stateStackPtr++] = nextState;

					inputVectors[nextState] = 0;

				} else if (inputVectors[nextState] != VISITED) {

					// found a cycle

					// if (n_n59_20 == -1) {

					// 	for (int i = 0; i < stateStackPtr; i++)
					// 		printState(stateStackPtr + 1, "State", stateStack[i]);

					// 	printState(stateStackPtr + 1, "State", nextState);

					// }

					//return;

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
