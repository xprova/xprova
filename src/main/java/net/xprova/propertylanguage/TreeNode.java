package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	public String name;
	public List<TreeNode> children;

	public TreeNode(String name, List<TreeNode> children) {

		this.name = name;

		this.children = children == null ? new ArrayList<TreeNode>() : children;
	}

	public void print() {
		print("", true);
	}

	private void print(String prefix, boolean isTail) {
		System.out.println(prefix + (isTail ? "└── " : "├── ") + name);
		if (children != null) {
			for (int i = 0; i < children.size() - 1; i++) {
				children.get(i).print(prefix + (isTail ? "    " : "│   "), false);
			}
			if (children.size() > 0) {
				children.get(children.size() - 1).print(prefix + (isTail ? "    " : "│   "), true);
			}
		}
	}
}