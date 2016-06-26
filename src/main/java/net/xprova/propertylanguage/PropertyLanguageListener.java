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
	 * Enter a parse tree produced by {@link PropertyLanguageParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(PropertyLanguageParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PropertyLanguageParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(PropertyLanguageParser.IdentifierContext ctx);
}