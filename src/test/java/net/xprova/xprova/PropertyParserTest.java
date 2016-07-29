package net.xprova.xprova;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import net.xprova.propertylanguage.Property;

public class PropertyParserTest {

	@Test
	public void testUnaryOperators() throws Exception {

		String[] ops = { "~" };

		for (String s : ops) {

			Property p = new Property(String.format("%sx", s));

			ArrayList<String> ids = p.getIdentifiers();

			assertEquals(ids.size(), 1);

			assertEquals(ids.get(0), "x");

		}
	}

	@Test
	public void testBinaryOperators() throws Exception {

		String[] ops = { "&", "|", "^", "==", "!=", "|->", "|=>" };

		for (String s : ops) {

			Property p = new Property(String.format("x %s y", s));

			ArrayList<String> ids = p.getIdentifiers();

			assertEquals(ids.size(), 2);

			assertEquals(ids.get(0), "x");

			assertEquals(ids.get(1), "y");
		}

	}

	@Test
	public void testFunctions() throws Exception {

		String[] ops = { "$rose", "$fell", "$fell", "$changed" };

		for (String s : ops) {

			Property p = new Property(String.format("%s (x)", s));

			ArrayList<String> ids = p.getIdentifiers();

			assertEquals(ids.size(), 2);

			assertEquals(ids.get(0), "x");

		}

	}

	@Test
	public void testDelayOperators() throws Exception {

		String[] ops = { "@1", "#1" };

		for (String s : ops) {

			Property p = new Property(String.format("%s x", s));

			ArrayList<String> ids = p.getIdentifiers();

			assertEquals(ids.size(), 1);

			assertEquals(ids.get(0), "x");

		}

	}

}
