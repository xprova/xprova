package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Property {

	private String expr;

	private List<String> orderedIdentifiers;

	public Property(String expr) {

		this.expr = "ID";

		orderedIdentifiers = new ArrayList<String>();

		orderedIdentifiers.add(expr);

	}

	public Property(String antecedent, String consequent) {

		this.expr = "(~ID | ID)";

//		this.expr = String.format("(~%s | %s)", antecedent, consequent);

		orderedIdentifiers = new ArrayList<String>();

		orderedIdentifiers.add(antecedent);

		orderedIdentifiers.add(consequent);

	}

	public List<String> getIdentifiers() {

		return new ArrayList<String>(orderedIdentifiers);

	}

	@Override
	public String toString() {

		String result = expr;

		for (String id : orderedIdentifiers)
			result = result.replaceFirst("ID", id);

		return expr;

	}

	public String toString(HashMap<String, String> mapID) {

		// same as toString but maps identifier names according
		// to mapID

		String result = expr;

		for (String id : orderedIdentifiers)
			result = result.replaceFirst("ID", mapID.get(id));

		return result;

	}

}
