package net.xprova.propertylanguage;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import net.xprova.propertylanguage.PropertyLanguageParser.AssertionContext;

public class PropertyLanguageTest {

	public void test() {

		String str = "assert hello assert world";

		ANTLRInputStream antlr = new ANTLRInputStream(str);

		PropertyLanguageLexer lexer1 = new PropertyLanguageLexer(antlr);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer1);

		PropertyLanguageParser p1 = new PropertyLanguageParser(tokenStream);

		List<AssertionContext> assertionList = p1.document().assertion();

		System.out.printf("Parsed %d assertions\n", assertionList.size());

		for (AssertionContext a : assertionList)
			System.out.println("ID = " + a.ID().toString());

	}

}
