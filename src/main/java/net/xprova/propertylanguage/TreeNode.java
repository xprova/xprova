package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	public String name;

	public int delay;

	public List<TreeNode> children;

	public static TreeNode build(String name) {

		return new TreeNode(name);

	}

	public TreeNode delay(int delay) {

		this.delay = delay;

		return this;

	}

	public TreeNode child(TreeNode child) {

		children = new ArrayList<TreeNode>();

		children.add(child);

		return this;

	}

	public TreeNode children(ArrayList<TreeNode> children) {

		this.children = children;

		return this;

	}

	public TreeNode(String name) {

		this.name = name;

		this.children = new ArrayList<TreeNode>();

	}

	public TreeNode(TreeNode other) {

		this.name = other.name;

		this.delay = other.delay;

		this.children = new ArrayList<TreeNode>();

		for (TreeNode c : other.children)
			this.children.add(new TreeNode(c));

	}

	public void print() {

		print("", true);
	}

	public boolean isTerminal() {

		return children.isEmpty();

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