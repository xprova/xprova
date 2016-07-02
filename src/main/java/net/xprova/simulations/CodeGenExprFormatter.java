package net.xprova.simulations;

import net.xprova.propertylanguage.ExpressionFormatter;

public class CodeGenExprFormatter extends ExpressionFormatter {

	@Override
	public String getNOT(String operand) {

		return String.format("~%s", operand);

	}

	@Override
	public String getEQ(String op1, String op2) {

		// can't use == here because the expression:
		// (0xffffffff = 0xffffffff) will return true
		// instead of 0xffffffff (logic high)

		return String.format("~(%s ^ %s)", op1, op2);

	}

	@Override
	public String getNEQ(String op1, String op2) {

		// see note in getEQ

		return String.format("%s ^ %s", op1, op2);

	}

	@Override
	public String getIMPLY(String antec, String conseq) {

		// (antec |-> conseq) == (!antec | conseq)

		return String.format("~%s | %s", antec, conseq);

	}

}
