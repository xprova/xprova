package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.List;

public class Property {

	private String expr;

	private List<String> identifiers;

	public Property(String expr) {

		this.expr = expr;

		identifiers = new ArrayList<String>();

		identifiers.add(expr);

	}

	public Property(String antecedent, String consequent) {

		this.expr = String.format("(~%s | %s)", antecedent, consequent);

		identifiers = new ArrayList<String>();

		identifiers.add(antecedent);

		identifiers.add(consequent);

	}

	public List<String> getIdentifiers() {

		return new ArrayList<String>(identifiers);

	}

	@Override
	public String toString() {

		return expr;

	}

}
