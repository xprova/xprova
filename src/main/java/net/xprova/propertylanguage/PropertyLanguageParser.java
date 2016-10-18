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
		High=1, Low=2, ID=3, Simple_identifier=4, Bit_identifier=5, Escaped_identifier=6, 
		AND=7, OR=8, XOR=9, NOT=10, EQ=11, NEQ=12, IMPLY=13, IMPLY_NEXT=14, LPAREN=15, 
		RPAREN=16, HASH=17, DOUBLE_HASH=18, AT=19, ROSE=20, FELL=21, STABLE=22, 
		CHANGED=23, ALWAYS=24, NEVER=25, ONCE=26, EVENTUALLY=27, NUM=28, COMMA=29, 
		WS=30;
	public static final int
		RULE_property = 0, RULE_tempExpr = 1, RULE_baseExpr = 2, RULE_implyExpr = 3, 
		RULE_orExpr = 4, RULE_xorExpr = 5, RULE_andExpr = 6, RULE_eqExpr = 7, 
		RULE_timeAtom = 8, RULE_nAtom = 9, RULE_atom = 10;
	public static final String[] ruleNames = {
		"property", "tempExpr", "baseExpr", "implyExpr", "orExpr", "xorExpr", 
		"andExpr", "eqExpr", "timeAtom", "nAtom", "atom"
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
		public TempExprContext tempExpr() {
			return getRuleContext(TempExprContext.class,0);
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
			tempExpr();
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

	public static class TempExprContext extends ParserRuleContext {
		public List<BaseExprContext> baseExpr() {
			return getRuleContexts(BaseExprContext.class);
		}
		public BaseExprContext baseExpr(int i) {
			return getRuleContext(BaseExprContext.class,i);
		}
		public TerminalNode EVENTUALLY() { return getToken(PropertyLanguageParser.EVENTUALLY, 0); }
		public TerminalNode LPAREN() { return getToken(PropertyLanguageParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public TerminalNode COMMA() { return getToken(PropertyLanguageParser.COMMA, 0); }
		public TempExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tempExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterTempExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitTempExpr(this);
		}
	}

	public final TempExprContext tempExpr() throws RecognitionException {
		TempExprContext _localctx = new TempExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tempExpr);
		try {
			setState(36);
			switch (_input.LA(1)) {
			case High:
			case Low:
			case ID:
			case NOT:
			case LPAREN:
			case HASH:
			case AT:
			case ROSE:
			case FELL:
			case STABLE:
			case CHANGED:
			case ALWAYS:
			case NEVER:
			case ONCE:
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				baseExpr();
				}
				break;
			case EVENTUALLY:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				match(EVENTUALLY);
				setState(27);
				match(LPAREN);
				setState(31);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(28);
					baseExpr();
					setState(29);
					match(COMMA);
					}
					break;
				}
				setState(33);
				baseExpr();
				setState(34);
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

	public static class BaseExprContext extends ParserRuleContext {
		public ImplyExprContext implyExpr() {
			return getRuleContext(ImplyExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PropertyLanguageParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public TerminalNode ALWAYS() { return getToken(PropertyLanguageParser.ALWAYS, 0); }
		public TerminalNode NEVER() { return getToken(PropertyLanguageParser.NEVER, 0); }
		public TerminalNode ONCE() { return getToken(PropertyLanguageParser.ONCE, 0); }
		public BaseExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_baseExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).enterBaseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PropertyLanguageListener ) ((PropertyLanguageListener)listener).exitBaseExpr(this);
		}
	}

	public final BaseExprContext baseExpr() throws RecognitionException {
		BaseExprContext _localctx = new BaseExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_baseExpr);
		int _la;
		try {
			setState(44);
			switch (_input.LA(1)) {
			case High:
			case Low:
			case ID:
			case NOT:
			case LPAREN:
			case HASH:
			case AT:
			case ROSE:
			case FELL:
			case STABLE:
			case CHANGED:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				implyExpr();
				}
				break;
			case ALWAYS:
			case NEVER:
			case ONCE:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALWAYS) | (1L << NEVER) | (1L << ONCE))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(40);
				match(LPAREN);
				setState(41);
				implyExpr();
				setState(42);
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
			setState(46);
			orExpr();
			setState(49);
			_la = _input.LA(1);
			if (_la==IMPLY || _la==IMPLY_NEXT) {
				{
				setState(47);
				_la = _input.LA(1);
				if ( !(_la==IMPLY || _la==IMPLY_NEXT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(48);
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
			setState(51);
			xorExpr();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(52);
				match(OR);
				setState(53);
				xorExpr();
				}
				}
				setState(58);
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
			setState(59);
			andExpr();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(60);
				match(XOR);
				setState(61);
				andExpr();
				}
				}
				setState(66);
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
			setState(86);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				eqExpr();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(68);
					match(AND);
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
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(75);
				eqExpr();
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOUBLE_HASH) {
					{
					{
					setState(76);
					match(DOUBLE_HASH);
					setState(78);
					_la = _input.LA(1);
					if (_la==NUM) {
						{
						setState(77);
						match(NUM);
						}
					}

					setState(80);
					eqExpr();
					}
					}
					setState(85);
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
			setState(88);
			timeAtom();
			setState(91);
			_la = _input.LA(1);
			if (_la==EQ || _la==NEQ) {
				{
				setState(89);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(90);
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
			setState(95);
			_la = _input.LA(1);
			if (_la==HASH || _la==AT) {
				{
				setState(93);
				_la = _input.LA(1);
				if ( !(_la==HASH || _la==AT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(94);
				match(NUM);
				}
			}

			setState(97);
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
			setState(100);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(99);
				match(NOT);
				}
			}

			setState(102);
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
		public BaseExprContext baseExpr() {
			return getRuleContext(BaseExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public TerminalNode ROSE() { return getToken(PropertyLanguageParser.ROSE, 0); }
		public TerminalNode FELL() { return getToken(PropertyLanguageParser.FELL, 0); }
		public TerminalNode STABLE() { return getToken(PropertyLanguageParser.STABLE, 0); }
		public TerminalNode CHANGED() { return getToken(PropertyLanguageParser.CHANGED, 0); }
		public TerminalNode High() { return getToken(PropertyLanguageParser.High, 0); }
		public TerminalNode Low() { return getToken(PropertyLanguageParser.Low, 0); }
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
		int _la;
		try {
			setState(116);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(104);
				match(ID);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(LPAREN);
				setState(106);
				baseExpr();
				setState(107);
				match(RPAREN);
				}
				break;
			case ROSE:
			case FELL:
			case STABLE:
			case CHANGED:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ROSE) | (1L << FELL) | (1L << STABLE) | (1L << CHANGED))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(110);
				match(LPAREN);
				setState(111);
				baseExpr();
				setState(112);
				match(RPAREN);
				}
				break;
			case High:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				match(High);
				}
				break;
			case Low:
				enterOuterAlt(_localctx, 5);
				{
				setState(115);
				match(Low);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3 y\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\"\n\3\3\3\3\3\3\3\5\3\'\n"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4/\n\4\3\5\3\5\3\5\5\5\64\n\5\3\6\3\6\3\6"+
		"\7\69\n\6\f\6\16\6<\13\6\3\7\3\7\3\7\7\7A\n\7\f\7\16\7D\13\7\3\b\3\b\3"+
		"\b\7\bI\n\b\f\b\16\bL\13\b\3\b\3\b\3\b\5\bQ\n\b\3\b\7\bT\n\b\f\b\16\b"+
		"W\13\b\5\bY\n\b\3\t\3\t\3\t\5\t^\n\t\3\n\3\n\5\nb\n\n\3\n\3\n\3\13\5\13"+
		"g\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fw"+
		"\n\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\7\3\2\32\34\3\2\17\20\3\2"+
		"\r\16\4\2\23\23\25\25\3\2\26\31~\2\30\3\2\2\2\4&\3\2\2\2\6.\3\2\2\2\b"+
		"\60\3\2\2\2\n\65\3\2\2\2\f=\3\2\2\2\16X\3\2\2\2\20Z\3\2\2\2\22a\3\2\2"+
		"\2\24f\3\2\2\2\26v\3\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33"+
		"\'\5\6\4\2\34\35\7\35\2\2\35!\7\21\2\2\36\37\5\6\4\2\37 \7\37\2\2 \"\3"+
		"\2\2\2!\36\3\2\2\2!\"\3\2\2\2\"#\3\2\2\2#$\5\6\4\2$%\7\22\2\2%\'\3\2\2"+
		"\2&\33\3\2\2\2&\34\3\2\2\2\'\5\3\2\2\2(/\5\b\5\2)*\t\2\2\2*+\7\21\2\2"+
		"+,\5\b\5\2,-\7\22\2\2-/\3\2\2\2.(\3\2\2\2.)\3\2\2\2/\7\3\2\2\2\60\63\5"+
		"\n\6\2\61\62\t\3\2\2\62\64\5\n\6\2\63\61\3\2\2\2\63\64\3\2\2\2\64\t\3"+
		"\2\2\2\65:\5\f\7\2\66\67\7\n\2\2\679\5\f\7\28\66\3\2\2\29<\3\2\2\2:8\3"+
		"\2\2\2:;\3\2\2\2;\13\3\2\2\2<:\3\2\2\2=B\5\16\b\2>?\7\13\2\2?A\5\16\b"+
		"\2@>\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\r\3\2\2\2DB\3\2\2\2EJ\5\20"+
		"\t\2FG\7\t\2\2GI\5\20\t\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2KY\3"+
		"\2\2\2LJ\3\2\2\2MU\5\20\t\2NP\7\24\2\2OQ\7\36\2\2PO\3\2\2\2PQ\3\2\2\2"+
		"QR\3\2\2\2RT\5\20\t\2SN\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2"+
		"\2WU\3\2\2\2XE\3\2\2\2XM\3\2\2\2Y\17\3\2\2\2Z]\5\22\n\2[\\\t\4\2\2\\^"+
		"\5\22\n\2][\3\2\2\2]^\3\2\2\2^\21\3\2\2\2_`\t\5\2\2`b\7\36\2\2a_\3\2\2"+
		"\2ab\3\2\2\2bc\3\2\2\2cd\5\24\13\2d\23\3\2\2\2eg\7\f\2\2fe\3\2\2\2fg\3"+
		"\2\2\2gh\3\2\2\2hi\5\26\f\2i\25\3\2\2\2jw\7\5\2\2kl\7\21\2\2lm\5\6\4\2"+
		"mn\7\22\2\2nw\3\2\2\2op\t\6\2\2pq\7\21\2\2qr\5\6\4\2rs\7\22\2\2sw\3\2"+
		"\2\2tw\7\3\2\2uw\7\4\2\2vj\3\2\2\2vk\3\2\2\2vo\3\2\2\2vt\3\2\2\2vu\3\2"+
		"\2\2w\27\3\2\2\2\20!&.\63:BJPUX]afv";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}