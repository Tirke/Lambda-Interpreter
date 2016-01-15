import java.util.HashSet;
import java.util.Set;

/**
 * Cette classe a pour but d'évaluer les variables libres
 * d'une expression en visitant son AST
 */
public class FreeVariableVisitor extends LambdaBasicVisitor {

    private Set<String> freeVars = new HashSet<>();
    private Set<String> boundVars = new HashSet<>();

    /**
     * @return Retourne l'ensemble des variables libres de l'expression
     */
    public Set<String> getFreeVariables() {
        return freeVars;
    }


    /**
     * Pour chaque abstraction :
     * La variable sous le lambda est ajoutée à la liste des variables liées
     * Puis on évalue l'expression sous le lambda (qui peut contenir des variables ou d'autres abstraction)
     * Et enfin on enlève la variable sous le lambda de la liste des variables liés pour la suite de l'évaluation
     *
     * @param ctx Représente le noeud courant
     * @return Le valeur retournée est une abstraction
     */
    @Override
    public Value visitAbstraction(LambdaParser.AbstractionContext ctx) {

        String var = ctx.VAR().getText();
        boundVars.add(var);
        Value expression = this.visit(ctx.expression());
        boundVars.remove(var);

        if (ctx.parent instanceof LambdaParser.ParenExpressionContext) {
            return new Value(new Abstraction(var, expression, false));
        } else {
            return new Value(new Abstraction(var, expression, true));
        }
    }


    /**
     * Pour chaque variable :
     * On ajoute la variable à la lsite des variables libres si elle n'es tpas liée par un lambda
     *
     * @param ctx Le noeud courant
     * @return Renvoie une variable selon les mêmes conditions que <code>LambdaBasicVisitor</code>
     */
    @Override
    public Value visitVariable(LambdaParser.VariableContext ctx) {

        String var = ctx.getText();
        if (!boundVars.contains(var)) {
            freeVars.add(var); // La variable est libre
        }
        return super.visitVariable(ctx);
    }

}
