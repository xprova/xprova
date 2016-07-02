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
		WS=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", "AND", 
		"OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "LPAREN", "RPAREN", "HASH", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, "'&'", "'|'", "'^'", "'~'", "'=='", "'!='", 
		"'|->'", "'('", "')'", "'#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "IMPLY", "LPAREN", "RPAREN", "HASH", 
		"WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21i\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\5\2%\n\2"+
		"\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13\4\3"+
		"\4\3\4\3\4\7\48\n\4\f\4\16\4;\13\4\3\4\3\4\3\5\3\5\6\5A\n\5\r\5\16\5B"+
		"\3\5\7\5F\n\5\f\5\16\5I\13\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\6\20"+
		"d\n\20\r\20\16\20e\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\6\5\2C\\aac|\7\2&&\62;C\\"+
		"aac|\3\2\62;\5\2\13\f\17\17\"\"p\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\3$\3\2\2\2\5&\3\2\2\2\7-\3\2\2\2\t>\3\2\2\2\13J\3\2\2\2"+
		"\rL\3\2\2\2\17N\3\2\2\2\21P\3\2\2\2\23R\3\2\2\2\25U\3\2\2\2\27X\3\2\2"+
		"\2\31\\\3\2\2\2\33^\3\2\2\2\35`\3\2\2\2\37c\3\2\2\2!%\5\5\3\2\"%\5\7\4"+
		"\2#%\5\t\5\2$!\3\2\2\2$\"\3\2\2\2$#\3\2\2\2%\4\3\2\2\2&*\t\2\2\2\')\t"+
		"\3\2\2(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\6\3\2\2\2,*\3\2\2\2-"+
		"\61\t\2\2\2.\60\t\3\2\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2"+
		"\2\2\62\64\3\2\2\2\63\61\3\2\2\2\64\65\7]\2\2\659\t\4\2\2\668\t\4\2\2"+
		"\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7"+
		"_\2\2=\b\3\2\2\2>@\7^\2\2?A\4#\u0080\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2B"+
		"C\3\2\2\2CG\3\2\2\2DF\n\5\2\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2"+
		"H\n\3\2\2\2IG\3\2\2\2JK\7(\2\2K\f\3\2\2\2LM\7~\2\2M\16\3\2\2\2NO\7`\2"+
		"\2O\20\3\2\2\2PQ\7\u0080\2\2Q\22\3\2\2\2RS\7?\2\2ST\7?\2\2T\24\3\2\2\2"+
		"UV\7#\2\2VW\7?\2\2W\26\3\2\2\2XY\7~\2\2YZ\7/\2\2Z[\7@\2\2[\30\3\2\2\2"+
		"\\]\7*\2\2]\32\3\2\2\2^_\7+\2\2_\34\3\2\2\2`a\7%\2\2a\36\3\2\2\2bd\t\5"+
		"\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\b\20\2\2h \3"+
		"\2\2\2\n\2$*\619BGe\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}