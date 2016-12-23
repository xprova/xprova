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
		AND=7, OR=8, XOR=9, NOT=10, EQ=11, NEQ=12, LT=13, GT=14, LE=15, GE=16, 
		IMPLY=17, IMPLY_NEXT=18, LPAREN=19, RPAREN=20, HASH=21, DOUBLE_HASH=22, 
		AT=23, ROSE=24, FELL=25, STABLE=26, CHANGED=27, ALWAYS=28, NEVER=29, ONCE=30, 
		UNTIL=31, ANY=32, ALL=33, EVENTUALLY=34, NUM=35, COMMA=36, WS=37;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "LT", "GT", "LE", "GE", "IMPLY", 
		"IMPLY_NEXT", "LPAREN", "RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", 
		"FELL", "STABLE", "CHANGED", "ALWAYS", "NEVER", "ONCE", "UNTIL", "ANY", 
		"ALL", "EVENTUALLY", "NUM", "COMMA", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'1'", "'0'", null, null, null, null, "'&'", "'|'", "'^'", "'~'", 
		"'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'|->'", "'|=>'", "'('", 
		"')'", "'#'", "'##'", "'@'", "'$rose'", "'$fell'", "'$stable'", "'$changed'", 
		"'$always'", "'$never'", "'$once'", "'$until'", "'$any'", "'$all'", "'$eventually'", 
		null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "LT", "GT", "LE", "GE", "IMPLY", 
		"IMPLY_NEXT", "LPAREN", "RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", 
		"FELL", "STABLE", "CHANGED", "ALWAYS", "NEVER", "ONCE", "UNTIL", "ANY", 
		"ALL", "EVENTUALLY", "NUM", "COMMA", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\'\u0102\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\3\3\3\3\4\3\4\3\4\5\4U\n"+
		"\4\3\5\3\5\7\5Y\n\5\f\5\16\5\\\13\5\3\6\3\6\7\6`\n\6\f\6\16\6c\13\6\3"+
		"\6\3\6\3\6\7\6h\n\6\f\6\16\6k\13\6\3\6\3\6\3\7\3\7\6\7q\n\7\r\7\16\7r"+
		"\3\7\7\7v\n\7\f\7\16\7y\13\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3$\6$\u00f6\n$\r$\16$\u00f7\3%\3%\3&\6&\u00fd\n&\r&\16&"+
		"\u00fe\3&\3&\2\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'\3\2\6\5\2C\\aac|\7\2&&\62;C\\a"+
		"ac|\3\2\62;\5\2\13\f\17\17\"\"\u010a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3M\3\2\2\2"+
		"\5O\3\2\2\2\7T\3\2\2\2\tV\3\2\2\2\13]\3\2\2\2\rn\3\2\2\2\17z\3\2\2\2\21"+
		"|\3\2\2\2\23~\3\2\2\2\25\u0080\3\2\2\2\27\u0082\3\2\2\2\31\u0085\3\2\2"+
		"\2\33\u0088\3\2\2\2\35\u008a\3\2\2\2\37\u008c\3\2\2\2!\u008f\3\2\2\2#"+
		"\u0092\3\2\2\2%\u0096\3\2\2\2\'\u009a\3\2\2\2)\u009c\3\2\2\2+\u009e\3"+
		"\2\2\2-\u00a0\3\2\2\2/\u00a3\3\2\2\2\61\u00a5\3\2\2\2\63\u00ab\3\2\2\2"+
		"\65\u00b1\3\2\2\2\67\u00b9\3\2\2\29\u00c2\3\2\2\2;\u00ca\3\2\2\2=\u00d1"+
		"\3\2\2\2?\u00d7\3\2\2\2A\u00de\3\2\2\2C\u00e3\3\2\2\2E\u00e8\3\2\2\2G"+
		"\u00f5\3\2\2\2I\u00f9\3\2\2\2K\u00fc\3\2\2\2MN\7\63\2\2N\4\3\2\2\2OP\7"+
		"\62\2\2P\6\3\2\2\2QU\5\t\5\2RU\5\13\6\2SU\5\r\7\2TQ\3\2\2\2TR\3\2\2\2"+
		"TS\3\2\2\2U\b\3\2\2\2VZ\t\2\2\2WY\t\3\2\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2"+
		"\2Z[\3\2\2\2[\n\3\2\2\2\\Z\3\2\2\2]a\t\2\2\2^`\t\3\2\2_^\3\2\2\2`c\3\2"+
		"\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7]\2\2ei\t\4\2\2fh\t\4"+
		"\2\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7_"+
		"\2\2m\f\3\2\2\2np\7^\2\2oq\4#\u0080\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs"+
		"\3\2\2\2sw\3\2\2\2tv\n\5\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x"+
		"\16\3\2\2\2yw\3\2\2\2z{\7(\2\2{\20\3\2\2\2|}\7~\2\2}\22\3\2\2\2~\177\7"+
		"`\2\2\177\24\3\2\2\2\u0080\u0081\7\u0080\2\2\u0081\26\3\2\2\2\u0082\u0083"+
		"\7?\2\2\u0083\u0084\7?\2\2\u0084\30\3\2\2\2\u0085\u0086\7#\2\2\u0086\u0087"+
		"\7?\2\2\u0087\32\3\2\2\2\u0088\u0089\7>\2\2\u0089\34\3\2\2\2\u008a\u008b"+
		"\7@\2\2\u008b\36\3\2\2\2\u008c\u008d\7>\2\2\u008d\u008e\7?\2\2\u008e "+
		"\3\2\2\2\u008f\u0090\7@\2\2\u0090\u0091\7?\2\2\u0091\"\3\2\2\2\u0092\u0093"+
		"\7~\2\2\u0093\u0094\7/\2\2\u0094\u0095\7@\2\2\u0095$\3\2\2\2\u0096\u0097"+
		"\7~\2\2\u0097\u0098\7?\2\2\u0098\u0099\7@\2\2\u0099&\3\2\2\2\u009a\u009b"+
		"\7*\2\2\u009b(\3\2\2\2\u009c\u009d\7+\2\2\u009d*\3\2\2\2\u009e\u009f\7"+
		"%\2\2\u009f,\3\2\2\2\u00a0\u00a1\7%\2\2\u00a1\u00a2\7%\2\2\u00a2.\3\2"+
		"\2\2\u00a3\u00a4\7B\2\2\u00a4\60\3\2\2\2\u00a5\u00a6\7&\2\2\u00a6\u00a7"+
		"\7t\2\2\u00a7\u00a8\7q\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7g\2\2\u00aa"+
		"\62\3\2\2\2\u00ab\u00ac\7&\2\2\u00ac\u00ad\7h\2\2\u00ad\u00ae\7g\2\2\u00ae"+
		"\u00af\7n\2\2\u00af\u00b0\7n\2\2\u00b0\64\3\2\2\2\u00b1\u00b2\7&\2\2\u00b2"+
		"\u00b3\7u\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7d\2\2"+
		"\u00b6\u00b7\7n\2\2\u00b7\u00b8\7g\2\2\u00b8\66\3\2\2\2\u00b9\u00ba\7"+
		"&\2\2\u00ba\u00bb\7e\2\2\u00bb\u00bc\7j\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be"+
		"\7p\2\2\u00be\u00bf\7i\2\2\u00bf\u00c0\7g\2\2\u00c0\u00c1\7f\2\2\u00c1"+
		"8\3\2\2\2\u00c2\u00c3\7&\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5\7n\2\2\u00c5"+
		"\u00c6\7y\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8\7{\2\2\u00c8\u00c9\7u\2\2"+
		"\u00c9:\3\2\2\2\u00ca\u00cb\7&\2\2\u00cb\u00cc\7p\2\2\u00cc\u00cd\7g\2"+
		"\2\u00cd\u00ce\7x\2\2\u00ce\u00cf\7g\2\2\u00cf\u00d0\7t\2\2\u00d0<\3\2"+
		"\2\2\u00d1\u00d2\7&\2\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7p\2\2\u00d4\u00d5"+
		"\7e\2\2\u00d5\u00d6\7g\2\2\u00d6>\3\2\2\2\u00d7\u00d8\7&\2\2\u00d8\u00d9"+
		"\7w\2\2\u00d9\u00da\7p\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7k\2\2\u00dc"+
		"\u00dd\7n\2\2\u00dd@\3\2\2\2\u00de\u00df\7&\2\2\u00df\u00e0\7c\2\2\u00e0"+
		"\u00e1\7p\2\2\u00e1\u00e2\7{\2\2\u00e2B\3\2\2\2\u00e3\u00e4\7&\2\2\u00e4"+
		"\u00e5\7c\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7n\2\2\u00e7D\3\2\2\2\u00e8"+
		"\u00e9\7&\2\2\u00e9\u00ea\7g\2\2\u00ea\u00eb\7x\2\2\u00eb\u00ec\7g\2\2"+
		"\u00ec\u00ed\7p\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7w\2\2\u00ef\u00f0"+
		"\7c\2\2\u00f0\u00f1\7n\2\2\u00f1\u00f2\7n\2\2\u00f2\u00f3\7{\2\2\u00f3"+
		"F\3\2\2\2\u00f4\u00f6\t\4\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2"+
		"\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8H\3\2\2\2\u00f9\u00fa\7"+
		".\2\2\u00faJ\3\2\2\2\u00fb\u00fd\t\5\2\2\u00fc\u00fb\3\2\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100"+
		"\u0101\b&\2\2\u0101L\3\2\2\2\13\2TZairw\u00f7\u00fe\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}