package net.xprova.simulations;

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstExplorer {

	static final int[][] graph0 = { { 1 }, { 2 }, { 0 } };

	static final int[][] graph1 = { { 1, 2 }, { 3, 4 }, { 6 }, {}, { 5, 6 }, { 4 }, {} };

	static final int[][] graph2 = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

	static final int[][] graph3 = { { 1 }, { 2, 4 }, { 3 }, { 2 }, { 3 } };

	private static void dfs2_arrays(int[][] graph) {

		final int MAX_SIZE = 20;

		final int INITIAL_STATE = 0;

		// a variation of dfs2 that uses arrays instead of stacks and sets

		int visited[] = new int[MAX_SIZE];

		int onStack[] = new int[MAX_SIZE];

		int stateStack[] = new int[MAX_SIZE];

		int inputVecStack[] = new int[MAX_SIZE];

		int stateStackPtr = 0; // next available empty slot

		int inputVecStackPtr = 0; // next available empty slot

		stateStack[stateStackPtr++] = INITIAL_STATE; // initial state

		inputVecStack[inputVecStackPtr++] = 0; // initial state

		onStack[INITIAL_STATE] = 1;

		while (stateStackPtr > 0) {

			int currentState = stateStack[--stateStackPtr];

			int currentInputVec = inputVecStack[--inputVecStackPtr];

			onStack[currentState] -= 1;

			if (visited[currentState] == 1) {

				continue;
			}

			if (onStack[currentState] != 0) {

				Stack<Integer> states = new Stack<Integer>();

				for (int i = 0; i < stateStackPtr; i++)
					states.push(stateStack[i]);

				printCycle(states, currentState);

				continue;
			}

			if (currentInputVec < graph[currentState].length) {

				// there are more input vectors to explore in currentState

				// first push next input vector of current state:

				stateStack[stateStackPtr++] = currentState;

				inputVecStack[inputVecStackPtr++] = currentInputVec + 1;

				onStack[currentState] += 1;

				// now push next state to stack:

				int nextState = graph[currentState][currentInputVec];

				stateStack[stateStackPtr++] = nextState;

				inputVecStack[inputVecStackPtr++] = 0;

				onStack[nextState] += 1;

				continue;

			} else {

				// explored all input vectors of currentState

				// currentState is now marked as visited

				visited[currentState] = 1;

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

				printCycle(states, currentState);

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

				printCycle(stack, c);

				stack.pop();

				return true;
			}

			dfs(graph, c, stack, visited);

		}

		stack.pop();

		visited.add(current);

		return false;

	}

	private static void printCycle(Stack<Integer> stack, int currentState) {

		System.out.println("Found cycle:");

		stack.push(currentState);

		for (int c : stack)
			System.out.println("  item: " + c);

		stack.pop();

	}

	public static void main(String[] args) {

		int[][] graph = graph3;

		System.out.println("output of dfs():\n");

		dfs(graph, 0, new Stack<Integer>(), new HashSet<Integer>());

		System.out.println("\noutput of dfs2():\n");

		dfs2(graph);

		System.out.println("\noutput of dfs2_arrays():\n");

		dfs2_arrays(graph);

	}

}
