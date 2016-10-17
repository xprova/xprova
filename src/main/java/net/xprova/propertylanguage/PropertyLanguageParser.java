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
		CHANGED=23, ALWAYS=24, NEVER=25, EVENTUALLY=26, NUM=27, COMMA=28, WS=29;
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
		"'$eventually'", null, "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "High", "Low", "ID", "Simple_identifier", "Bit_identifier", "Escaped_identifier", 
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
		public TerminalNode LPAREN() { return getToken(PropertyLanguageParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PropertyLanguageParser.RPAREN, 0); }
		public TerminalNode ALWAYS() { return getToken(PropertyLanguageParser.ALWAYS, 0); }
		public TerminalNode NEVER() { return getToken(PropertyLanguageParser.NEVER, 0); }
		public TerminalNode EVENTUALLY() { return getToken(PropertyLanguageParser.EVENTUALLY, 0); }
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
		int _la;
		try {
			setState(38);
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
				setState(25);
				baseExpr();
				}
				break;
			case ALWAYS:
			case NEVER:
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				_la = _input.LA(1);
				if ( !(_la==ALWAYS || _la==NEVER) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(27);
				match(LPAREN);
				setState(28);
				baseExpr();
				setState(29);
				match(RPAREN);
				}
				break;
			case EVENTUALLY:
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
				match(EVENTUALLY);
				setState(32);
				match(LPAREN);
				setState(33);
				baseExpr();
				setState(34);
				match(COMMA);
				setState(35);
				baseExpr();
				setState(36);
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
			setState(40);
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
			setState(42);
			orExpr();
			setState(45);
			_la = _input.LA(1);
			if (_la==IMPLY || _la==IMPLY_NEXT) {
				{
				setState(43);
				_la = _input.LA(1);
				if ( !(_la==IMPLY || _la==IMPLY_NEXT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(44);
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
			setState(47);
			xorExpr();
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(48);
				match(OR);
				setState(49);
				xorExpr();
				}
				}
				setState(54);
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
			setState(55);
			andExpr();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==XOR) {
				{
				{
				setState(56);
				match(XOR);
				setState(57);
				andExpr();
				}
				}
				setState(62);
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
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				eqExpr();
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==AND) {
					{
					{
					setState(64);
					match(AND);
					setState(65);
					eqExpr();
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				eqExpr();
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOUBLE_HASH) {
					{
					{
					setState(72);
					match(DOUBLE_HASH);
					setState(74);
					_la = _input.LA(1);
					if (_la==NUM) {
						{
						setState(73);
						match(NUM);
						}
					}

					setState(76);
					eqExpr();
					}
					}
					setState(81);
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
			setState(84);
			timeAtom();
			setState(87);
			_la = _input.LA(1);
			if (_la==EQ || _la==NEQ) {
				{
				setState(85);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(86);
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
			setState(91);
			_la = _input.LA(1);
			if (_la==HASH || _la==AT) {
				{
				setState(89);
				_la = _input.LA(1);
				if ( !(_la==HASH || _la==AT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(90);
				match(NUM);
				}
			}

			setState(93);
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
			setState(96);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(95);
				match(NOT);
				}
			}

			setState(98);
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
			setState(112);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(ID);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				match(LPAREN);
				setState(102);
				baseExpr();
				setState(103);
				match(RPAREN);
				}
				break;
			case ROSE:
			case FELL:
			case STABLE:
			case CHANGED:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ROSE) | (1L << FELL) | (1L << STABLE) | (1L << CHANGED))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(106);
				match(LPAREN);
				setState(107);
				baseExpr();
				setState(108);
				match(RPAREN);
				}
				break;
			case High:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				match(High);
				}
				break;
			case Low:
				enterOuterAlt(_localctx, 5);
				{
				setState(111);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\37u\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\3)\n\3\3\4\3\4\3\5\3\5\3\5\5\5\60\n\5\3\6\3\6\3\6\7\6\65\n\6\f\6\16"+
		"\68\13\6\3\7\3\7\3\7\7\7=\n\7\f\7\16\7@\13\7\3\b\3\b\3\b\7\bE\n\b\f\b"+
		"\16\bH\13\b\3\b\3\b\3\b\5\bM\n\b\3\b\7\bP\n\b\f\b\16\bS\13\b\5\bU\n\b"+
		"\3\t\3\t\3\t\5\tZ\n\t\3\n\3\n\5\n^\n\n\3\n\3\n\3\13\5\13c\n\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fs\n\f\3\f\2\2\r"+
		"\2\4\6\b\n\f\16\20\22\24\26\2\7\3\2\32\33\3\2\17\20\3\2\r\16\4\2\23\23"+
		"\25\25\3\2\26\31y\2\30\3\2\2\2\4(\3\2\2\2\6*\3\2\2\2\b,\3\2\2\2\n\61\3"+
		"\2\2\2\f9\3\2\2\2\16T\3\2\2\2\20V\3\2\2\2\22]\3\2\2\2\24b\3\2\2\2\26r"+
		"\3\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33)\5\6\4\2\34\35\t"+
		"\2\2\2\35\36\7\21\2\2\36\37\5\6\4\2\37 \7\22\2\2 )\3\2\2\2!\"\7\34\2\2"+
		"\"#\7\21\2\2#$\5\6\4\2$%\7\36\2\2%&\5\6\4\2&\'\7\22\2\2\')\3\2\2\2(\33"+
		"\3\2\2\2(\34\3\2\2\2(!\3\2\2\2)\5\3\2\2\2*+\5\b\5\2+\7\3\2\2\2,/\5\n\6"+
		"\2-.\t\3\2\2.\60\5\n\6\2/-\3\2\2\2/\60\3\2\2\2\60\t\3\2\2\2\61\66\5\f"+
		"\7\2\62\63\7\n\2\2\63\65\5\f\7\2\64\62\3\2\2\2\658\3\2\2\2\66\64\3\2\2"+
		"\2\66\67\3\2\2\2\67\13\3\2\2\28\66\3\2\2\29>\5\16\b\2:;\7\13\2\2;=\5\16"+
		"\b\2<:\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?\r\3\2\2\2@>\3\2\2\2AF\5"+
		"\20\t\2BC\7\t\2\2CE\5\20\t\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G"+
		"U\3\2\2\2HF\3\2\2\2IQ\5\20\t\2JL\7\24\2\2KM\7\35\2\2LK\3\2\2\2LM\3\2\2"+
		"\2MN\3\2\2\2NP\5\20\t\2OJ\3\2\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RU\3\2"+
		"\2\2SQ\3\2\2\2TA\3\2\2\2TI\3\2\2\2U\17\3\2\2\2VY\5\22\n\2WX\t\4\2\2XZ"+
		"\5\22\n\2YW\3\2\2\2YZ\3\2\2\2Z\21\3\2\2\2[\\\t\5\2\2\\^\7\35\2\2][\3\2"+
		"\2\2]^\3\2\2\2^_\3\2\2\2_`\5\24\13\2`\23\3\2\2\2ac\7\f\2\2ba\3\2\2\2b"+
		"c\3\2\2\2cd\3\2\2\2de\5\26\f\2e\25\3\2\2\2fs\7\5\2\2gh\7\21\2\2hi\5\6"+
		"\4\2ij\7\22\2\2js\3\2\2\2kl\t\6\2\2lm\7\21\2\2mn\5\6\4\2no\7\22\2\2os"+
		"\3\2\2\2ps\7\3\2\2qs\7\4\2\2rf\3\2\2\2rg\3\2\2\2rk\3\2\2\2rp\3\2\2\2r"+
		"q\3\2\2\2s\27\3\2\2\2\16(/\66>FLQTY]br";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}