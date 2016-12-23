package net.xprova.propertylanguage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Property {

	public String name;

	public int delay;

	// bitWidth: 0 stands for "not specified yet" and -1 for "variable" (i.e.
	// arbitrarily expandable, for constants)

	public List<Property> children;

	public static Property build(String name) {

		return new Property(name);

	}

	public boolean isNumber() {

		return name.matches("\\d*\\d+");

	}

	private static String getVerilogArrayName(String arrName, int index) {

		return String.format("%s[%d]", arrName, index);

	}

	public static Property slice(Property other, int index) {

		// returns a deep copy of `other` where each multi-bit net is replaced
		// with its item of index `index`

		if (other.isTerminal()) {

			Property p = new Property(other);

			if (p.isNumber()) {

				int val = Integer.parseInt(other.name);

				int bit = (val >> index) & 1;

				return new Property("" + bit);

			} else {

				p.name = getVerilogArrayName(other.name, index);

			}

			return p;

		} else {

			Property p = new Property(other);

			// replace p's children with deep copies

			ArrayList<Property> newChildren = new ArrayList<Property>();

			for (int i = 0; i < p.children.size(); i++) {

				Property c = other.children.get(i);

				newChildren.add(slice(c, index));

			}

			p.setChildren(newChildren);

			return p;

		}

	}

	public Property delay(int delay) {

		this.delay = delay;

		return this;

	}

	public Property setChild(Property child) {

		children = new ArrayList<Property>();

		return addChild(child);

	}

	public Property addChild(Property child) {

		children.add(child);

		return this;

	}

	public Property setChildren(List<Property> children) {

		this.children = new ArrayList<Property>();

		for (Property c : children)
			this.children.add(new Property(c));

		return this;

	}

	public Property(String name) {

		this.name = name;

		this.children = new ArrayList<Property>();

		this.delay = 0;

	}

	public Property(Property other) {

		this.name = other.name;

		this.delay = other.delay;

		setChildren(other.children);

	}

	public void copyFrom(Property other) {

		this.name = other.name;

		this.delay = other.delay;

		this.children = other.children;

	}

	public void print() {

		try {

			PrintStream out = new PrintStream(System.out, true, "UTF-8");

			print("", true, out);

		} catch (UnsupportedEncodingException e) {

			System.out.println("(internal error, could not print property)");

		}

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

	@Override
	public String toString() {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(baos);

		print("", true, ps);

		String content = new String(baos.toByteArray(), StandardCharsets.UTF_8);

		return content;

	}

	private void print(String prefix, boolean isTail, PrintStream out) {

		String n = name;

		n += (delay != 0) ? String.format(" (delay %d)", delay) : "";

		out.println(prefix + (isTail ? "\u2514\u2500\u2500 " : "\u251C\u2500\u2500 ") + n);

		if (children != null) {

			for (int i = 0; i < children.size() - 1; i++)
				children.get(i).print(prefix + (isTail ? "    " : "\u2502   "), false, out);

			if (children.size() > 0)
				children.get(children.size() - 1).print(prefix + (isTail ? "    " : "\u2502   "), true, out);

		}
	}

	public Property setName(String name) {

		this.name = name;

		return this;

	}
}