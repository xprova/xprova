package net.xprova.xprova;

import java.util.Arrays;

public class CodeSimulator2 {

	public static final int L = 0;
	public static final int H = -1;

	static final int[][] graph = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	public static void main(String args[]) throws Exception {

		//@formatter:off
		// int stateBitCount = {STATE_BIT_COUNT};
		// int inputBitCount = {INPUT_BIT_COUNT};
		//@formatter:on

		final int MAX_SIZE = 20;

		final int INITIAL_STATE = 0;

		final int UNDISCOVERED = -1;

		final int VISITED = -2;

		int stateStack[] = new int[MAX_SIZE];

		int inputVectors[] = new int[MAX_SIZE];

		Arrays.fill(inputVectors, UNDISCOVERED);

		int stateStackPtr = 0; // next available empty slot

		stateStack[stateStackPtr++] = INITIAL_STATE; // initial state

		inputVectors[INITIAL_STATE] = 0; // initial state

		while (stateStackPtr > 0) {

			int currentState = stateStack[stateStackPtr - 1]; // peek

			int currentInputVec = inputVectors[currentState];

			if (currentInputVec < graph[currentState].length) {

				// there is at least one more nextState to explore

				inputVectors[currentState] = currentInputVec + 1;

				int nextState = graph[currentState][currentInputVec];

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