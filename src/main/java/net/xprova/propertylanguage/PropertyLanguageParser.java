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
		AND=7, OR=8, XOR=9, NOT=10, EQ=11, NEQ=12, LT=13, GT=14, LE=15, GE=16, 
		IMPLY=17, IMPLY_NEXT=18, LPAREN=19, RPAREN=20, HASH=21, DOUBLE_HASH=22, 
		AT=23, ROSE=24, FELL=25, STABLE=26, CHANGED=27, ALWAYS=28, NEVER=29, ONCE=30, 
		UNTIL=31, WHEN=32, ANY=33, ALL=34, EVENTUALLY=35, NUM=36, COMMA=37, WS=38;
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
		"'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'|->'", "'|=>'", "'('", 
		"')'", "'#'", "'##'", "'@'", "'$rose'", "'$fell'", "'$stable'", "'$changed'", 
		"'$always'", "'$never'", "'$once'", "'$until'", "'$when'", "'$any'", "'$all'", 
		"'$eventually'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
		"AND", "OR", "XOR", "NOT", "EQ", "NEQ", "LT", "GT", "LE", "GE", "IMPLY", 
		"IMPLY_NEXT", "LPAREN", "RPAREN", "HASH", "DOUBLE_HASH", "AT", "ROSE", 
		"FELL", "STABLE", "CHANGED", "ALWAYS", "NEVER", "ONCE", "UNTIL", "WHEN", 
		"ANY", "ALL", "EVENTUALLY", "NUM", "COMMA", "WS"
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
			case UNTIL:
			case WHEN:
			case ANY:
			case ALL:
			case NUM:
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			implyExpr();
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
			setState(40);
			orExpr();
			setState(43);
			_la = _input.LA(1);
			if (_la==IMPLY || _la==IMPLY_NEXT) {
				{
				setState(41);
				_la = _input.LA(1);
				if ( !(_la==IMPLY || _la==IMPLY_NEXT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(42);
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
			setState(45);
			xorExpr();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(46);
				match(OR);
				setState(47);
				xorExpr();
				}
				}
				setState(52);
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
			setState(53);
			andExpr();
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(54);
				match(XOR);
				setState(55);
				andExpr();
				}
				}
				setState(60);
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
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				eqExpr();
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(62);
					match(AND);
					setState(63);
					eqExpr();
					}
					}
					setState(68);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				eqExpr();
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOUBLE_HASH) {
					{
					{
					setState(70);
					match(DOUBLE_HASH);
					setState(72);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(71);
						match(NUM);
						}
						break;
					}
					setState(74);
					eqExpr();
					}
					}
					setState(79);
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
		public TerminalNode LT() { return getToken(PropertyLanguageParser.LT, 0); }
		public TerminalNode GT() { return getToken(PropertyLanguageParser.GT, 0); }
		public TerminalNode LE() { return getToken(PropertyLanguageParser.LE, 0); }
		public TerminalNode GE() { return getToken(PropertyLanguageParser.GE, 0); }
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
			setState(82);
			timeAtom();
			setState(85);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) {
				{
				setState(83);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(84);
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
			setState(89);
			_la = _input.LA(1);
			if (_la==HASH || _la==AT) {
				{
				setState(87);
				_la = _input.LA(1);
				if ( !(_la==HASH || _la==AT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(88);
				match(NUM);
				}
			}

			setState(91);
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
			setState(94);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(93);
				match(NOT);
				}
			}

			setState(96);
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
		public List<BaseExprContext> baseExpr() {
			return getRuleContexts(BaseExprContext.class);
		}
		public BaseExprContext baseExpr(int i) {
			return getRuleContext(BaseExprContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public TerminalNode ROSE() { return getToken(PropertyLanguageParser.ROSE, 0); }
		public TerminalNode FELL() { return getToken(PropertyLanguageParser.FELL, 0); }
		public TerminalNode STABLE() { return getToken(PropertyLanguageParser.STABLE, 0); }
		public TerminalNode CHANGED() { return getToken(PropertyLanguageParser.CHANGED, 0); }
		public TerminalNode ALWAYS() { return getToken(PropertyLanguageParser.ALWAYS, 0); }
		public TerminalNode NEVER() { return getToken(PropertyLanguageParser.NEVER, 0); }
		public TerminalNode ONCE() { return getToken(PropertyLanguageParser.ONCE, 0); }
		public TerminalNode ANY() { return getToken(PropertyLanguageParser.ANY, 0); }
		public TerminalNode ALL() { return getToken(PropertyLanguageParser.ALL, 0); }
		public TerminalNode COMMA() { return getToken(PropertyLanguageParser.COMMA, 0); }
		public TerminalNode UNTIL() { return getToken(PropertyLanguageParser.UNTIL, 0); }
		public TerminalNode WHEN() { return getToken(PropertyLanguageParser.WHEN, 0); }
		public TerminalNode NUM() { return getToken(PropertyLanguageParser.NUM, 0); }
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
			setState(123);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				match(ID);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(LPAREN);
				setState(100);
				baseExpr();
				setState(101);
				match(RPAREN);
				}
				break;
			case ROSE:
			case FELL:
			case STABLE:
			case CHANGED:
				enterOuterAlt(_localctx, 3);
				{
				setState(103);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ROSE) | (1L << FELL) | (1L << STABLE) | (1L << CHANGED))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(104);
				match(LPAREN);
				setState(105);
				baseExpr();
				setState(106);
				match(RPAREN);
				}
				break;
			case ALWAYS:
			case NEVER:
			case ONCE:
			case ANY:
			case ALL:
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALWAYS) | (1L << NEVER) | (1L << ONCE) | (1L << ANY) | (1L << ALL))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(109);
				match(LPAREN);
				setState(110);
				baseExpr();
				setState(111);
				match(RPAREN);
				}
				break;
			case UNTIL:
			case WHEN:
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				_la = _input.LA(1);
				if ( !(_la==UNTIL || _la==WHEN) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(114);
				match(LPAREN);
				setState(115);
				baseExpr();
				setState(116);
				match(COMMA);
				setState(117);
				baseExpr();
				setState(118);
				match(RPAREN);
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 6);
				{
				setState(120);
				match(NUM);
				}
				break;
			case High:
				enterOuterAlt(_localctx, 7);
				{
				setState(121);
				match(High);
				}
				break;
			case Low:
				enterOuterAlt(_localctx, 8);
				{
				setState(122);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3(\u0080\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\"\n\3\3\3\3\3\3\3"+
		"\5\3\'\n\3\3\4\3\4\3\5\3\5\3\5\5\5.\n\5\3\6\3\6\3\6\7\6\63\n\6\f\6\16"+
		"\6\66\13\6\3\7\3\7\3\7\7\7;\n\7\f\7\16\7>\13\7\3\b\3\b\3\b\7\bC\n\b\f"+
		"\b\16\bF\13\b\3\b\3\b\3\b\5\bK\n\b\3\b\7\bN\n\b\f\b\16\bQ\13\b\5\bS\n"+
		"\b\3\t\3\t\3\t\5\tX\n\t\3\n\3\n\5\n\\\n\n\3\n\3\n\3\13\5\13a\n\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f~\n\f\3\f\2\2\r\2\4\6\b\n\f\16"+
		"\20\22\24\26\2\b\3\2\23\24\3\2\r\22\4\2\27\27\31\31\3\2\32\35\4\2\36 "+
		"#$\3\2!\"\u0087\2\30\3\2\2\2\4&\3\2\2\2\6(\3\2\2\2\b*\3\2\2\2\n/\3\2\2"+
		"\2\f\67\3\2\2\2\16R\3\2\2\2\20T\3\2\2\2\22[\3\2\2\2\24`\3\2\2\2\26}\3"+
		"\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33\'\5\6\4\2\34\35\7%"+
		"\2\2\35!\7\25\2\2\36\37\5\6\4\2\37 \7\'\2\2 \"\3\2\2\2!\36\3\2\2\2!\""+
		"\3\2\2\2\"#\3\2\2\2#$\5\6\4\2$%\7\26\2\2%\'\3\2\2\2&\33\3\2\2\2&\34\3"+
		"\2\2\2\'\5\3\2\2\2()\5\b\5\2)\7\3\2\2\2*-\5\n\6\2+,\t\2\2\2,.\5\n\6\2"+
		"-+\3\2\2\2-.\3\2\2\2.\t\3\2\2\2/\64\5\f\7\2\60\61\7\n\2\2\61\63\5\f\7"+
		"\2\62\60\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\13\3\2\2"+
		"\2\66\64\3\2\2\2\67<\5\16\b\289\7\13\2\29;\5\16\b\2:8\3\2\2\2;>\3\2\2"+
		"\2<:\3\2\2\2<=\3\2\2\2=\r\3\2\2\2><\3\2\2\2?D\5\20\t\2@A\7\t\2\2AC\5\20"+
		"\t\2B@\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2ES\3\2\2\2FD\3\2\2\2GO\5\20"+
		"\t\2HJ\7\30\2\2IK\7&\2\2JI\3\2\2\2JK\3\2\2\2KL\3\2\2\2LN\5\20\t\2MH\3"+
		"\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2R?\3\2\2\2RG\3"+
		"\2\2\2S\17\3\2\2\2TW\5\22\n\2UV\t\3\2\2VX\5\22\n\2WU\3\2\2\2WX\3\2\2\2"+
		"X\21\3\2\2\2YZ\t\4\2\2Z\\\7&\2\2[Y\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\5\24"+
		"\13\2^\23\3\2\2\2_a\7\f\2\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2\2bc\5\26\f\2c"+
		"\25\3\2\2\2d~\7\5\2\2ef\7\25\2\2fg\5\6\4\2gh\7\26\2\2h~\3\2\2\2ij\t\5"+
		"\2\2jk\7\25\2\2kl\5\6\4\2lm\7\26\2\2m~\3\2\2\2no\t\6\2\2op\7\25\2\2pq"+
		"\5\6\4\2qr\7\26\2\2r~\3\2\2\2st\t\7\2\2tu\7\25\2\2uv\5\6\4\2vw\7\'\2\2"+
		"wx\5\6\4\2xy\7\26\2\2y~\3\2\2\2z~\7&\2\2{~\7\3\2\2|~\7\4\2\2}d\3\2\2\2"+
		"}e\3\2\2\2}i\3\2\2\2}n\3\2\2\2}s\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2"+
		"~\27\3\2\2\2\17!&-\64<DJORW[`}";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}