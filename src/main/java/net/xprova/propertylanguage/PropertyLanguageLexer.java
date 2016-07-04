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
		OR=6, XOR=7, NOT=8, EQ=9, NEQ=10, IMPLY=11, LPAREN=12, RPAREN=13, HASH=14, 
		AT=15, NUM=16, WS=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "LPAREN", "RPAREN", "HASH", 
		"AT", "NUM", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'|->'", "'('", "')'", "'#'", "'@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "LPAREN", "RPAREN", "HASH", 
		"AT", "NUM", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23t\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\5\2)\n\2\3\3\3\3\7\3-\n\3\f\3\16\3\60\13\3\3\4\3\4\7\4\64"+
		"\n\4\f\4\16\4\67\13\4\3\4\3\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\4\3\4\3\5"+
		"\3\5\6\5E\n\5\r\5\16\5F\3\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\21\6\21j\n\21\r\21\16\21k\3\22\6\22o\n\22"+
		"\r\22\16\22p\3\22\3\22\2\2\23\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23\3\2\6\5\2C\\aac|\7\2&&\62;"+
		"C\\aac|\3\2\62;\5\2\13\f\17\17\"\"|\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\3(\3\2\2\2\5*\3\2\2\2\7\61\3\2"+
		"\2\2\tB\3\2\2\2\13N\3\2\2\2\rP\3\2\2\2\17R\3\2\2\2\21T\3\2\2\2\23V\3\2"+
		"\2\2\25Y\3\2\2\2\27\\\3\2\2\2\31`\3\2\2\2\33b\3\2\2\2\35d\3\2\2\2\37f"+
		"\3\2\2\2!i\3\2\2\2#n\3\2\2\2%)\5\5\3\2&)\5\7\4\2\')\5\t\5\2(%\3\2\2\2"+
		"(&\3\2\2\2(\'\3\2\2\2)\4\3\2\2\2*.\t\2\2\2+-\t\3\2\2,+\3\2\2\2-\60\3\2"+
		"\2\2.,\3\2\2\2./\3\2\2\2/\6\3\2\2\2\60.\3\2\2\2\61\65\t\2\2\2\62\64\t"+
		"\3\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2"+
		"\2\2\67\65\3\2\2\289\7]\2\29=\t\4\2\2:<\t\4\2\2;:\3\2\2\2<?\3\2\2\2=;"+
		"\3\2\2\2=>\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7_\2\2A\b\3\2\2\2BD\7^\2\2CE"+
		"\4#\u0080\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2GK\3\2\2\2HJ\n\5\2"+
		"\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\n\3\2\2\2MK\3\2\2\2NO\7(\2"+
		"\2O\f\3\2\2\2PQ\7~\2\2Q\16\3\2\2\2RS\7`\2\2S\20\3\2\2\2TU\7\u0080\2\2"+
		"U\22\3\2\2\2VW\7?\2\2WX\7?\2\2X\24\3\2\2\2YZ\7#\2\2Z[\7?\2\2[\26\3\2\2"+
		"\2\\]\7~\2\2]^\7/\2\2^_\7@\2\2_\30\3\2\2\2`a\7*\2\2a\32\3\2\2\2bc\7+\2"+
		"\2c\34\3\2\2\2de\7%\2\2e\36\3\2\2\2fg\7B\2\2g \3\2\2\2hj\t\4\2\2ih\3\2"+
		"\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2l\"\3\2\2\2mo\t\5\2\2nm\3\2\2\2op\3"+
		"\2\2\2pn\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\b\22\2\2s$\3\2\2\2\13\2(.\65=F"+
		"Kkp\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}