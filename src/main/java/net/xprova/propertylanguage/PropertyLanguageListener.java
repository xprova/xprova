// Generated from PropertyLanguage.g4 by ANTLR 4.5.3
package net.xprova.propertylanguage;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PropertyLanguageParser}.
 */
public interface PropertyLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(PropertyLanguageParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(PropertyLanguageParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PropertyLanguageParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PropertyLanguageParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#implyExp}.
	 * @param ctx the parse tree
	 */
	void enterImplyExp(PropertyLanguageParser.ImplyExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#implyExp}.
	 * @param ctx the parse tree
	 */
	void exitImplyExp(PropertyLanguageParser.ImplyExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(PropertyLanguageParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(PropertyLanguageParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#xorExpr}.
	 * @param ctx the parse tree
	 */
	void enterXorExpr(PropertyLanguageParser.XorExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#xorExpr}.
	 * @param ctx the parse tree
	 */
	void exitXorExpr(PropertyLanguageParser.XorExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(PropertyLanguageParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(PropertyLanguageParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqExpr(PropertyLanguageParser.EqExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#eqExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqExpr(PropertyLanguageParser.EqExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#nAtom}.
	 * @param ctx the parse tree
	 */
	void enterNAtom(PropertyLanguageParser.NAtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#nAtom}.
	 * @param ctx the parse tree
	 */
	void exitNAtom(PropertyLanguageParser.NAtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PropertyLanguageParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(PropertyLanguageParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(PropertyLanguageParser.AtomContext ctx);
}