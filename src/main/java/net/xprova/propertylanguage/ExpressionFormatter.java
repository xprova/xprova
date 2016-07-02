package net.xprova.propertylanguage;

import java.util.List;

public class ExpressionFormatter {

	// by convention the expressions returned by the methods of this class
	// need not be enclosed in parenthesis

	private String getCommon(String operator, List<String> operands) {

		String result = "";

		for (String operand : operands)
			result += result.isEmpty() ? operand : String.format(" %s %s", operator, operand);

		return result;

	}

	public String getAND(List<String> operands) {

		return getCommon("&", operands);

	}

	public String getOR(List<String> operands) {

		return getCommon("|", operands);

	}

	public String getXOR(List<String> operands) {

		return getCommon("^", operands);

	}

	public String getNOT(String operand) {

		return String.format("!%s", operand);

	}

	public String getEQ(String op1, String op2) {

		return String.format("%s == %s", op1, op2);

	}

	public String getNEQ(String op1, String op2) {

		return String.format("%s != %s", op1, op2);

	}

	public String getIMPLY(String antec, String conseq) {

		return String.format("%s |-> %s", antec, conseq);

	}

}
