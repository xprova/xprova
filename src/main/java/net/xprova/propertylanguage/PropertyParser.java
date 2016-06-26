package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import net.xprova.propertylanguage.PropertyLanguageParser.PropertyContext;

public class PropertyParser {

	public List<Property> parse(String str) {

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		List<PropertyContext> properyList = p1.document().property();

		ArrayList<Property> pList = new ArrayList<Property>();

		for (PropertyContext a : properyList) {

			if (a.expression().getChildCount() == 3) {

				// this is an implication expression

				String antecedent = a.expression().identifier(0).getText();
				String consequent = a.expression().identifier(1).getText();

				pList.add(new Property(antecedent, consequent));

			} else {

				// a regular expression

				pList.add(new Property(a.expression().identifier(0).getText()));

			}

		}

		return pList;

	}

}
