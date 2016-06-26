package net.xprova.propertylanguage;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import net.xprova.propertylanguage.PropertyLanguageParser.PropertyContext;

public class PropertyParser {

	public Property parse(String str) {

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		PropertyContext p = p1.property();

		if (p.getChildCount() == 3) {

			// this is an implication expression

			String antecedent = p.identifier(0).getText();
			String consequent = p.identifier(1).getText();

			return new Property(antecedent, consequent);

		} else {

			// a regular expression

			return new Property(p.identifier(0).getText());

		}

	}

}
