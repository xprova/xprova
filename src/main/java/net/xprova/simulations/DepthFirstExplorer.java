package net.xprova.simulations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstExplorer {

	static final int STACK_SIZE = 10000;
	static final int STATE_SIZE = 50000;

	private static boolean dfs(int current, Stack<Integer> stack, HashSet<Integer> visited) {

		stack.push(current);

		for (int c : getNextStates(current)) {

			if (visited.contains(c))
				continue;

			if (stack.contains(c)) {
				System.out.println("Found cycle involving state " + c);
				return true;
			}

			if (dfs(c, stack, visited))
				return true;

		}

		stack.pop();

		return false;

	}

	public static void main(String[] args) {

		Stack<Integer> stack = new Stack<Integer>();

		HashSet<Integer> visited = new HashSet<Integer>();

		dfs(0, stack, visited);

	}

	public static void main_old(String[] args) {

		int state = 0; // initial state

		int[] stack = new int[STACK_SIZE];
		int[] visited = new int[STATE_SIZE];

		Arrays.fill(visited, 0);

		int stackPtr = 0; // next empty slot

		stack[stackPtr++] = state; // push initial state

		while (stackPtr > 0) {

			int s = stack[--stackPtr]; // peek state

			if (visited[s] == 1) {

				// check if s is currently on the stack

				System.out.println("node " + s + " is visited");

				System.out.println("current stack:");

				boolean onStack = false;

				for (int k = 0; k < stackPtr; k++) {

					System.out.println("* " + stack[k]);

					onStack |= (stack[k] == s);

				}

				if (onStack)
					System.out.println("discovered cycle involving node " + s);

				continue;

			} else {

				visited[s] = 1;

				System.out.println("popped " + s);

				for (Integer x : getNextStates(s)) {

					System.out.println("added to stack: " + x);

					stack[stackPtr++] = x;
				}

			}
		}

	}

	public static ArrayList<Integer> getNextStates(int state) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		if (state == 0) {

			result.add(1);
			result.add(2);

		} else if (state == 1) {

			result.add(3);
			result.add(4);

		} else if (state == 2) {

			result.add(6);

		} else if (state == 4) {

			result.add(5);
			result.add(6);

		} else if (state == 6) {

			result.add(0);

		} else {

			result.add(state);
		}

		return result;

	}

}
