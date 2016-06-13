// Generated from PropertyLanguage.g4 by ANTLR 4.5.3
package net.xprova.propertylanguage;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PropertyLanguageParser}.
 */
public interface PropertyLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(PropertyLanguageParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(PropertyLanguageParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#assertion}.
	 * @param ctx the parse tree
	 */
	void enterAssertion(PropertyLanguageParser.AssertionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#assertion}.
	 * @param ctx the parse tree
	 */
	void exitAssertion(PropertyLanguageParser.AssertionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PropertyLanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PropertyLanguageParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(PropertyLanguageParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(PropertyLanguageParser.OperatorContext ctx);
}