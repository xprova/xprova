package net.xprova.xprova;

import org.junit.Test;

import net.xprova.propertylanguage.Property;

public class PropertyParserTest {

	@Test
	public void testUnaryOperators() throws Exception {

		String[] ops = { "~" };

		for (String s : ops) {

			new Property(String.format("%sx", s));

		}
	}

	@Test
	public void testBinaryOperators() throws Exception {

		String[] ops = { "&", "|", "^", "==", "!=", "|->", "|=>" };

		for (String s : ops) {

			new Property(String.format("x %s y", s));

		}

	}

	@Test
	public void testFunctions() throws Exception {

		String[] ops = { "$rose", "$fell", "$stable", "$changed" };

		for (String s : ops) {

			new Property(String.format("%s (x)", s));

		}

	}

	@Test
	public void testDelayOperators() throws Exception {

		String[] ops = { "@1", "#1" };

		for (String s : ops) {

			new Property(String.format("%s x", s));

		}

	}

}
