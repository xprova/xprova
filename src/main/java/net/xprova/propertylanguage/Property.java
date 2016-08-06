package net.xprova.propertylanguage;

import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import net.xprova.propertylanguage.PropertyLanguageParser.AtomContext;
import net.xprova.propertylanguage.PropertyLanguageParser.PropertyContext;

public class Property {

	public final TreeNode root;

	// these must be the same as their correspondents in grammar:
	private final String NOT = "~";
	private final String AND = "&";
	private final String XOR = "^";
	private final String OR = "|";
	private final String EQ = "==";
	private final String NEQ = "!=";
	private final String IMPLY = "|->";
	private final String IMPLY_NEXT = "|=>";
	private final String LPAREN = "(";
	private final String AT = "@";
	private final String HASH = "#";
	private final String DOUBLE_HASH = "##";
	private final String ROSE = "$rose";
	private final String FELL = "$fell";
	private final String STABLE = "$stable";
	private final String CHANGED = "$changed";
	private final String ALWAYS = "$always";

	private void rewriteSyntaticSugar(TreeNode root) {

		for (TreeNode c : root.children)
			rewriteSyntaticSugar(c);

		// change (x |=> #n y) into (x |-> #n+1 y)

		if (root.name.equals(IMPLY_NEXT)) {

			root.name = IMPLY;

			TreeNode c1 = root.children.get(1);

			c1.delay -= 1;
		}

		// change (x |-> y) into (~x | y)

		if (root.name.equals(IMPLY)) {

			TreeNode c1 = root.children.get(0);

			TreeNode not = TreeNode.build(NOT);

			not.children.add(c1);

			root.children.set(0, not);

			root.name = "|";

		}

		// change ($rose(x)) into (~x & #1 x)

		if (root.name.equals(ROSE)) {

			root.name = AND;

			TreeNode c1 = root.children.get(0);

			TreeNode c2 = TreeNode.build(NOT).child(new TreeNode(c1)).delay(1);

			root.children.clear();

			root.children.add(c1);
			root.children.add(c2);
		}

		// change ($fell(x)) into (x & #1 ~x)

		if (root.name.equals(FELL)) {

			root.name = AND;

			TreeNode c1 = root.children.get(0);

			TreeNode c2 = TreeNode.build(NOT).child(new TreeNode(c1));

			c1.delay += 1;

			root.children.clear();

			root.children.add(c1);
			root.children.add(c2);
		}

		// $stable(x) into ~(x ^ #1 x)

		if (root.name.equals(STABLE)) {

			TreeNode c1 = new TreeNode(root.children.get(0));
			TreeNode c2 = new TreeNode(root.children.get(0));

			c2.delay += 1;

			TreeNode xorN = TreeNode.build(XOR);

			xorN.children.add(c1);
			xorN.children.add(c2);

			root.name = NOT;

			root.children.clear();

			root.children.add(xorN);

		}

		// $changed(x) into (x ^ #1 x)

		if (root.name.equals(CHANGED)) {

			TreeNode c1 = new TreeNode(root.children.get(0));
			TreeNode c2 = new TreeNode(root.children.get(0));

			c2.delay += 1;

			root.name = XOR;

			root.children.clear();

			root.children.add(c1);
			root.children.add(c2);

		}

		// (x == y) into ~(x ^ y)

		if (root.name.equals(EQ)) {

			TreeNode xorNode = new TreeNode(root);

			xorNode.name = "^";

			root.children.clear();

			root.name = "~";

			root.children.add(xorNode);

		}

		// (x != y) into (x ^ y)

		if (root.name.equals(NEQ)) {

			root.name = "^";

		}

	}

	private TreeNode parseAST(ParseTree root) throws Exception {

		ArrayList<TreeNode> children = new ArrayList<TreeNode>();

		if (root.getChildCount() == 1) {

			if (root.getPayload() instanceof AtomContext) {

				return TreeNode.build(root.getText());

			} else {

				return parseAST(root.getChild(0));

			}

		}

		if (root.getChildCount() == 2) {

			// NOT

			children.add(parseAST(root.getChild(1)));

			return TreeNode.build(root.getChild(0).getText()).children(children);

		}

		String c0 = root.getChild(0).getText();
		String c1 = root.getChild(1).getText();

		if (ROSE.equals(c0) || FELL.equals(c0) || STABLE.equals(c0) || CHANGED.equals(c0) || ALWAYS.equals(c0)) {

			children.add(parseAST(root.getChild(2)));

			return TreeNode.build(c0).children(children);

		}

		if (AND.equals(c1) || XOR.equals(c1) || OR.equals(c1)) {

			for (int i = 0; i < root.getChildCount(); i += 2)
				children.add(parseAST(root.getChild(i)));

			return TreeNode.build(c1).children(children);

		} else if (DOUBLE_HASH.equals(c1)) {

			int cumDelay = 0;

			for (int i = 0; i < root.getChildCount(); i++) {

				ParseTree ci = root.getChild(i);

				if (ci.getPayload() instanceof Token) {

					Token pl = (Token) ci.getPayload();

					if (DOUBLE_HASH.equals(pl.getText())) {

						cumDelay += 1;

					} else {

						// token is NUM

						// mind the (-1): we've incremented cumDelay when
						// we processed the preceding DOUBLE_DASH so this
						// is to make the total increase due to ##n equal
						// to n

						cumDelay += Integer.valueOf(pl.getText()) - 1;

					}

				} else {

					// this is an identifier

					TreeNode childNode = parseAST(ci);

					childNode.delay -= cumDelay;

					children.add(childNode);

				}

			}

			return TreeNode.build(AND).children(children);

		} else if (EQ.equals(c1) || NEQ.equals(c1) || IMPLY.equals(c1) || IMPLY_NEXT.equals(c1)) {

			children.add(parseAST(root.getChild(0)));
			children.add(parseAST(root.getChild(2)));

			return TreeNode.build(c1).children(children);

		} else if (c0.equals(LPAREN)) {

			children.add(parseAST(root.getChild(1)));

			return TreeNode.build(LPAREN).children(children);

		} else if (c0.equals(AT)) {

			children.add(parseAST(root.getChild(2)));

			int delay = Integer.valueOf(c1);

			return TreeNode.build(LPAREN).children(children).delay(delay);

		} else if (c0.equals(HASH)) {

			children.add(parseAST(root.getChild(2)));

			int delay = -Integer.valueOf(c1);

			return TreeNode.build(LPAREN).children(children).delay(delay);

		}

		System.out.println(root.getText());

		throw new Exception("error while traversing property AST");

	}

	public Property(String str) throws Exception {

		// step 1: generate property AST

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		PropertyContext e = p1.property();

		// step 2: traverse AST to generate expression tree

		root = parseAST(e.getChild(0));

		// step 3: process syntactic sugar

		rewriteSyntaticSugar(root);

		// step 4: normalise delays

		root.flattenDelays(0);

		root.addDelayRecur(-root.getMinDelay(0));

		root.groupDelays();

		root.print();

	}

}
