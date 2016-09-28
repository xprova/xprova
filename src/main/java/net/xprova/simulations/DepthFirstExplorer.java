package net.xprova.simulations;

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstExplorer {

	static final int[][] graph1 = { { 1, 2 }, { 3, 4 }, { 6 }, {}, { 5, 6 }, { 4 }, {} };

	static final int[][] graph2 = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, { 6 }, { 7 }, { 5 } };

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

		System.out.println("output of dfs():\n");

		int[][] graph = graph2;

		dfs(graph, 0, new Stack<Integer>(), new HashSet<Integer>());

		System.out.println("\noutput of dfs2():\n");

		dfs2(graph);

	}

}
