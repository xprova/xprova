// Generated from PropertyLanguage.g4 by ANTLR 4.5.3
package net.xprova.propertylanguage;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PropertyLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, Simple_identifier=2, Bit_identifier=3, Escaped_identifier=4, AND=5, 
		OR=6, XOR=7, NOT=8, EQ=9, NEQ=10, IMPLY=11, IMPLY_NEXT=12, LPAREN=13, 
		RPAREN=14, HASH=15, DOUBLE_HASH=16, AT=17, ROSE=18, FELL=19, STABLE=20, 
		CHANGED=21, NUM=22, WS=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", "RPAREN", 
		"HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", "NUM", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", "'@'", "'$rose'", "'$fell'", 
		"'$stable'", "'$changed'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"NUM", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PropertyLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PropertyLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u00a4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\5\2\65\n\2\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\4\3\4\7\4@\n\4\f"+
		"\4\16\4C\13\4\3\4\3\4\3\4\7\4H\n\4\f\4\16\4K\13\4\3\4\3\4\3\5\3\5\6\5"+
		"Q\n\5\r\5\16\5R\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\6\3\6\3\7\3\7\3\b\3\b\3"+
		"\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\6\27\u009a"+
		"\n\27\r\27\16\27\u009b\3\30\6\30\u009f\n\30\r\30\16\30\u00a0\3\30\3\30"+
		"\2\2\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\6\5\2C\\aac|\7\2&&\62"+
		";C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\u00ac\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\64\3\2\2\2\5\66\3\2\2\2"+
		"\7=\3\2\2\2\tN\3\2\2\2\13Z\3\2\2\2\r\\\3\2\2\2\17^\3\2\2\2\21`\3\2\2\2"+
		"\23b\3\2\2\2\25e\3\2\2\2\27h\3\2\2\2\31l\3\2\2\2\33p\3\2\2\2\35r\3\2\2"+
		"\2\37t\3\2\2\2!v\3\2\2\2#y\3\2\2\2%{\3\2\2\2\'\u0081\3\2\2\2)\u0087\3"+
		"\2\2\2+\u008f\3\2\2\2-\u0099\3\2\2\2/\u009e\3\2\2\2\61\65\5\5\3\2\62\65"+
		"\5\7\4\2\63\65\5\t\5\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2\2\65\4"+
		"\3\2\2\2\66:\t\2\2\2\679\t\3\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2"+
		"\2\2;\6\3\2\2\2<:\3\2\2\2=A\t\2\2\2>@\t\3\2\2?>\3\2\2\2@C\3\2\2\2A?\3"+
		"\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7]\2\2EI\t\4\2\2FH\t\4\2\2GF\3"+
		"\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7_\2\2M\b\3"+
		"\2\2\2NP\7^\2\2OQ\4#\u0080\2PO\3\2\2\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2S"+
		"W\3\2\2\2TV\n\5\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\n\3\2\2\2"+
		"YW\3\2\2\2Z[\7(\2\2[\f\3\2\2\2\\]\7~\2\2]\16\3\2\2\2^_\7`\2\2_\20\3\2"+
		"\2\2`a\7\u0080\2\2a\22\3\2\2\2bc\7?\2\2cd\7?\2\2d\24\3\2\2\2ef\7#\2\2"+
		"fg\7?\2\2g\26\3\2\2\2hi\7~\2\2ij\7/\2\2jk\7@\2\2k\30\3\2\2\2lm\7~\2\2"+
		"mn\7?\2\2no\7@\2\2o\32\3\2\2\2pq\7*\2\2q\34\3\2\2\2rs\7+\2\2s\36\3\2\2"+
		"\2tu\7%\2\2u \3\2\2\2vw\7%\2\2wx\7%\2\2x\"\3\2\2\2yz\7B\2\2z$\3\2\2\2"+
		"{|\7&\2\2|}\7t\2\2}~\7q\2\2~\177\7u\2\2\177\u0080\7g\2\2\u0080&\3\2\2"+
		"\2\u0081\u0082\7&\2\2\u0082\u0083\7h\2\2\u0083\u0084\7g\2\2\u0084\u0085"+
		"\7n\2\2\u0085\u0086\7n\2\2\u0086(\3\2\2\2\u0087\u0088\7&\2\2\u0088\u0089"+
		"\7u\2\2\u0089\u008a\7v\2\2\u008a\u008b\7c\2\2\u008b\u008c\7d\2\2\u008c"+
		"\u008d\7n\2\2\u008d\u008e\7g\2\2\u008e*\3\2\2\2\u008f\u0090\7&\2\2\u0090"+
		"\u0091\7e\2\2\u0091\u0092\7j\2\2\u0092\u0093\7c\2\2\u0093\u0094\7p\2\2"+
		"\u0094\u0095\7i\2\2\u0095\u0096\7g\2\2\u0096\u0097\7f\2\2\u0097,\3\2\2"+
		"\2\u0098\u009a\t\4\2\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099"+
		"\3\2\2\2\u009b\u009c\3\2\2\2\u009c.\3\2\2\2\u009d\u009f\t\5\2\2\u009e"+
		"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\30\2\2\u00a3\60\3\2\2\2\13\2\64"+
		":AIRW\u009b\u00a0\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}