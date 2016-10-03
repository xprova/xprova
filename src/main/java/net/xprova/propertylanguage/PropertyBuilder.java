package net.xprova.propertylanguage;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import net.xprova.propertylanguage.PropertyLanguageParser.AtomContext;
import net.xprova.propertylanguage.PropertyLanguageParser.PropertyContext;

public class PropertyBuilder {

	// these must be the same as their correspondents in grammar:
	public static final String NOT = "~";
	public static final String AND = "&";
	public static final String XOR = "^";
	public static final String OR = "|";
	public static final String EQ = "==";
	public static final String NEQ = "!=";
	public static final String IMPLY = "|->";
	public static final String IMPLY_NEXT = "|=>";
	public static final String LPAREN = "(";
	public static final String AT = "@";
	public static final String HASH = "#";
	public static final String DOUBLE_HASH = "##";
	public static final String ROSE = "$rose";
	public static final String FELL = "$fell";
	public static final String STABLE = "$stable";
	public static final String CHANGED = "$changed";
	public static final String ALWAYS = "$always";
	public static final String NEVER = "$never";
	public static final String EVENTUALLY = "$eventually";

	private static void rewriteSyntaticSugar(Property root) {

		for (Property c : root.children)
			rewriteSyntaticSugar(c);

		// change (x |=> #n y) into (x |-> #n+1 y)

		if (root.name.equals(IMPLY_NEXT)) {

			root.name = IMPLY;

			root.children.get(1).delay -= 1;

		}

		// change (x |-> y) into (~x | y)

		if (root.name.equals(IMPLY)) {

			Property c1 = root.children.get(0);
			Property c2 = root.children.get(1);

			Property notC1 = Property.build(NOT).setChild(c1);

			root.addChild(notC1).addChild(c2).name = OR;

		}

		// change ($rose(x)) into (~x & #1 x)

		if (root.name.equals(ROSE)) {

			Property c1 = root.children.get(0);

			Property notC1 = Property.build(NOT).setChild(new Property(c1));

			notC1.delay += 2;

			root.addChild(c1).addChild(notC1).name = AND;

		}

		// change ($fell(x)) into (x & #1 ~x)

		if (root.name.equals(FELL)) {

			Property c1 = root.children.get(0);

			Property notC1 = Property.build(NOT).setChild(new Property(c1));

			c1.delay += 1;

			root.addChild(c1).addChild(notC1).name = AND;

		}

		// $stable(x) into ~(x ^ #1 x)

		if (root.name.equals(STABLE)) {

			Property c1 = new Property(root.children.get(0));
			Property c2 = new Property(root.children.get(0));

			c2.delay += 1;

			Property xorN = Property.build(XOR).addChild(c1).addChild(c2);

			root.setChild(xorN).name = NOT;

		}

		// $changed(x) into (x ^ #1 x)

		if (root.name.equals(CHANGED)) {

			Property c1 = new Property(root.children.get(0));
			Property c2 = new Property(root.children.get(0));

			c2.delay += 1;

			root.addChild(c1).addChild(c2).name = XOR;

		}

		// (x == y) into ~(x ^ y)

		if (root.name.equals(EQ)) {

			Property xorNode = new Property(root);

			xorNode.name = XOR;

			root.setChild(xorNode).name = NOT;

		}

		// (x != y) into (x ^ y)

		if (root.name.equals(NEQ)) {

			root.name = XOR;

		}

		// $never(x) into $always(~x)

		if (root.name.equals(NEVER)) {

			Property notChild = Property.build(NOT).setChildren(root.children);

			root.setChild(notChild).name = ALWAYS;

		}

	}

	private static Property parseAST(ParseTree root) throws Exception {

		if (root.getChildCount() == 1) {

			if (root.getPayload() instanceof AtomContext) {

				return Property.build(root.getText());

			} else {

				return parseAST(root.getChild(0));

			}

		}

		if (root.getChildCount() == 2) {

			// NOT

			Property child = parseAST(root.getChild(1));

			return Property.build(root.getChild(0).getText()).setChild(child);

		}

		String c0 = root.getChild(0).getText();
		String c1 = root.getChild(1).getText();

		if (ROSE.equals(c0) || FELL.equals(c0) || STABLE.equals(c0) || CHANGED.equals(c0) || ALWAYS.equals(c0)
				|| NEVER.equals(c0)) {

			Property child = parseAST(root.getChild(2));

			return Property.build(c0).setChild(child);

		}

		if (AND.equals(c1) || XOR.equals(c1) || OR.equals(c1)) {

			Property result = Property.build(c1);

			for (int i = 0; i < root.getChildCount(); i += 2)
				result.addChild(parseAST(root.getChild(i)));

			return result;

		}

		if (DOUBLE_HASH.equals(c1)) {

			Property result = Property.build(AND);

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

					Property childNode = parseAST(ci);

					childNode.delay -= cumDelay;

					result.addChild(childNode);

				}

			}

			return result;

		}

		if (EQ.equals(c1) || NEQ.equals(c1) || IMPLY.equals(c1) || IMPLY_NEXT.equals(c1)) {

			Property op1 = parseAST(root.getChild(0));
			Property op2 = parseAST(root.getChild(2));

			return Property.build(c1).addChild(op1).addChild(op2);

		}

		if (c0.equals(LPAREN)) {

			Property expr = parseAST(root.getChild(1));

			return Property.build(LPAREN).setChild(expr);

		}

		if (c0.equals(AT)) {

			Property expr = parseAST(root.getChild(2));

			int delay = Integer.valueOf(c1);

			return Property.build(LPAREN).setChild(expr).delay(delay);

		}

		if (c0.equals(HASH)) {

			Property child = parseAST(root.getChild(2));

			int delay = -Integer.valueOf(c1);

			return Property.build(LPAREN).setChild(child).delay(delay);

		}

		if (c0.equals(EVENTUALLY)) {

			Property trigger = parseAST(root.getChild(2));

			Property expr = parseAST(root.getChild(4));

			return Property.build(EVENTUALLY).addChild(trigger).addChild(expr);

		}

		System.out.println(root.getText());

		throw new Exception("error while traversing property AST");

	}

	public static Property build(String str) throws Exception {

		// step 1: generate property AST

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		PropertyContext e = p1.property();

		// step 2: traverse AST to generate expression tree

		Property root = parseAST(e.getChild(0));

		// step 3: process syntactic sugar

		rewriteSyntaticSugar(root);

		// step 4: normalise delays

		root.flattenDelays(0);

		root.addDelayRecur(-root.getMinDelay(0));

		root.groupDelays();

		root.print();

		return root;

	}

}
