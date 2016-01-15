/**
 * The Abstraction class represents an Abstraction in form of
 * Abstraction = λ var.expression
 */
public class Abstraction {
    private String var;
    private Value exp;

    /**
     * Cet attribut est utilisé pour le parenthésage des termes
     */
    private boolean isInside;

    public Abstraction(String var, Value exp, boolean isInside) {
        this.var = var;
        this.exp = exp;
        this.isInside = isInside;
    }

    public String getVar() {
        return var;
    }

    public Value getExp() {
        return exp;
    }

    public void setExp(Value exp) {
        this.exp = exp;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    @Override
    public String toString() {
        if (this.isInside()) {
            return "λ" + var + "." + exp.toString();
        } else {
            return "(λ" + var + "." + exp.toString() + ")";
        }
    }


    /**
     * La méthode vérifie pour une abstraction que la variable liée par le lambda est présente
     * dans l'expression de l'abstraction.
     * L'utilisation d'une expression régulière permet d'éviter les erreurs dans des termes
     * comme λx.xy où la variable dans l'expression contient x mais ne représente pas
     * la même variable.
     *
     * @return <code>true</code> si la variable est présente, <code>false</code> sinon.
     */
    public boolean canApply() {
        return exp.asString().matches(".*\\b" + var + "\\b.*");
    }

}
