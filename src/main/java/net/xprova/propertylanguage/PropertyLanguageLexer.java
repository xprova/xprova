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
		High=1, Low=2, ID=3, Simple_identifier=4, Bit_identifier=5, Escaped_identifier=6, 
		AND=7, OR=8, XOR=9, NOT=10, EQ=11, NEQ=12, IMPLY=13, IMPLY_NEXT=14, LPAREN=15, 
		RPAREN=16, HASH=17, DOUBLE_HASH=18, AT=19, ROSE=20, FELL=21, STABLE=22, 
		CHANGED=23, ALWAYS=24, NEVER=25, ONCE=26, EVENTUALLY=27, NUM=28, COMMA=29, 
		WS=30;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"ALWAYS", "NEVER", "ONCE", "EVENTUALLY", "NUM", "COMMA", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'1'", "'0'", null, null, null, null, "'&'", "'|'", "'^'", "'~'", 
		"'=='", "'!='", "'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", "'@'", 
		"'$rose'", "'$fell'", "'$stable'", "'$changed'", "'$always'", "'$never'", 
		"'$once'", "'$eventually'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"ALWAYS", "NEVER", "ONCE", "EVENTUALLY", "NUM", "COMMA", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2 \u00d9\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\4\5\4G\n\4\3\5\3\5\7\5K\n\5\f\5\16\5N\13\5\3\6\3\6\7"+
		"\6R\n\6\f\6\16\6U\13\6\3\6\3\6\3\6\7\6Z\n\6\f\6\16\6]\13\6\3\6\3\6\3\7"+
		"\3\7\6\7c\n\7\r\7\16\7d\3\7\7\7h\n\7\f\7\16\7k\13\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\6\35\u00cd\n\35\r\35\16\35\u00ce\3\36\3"+
		"\36\3\37\6\37\u00d4\n\37\r\37\16\37\u00d5\3\37\3\37\2\2 \3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= \3\2\6\5\2"+
		"C\\aac|\7\2&&\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\u00e1\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\3?\3\2\2\2\5A\3\2\2\2\7F\3\2\2\2\tH\3\2\2\2\13O\3\2\2\2\r`\3\2\2"+
		"\2\17l\3\2\2\2\21n\3\2\2\2\23p\3\2\2\2\25r\3\2\2\2\27t\3\2\2\2\31w\3\2"+
		"\2\2\33z\3\2\2\2\35~\3\2\2\2\37\u0082\3\2\2\2!\u0084\3\2\2\2#\u0086\3"+
		"\2\2\2%\u0088\3\2\2\2\'\u008b\3\2\2\2)\u008d\3\2\2\2+\u0093\3\2\2\2-\u0099"+
		"\3\2\2\2/\u00a1\3\2\2\2\61\u00aa\3\2\2\2\63\u00b2\3\2\2\2\65\u00b9\3\2"+
		"\2\2\67\u00bf\3\2\2\29\u00cc\3\2\2\2;\u00d0\3\2\2\2=\u00d3\3\2\2\2?@\7"+
		"\63\2\2@\4\3\2\2\2AB\7\62\2\2B\6\3\2\2\2CG\5\t\5\2DG\5\13\6\2EG\5\r\7"+
		"\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\b\3\2\2\2HL\t\2\2\2IK\t\3\2\2JI\3\2"+
		"\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\n\3\2\2\2NL\3\2\2\2OS\t\2\2\2PR\t"+
		"\3\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\7"+
		"]\2\2W[\t\4\2\2XZ\t\4\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^"+
		"\3\2\2\2][\3\2\2\2^_\7_\2\2_\f\3\2\2\2`b\7^\2\2ac\4#\u0080\2ba\3\2\2\2"+
		"cd\3\2\2\2db\3\2\2\2de\3\2\2\2ei\3\2\2\2fh\n\5\2\2gf\3\2\2\2hk\3\2\2\2"+
		"ig\3\2\2\2ij\3\2\2\2j\16\3\2\2\2ki\3\2\2\2lm\7(\2\2m\20\3\2\2\2no\7~\2"+
		"\2o\22\3\2\2\2pq\7`\2\2q\24\3\2\2\2rs\7\u0080\2\2s\26\3\2\2\2tu\7?\2\2"+
		"uv\7?\2\2v\30\3\2\2\2wx\7#\2\2xy\7?\2\2y\32\3\2\2\2z{\7~\2\2{|\7/\2\2"+
		"|}\7@\2\2}\34\3\2\2\2~\177\7~\2\2\177\u0080\7?\2\2\u0080\u0081\7@\2\2"+
		"\u0081\36\3\2\2\2\u0082\u0083\7*\2\2\u0083 \3\2\2\2\u0084\u0085\7+\2\2"+
		"\u0085\"\3\2\2\2\u0086\u0087\7%\2\2\u0087$\3\2\2\2\u0088\u0089\7%\2\2"+
		"\u0089\u008a\7%\2\2\u008a&\3\2\2\2\u008b\u008c\7B\2\2\u008c(\3\2\2\2\u008d"+
		"\u008e\7&\2\2\u008e\u008f\7t\2\2\u008f\u0090\7q\2\2\u0090\u0091\7u\2\2"+
		"\u0091\u0092\7g\2\2\u0092*\3\2\2\2\u0093\u0094\7&\2\2\u0094\u0095\7h\2"+
		"\2\u0095\u0096\7g\2\2\u0096\u0097\7n\2\2\u0097\u0098\7n\2\2\u0098,\3\2"+
		"\2\2\u0099\u009a\7&\2\2\u009a\u009b\7u\2\2\u009b\u009c\7v\2\2\u009c\u009d"+
		"\7c\2\2\u009d\u009e\7d\2\2\u009e\u009f\7n\2\2\u009f\u00a0\7g\2\2\u00a0"+
		".\3\2\2\2\u00a1\u00a2\7&\2\2\u00a2\u00a3\7e\2\2\u00a3\u00a4\7j\2\2\u00a4"+
		"\u00a5\7c\2\2\u00a5\u00a6\7p\2\2\u00a6\u00a7\7i\2\2\u00a7\u00a8\7g\2\2"+
		"\u00a8\u00a9\7f\2\2\u00a9\60\3\2\2\2\u00aa\u00ab\7&\2\2\u00ab\u00ac\7"+
		"c\2\2\u00ac\u00ad\7n\2\2\u00ad\u00ae\7y\2\2\u00ae\u00af\7c\2\2\u00af\u00b0"+
		"\7{\2\2\u00b0\u00b1\7u\2\2\u00b1\62\3\2\2\2\u00b2\u00b3\7&\2\2\u00b3\u00b4"+
		"\7p\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7x\2\2\u00b6\u00b7\7g\2\2\u00b7"+
		"\u00b8\7t\2\2\u00b8\64\3\2\2\2\u00b9\u00ba\7&\2\2\u00ba\u00bb\7q\2\2\u00bb"+
		"\u00bc\7p\2\2\u00bc\u00bd\7e\2\2\u00bd\u00be\7g\2\2\u00be\66\3\2\2\2\u00bf"+
		"\u00c0\7&\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7x\2\2\u00c2\u00c3\7g\2\2"+
		"\u00c3\u00c4\7p\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7w\2\2\u00c6\u00c7"+
		"\7c\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9\7n\2\2\u00c9\u00ca\7{\2\2\u00ca"+
		"8\3\2\2\2\u00cb\u00cd\t\4\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2"+
		"\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf:\3\2\2\2\u00d0\u00d1\7"+
		".\2\2\u00d1<\3\2\2\2\u00d2\u00d4\t\5\2\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5"+
		"\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d8\b\37\2\2\u00d8>\3\2\2\2\13\2FLS[di\u00ce\u00d5\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}