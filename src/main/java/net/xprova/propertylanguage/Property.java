package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import net.xprova.propertylanguage.PropertyLanguageParser.AtomContext;
import net.xprova.propertylanguage.PropertyLanguageParser.PropertyContext;

public class Property {

	private final TreeNode root;

	// these must be the same as their correspondents in grammar:
	private final String NOT = "~";
	private final String AND = "&";
	private final String XOR = "^";
	private final String OR = "|";
	private final String EQ = "==";
	private final String NEQ = "!=";
	private final String IMPLY = "|->";
	private final String LPAREN = "(";
	private final String AT = "@";

	// expression tree traversal functions

	private void addDelayRecur(TreeNode root, int extraDelay) {

		// adds to the delay of terminal nodes under root

		if (root.isTerminal()) {

			root.delay += extraDelay;

		} else {

			for (TreeNode c : root.children)
				addDelayRecur(c, extraDelay);

		}

	}

	private int getMaxDelay(TreeNode root) {

		if (root.isTerminal()) {

			return root.delay;

		} else {

			int maxDelay = Integer.MIN_VALUE;

			for (TreeNode n : root.children) {

				int d = getMaxDelay(n);

				maxDelay = d > maxDelay ? d : maxDelay;

			}

			return maxDelay;

		}

	}

	private void getIDsRecur(TreeNode root, ArrayList<String> ids) {

		if (root.isTerminal()) {

			ids.add(root.name);

		} else {

			for (TreeNode c : root.children)
				getIDsRecur(c, ids);

		}

	}

	private String getExprRecur(TreeNode root, ExpressionFormatter formatter, HashMap<String, String> identifierMap,
			String delayFormat) {

		// each non-ID expression is wrapped in parenthesis

		// delay format specifies how identifiers with non-zero delays should be
		// formatted
		// e.g. "%s @%d" formats net1 with a delay of 3 as "net1 @3"

		String r = (root.delay == 0) ? root.name : String.format(delayFormat, root.name, root.delay);

		if (root.isTerminal()) {

			return (identifierMap == null) ? r : identifierMap.get(r);

		}

		ArrayList<String> operands = new ArrayList<String>();

		for (TreeNode c : root.children)
			operands.add(getExprRecur(c, formatter, identifierMap, delayFormat));

		if (r.equals(NOT)) {

			return String.format("(%s)", formatter.getNOT(operands.get(0)));

		} else if (r.equals(AND)) {

			return String.format("(%s)", formatter.getAND(operands));

		} else if (r.equals(OR)) {

			return String.format("(%s)", formatter.getOR(operands));

		} else if (r.equals(XOR)) {

			return String.format("(%s)", formatter.getXOR(operands));

		} else if (r.equals(EQ)) {

			return String.format("(%s)", formatter.getEQ(operands.get(0), operands.get(1)));

		} else if (r.equals(NEQ)) {

			return String.format("(%s)", formatter.getNEQ(operands.get(0), operands.get(1)));

		} else if (r.equals(IMPLY)) {

			return String.format("(%s)", formatter.getIMPLY(operands.get(0), operands.get(1)));

		} else if (r.equals(LPAREN)) {

			return operands.get(0);

		} else {

			return "???";

		}

	}

	private void flattenDelays(TreeNode root, int parentDelay) {

		// this function propagates delays down a tree, effectively
		// mapping an expression like ((a & b) @ 1) to (a@1 & b@1)

		if (root.isTerminal()) {

			root.delay += parentDelay;

		} else {

			for (TreeNode c : root.children)
				flattenDelays(c, root.delay + parentDelay);

			root.delay = 0;

		}

	}

	private void getDelaysRecur(TreeNode root, HashMap<String, Integer> delays) {

		if (root.isTerminal()) {

			int currentDelay = delays.getOrDefault(root.name, 0);

			int d = root.delay > currentDelay ? root.delay : currentDelay;

			if (d != 0)
				delays.put(root.name, d);

		} else {

			for (TreeNode c : root.children)
				getDelaysRecur(c, delays);

		}

	}

	// AST parsing

	private TreeNode parseAST(ParseTree root) throws Exception {

		ArrayList<TreeNode> children = new ArrayList<TreeNode>();

		if (root.getChildCount() == 1) {

			if (root.getPayload() instanceof AtomContext) {

				return new TreeNode(root.getText(), null);

			} else {

				return parseAST(root.getChild(0));

			}

		}

		if (root.getChildCount() == 2) {

			// NOT

			children.add(parseAST(root.getChild(1)));

			return new TreeNode(root.getChild(0).getText(), children);

		} else {

			String c0 = root.getChild(0).getText();
			String c1 = root.getChild(1).getText();

			if (AND.equals(c1) || XOR.equals(c1) || OR.equals(c1)) {

				for (int i = 0; i < root.getChildCount(); i += 2)
					children.add(parseAST(root.getChild(i)));

				return new TreeNode(c1, children);

			} else if (EQ.equals(c1) || NEQ.equals(c1) || IMPLY.equals(c1)) {

				children.add(parseAST(root.getChild(0)));
				children.add(parseAST(root.getChild(2)));

				return new TreeNode(c1, children);

			} else if (c0.equals(LPAREN)) {

				children.add(parseAST(root.getChild(1)));

				return new TreeNode(LPAREN, children);

			} else if (c1.equals(AT)) {

				children.add(parseAST(root.getChild(0)));

				String c2 = root.getChild(2).getText();

				int delay = Integer.valueOf(c2);

				return new TreeNode(LPAREN, children, delay);
			}
		}

		System.out.println(root.getText());

		throw new Exception("error while traversing property AST");

	}

	// public methods

	public Property(String str) throws Exception {

		// step 1: generate property AST

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		PropertyContext e = p1.property();

		// step 2: traverse AST to generate expression tree

		root = parseAST(e.getChild(0));

		// temp

		flattenDelays(root, 0);

		root.print();
		//
		// System.out.println("max delay = " + getMaxDelay(root));
		//
		// addDelayRecur(root, -getMaxDelay(root));
		//
		// root.print();
		//
		// System.out.println("max delay = " + getMaxDelay(root));

	}

	public ArrayList<String> getIdentifiers() {

		ArrayList<String> list = new ArrayList<String>();

		getIDsRecur(root, list);

		return list;

	}

	public String getExpression(ExpressionFormatter formatter, HashMap<String, String> identifierMap,
			String delayFormat) {

		return getExprRecur(root, formatter, identifierMap, delayFormat);

	}

	@Override
	public String toString() {

		return getExprRecur(root, new ExpressionFormatter(), null, "%s @%d");

	}

	public void printExpressionTree() {

		root.print();

	}

	public void getDelays(HashMap<String, Integer> delays) {

		getDelaysRecur(root, delays);

	}

}
