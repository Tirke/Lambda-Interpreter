// Generated from /Users/Tirke/Downloads/The Lambda Interpreter/Sources/Lambda.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LambdaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, RPAR=2, LPAR=3, RCURL=4, LCURL=5, PLUS=6, MINUS=7, MULT=8, DOT=9, 
		EQ=10, NEQ=11, GT=12, LT=13, GTEQ=14, LTEQ=15, IF=16, THEN=17, ELSE=18, 
		REC=19, VAR=20, INT=21, LAMBDA=22, WS=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "RPAR", "LPAR", "RCURL", "LCURL", "PLUS", "MINUS", "MULT", "DOT", 
		"EQ", "NEQ", "GT", "LT", "GTEQ", "LTEQ", "IF", "THEN", "ELSE", "REC", 
		"VAR", "INT", "LAMBDA", "WS"
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


	public LambdaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lambda.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\7\25f\n\25\f\25\16\25i\13\25\3\26\6\26l\n\26\r\26\16"+
		"\26m\3\27\3\27\3\30\6\30s\n\30\r\30\16\30t\3\30\3\30\2\2\31\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\3\2\7\4\2C\\c|\5\2\62;C\\c|\3\2\62;\4\2"+
		"^^\u03bd\u03bd\4\2\13\f\"\"z\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\65\3\2\2\2\t"+
		"\67\3\2\2\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23A\3\2\2\2"+
		"\25C\3\2\2\2\27E\3\2\2\2\31H\3\2\2\2\33J\3\2\2\2\35L\3\2\2\2\37O\3\2\2"+
		"\2!R\3\2\2\2#U\3\2\2\2%Z\3\2\2\2\'_\3\2\2\2)c\3\2\2\2+k\3\2\2\2-o\3\2"+
		"\2\2/r\3\2\2\2\61\62\7\"\2\2\62\4\3\2\2\2\63\64\7+\2\2\64\6\3\2\2\2\65"+
		"\66\7*\2\2\66\b\3\2\2\2\678\7\177\2\28\n\3\2\2\29:\7}\2\2:\f\3\2\2\2;"+
		"<\7-\2\2<\16\3\2\2\2=>\7/\2\2>\20\3\2\2\2?@\7,\2\2@\22\3\2\2\2AB\7\60"+
		"\2\2B\24\3\2\2\2CD\7?\2\2D\26\3\2\2\2EF\7#\2\2FG\7?\2\2G\30\3\2\2\2HI"+
		"\7@\2\2I\32\3\2\2\2JK\7>\2\2K\34\3\2\2\2LM\7@\2\2MN\7?\2\2N\36\3\2\2\2"+
		"OP\7>\2\2PQ\7?\2\2Q \3\2\2\2RS\7k\2\2ST\7h\2\2T\"\3\2\2\2UV\7v\2\2VW\7"+
		"j\2\2WX\7g\2\2XY\7p\2\2Y$\3\2\2\2Z[\7g\2\2[\\\7n\2\2\\]\7u\2\2]^\7g\2"+
		"\2^&\3\2\2\2_`\7t\2\2`a\7g\2\2ab\7e\2\2b(\3\2\2\2cg\t\2\2\2df\t\3\2\2"+
		"ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2h*\3\2\2\2ig\3\2\2\2jl\t\4\2\2"+
		"kj\3\2\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2n,\3\2\2\2op\t\5\2\2p.\3\2\2\2"+
		"qs\t\6\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\b\30\2"+
		"\2w\60\3\2\2\2\6\2gmt\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}