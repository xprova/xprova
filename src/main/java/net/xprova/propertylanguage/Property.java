package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Property {

	private String expr;

	private String printExpr;

	private List<String> orderedIdentifiers;

	public Property(String expr) {

		this.expr = "ID";

		this.printExpr = expr;

		orderedIdentifiers = new ArrayList<String>();

		orderedIdentifiers.add(expr);

	}

	public Property(String antecedent, String consequent) {

		this.expr = "(~ID | ID)";

		this.printExpr = String.format("%s |-> %s", antecedent, consequent);

		orderedIdentifiers = new ArrayList<String>();

		orderedIdentifiers.add(antecedent);

		orderedIdentifiers.add(consequent);

	}

	public List<String> getIdentifiers() {

		return new ArrayList<String>(orderedIdentifiers);

	}

	@Override
	public String toString() {

		return printExpr;

	}

	public String toString(HashMap<String, String> mapID) {

		String result = expr;

		for (String id : orderedIdentifiers)
			result = result.replaceFirst("ID", mapID.get(id));

		return result;

	}

}
