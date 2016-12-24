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
		OR=6, XOR=7, NOT=8, EQ=9, NEQ=10, LT=11, GT=12, LE=13, GE=14, IMPLY=15, 
		IMPLY_NEXT=16, LPAREN=17, RPAREN=18, HASH=19, DOUBLE_HASH=20, AT=21, ROSE=22, 
		FELL=23, STABLE=24, CHANGED=25, ALWAYS=26, NEVER=27, ONCE=28, UNTIL=29, 
		WHEN=30, ANY=31, ALL=32, EVENTUALLY=33, INTEGER=34, COMMA=35, WS=36;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "LT", "GT", "LE", "GE", "IMPLY", "IMPLY_NEXT", 
		"LPAREN", "RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", 
		"CHANGED", "ALWAYS", "NEVER", "ONCE", "UNTIL", "WHEN", "ANY", "ALL", "EVENTUALLY", 
		"INTEGER", "COMMA", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'<'", "'>'", "'<='", "'>='", "'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", 
		"'@'", "'$rose'", "'$fell'", "'$stable'", "'$changed'", "'$always'", "'$never'", 
		"'$once'", "'$until'", "'$when'", "'$any'", "'$all'", "'$eventually'", 
		null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "LT", "GT", "LE", "GE", "IMPLY", 
		"IMPLY_NEXT", "LPAREN", "RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", 
		"FELL", "STABLE", "CHANGED", "ALWAYS", "NEVER", "ONCE", "UNTIL", "WHEN", 
		"ANY", "ALL", "EVENTUALLY", "INTEGER", "COMMA", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2&\u0102\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\5\2O\n\2\3\3\3\3\7\3S\n\3\f"+
		"\3\16\3V\13\3\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\4\3\4\3\4\7\4b\n\4\f\4"+
		"\16\4e\13\4\3\4\3\4\3\5\3\5\6\5k\n\5\r\5\16\5l\3\5\7\5p\n\5\f\5\16\5s"+
		"\13\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\6#\u00f6\n#\r#\16#\u00f7\3$"+
		"\3$\3%\6%\u00fd\n%\r%\16%\u00fe\3%\3%\2\2&\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&\3\2\6\5\2C"+
		"\\aac|\7\2&&\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\u010a\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\3"+
		"N\3\2\2\2\5P\3\2\2\2\7W\3\2\2\2\th\3\2\2\2\13t\3\2\2\2\rv\3\2\2\2\17x"+
		"\3\2\2\2\21z\3\2\2\2\23|\3\2\2\2\25\177\3\2\2\2\27\u0082\3\2\2\2\31\u0084"+
		"\3\2\2\2\33\u0086\3\2\2\2\35\u0089\3\2\2\2\37\u008c\3\2\2\2!\u0090\3\2"+
		"\2\2#\u0094\3\2\2\2%\u0096\3\2\2\2\'\u0098\3\2\2\2)\u009a\3\2\2\2+\u009d"+
		"\3\2\2\2-\u009f\3\2\2\2/\u00a5\3\2\2\2\61\u00ab\3\2\2\2\63\u00b3\3\2\2"+
		"\2\65\u00bc\3\2\2\2\67\u00c4\3\2\2\29\u00cb\3\2\2\2;\u00d1\3\2\2\2=\u00d8"+
		"\3\2\2\2?\u00de\3\2\2\2A\u00e3\3\2\2\2C\u00e8\3\2\2\2E\u00f5\3\2\2\2G"+
		"\u00f9\3\2\2\2I\u00fc\3\2\2\2KO\5\5\3\2LO\5\7\4\2MO\5\t\5\2NK\3\2\2\2"+
		"NL\3\2\2\2NM\3\2\2\2O\4\3\2\2\2PT\t\2\2\2QS\t\3\2\2RQ\3\2\2\2SV\3\2\2"+
		"\2TR\3\2\2\2TU\3\2\2\2U\6\3\2\2\2VT\3\2\2\2W[\t\2\2\2XZ\t\3\2\2YX\3\2"+
		"\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^_\7]\2\2_c\t"+
		"\4\2\2`b\t\4\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3"+
		"\2\2\2fg\7_\2\2g\b\3\2\2\2hj\7^\2\2ik\4#\u0080\2ji\3\2\2\2kl\3\2\2\2l"+
		"j\3\2\2\2lm\3\2\2\2mq\3\2\2\2np\n\5\2\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2"+
		"qr\3\2\2\2r\n\3\2\2\2sq\3\2\2\2tu\7(\2\2u\f\3\2\2\2vw\7~\2\2w\16\3\2\2"+
		"\2xy\7`\2\2y\20\3\2\2\2z{\7\u0080\2\2{\22\3\2\2\2|}\7?\2\2}~\7?\2\2~\24"+
		"\3\2\2\2\177\u0080\7#\2\2\u0080\u0081\7?\2\2\u0081\26\3\2\2\2\u0082\u0083"+
		"\7>\2\2\u0083\30\3\2\2\2\u0084\u0085\7@\2\2\u0085\32\3\2\2\2\u0086\u0087"+
		"\7>\2\2\u0087\u0088\7?\2\2\u0088\34\3\2\2\2\u0089\u008a\7@\2\2\u008a\u008b"+
		"\7?\2\2\u008b\36\3\2\2\2\u008c\u008d\7~\2\2\u008d\u008e\7/\2\2\u008e\u008f"+
		"\7@\2\2\u008f \3\2\2\2\u0090\u0091\7~\2\2\u0091\u0092\7?\2\2\u0092\u0093"+
		"\7@\2\2\u0093\"\3\2\2\2\u0094\u0095\7*\2\2\u0095$\3\2\2\2\u0096\u0097"+
		"\7+\2\2\u0097&\3\2\2\2\u0098\u0099\7%\2\2\u0099(\3\2\2\2\u009a\u009b\7"+
		"%\2\2\u009b\u009c\7%\2\2\u009c*\3\2\2\2\u009d\u009e\7B\2\2\u009e,\3\2"+
		"\2\2\u009f\u00a0\7&\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7q\2\2\u00a2\u00a3"+
		"\7u\2\2\u00a3\u00a4\7g\2\2\u00a4.\3\2\2\2\u00a5\u00a6\7&\2\2\u00a6\u00a7"+
		"\7h\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7n\2\2\u00a9\u00aa\7n\2\2\u00aa"+
		"\60\3\2\2\2\u00ab\u00ac\7&\2\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7v\2\2\u00ae"+
		"\u00af\7c\2\2\u00af\u00b0\7d\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2\7g\2\2"+
		"\u00b2\62\3\2\2\2\u00b3\u00b4\7&\2\2\u00b4\u00b5\7e\2\2\u00b5\u00b6\7"+
		"j\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9\7i\2\2\u00b9\u00ba"+
		"\7g\2\2\u00ba\u00bb\7f\2\2\u00bb\64\3\2\2\2\u00bc\u00bd\7&\2\2\u00bd\u00be"+
		"\7c\2\2\u00be\u00bf\7n\2\2\u00bf\u00c0\7y\2\2\u00c0\u00c1\7c\2\2\u00c1"+
		"\u00c2\7{\2\2\u00c2\u00c3\7u\2\2\u00c3\66\3\2\2\2\u00c4\u00c5\7&\2\2\u00c5"+
		"\u00c6\7p\2\2\u00c6\u00c7\7g\2\2\u00c7\u00c8\7x\2\2\u00c8\u00c9\7g\2\2"+
		"\u00c9\u00ca\7t\2\2\u00ca8\3\2\2\2\u00cb\u00cc\7&\2\2\u00cc\u00cd\7q\2"+
		"\2\u00cd\u00ce\7p\2\2\u00ce\u00cf\7e\2\2\u00cf\u00d0\7g\2\2\u00d0:\3\2"+
		"\2\2\u00d1\u00d2\7&\2\2\u00d2\u00d3\7w\2\2\u00d3\u00d4\7p\2\2\u00d4\u00d5"+
		"\7v\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7\7n\2\2\u00d7<\3\2\2\2\u00d8\u00d9"+
		"\7&\2\2\u00d9\u00da\7y\2\2\u00da\u00db\7j\2\2\u00db\u00dc\7g\2\2\u00dc"+
		"\u00dd\7p\2\2\u00dd>\3\2\2\2\u00de\u00df\7&\2\2\u00df\u00e0\7c\2\2\u00e0"+
		"\u00e1\7p\2\2\u00e1\u00e2\7{\2\2\u00e2@\3\2\2\2\u00e3\u00e4\7&\2\2\u00e4"+
		"\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7n\2\2\u00e7B\3\2\2\2\u00e8"+
		"\u00e9\7&\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7x\2\2\u00eb\u00ec\7g\2\2"+
		"\u00ec\u00ed\7p\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7w\2\2\u00ef\u00f0"+
		"\7c\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7{\2\2\u00f3"+
		"D\3\2\2\2\u00f4\u00f6\t\4\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2"+
		"\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8F\3\2\2\2\u00f9\u00fa\7"+
		".\2\2\u00faH\3\2\2\2\u00fb\u00fd\t\5\2\2\u00fc\u00fb\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100"+
		"\u0101\b%\2\2\u0101J\3\2\2\2\13\2NT[clq\u00f7\u00fe\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}