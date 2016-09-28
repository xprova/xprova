package net.xprova.simulations;

import java.util.HashSet;
import java.util.Stack;

public class DepthFirstExplorer {

	static final int[][] graph1 = { { 1, 2 }, { 3, 4 }, { 6 }, {}, { 5, 6 }, { 4 }, {} };

	static final int[][] graph2 = { { 1 }, { 2, 5 }, { 3 }, { 4 }, { 2 }, {6 }, { 7 }, { 5 } };

	private static boolean dfs(int current, Stack<Integer> stack, HashSet<Integer> visited) {

		stack.push(current);

		for (int c : graph2[current]) {

			if (visited.contains(c))
				continue;

			if (stack.contains(c)) {

				System.out.println("Found cycle:");

				stack.push(c);

				for (int c2 : stack)
					System.out.println("  item: " + c2);

				stack.pop();

				return true;
			}

			dfs(c, stack, visited);

		}

		visited.add(current);

		stack.pop();

		return false;

	}

	public static void main(String[] args) {

		dfs(0, new Stack<Integer>(), new HashSet<Integer>());

	}

}
