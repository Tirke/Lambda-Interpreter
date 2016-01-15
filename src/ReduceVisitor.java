import org.antlr.v4.runtime.RuleContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <code>ReduceVisitor</code> est une classe qui applique les principes de β-reduction mais
 * aussi qui renomme les variables des abstractions selon les principes
 * de l'α-conversion.
 * La classe implemente le pattern de Visiteur qui est une façon de parcourir l'AST
 * produit par le parser.
 * La classe extends <code>LambdaBasicVisitor</code> dont elle hérite les méthodes de réduction
 * pour le calcul des integers mais aussi la réduction des clauses <code>if</code>.
 */
public class ReduceVisitor extends LambdaBasicVisitor {


    /**
     * <code>replaceMap</code> est une <code>HashMap</code> qui à une variable associe un rennomage
     * de cette même variable
     * <code>freeVars</code> est liste des variables libres issue du visitor <code>FreeVariableVisitor</code>
     */
    Map<String, String> replaceMap = new HashMap<>();
    Set<String> freeVars;

    public ReduceVisitor(Set<String> freeVars) {
        this.freeVars = freeVars;
    }

    /**
     * La méthode qui renvoie une Variable renommé si besoin est
     *
     * @param ctx Le noeud courant
     * @return Retourne une <code>Variable</code>
     */
    @Override
    public Value visitVariable(LambdaParser.VariableContext ctx) {
        String var = ctx.getText();
        // On ne renomme que des variables qui sont dans des abstractions
        if (underAbs(ctx)) {
            if (replaceMap.containsKey(var)) {
                var = replaceMap.get(var);
            }
            return new Value(new Variable(var));
        } else {
            return super.visitVariable(ctx);
        }
    }

    /**
     * Pour éviter de renommer des variables qui ne devrait pas l'être,
     * on vide la <code>HashMap</code> qui contient les régles de renommage
     * lorsque que l'on change d'abstraction "globale"
     *
     * @param ctx Le noeud courant
     * @return Retourne l'expression à l'intérieur des parentheses.
     */
    @Override
    public Value visitParenExpression(LambdaParser.ParenExpressionContext ctx) {
        replaceMap.clear();
        return super.visitParenExpression(ctx);
    }

    /**
     * On procède au rennomage de la variable "bounded" par le lmabda si besoin est.
     * Et on crée une règle de rennomage pour les variables qui correspondent dans l'expression.
     *
     * @param ctx Représente le noeud courant
     * @return Renvoie une abstraction paranthésée ou non selon le contexte du parent.
     */
    @Override
    public Value visitAbstraction(LambdaParser.AbstractionContext ctx) {
        String var = ctx.VAR().getText();
        if (freeVars.contains(var)) {
            String newVar = var;
            for (int i = 0; freeVars.contains(newVar); i++) {
                newVar = var + i;
            }
            replaceMap.put(var, newVar);
            var = newVar;
        }

        Value expression = this.visit(ctx.expression());


        if (ctx.parent instanceof LambdaParser.ParenExpressionContext) {
            return new Value(new Abstraction(var, expression, false));
        } else {
            return new Value(new Abstraction(var, expression, true));
        }
    }

    /**
     * La plus grosse méthode de la classe et même du projet,
     * cette méthode assure à elle seule les  β-reduction des applications
     * et des fonctions récursives.
     * L'expression régulière \bx\b remplace x dans x entier et pas dans xy
     *
     * @param ctx Le noeud courant
     * @return La méthode renvoie le plus souvent une application, mais en réalité la méthode renvoie
     * tous les types possibles compris dans le wrapper <code>Value</code>
     */
    @Override
    public Value visitApplication(LambdaParser.ApplicationContext ctx) {
        Value left = this.visit(ctx.expression(0));
        Value right = this.visit(ctx.expression(1));

        if (left.isRec()) {

            /* On prend l'expression
            Dans l'expression on remplace x par right qui est la valeur appliquée
            et on remplace f par le if récursif complet
            * */

            RecFunction recFunction = left.asRec();
            IfStat ifStat = recFunction.getFunction().asIfStat();


            String newRight = ifStat.getRightToEq().toString().replaceAll("\\b"+recFunction.getApplyToVar().getId()+"\\b", right.asString());
            String newLeft = ifStat.getLeftToEq().toString().replaceAll("\\b"+recFunction.getApplyToVar().getId()+"\\b", right.asString());

            String replaceThen = ifStat.getThenReturn().asString().replaceAll("\\b"+recFunction.getApplyToVar().getId()+"\\b", right.asString());
            replaceThen = replaceThen.replaceAll("\\b"+recFunction.getFuncNameVar().getId()+"\\b", recFunction.toString());

            String replaceElse = ifStat.getElseReturn().asString().replaceAll("\\b"+recFunction.getApplyToVar().getId()+"\\b", right.asString());
            replaceElse = replaceElse.replaceAll("\\b"+recFunction.getFuncNameVar().getId()+"\\b", recFunction.toString());


            IfStat ifChanged = new IfStat(new Value(newLeft), new Value(newRight), new Value(replaceThen), new Value(replaceElse), ifStat.getOperator());


            return new Value(ifChanged);

        }


        if (!left.isAbstraction()) {
            if (ctx.parent instanceof LambdaParser.ParenExpressionContext) {
                return new Value(new Apply(left, right, true));
            } else {
                return new Value(new Apply(left, right, false));
            }
        } else {
            Abstraction function = left.asAbstraction();
            // SI le corps de la fonction contient un de ses arguments
            if (function.canApply()) {
                if (function.getExp().isVariable()) {
                    if (underAbs(ctx)) {
                        return new Value(new Apply(left, right, false));
                    }
                    //On retourne simplement right
                    return right;
                } else if (function.getExp().isAbstraction()) {
                    //On renvoie une abstraction
                    if (right.isAbstraction()) {
                        // Hésitation possible pour false ou true, mis à false pour la soutenance
                        right.asAbstraction().setInside(false);
                    }
                    Abstraction returnAbs = function.getExp().asAbstraction();
                    return new Value(new Abstraction(returnAbs.getVar(), new Value(returnAbs.getExp().asString().replaceAll("\\b" + function.getVar() + "\\b", right.asString())), false));
                } else if (function.getExp().isString()) {

                    /*Comme le travail est souvent fait sur des String et non directement
                    dans les noeuds de Variable il peut arriver qu'après remplacement
                    on se retrouve dans un cas ou on évalue un String appliqué à une valeur
                    Comme les termes sont bien formés il suffit de renvoyer le tout tel quel et de repasser
                    le terme au Parser/Lexer pour continuer l'évaluation.*/

                    return new Value(new Apply(left, right, false));
                } else {
                    /* On renvoie le String résultant de l'application, si le corps de la fonction
                    est un calcul ou une application */

                    if (underAbs(ctx)) {
                        return new Value(new Apply(left, right, false));
                    }

                    return new Value(function.getExp().asString().replaceAll("\\b" + function.getVar() + "\\b", right.asString()));
                }
            } else {
                //La variable sous le lambda n'est pas présente dans l'expression de l'abstraction, on renvoie une constante
                if (function.getExp().isString()) {
                    return new Value(new Apply(left, right, false));
                }
                return function.getExp();
            }
        }
    }

    /**
     * En partant d'une noeud permet de déterminer si il est dans l'expression d'une abstraction ou non.
     *
     * @param ctx Le noeud courant
     * @return Retourne vrai si le noeud est sous une abstraction et faux dans l'autre cas.
     */
    private boolean underAbs(RuleContext ctx) {
        RuleContext parent = ctx.parent;

        if (parent == null) {
            return false;
        }

        while (parent.getParent() != null) {
            if (parent instanceof LambdaParser.AbstractionContext) {
                return true;
            }
            parent = parent.parent;
        }
        return false;
    }

}
