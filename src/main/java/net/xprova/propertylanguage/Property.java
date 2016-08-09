package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.List;

public class Property {

	public String name;

	public int delay;

	public List<Property> children;

	public static Property build(String name) {

		return new Property(name);

	}

	public Property delay(int delay) {

		this.delay = delay;

		return this;

	}

	public Property child(Property child) {

		children = new ArrayList<Property>();

		children.add(child);

		return this;

	}

	public Property children(List<Property> children) {

		this.children = children;

		return this;

	}

	public Property(String name) {

		this.name = name;

		this.children = new ArrayList<Property>();

	}

	public Property(Property other) {

		this.name = other.name;

		this.delay = other.delay;

		this.children = new ArrayList<Property>();

		for (Property c : other.children)
			this.children.add(new Property(c));

	}

	public void print() {

		print("", true);
	}

	public boolean isTerminal() {

		return children.isEmpty();

	}

	public void addDelayRecur(int extraDelay) {

		// adds to the delay of terminal nodes under root

		if (isTerminal()) {

			delay += extraDelay;

		} else {

			for (Property c : children)
				c.addDelayRecur(extraDelay);

		}

	}

	public int getMinDelay(int parentDelay) {

		if (isTerminal()) {

			return parentDelay + delay;

		} else {

			int minDelay = Integer.MAX_VALUE;

			for (Property n : children) {

				int d = n.getMinDelay(parentDelay + delay);

				minDelay = d < minDelay ? d : minDelay;

			}

			return minDelay;

		}

	}

	public int getMaxDelay(int parentDelay) {

		if (isTerminal()) {

			return parentDelay + delay;

		} else {

			int maxDelay = Integer.MIN_VALUE;

			for (Property n : children) {

				int d = n.getMaxDelay(parentDelay + delay);

				maxDelay = d > maxDelay ? d : maxDelay;

			}

			return maxDelay;

		}

	}

	public void flattenDelays(int parentDelay) {

		// this function propagates delays down a tree, effectively
		// mapping an expression like (@1 (a & b)) to (@1 a & @1 b)

		if (isTerminal()) {

			delay += parentDelay;

		} else {

			for (Property c : children)
				c.flattenDelays(delay + parentDelay);

			delay = 0;

		}

	}

	public void groupDelays() {

		// this is the reverse operation of flatten

		// it minimises the sum of delays across all nodes, effectively mapping
		// an expression like (@1 a & @1 b) to (@1 (a & b))

		if (isTerminal()) {

			return;

		} else {

			for (Property c : children)
				c.groupDelays();

		}

		int minChildDelay = Integer.MAX_VALUE;

		for (Property c : children)
			minChildDelay = c.delay < minChildDelay ? c.delay : minChildDelay;

		for (Property c : children)
			c.delay -= minChildDelay;

		delay += minChildDelay;

	}

	private void print(String prefix, boolean isTail) {

		String n = delay != 0 ? String.format("%s (delay %d)", name, delay) : name;

		System.out.println(prefix + (isTail ? "\u2514\u2500\u2500 " : "\u251C\u2500\u2500 ") + n);

		if (children != null) {

			for (int i = 0; i < children.size() - 1; i++)
				children.get(i).print(prefix + (isTail ? "    " : "\u2502   "), false);

			if (children.size() > 0)
				children.get(children.size() - 1).print(prefix + (isTail ? "    " : "\u2502   "), true);

		}
	}
}