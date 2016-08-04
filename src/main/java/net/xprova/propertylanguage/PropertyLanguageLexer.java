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
		CHANGED=21, ALWAYS=22, NUM=23, WS=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", "RPAREN", 
		"HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", "ALWAYS", 
		"NUM", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", "'@'", "'$rose'", "'$fell'", 
		"'$stable'", "'$changed'", "'$always'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"ALWAYS", "NUM", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u00ae\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\5\2\67\n\2\3\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\4\3\4\7"+
		"\4B\n\4\f\4\16\4E\13\4\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\3\5"+
		"\3\5\6\5S\n\5\r\5\16\5T\3\5\7\5X\n\5\f\5\16\5[\13\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\6\30\u00a4\n\30\r\30\16\30\u00a5"+
		"\3\31\6\31\u00a9\n\31\r\31\16\31\u00aa\3\31\3\31\2\2\32\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\3\2\6\5\2C\\aac|\7\2&&\62;C\\aac|\3\2"+
		"\62;\5\2\13\f\17\17\"\"\u00b6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\66\3\2\2\2\58\3\2\2\2\7"+
		"?\3\2\2\2\tP\3\2\2\2\13\\\3\2\2\2\r^\3\2\2\2\17`\3\2\2\2\21b\3\2\2\2\23"+
		"d\3\2\2\2\25g\3\2\2\2\27j\3\2\2\2\31n\3\2\2\2\33r\3\2\2\2\35t\3\2\2\2"+
		"\37v\3\2\2\2!x\3\2\2\2#{\3\2\2\2%}\3\2\2\2\'\u0083\3\2\2\2)\u0089\3\2"+
		"\2\2+\u0091\3\2\2\2-\u009a\3\2\2\2/\u00a3\3\2\2\2\61\u00a8\3\2\2\2\63"+
		"\67\5\5\3\2\64\67\5\7\4\2\65\67\5\t\5\2\66\63\3\2\2\2\66\64\3\2\2\2\66"+
		"\65\3\2\2\2\67\4\3\2\2\28<\t\2\2\29;\t\3\2\2:9\3\2\2\2;>\3\2\2\2<:\3\2"+
		"\2\2<=\3\2\2\2=\6\3\2\2\2><\3\2\2\2?C\t\2\2\2@B\t\3\2\2A@\3\2\2\2BE\3"+
		"\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7]\2\2GK\t\4\2\2HJ\t"+
		"\4\2\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7"+
		"_\2\2O\b\3\2\2\2PR\7^\2\2QS\4#\u0080\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2T"+
		"U\3\2\2\2UY\3\2\2\2VX\n\5\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2"+
		"Z\n\3\2\2\2[Y\3\2\2\2\\]\7(\2\2]\f\3\2\2\2^_\7~\2\2_\16\3\2\2\2`a\7`\2"+
		"\2a\20\3\2\2\2bc\7\u0080\2\2c\22\3\2\2\2de\7?\2\2ef\7?\2\2f\24\3\2\2\2"+
		"gh\7#\2\2hi\7?\2\2i\26\3\2\2\2jk\7~\2\2kl\7/\2\2lm\7@\2\2m\30\3\2\2\2"+
		"no\7~\2\2op\7?\2\2pq\7@\2\2q\32\3\2\2\2rs\7*\2\2s\34\3\2\2\2tu\7+\2\2"+
		"u\36\3\2\2\2vw\7%\2\2w \3\2\2\2xy\7%\2\2yz\7%\2\2z\"\3\2\2\2{|\7B\2\2"+
		"|$\3\2\2\2}~\7&\2\2~\177\7t\2\2\177\u0080\7q\2\2\u0080\u0081\7u\2\2\u0081"+
		"\u0082\7g\2\2\u0082&\3\2\2\2\u0083\u0084\7&\2\2\u0084\u0085\7h\2\2\u0085"+
		"\u0086\7g\2\2\u0086\u0087\7n\2\2\u0087\u0088\7n\2\2\u0088(\3\2\2\2\u0089"+
		"\u008a\7&\2\2\u008a\u008b\7u\2\2\u008b\u008c\7v\2\2\u008c\u008d\7c\2\2"+
		"\u008d\u008e\7d\2\2\u008e\u008f\7n\2\2\u008f\u0090\7g\2\2\u0090*\3\2\2"+
		"\2\u0091\u0092\7&\2\2\u0092\u0093\7e\2\2\u0093\u0094\7j\2\2\u0094\u0095"+
		"\7c\2\2\u0095\u0096\7p\2\2\u0096\u0097\7i\2\2\u0097\u0098\7g\2\2\u0098"+
		"\u0099\7f\2\2\u0099,\3\2\2\2\u009a\u009b\7&\2\2\u009b\u009c\7c\2\2\u009c"+
		"\u009d\7n\2\2\u009d\u009e\7y\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7{\2\2"+
		"\u00a0\u00a1\7u\2\2\u00a1.\3\2\2\2\u00a2\u00a4\t\4\2\2\u00a3\u00a2\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\60\3\2\2\2\u00a7\u00a9\t\5\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2"+
		"\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad"+
		"\b\31\2\2\u00ad\62\3\2\2\2\13\2\66<CKTY\u00a5\u00aa\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}