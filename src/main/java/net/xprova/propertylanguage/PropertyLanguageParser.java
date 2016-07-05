// Generated from PropertyLanguage.g4 by ANTLR 4.5.3
package net.xprova.propertylanguage;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PropertyLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ID=1, Simple_identifier=2, Bit_identifier=3, Escaped_identifier=4, AND=5, 
		OR=6, XOR=7, NOT=8, EQ=9, NEQ=10, IMPLY=11, IMPLY_NEXT=12, LPAREN=13, 
		RPAREN=14, HASH=15, DOUBLE_HASH=16, AT=17, ROSE=18, FELL=19, STABLE=20, 
		CHANGED=21, NUM=22, WS=23;
	public static final int
		RULE_property = 0, RULE_expr = 1, RULE_funcExpr = 2, RULE_implyExpr = 3, 
		RULE_orExpr = 4, RULE_xorExpr = 5, RULE_andExpr = 6, RULE_eqExpr = 7, 
		RULE_timeAtom = 8, RULE_nAtom = 9, RULE_atom = 10;
	public static final String[] ruleNames = {
		"property", "expr", "funcExpr", "implyExpr", "orExpr", "xorExpr", "andExpr", 
		"eqExpr", "timeAtom", "nAtom", "atom"
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

	@Override
	public String getGrammarFileName() { return "PropertyLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PropertyLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PropertyContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PropertyLanguageParser.EOF, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitProperty(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			expr();
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			funcExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncExprContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PropertyLanguageParser.LPAREN, 0); }
		public ImplyExprContext implyExpr() {
			return getRuleContext(ImplyExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public TerminalNode ROSE() { return getToken(PropertyLanguageParser.ROSE, 0); }
		public TerminalNode FELL() { return getToken(PropertyLanguageParser.FELL, 0); }
		public TerminalNode STABLE() { return getToken(PropertyLanguageParser.STABLE, 0); }
		public TerminalNode CHANGED() { return getToken(PropertyLanguageParser.CHANGED, 0); }
		public FuncExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitFuncExpr(this);
		}
	}

	public final FuncExprContext funcExpr() throws RecognitionException {
		FuncExprContext _localctx = new FuncExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcExpr);
		int _la;
		try {
			setState(33);
			switch (_input.LA(1)) {
			case ROSE:
			case FELL:
			case STABLE:
			case CHANGED:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ROSE) | (1L << FELL) | (1L << STABLE) | (1L << CHANGED))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(28);
				match(LPAREN);
				setState(29);
				implyExpr();
				setState(30);
				match(RPAREN);
				}
				break;
			case ID:
			case NOT:
			case LPAREN:
			case HASH:
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				implyExpr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImplyExprContext extends ParserRuleContext {
		public List<OrExprContext> orExpr() {
			return getRuleContexts(OrExprContext.class);
		}
		public OrExprContext orExpr(int i) {
			return getRuleContext(OrExprContext.class,i);
		}
		public TerminalNode IMPLY() { return getToken(PropertyLanguageParser.IMPLY, 0); }
		public TerminalNode IMPLY_NEXT() { return getToken(PropertyLanguageParser.IMPLY_NEXT, 0); }
		public ImplyExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implyExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterImplyExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitImplyExpr(this);
		}
	}

	public final ImplyExprContext implyExpr() throws RecognitionException {
		ImplyExprContext _localctx = new ImplyExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_implyExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			orExpr();
			setState(38);
			_la = _input.LA(1);
			if (_la==IMPLY || _la==IMPLY_NEXT) {
				{
				setState(36);
				_la = _input.LA(1);
				if ( !(_la==IMPLY || _la==IMPLY_NEXT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(37);
				orExpr();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExprContext extends ParserRuleContext {
		public List<XorExprContext> xorExpr() {
			return getRuleContexts(XorExprContext.class);
		}
		public XorExprContext xorExpr(int i) {
			return getRuleContext(XorExprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(PropertyLanguageParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(PropertyLanguageParser.OR, i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitOrExpr(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			xorExpr();
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(41);
				match(OR);
				setState(42);
				xorExpr();
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class XorExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(PropertyLanguageParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(PropertyLanguageParser.XOR, i);
		}
		public XorExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xorExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterXorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitXorExpr(this);
		}
	}

	public final XorExprContext xorExpr() throws RecognitionException {
		XorExprContext _localctx = new XorExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_xorExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			andExpr();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(49);
				match(XOR);
				setState(50);
				andExpr();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExprContext extends ParserRuleContext {
		public List<EqExprContext> eqExpr() {
			return getRuleContexts(EqExprContext.class);
		}
		public EqExprContext eqExpr(int i) {
			return getRuleContext(EqExprContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(PropertyLanguageParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(PropertyLanguageParser.AND, i);
		}
		public List<TerminalNode> DOUBLE_HASH() { return getTokens(PropertyLanguageParser.DOUBLE_HASH); }
		public TerminalNode DOUBLE_HASH(int i) {
			return getToken(PropertyLanguageParser.DOUBLE_HASH, i);
		}
		public List<TerminalNode> NUM() { return getTokens(PropertyLanguageParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(PropertyLanguageParser.NUM, i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitAndExpr(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_andExpr);
		int _la;
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				eqExpr();
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(57);
					match(AND);
					setState(58);
					eqExpr();
					}
					}
					setState(63);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				eqExpr();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOUBLE_HASH) {
					{
					{
					setState(65);
					match(DOUBLE_HASH);
					setState(67);
					_la = _input.LA(1);
					if (_la==NUM) {
						{
						setState(66);
						match(NUM);
						}
					}

					setState(69);
					eqExpr();
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqExprContext extends ParserRuleContext {
		public List<TimeAtomContext> timeAtom() {
			return getRuleContexts(TimeAtomContext.class);
		}
		public TimeAtomContext timeAtom(int i) {
			return getRuleContext(TimeAtomContext.class,i);
		}
		public TerminalNode EQ() { return getToken(PropertyLanguageParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(PropertyLanguageParser.NEQ, 0); }
		public EqExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_eqExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterEqExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitEqExpr(this);
		}
	}

	public final EqExprContext eqExpr() throws RecognitionException {
		EqExprContext _localctx = new EqExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_eqExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			timeAtom();
			setState(80);
			_la = _input.LA(1);
			if (_la==EQ || _la==NEQ) {
				{
				setState(78);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(79);
				timeAtom();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TimeAtomContext extends ParserRuleContext {
		public NAtomContext nAtom() {
			return getRuleContext(NAtomContext.class,0);
		}
		public TerminalNode NUM() { return getToken(PropertyLanguageParser.NUM, 0); }
		public TerminalNode HASH() { return getToken(PropertyLanguageParser.HASH, 0); }
		public TerminalNode AT() { return getToken(PropertyLanguageParser.AT, 0); }
		public TimeAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterTimeAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitTimeAtom(this);
		}
	}

	public final TimeAtomContext timeAtom() throws RecognitionException {
		TimeAtomContext _localctx = new TimeAtomContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_timeAtom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			_la = _input.LA(1);
			if (_la==HASH || _la==AT) {
				{
				setState(82);
				_la = _input.LA(1);
				if ( !(_la==HASH || _la==AT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(83);
				match(NUM);
				}
			}

			setState(86);
			nAtom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NAtomContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PropertyLanguageParser.NOT, 0); }
		public NAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterNAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitNAtom(this);
		}
	}

	public final NAtomContext nAtom() throws RecognitionException {
		NAtomContext _localctx = new NAtomContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_nAtom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(88);
				match(NOT);
				}
			}

			setState(91);
			atom();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PropertyLanguageParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(PropertyLanguageParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_atom);
		try {
			setState(98);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				match(ID);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				match(LPAREN);
				setState(95);
				expr();
				setState(96);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31g\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4$\n\4\3\5\3\5\3"+
		"\5\5\5)\n\5\3\6\3\6\3\6\7\6.\n\6\f\6\16\6\61\13\6\3\7\3\7\3\7\7\7\66\n"+
		"\7\f\7\16\79\13\7\3\b\3\b\3\b\7\b>\n\b\f\b\16\bA\13\b\3\b\3\b\3\b\5\b"+
		"F\n\b\3\b\7\bI\n\b\f\b\16\bL\13\b\5\bN\n\b\3\t\3\t\3\t\5\tS\n\t\3\n\3"+
		"\n\5\nW\n\n\3\n\3\n\3\13\5\13\\\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\f"+
		"e\n\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\6\3\2\24\27\3\2\r\16\3\2"+
		"\13\f\4\2\21\21\23\23g\2\30\3\2\2\2\4\33\3\2\2\2\6#\3\2\2\2\b%\3\2\2\2"+
		"\n*\3\2\2\2\f\62\3\2\2\2\16M\3\2\2\2\20O\3\2\2\2\22V\3\2\2\2\24[\3\2\2"+
		"\2\26d\3\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\5\6\4\2"+
		"\34\5\3\2\2\2\35\36\t\2\2\2\36\37\7\17\2\2\37 \5\b\5\2 !\7\20\2\2!$\3"+
		"\2\2\2\"$\5\b\5\2#\35\3\2\2\2#\"\3\2\2\2$\7\3\2\2\2%(\5\n\6\2&\'\t\3\2"+
		"\2\')\5\n\6\2(&\3\2\2\2()\3\2\2\2)\t\3\2\2\2*/\5\f\7\2+,\7\b\2\2,.\5\f"+
		"\7\2-+\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\13\3\2\2\2\61/\3\2"+
		"\2\2\62\67\5\16\b\2\63\64\7\t\2\2\64\66\5\16\b\2\65\63\3\2\2\2\669\3\2"+
		"\2\2\67\65\3\2\2\2\678\3\2\2\28\r\3\2\2\29\67\3\2\2\2:?\5\20\t\2;<\7\7"+
		"\2\2<>\5\20\t\2=;\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@N\3\2\2\2A?\3"+
		"\2\2\2BJ\5\20\t\2CE\7\22\2\2DF\7\30\2\2ED\3\2\2\2EF\3\2\2\2FG\3\2\2\2"+
		"GI\5\20\t\2HC\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KN\3\2\2\2LJ\3\2\2"+
		"\2M:\3\2\2\2MB\3\2\2\2N\17\3\2\2\2OR\5\22\n\2PQ\t\4\2\2QS\5\22\n\2RP\3"+
		"\2\2\2RS\3\2\2\2S\21\3\2\2\2TU\t\5\2\2UW\7\30\2\2VT\3\2\2\2VW\3\2\2\2"+
		"WX\3\2\2\2XY\5\24\13\2Y\23\3\2\2\2Z\\\7\n\2\2[Z\3\2\2\2[\\\3\2\2\2\\]"+
		"\3\2\2\2]^\5\26\f\2^\25\3\2\2\2_e\7\3\2\2`a\7\17\2\2ab\5\4\3\2bc\7\20"+
		"\2\2ce\3\2\2\2d_\3\2\2\2d`\3\2\2\2e\27\3\2\2\2\16#(/\67?EJMRV[d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}