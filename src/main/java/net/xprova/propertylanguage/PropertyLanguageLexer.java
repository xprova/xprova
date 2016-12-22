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
		CHANGED=23, ALWAYS=24, NEVER=25, ONCE=26, UNTIL=27, ANY=28, ALL=29, EVENTUALLY=30, 
		NUM=31, COMMA=32, WS=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"ALWAYS", "NEVER", "ONCE", "UNTIL", "ANY", "ALL", "EVENTUALLY", "NUM", 
		"COMMA", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'1'", "'0'", null, null, null, null, "'&'", "'|'", "'^'", "'~'", 
		"'=='", "'!='", "'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", "'@'", 
		"'$rose'", "'$fell'", "'$stable'", "'$changed'", "'$always'", "'$never'", 
		"'$once'", "'$until'", "'$any'", "'$all'", "'$eventually'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"ALWAYS", "NEVER", "ONCE", "UNTIL", "ANY", "ALL", "EVENTUALLY", "NUM", 
		"COMMA", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u00f0\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\5\4M\n\4\3\5\3\5\7\5Q\n\5\f\5"+
		"\16\5T\13\5\3\6\3\6\7\6X\n\6\f\6\16\6[\13\6\3\6\3\6\3\6\7\6`\n\6\f\6\16"+
		"\6c\13\6\3\6\3\6\3\7\3\7\6\7i\n\7\r\7\16\7j\3\7\7\7n\n\7\f\7\16\7q\13"+
		"\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \6 \u00e4"+
		"\n \r \16 \u00e5\3!\3!\3\"\6\"\u00eb\n\"\r\"\16\"\u00ec\3\"\3\"\2\2#\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#\3\2\6\5\2C\\aac|\7\2&&\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\""+
		"\u00f8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E\3\2\2\2\5"+
		"G\3\2\2\2\7L\3\2\2\2\tN\3\2\2\2\13U\3\2\2\2\rf\3\2\2\2\17r\3\2\2\2\21"+
		"t\3\2\2\2\23v\3\2\2\2\25x\3\2\2\2\27z\3\2\2\2\31}\3\2\2\2\33\u0080\3\2"+
		"\2\2\35\u0084\3\2\2\2\37\u0088\3\2\2\2!\u008a\3\2\2\2#\u008c\3\2\2\2%"+
		"\u008e\3\2\2\2\'\u0091\3\2\2\2)\u0093\3\2\2\2+\u0099\3\2\2\2-\u009f\3"+
		"\2\2\2/\u00a7\3\2\2\2\61\u00b0\3\2\2\2\63\u00b8\3\2\2\2\65\u00bf\3\2\2"+
		"\2\67\u00c5\3\2\2\29\u00cc\3\2\2\2;\u00d1\3\2\2\2=\u00d6\3\2\2\2?\u00e3"+
		"\3\2\2\2A\u00e7\3\2\2\2C\u00ea\3\2\2\2EF\7\63\2\2F\4\3\2\2\2GH\7\62\2"+
		"\2H\6\3\2\2\2IM\5\t\5\2JM\5\13\6\2KM\5\r\7\2LI\3\2\2\2LJ\3\2\2\2LK\3\2"+
		"\2\2M\b\3\2\2\2NR\t\2\2\2OQ\t\3\2\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3"+
		"\2\2\2S\n\3\2\2\2TR\3\2\2\2UY\t\2\2\2VX\t\3\2\2WV\3\2\2\2X[\3\2\2\2YW"+
		"\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\]\7]\2\2]a\t\4\2\2^`\t\4\2\2"+
		"_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7_\2\2"+
		"e\f\3\2\2\2fh\7^\2\2gi\4#\u0080\2hg\3\2\2\2ij\3\2\2\2jh\3\2\2\2jk\3\2"+
		"\2\2ko\3\2\2\2ln\n\5\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2p\16\3"+
		"\2\2\2qo\3\2\2\2rs\7(\2\2s\20\3\2\2\2tu\7~\2\2u\22\3\2\2\2vw\7`\2\2w\24"+
		"\3\2\2\2xy\7\u0080\2\2y\26\3\2\2\2z{\7?\2\2{|\7?\2\2|\30\3\2\2\2}~\7#"+
		"\2\2~\177\7?\2\2\177\32\3\2\2\2\u0080\u0081\7~\2\2\u0081\u0082\7/\2\2"+
		"\u0082\u0083\7@\2\2\u0083\34\3\2\2\2\u0084\u0085\7~\2\2\u0085\u0086\7"+
		"?\2\2\u0086\u0087\7@\2\2\u0087\36\3\2\2\2\u0088\u0089\7*\2\2\u0089 \3"+
		"\2\2\2\u008a\u008b\7+\2\2\u008b\"\3\2\2\2\u008c\u008d\7%\2\2\u008d$\3"+
		"\2\2\2\u008e\u008f\7%\2\2\u008f\u0090\7%\2\2\u0090&\3\2\2\2\u0091\u0092"+
		"\7B\2\2\u0092(\3\2\2\2\u0093\u0094\7&\2\2\u0094\u0095\7t\2\2\u0095\u0096"+
		"\7q\2\2\u0096\u0097\7u\2\2\u0097\u0098\7g\2\2\u0098*\3\2\2\2\u0099\u009a"+
		"\7&\2\2\u009a\u009b\7h\2\2\u009b\u009c\7g\2\2\u009c\u009d\7n\2\2\u009d"+
		"\u009e\7n\2\2\u009e,\3\2\2\2\u009f\u00a0\7&\2\2\u00a0\u00a1\7u\2\2\u00a1"+
		"\u00a2\7v\2\2\u00a2\u00a3\7c\2\2\u00a3\u00a4\7d\2\2\u00a4\u00a5\7n\2\2"+
		"\u00a5\u00a6\7g\2\2\u00a6.\3\2\2\2\u00a7\u00a8\7&\2\2\u00a8\u00a9\7e\2"+
		"\2\u00a9\u00aa\7j\2\2\u00aa\u00ab\7c\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad"+
		"\7i\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7f\2\2\u00af\60\3\2\2\2\u00b0\u00b1"+
		"\7&\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7y\2\2\u00b4"+
		"\u00b5\7c\2\2\u00b5\u00b6\7{\2\2\u00b6\u00b7\7u\2\2\u00b7\62\3\2\2\2\u00b8"+
		"\u00b9\7&\2\2\u00b9\u00ba\7p\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7x\2\2"+
		"\u00bc\u00bd\7g\2\2\u00bd\u00be\7t\2\2\u00be\64\3\2\2\2\u00bf\u00c0\7"+
		"&\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c2\7p\2\2\u00c2\u00c3\7e\2\2\u00c3\u00c4"+
		"\7g\2\2\u00c4\66\3\2\2\2\u00c5\u00c6\7&\2\2\u00c6\u00c7\7w\2\2\u00c7\u00c8"+
		"\7p\2\2\u00c8\u00c9\7v\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7n\2\2\u00cb"+
		"8\3\2\2\2\u00cc\u00cd\7&\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7p\2\2\u00cf"+
		"\u00d0\7{\2\2\u00d0:\3\2\2\2\u00d1\u00d2\7&\2\2\u00d2\u00d3\7c\2\2\u00d3"+
		"\u00d4\7n\2\2\u00d4\u00d5\7n\2\2\u00d5<\3\2\2\2\u00d6\u00d7\7&\2\2\u00d7"+
		"\u00d8\7g\2\2\u00d8\u00d9\7x\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7p\2\2"+
		"\u00db\u00dc\7v\2\2\u00dc\u00dd\7w\2\2\u00dd\u00de\7c\2\2\u00de\u00df"+
		"\7n\2\2\u00df\u00e0\7n\2\2\u00e0\u00e1\7{\2\2\u00e1>\3\2\2\2\u00e2\u00e4"+
		"\t\4\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6@\3\2\2\2\u00e7\u00e8\7.\2\2\u00e8B\3\2\2\2\u00e9"+
		"\u00eb\t\5\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ea\3\2"+
		"\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\b\"\2\2\u00ef"+
		"D\3\2\2\2\13\2LRYajo\u00e5\u00ec\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}