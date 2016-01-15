/**
 * <code>LambdaBasicVisitor</code> est une implémentation basique du Visitor généré par
 * ANTLR4.
 * Les méthodes de cette classe peuvent être <code>Override</code> par les classes héritantes.
 */
public class LambdaBasicVisitor extends LambdaBaseVisitor<Value> {

    /**
     * Implémentation basique d'une application qui renvoie une application telle quelle.
     *
     * @param ctx Le noeud courant
     * @return Une application
     */
    @Override
    public Value visitApplication(LambdaParser.ApplicationContext ctx) {
        Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));
        if (ctx.parent instanceof LambdaParser.ParenExpressionContext) {
            return new Value(new Apply(left, right, true));
        } else {
            return new Value(new Apply(left, right, false));
        }
    }

    /**
     * Méthode permettant d'évaluer une abstraction
     *
     * @param ctx Représente le noeud courant
     * @return Renvoie une abstraction bien parenthésée
     */
    @Override
    public Value visitAbstraction(LambdaParser.AbstractionContext ctx) {
        String varUnderLambda = ctx.VAR().getText();
        Value expression = this.visit(ctx.expression());
        if (ctx.parent instanceof LambdaParser.ParenExpressionContext) {
            return new Value(new Abstraction(varUnderLambda, expression, false));
        } else {
            return new Value(new Abstraction(varUnderLambda, expression, true));
        }
    }

    /**
     * @param ctx Le noeud courant
     * @return Renvoie une variable
     */
    @Override
    public Value visitVariable(LambdaParser.VariableContext ctx) {
        return new Value(new Variable(ctx.getText()));
    }

    /**
     * La réduction d'une <code>IfRule</code> est faite ici.
     * On évalue des égalités uniquement si les deux termes sont des <code>Integer</code>
     * Tous les opérateurs classiques de comparaison sont supportés.
     *
     * @param ctx Le noeud courant.
     * @return Renvoie le résultat sous le then ou le else ou une ifRule entière si
     * les termes comaprés ne sont pas des <code>Integer</code>.
     */
    @Override
    public Value visitIfRule(LambdaParser.IfRuleContext ctx) {

        Value leftToCheck = this.visit(ctx.expression(0));
        Value rightToCheck = this.visit(ctx.expression(1));


        if (leftToCheck.isInteger() && rightToCheck.isInteger()) {
            int left = leftToCheck.asInteger();
            int right = rightToCheck.asInteger();
            switch (ctx.op.getType()) {
                case LambdaParser.EQ:
                    if (left == right) {
                        Value trueReturn = this.visit(ctx.expression(2));
                        return new Value(trueReturn.value);
                    } else {
                        Value falseReturn = this.visit(ctx.expression(3));
                        return new Value(falseReturn.value);
                    }

                case LambdaParser.GT:
                    if (left > right) {
                        Value trueReturn = this.visit(ctx.expression(2));
                        return new Value(trueReturn.value);
                    } else {
                        Value falseReturn = this.visit(ctx.expression(3));
                        return new Value(falseReturn.value);
                    }

                case LambdaParser.LT:
                    if (left < right) {
                        Value trueReturn = this.visit(ctx.expression(2));
                        return new Value(trueReturn.value);
                    } else {
                        Value falseReturn = this.visit(ctx.expression(3));
                        return new Value(falseReturn.value);
                    }

                case LambdaParser.GTEQ:
                    if (left >= right) {
                        Value trueReturn = this.visit(ctx.expression(2));
                        return new Value(trueReturn.value);
                    } else {
                        Value falseReturn = this.visit(ctx.expression(3));
                        return new Value(falseReturn.value);
                    }

                case LambdaParser.LTEQ:
                    if (left <= right) {
                        Value trueReturn = this.visit(ctx.expression(2));
                        return new Value(trueReturn.value);
                    } else {
                        Value falseReturn = this.visit(ctx.expression(3));
                        return new Value(falseReturn.value);
                    }
                case LambdaParser.NEQ:
                    if (left != right) {
                        Value trueReturn = this.visit(ctx.expression(2));
                        return new Value(trueReturn.value);
                    } else {
                        Value falseReturn = this.visit(ctx.expression(3));
                        return new Value(falseReturn.value);
                    }
                default:
                    throw new RuntimeException("L'opérateur de l'expression est inconnu (mais cela ne devrait pas arriver)");
            }

        } else {
            return new Value(new IfStat(leftToCheck, rightToCheck, this.visit(ctx.expression(2)), this.visit(ctx.expression(3)), ctx.op.getText()));
        }

    }

    /**
     * @param ctx Le noeud courant
     * @return Une <code>RecRule</code>
     */
    @Override
    public Value visitRecRule(LambdaParser.RecRuleContext ctx) {
        String funcName = ctx.VAR(0).getText();
        String applyVar = ctx.VAR(1).getText();
        Value function = this.visit(ctx.expression());

        return new Value(new RecFunction(new Variable(funcName), new Variable(applyVar), function));

    }

    /**
     * @param ctx Le noeud courant
     * @return L'epression dans la parenthèse.
     */
    @Override
    public Value visitParenExpression(LambdaParser.ParenExpressionContext ctx) {
        return this.visit(ctx.expression());
    }

    /**
     * @param ctx Le noeud courant
     * @return Un <code>Integer</code>
     */
    @Override
    public Value visitInteger(LambdaParser.IntegerContext ctx) {
        return new Value(Integer.valueOf(ctx.getText()));
    }


    /**
     * La méthode qui fait les multiplications
     * On ne calcule deux termes que si ils sont tous les deux des <code>Integer</code>
     *
     * @param ctx Le noeud courant
     * @return Un <code>Integer</code> résultant, ou un <code>Calcul</code> de l'opération.
     */
    @Override
    public Value visitMult(LambdaParser.MultContext ctx) {
        Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));

        return left.isInteger() && right.isInteger() ?
                new Value(left.asInteger() * right.asInteger()) : new Value(new Calcul(new Value(left), '*', new Value(right)));

    }

    /**
     * La méthode qui fait les additions
     * On ne calcule deux termes que si ils sont tous les deux des <code>Integer</code>
     *
     * @param ctx Le noeud courant
     * @return Un <code>Integer</code> résultant, ou un <code>Calcul</code> de l'opération.
     */
    @Override
    public Value visitAdd(LambdaParser.AddContext ctx) {
        Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));

        switch (ctx.op.getType()) {
            case LambdaParser.PLUS:
                return left.isInteger() && right.isInteger() ?
                        new Value(left.asInteger() + right.asInteger()) : new Value(new Calcul(new Value(left), '+', new Value(right)));
            case LambdaParser.MINUS:
                return left.isInteger() && right.isInteger() ?
                        new Value(left.asInteger() - right.asInteger()) : new Value(new Calcul(new Value(left), '-', new Value(right)));
            default:
                throw new RuntimeException("L'opérateur de l'expression est inconnu (mais cela ne devrait pas arriver)");

        }
    }
}
