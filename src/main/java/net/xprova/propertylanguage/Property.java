package net.xprova.propertylanguage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Property {

	private final String expr;

	private final String printExpr;

	private final List<String> orderedIDs;

	private final String codeID;

	public Property(String expr, String printExpr, List<String> orderedIDs, String codeID) {

		this.expr = expr;
		this.printExpr = printExpr;
		this.orderedIDs = orderedIDs;
		this.codeID = codeID;

	}

	public List<String> getIdentifiers() {

		return new ArrayList<String>(orderedIDs);

	}

	@Override
	public String toString() {

		return printExpr;

	}

	public String toString(HashMap<String, String> mapID) {

		String result = expr;

		for (String id : orderedIDs)
			result = result.replaceFirst(codeID, mapID.get(id));

		return result;

	}

}
