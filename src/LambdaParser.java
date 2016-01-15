// Generated from /Users/Tirke/Downloads/The Lambda Interpreter/Sources/Lambda.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LambdaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, RPAR=2, LPAR=3, RCURL=4, LCURL=5, PLUS=6, MINUS=7, MULT=8, DOT=9, 
		EQ=10, NEQ=11, GT=12, LT=13, GTEQ=14, LTEQ=15, IF=16, THEN=17, ELSE=18, 
		REC=19, VAR=20, INT=21, LAMBDA=22, WS=23;
	public static final int
		RULE_expression = 0;
	public static final String[] ruleNames = {
		"expression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "' '", "')'", "'('", "'}'", "'{'", "'+'", "'-'", "'*'", "'.'", "'='", 
		"'!='", "'>'", "'<'", "'>='", "'<='", "'if'", "'then'", "'else'", "'rec'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "RPAR", "LPAR", "RCURL", "LCURL", "PLUS", "MINUS", "MULT", 
		"DOT", "EQ", "NEQ", "GT", "LT", "GTEQ", "LTEQ", "IF", "THEN", "ELSE", 
		"REC", "VAR", "INT", "LAMBDA", "WS"
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
	public String getGrammarFileName() { return "Lambda.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LambdaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(LambdaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(LambdaParser.MINUS, 0); }
		public AddContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitAdd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MULT() { return getToken(LambdaParser.MULT, 0); }
		public MultContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitMult(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ApplicationContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ApplicationContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitApplication(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class RecRuleContext extends ExpressionContext {
		public TerminalNode REC() { return getToken(LambdaParser.REC, 0); }
		public List<TerminalNode> VAR() { return getTokens(LambdaParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(LambdaParser.VAR, i);
		}
		public TerminalNode DOT() { return getToken(LambdaParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RecRuleContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitRecRule(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AbstractionContext extends ExpressionContext {
		public TerminalNode LAMBDA() { return getToken(LambdaParser.LAMBDA, 0); }
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public TerminalNode DOT() { return getToken(LambdaParser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AbstractionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitAbstraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableContext extends ExpressionContext {
		public TerminalNode VAR() { return getToken(LambdaParser.VAR, 0); }
		public VariableContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfRuleContext extends ExpressionContext {
		public Token op;
		public TerminalNode IF() { return getToken(LambdaParser.IF, 0); }
		public TerminalNode LPAR() { return getToken(LambdaParser.LPAR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RPAR() { return getToken(LambdaParser.RPAR, 0); }
		public TerminalNode THEN() { return getToken(LambdaParser.THEN, 0); }
		public List<TerminalNode> LCURL() { return getTokens(LambdaParser.LCURL); }
		public TerminalNode LCURL(int i) {
			return getToken(LambdaParser.LCURL, i);
		}
		public List<TerminalNode> RCURL() { return getTokens(LambdaParser.RCURL); }
		public TerminalNode RCURL(int i) {
			return getToken(LambdaParser.RCURL, i);
		}
		public TerminalNode ELSE() { return getToken(LambdaParser.ELSE, 0); }
		public TerminalNode EQ() { return getToken(LambdaParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(LambdaParser.NEQ, 0); }
		public TerminalNode GT() { return getToken(LambdaParser.GT, 0); }
		public TerminalNode LT() { return getToken(LambdaParser.LT, 0); }
		public TerminalNode GTEQ() { return getToken(LambdaParser.GTEQ, 0); }
		public TerminalNode LTEQ() { return getToken(LambdaParser.LTEQ, 0); }
		public IfRuleContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitIfRule(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExpressionContext extends ExpressionContext {
		public TerminalNode LPAR() { return getToken(LambdaParser.LPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(LambdaParser.RPAR, 0); }
		public ParenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitParenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntegerContext extends ExpressionContext {
		public TerminalNode INT() { return getToken(LambdaParser.INT, 0); }
		public IntegerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LambdaVisitor ) return ((LambdaVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			switch (_input.LA(1)) {
			case LAMBDA:
				{
				_localctx = new AbstractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(3);
				match(LAMBDA);
				setState(4);
				match(VAR);
				setState(5);
				match(DOT);
				setState(6);
				expression(2);
				}
				break;
			case REC:
				{
				_localctx = new RecRuleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(7);
				match(REC);
				setState(8);
				match(T__0);
				setState(9);
				match(VAR);
				setState(10);
				match(T__0);
				setState(11);
				match(VAR);
				setState(12);
				match(DOT);
				setState(13);
				expression(1);
				}
				break;
			case LPAR:
				{
				_localctx = new ParenExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(14);
				match(LPAR);
				setState(15);
				expression(0);
				setState(16);
				match(RPAR);
				}
				break;
			case VAR:
				{
				_localctx = new VariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(18);
				match(VAR);
				}
				break;
			case INT:
				{
				_localctx = new IntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(19);
				match(INT);
				}
				break;
			case IF:
				{
				_localctx = new IfRuleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(20);
				match(IF);
				setState(21);
				match(LPAR);
				setState(22);
				expression(0);
				setState(23);
				((IfRuleContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NEQ) | (1L << GT) | (1L << LT) | (1L << GTEQ) | (1L << LTEQ))) != 0)) ) {
					((IfRuleContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(24);
				expression(0);
				setState(25);
				match(RPAR);
				setState(26);
				match(THEN);
				setState(27);
				match(LCURL);
				setState(28);
				expression(0);
				setState(29);
				match(RCURL);
				setState(30);
				match(ELSE);
				setState(31);
				match(LCURL);
				setState(32);
				expression(0);
				setState(33);
				match(RCURL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(48);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(46);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new MultContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(37);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(38);
						match(MULT);
						setState(39);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new AddContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(40);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(41);
						((AddContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AddContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(42);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new ApplicationContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(43);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(44);
						match(T__0);
						setState(45);
						expression(4);
						}
						break;
					}
					} 
				}
				setState(50);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\31\66\4\2\t\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2&\n\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\61\n\2\f\2\16\2\64\13\2\3\2\2\3"+
		"\2\3\2\2\4\3\2\f\21\3\2\b\t<\2%\3\2\2\2\4\5\b\2\1\2\5\6\7\30\2\2\6\7\7"+
		"\26\2\2\7\b\7\13\2\2\b&\5\2\2\4\t\n\7\25\2\2\n\13\7\3\2\2\13\f\7\26\2"+
		"\2\f\r\7\3\2\2\r\16\7\26\2\2\16\17\7\13\2\2\17&\5\2\2\3\20\21\7\5\2\2"+
		"\21\22\5\2\2\2\22\23\7\4\2\2\23&\3\2\2\2\24&\7\26\2\2\25&\7\27\2\2\26"+
		"\27\7\22\2\2\27\30\7\5\2\2\30\31\5\2\2\2\31\32\t\2\2\2\32\33\5\2\2\2\33"+
		"\34\7\4\2\2\34\35\7\23\2\2\35\36\7\7\2\2\36\37\5\2\2\2\37 \7\6\2\2 !\7"+
		"\24\2\2!\"\7\7\2\2\"#\5\2\2\2#$\7\6\2\2$&\3\2\2\2%\4\3\2\2\2%\t\3\2\2"+
		"\2%\20\3\2\2\2%\24\3\2\2\2%\25\3\2\2\2%\26\3\2\2\2&\62\3\2\2\2\'(\f\b"+
		"\2\2()\7\n\2\2)\61\5\2\2\t*+\f\7\2\2+,\t\3\2\2,\61\5\2\2\b-.\f\5\2\2."+
		"/\7\3\2\2/\61\5\2\2\6\60\'\3\2\2\2\60*\3\2\2\2\60-\3\2\2\2\61\64\3\2\2"+
		"\2\62\60\3\2\2\2\62\63\3\2\2\2\63\3\3\2\2\2\64\62\3\2\2\2\5%\60\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}