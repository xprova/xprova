package net.xprova.simulations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstExplorer {

	static final int[][] graph0 = { { 1 }, { 2 }, { 0 } };

	static final int[][] graph1 = { { 1, 2 }, { 3, 4 }, { 6 }, {}, { 5, 6 }, { 4 }, {} };

	static final int[][] graph2 = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	static final int[][] graph3 = { { 1 }, { 2, 4 }, { 3 }, { 2 }, { 3 } };

	static final int[][] graph4 = { { 1, 2, 3 }, { 2 }, { 3 }, { 1 } };

	private static void dfs2_arrays(int[][] graph) {

		// a variation of dfs2 that uses arrays instead of stacks and sets

		// This is a DFS algorithm based on a stack of nodes (stateStack)
		// representing the current search branch and an additional lookup table
		// (inputVectors) that holds the index of each node's child in the
		// stack.
		//
		// For example:
		//
		// stateStack : 0 1 2
		// InputVectors : 2 3 4
		//
		// mean that the current path is:
		//
		// state 0, currently at its 3rd child (index = 2)
		// state 1, currently at its 4th child (index = 3)
		// state 2, currently at its 5th child (index = 4)
		//
		// i.e. state 1 is the 3rd child of state 0 and so forth
		//
		// As the 5th child of state 2 is not on the stack, this means the
		// algorithm has just removed it from the stack and is about to check
		// whether state 2 has any subsequent children.
		//
		// The array inputVectors can also hold the special values:
		//
		// UNDISCOVERED : node has never been pushed to the stack
		//
		// VISITED : all node children have been processed
		//
		// These values can be used to determine in O(1) whether a node is on
		// the stack or not (specifically, the condition for this is
		// UNDISCOVERED & !VISITED).

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

					Stack<Integer> states = new Stack<Integer>();

					for (int i = 0; i < stateStackPtr; i++)
						states.push(stateStack[i]);

					printCycle(states, nextState, "dfs2_arrays");

				}

			} else {

				// explored all input vectors of currentState

				// currentState is now marked as visited

				inputVectors[currentState] = -1;

				stateStackPtr--; // pop

			}

		}

	}

	private static void dfs2(int[][] graph) {

		// a non-recursive implementation of dfs()

		HashSet<Integer> visited = new HashSet<Integer>();

		Stack<Integer> states = new Stack<Integer>();

		Stack<Integer> inputVecs = new Stack<Integer>();

		states.push(0); // initial state

		inputVecs.push(0); // first input vector

		while (!states.isEmpty()) {

			int currentState = states.pop();

			int currentInputVec = inputVecs.pop();

			if (visited.contains(currentState)) {

				continue;
			}

			if (states.contains(currentState)) {

				printCycle(states, currentState, "dfs2");

				continue;
			}

			if (currentInputVec < graph[currentState].length) {

				// there are more input vectors to explore in currentState

				// first push next input vector of current state:

				states.push(currentState);

				inputVecs.push(currentInputVec + 1);

				// now push next state to stack:

				int nextState = graph[currentState][currentInputVec];

				states.push(nextState);

				inputVecs.push(0);

				continue;

			} else {

				// explored all input vectors of currentState

				// currentState is now marked as visited

				visited.add(currentState);

			}

		}

	}

	private static boolean dfs(int[][] graph, int current, Stack<Integer> stack, HashSet<Integer> visited) {

		stack.push(current);

		for (int c : graph[current]) {

			if (visited.contains(c))
				continue;

			if (stack.contains(c)) {

				printCycle(stack, c, "dfs");

				stack.pop();

				return true;
			}

			dfs(graph, c, stack, visited);

		}

		stack.pop();

		visited.add(current);

		return false;

	}

	private static void printCycle(Stack<Integer> stack, int currentState, String methodName) {

		stack.push(currentState);

		for (int c : stack)
			System.out.printf("%d ", c);

		System.out.printf(" (%s)\n", methodName);

		stack.pop();

	}

	public static void main(String[] args) {

		int[][][] graphs = { graph0, graph1, graph2, graph3, graph4 };

		for (int i = 0; i < graphs.length; i++) {

			System.out.printf("Cycles in graph %d:\n", i);

			int[][] graph = graphs[i];

			dfs(graph, 0, new Stack<Integer>(), new HashSet<Integer>());

			dfs2(graph);

			dfs2_arrays(graph);

			System.out.println("");

		}

	}

}
