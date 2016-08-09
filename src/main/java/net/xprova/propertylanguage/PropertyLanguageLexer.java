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
		CHANGED=21, ALWAYS=22, NEVER=23, EVENTUALLY=24, NUM=25, COMMA=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", "RPAREN", 
		"HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", "ALWAYS", 
		"NEVER", "EVENTUALLY", "NUM", "COMMA", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'|->'", "'|=>'", "'('", "')'", "'#'", "'##'", "'@'", "'$rose'", "'$fell'", 
		"'$stable'", "'$changed'", "'$always'", "'$never'", "'$eventually'", null, 
		"','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "IMPLY_NEXT", "LPAREN", 
		"RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", "FELL", "STABLE", "CHANGED", 
		"ALWAYS", "NEVER", "EVENTUALLY", "NUM", "COMMA", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u00c9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\5\2=\n\2\3\3\3\3\7\3A"+
		"\n\3\f\3\16\3D\13\3\3\4\3\4\7\4H\n\4\f\4\16\4K\13\4\3\4\3\4\3\4\7\4P\n"+
		"\4\f\4\16\4S\13\4\3\4\3\4\3\5\3\5\6\5Y\n\5\r\5\16\5Z\3\5\7\5^\n\5\f\5"+
		"\16\5a\13\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\6\32\u00bd\n\32\r\32\16\32\u00be\3\33\3\33\3\34"+
		"\6\34\u00c4\n\34\r\34\16\34\u00c5\3\34\3\34\2\2\35\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35\3\2\6\5\2C\\aac|\7\2&&\62"+
		";C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\u00d1\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\3<\3\2\2\2\5>\3\2\2\2\7E\3\2\2\2\tV\3\2\2\2"+
		"\13b\3\2\2\2\rd\3\2\2\2\17f\3\2\2\2\21h\3\2\2\2\23j\3\2\2\2\25m\3\2\2"+
		"\2\27p\3\2\2\2\31t\3\2\2\2\33x\3\2\2\2\35z\3\2\2\2\37|\3\2\2\2!~\3\2\2"+
		"\2#\u0081\3\2\2\2%\u0083\3\2\2\2\'\u0089\3\2\2\2)\u008f\3\2\2\2+\u0097"+
		"\3\2\2\2-\u00a0\3\2\2\2/\u00a8\3\2\2\2\61\u00af\3\2\2\2\63\u00bc\3\2\2"+
		"\2\65\u00c0\3\2\2\2\67\u00c3\3\2\2\29=\5\5\3\2:=\5\7\4\2;=\5\t\5\2<9\3"+
		"\2\2\2<:\3\2\2\2<;\3\2\2\2=\4\3\2\2\2>B\t\2\2\2?A\t\3\2\2@?\3\2\2\2AD"+
		"\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\6\3\2\2\2DB\3\2\2\2EI\t\2\2\2FH\t\3\2\2"+
		"GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7]\2\2"+
		"MQ\t\4\2\2NP\t\4\2\2ON\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2"+
		"SQ\3\2\2\2TU\7_\2\2U\b\3\2\2\2VX\7^\2\2WY\4#\u0080\2XW\3\2\2\2YZ\3\2\2"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[_\3\2\2\2\\^\n\5\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2"+
		"\2\2_`\3\2\2\2`\n\3\2\2\2a_\3\2\2\2bc\7(\2\2c\f\3\2\2\2de\7~\2\2e\16\3"+
		"\2\2\2fg\7`\2\2g\20\3\2\2\2hi\7\u0080\2\2i\22\3\2\2\2jk\7?\2\2kl\7?\2"+
		"\2l\24\3\2\2\2mn\7#\2\2no\7?\2\2o\26\3\2\2\2pq\7~\2\2qr\7/\2\2rs\7@\2"+
		"\2s\30\3\2\2\2tu\7~\2\2uv\7?\2\2vw\7@\2\2w\32\3\2\2\2xy\7*\2\2y\34\3\2"+
		"\2\2z{\7+\2\2{\36\3\2\2\2|}\7%\2\2} \3\2\2\2~\177\7%\2\2\177\u0080\7%"+
		"\2\2\u0080\"\3\2\2\2\u0081\u0082\7B\2\2\u0082$\3\2\2\2\u0083\u0084\7&"+
		"\2\2\u0084\u0085\7t\2\2\u0085\u0086\7q\2\2\u0086\u0087\7u\2\2\u0087\u0088"+
		"\7g\2\2\u0088&\3\2\2\2\u0089\u008a\7&\2\2\u008a\u008b\7h\2\2\u008b\u008c"+
		"\7g\2\2\u008c\u008d\7n\2\2\u008d\u008e\7n\2\2\u008e(\3\2\2\2\u008f\u0090"+
		"\7&\2\2\u0090\u0091\7u\2\2\u0091\u0092\7v\2\2\u0092\u0093\7c\2\2\u0093"+
		"\u0094\7d\2\2\u0094\u0095\7n\2\2\u0095\u0096\7g\2\2\u0096*\3\2\2\2\u0097"+
		"\u0098\7&\2\2\u0098\u0099\7e\2\2\u0099\u009a\7j\2\2\u009a\u009b\7c\2\2"+
		"\u009b\u009c\7p\2\2\u009c\u009d\7i\2\2\u009d\u009e\7g\2\2\u009e\u009f"+
		"\7f\2\2\u009f,\3\2\2\2\u00a0\u00a1\7&\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3"+
		"\7n\2\2\u00a3\u00a4\7y\2\2\u00a4\u00a5\7c\2\2\u00a5\u00a6\7{\2\2\u00a6"+
		"\u00a7\7u\2\2\u00a7.\3\2\2\2\u00a8\u00a9\7&\2\2\u00a9\u00aa\7p\2\2\u00aa"+
		"\u00ab\7g\2\2\u00ab\u00ac\7x\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7t\2\2"+
		"\u00ae\60\3\2\2\2\u00af\u00b0\7&\2\2\u00b0\u00b1\7g\2\2\u00b1\u00b2\7"+
		"x\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7v\2\2\u00b5\u00b6"+
		"\7w\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7n\2\2\u00b9"+
		"\u00ba\7{\2\2\u00ba\62\3\2\2\2\u00bb\u00bd\t\4\2\2\u00bc\u00bb\3\2\2\2"+
		"\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\64"+
		"\3\2\2\2\u00c0\u00c1\7.\2\2\u00c1\66\3\2\2\2\u00c2\u00c4\t\5\2\2\u00c3"+
		"\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2"+
		"\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b\34\2\2\u00c88\3\2\2\2\13\2<BIQ"+
		"Z_\u00be\u00c5\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}