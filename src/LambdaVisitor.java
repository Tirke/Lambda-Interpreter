// Generated from /Users/Tirke/Downloads/The Lambda Interpreter/Sources/Lambda.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LambdaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LambdaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(LambdaParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(LambdaParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code application}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplication(LambdaParser.ApplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code recRule}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecRule(LambdaParser.RecRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code abstraction}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstraction(LambdaParser.AbstractionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LambdaParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifRule}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfRule(LambdaParser.IfRuleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(LambdaParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integer}
	 * labeled alternative in {@link LambdaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(LambdaParser.IntegerContext ctx);
}