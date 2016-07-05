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
		RPAREN=14, HASH=15, DOUBLE_HASH=16, AT=17, NUM=18, WS=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", "RPAREN", 
		"HASH", "DOUBLE_HASH", "AT", "NUM", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", "'@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "NUM", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\2\5\2-\n\2\3\3\3\3\7\3\61\n\3\f\3\16\3"+
		"\64\13\3\3\4\3\4\7\48\n\4\f\4\16\4;\13\4\3\4\3\4\3\4\7\4@\n\4\f\4\16\4"+
		"C\13\4\3\4\3\4\3\5\3\5\6\5I\n\5\r\5\16\5J\3\5\7\5N\n\5\f\5\16\5Q\13\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3"+
		"\22\3\22\3\23\6\23u\n\23\r\23\16\23v\3\24\6\24z\n\24\r\24\16\24{\3\24"+
		"\3\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25\3\2\6\5\2C\\aac|\7\2&&\62;C\\aac|"+
		"\3\2\62;\5\2\13\f\17\17\"\"\u0087\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3,\3\2\2"+
		"\2\5.\3\2\2\2\7\65\3\2\2\2\tF\3\2\2\2\13R\3\2\2\2\rT\3\2\2\2\17V\3\2\2"+
		"\2\21X\3\2\2\2\23Z\3\2\2\2\25]\3\2\2\2\27`\3\2\2\2\31d\3\2\2\2\33h\3\2"+
		"\2\2\35j\3\2\2\2\37l\3\2\2\2!n\3\2\2\2#q\3\2\2\2%t\3\2\2\2\'y\3\2\2\2"+
		")-\5\5\3\2*-\5\7\4\2+-\5\t\5\2,)\3\2\2\2,*\3\2\2\2,+\3\2\2\2-\4\3\2\2"+
		"\2.\62\t\2\2\2/\61\t\3\2\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62"+
		"\63\3\2\2\2\63\6\3\2\2\2\64\62\3\2\2\2\659\t\2\2\2\668\t\3\2\2\67\66\3"+
		"\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7]\2\2=A"+
		"\t\4\2\2>@\t\4\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2C"+
		"A\3\2\2\2DE\7_\2\2E\b\3\2\2\2FH\7^\2\2GI\4#\u0080\2HG\3\2\2\2IJ\3\2\2"+
		"\2JH\3\2\2\2JK\3\2\2\2KO\3\2\2\2LN\n\5\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2"+
		"\2OP\3\2\2\2P\n\3\2\2\2QO\3\2\2\2RS\7(\2\2S\f\3\2\2\2TU\7~\2\2U\16\3\2"+
		"\2\2VW\7`\2\2W\20\3\2\2\2XY\7\u0080\2\2Y\22\3\2\2\2Z[\7?\2\2[\\\7?\2\2"+
		"\\\24\3\2\2\2]^\7#\2\2^_\7?\2\2_\26\3\2\2\2`a\7~\2\2ab\7/\2\2bc\7@\2\2"+
		"c\30\3\2\2\2de\7~\2\2ef\7?\2\2fg\7@\2\2g\32\3\2\2\2hi\7*\2\2i\34\3\2\2"+
		"\2jk\7+\2\2k\36\3\2\2\2lm\7%\2\2m \3\2\2\2no\7%\2\2op\7%\2\2p\"\3\2\2"+
		"\2qr\7B\2\2r$\3\2\2\2su\t\4\2\2ts\3\2\2\2uv\3\2\2\2vt\3\2\2\2vw\3\2\2"+
		"\2w&\3\2\2\2xz\t\5\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2"+
		"\2}~\b\24\2\2~(\3\2\2\2\13\2,\629AJOv{\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}