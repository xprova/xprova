package net.xprova.propertylanguage;

import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import net.xprova.propertylanguage.PropertyLanguageParser.AtomContext;
import net.xprova.propertylanguage.PropertyLanguageParser.PropertyContext;

public class PropertyParser {

	// these must be the same as their correspondents in grammar:
	private final String NOT = "~";
	private final String AND = "&";
	private final String XOR = "^";
	private final String OR = "|";
	private final String EQ = "==";
	private final String NEQ = "!=";
	private final String IMPLY = "|->";
	private final String LPAREN = "(";

	private final String placeHolderID = "%s";

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

			String c1 = root.getChild(1).getText();

			if (AND.equals(c1) || XOR.equals(c1) || OR.equals(c1)) {

				for (int i = 0; i < root.getChildCount(); i += 2)
					children.add(parseAST(root.getChild(i)));

				return new TreeNode(c1, children);

			} else if (EQ.equals(c1) || NEQ.equals(c1) || IMPLY.equals(c1)) {

				children.add(parseAST(root.getChild(0)));
				children.add(parseAST(root.getChild(2)));

				return new TreeNode(c1, children);

			} else if (root.getChild(0).getText().equals(LPAREN)) {

				children.add(parseAST(root.getChild(1)));

				return new TreeNode(LPAREN, children);

			}
		}

		throw new Exception("error while traversing property AST");

	}

	private void rewriteImply(TreeNode root) {

		// re-writes nodes in the form (x |-> y) as (!x | y)

		if (root.name.equals(IMPLY)) {

			TreeNode ant = root.children.get(0);

			ArrayList<TreeNode> nantChildren = new ArrayList<TreeNode>();

			nantChildren.add(ant);

			TreeNode nant = new TreeNode(NOT, nantChildren);

			root.children.set(0, nant);

			root.name = OR;

		} else {

			for (TreeNode c : root.children)
				rewriteImply(c);

		}

	}

	private String visitExprTree(TreeNode root, ArrayList<String> oIDs) {

		// each non-ID expression is wrapped in parenthesis

		switch (root.name) {

		case NOT:
			return String.format("(%s%s)", NOT, visitExprTree(root.children.get(0), oIDs));

		case AND:
		case XOR:
		case OR:

			String result = "";

			for (TreeNode c : root.children) {

				String cStr = visitExprTree(c, oIDs);

				result += result.isEmpty() ? cStr : String.format(" %s %s", root.name, cStr);

			}

			return String.format("(%s)", result);

		case EQ:
		case NEQ:
		case IMPLY:

			String c0Str = visitExprTree(root.children.get(0), oIDs);
			String c1Str = visitExprTree(root.children.get(1), oIDs);

			return String.format("(%s %s %s)", c0Str, root.name, c1Str);

		case LPAREN:

			return visitExprTree(root.children.get(0), oIDs);

		default:

			if (oIDs == null) {

				return root.name;

			} else {

				oIDs.add(root.name);

				return placeHolderID;

			}

		}

	}

	public Property parse(String str) throws Exception {

		// step 1: generate property AST

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		PropertyContext e = p1.property();

		// step 2: traverse AST to generate expression tree

		TreeNode root = parseAST(e.getChild(0));

		String printExpr = visitExprTree(root, null);

		// step 3: re-write certain portions of expression tree

		rewriteImply(root);

		// step 4: generate property

		ArrayList<String> oIDs = new ArrayList<String>();

		String expr = visitExprTree(root, oIDs);

		Property p = new Property(expr, printExpr, oIDs, placeHolderID);

		return p;

	}

}
