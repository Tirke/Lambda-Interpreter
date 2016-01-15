import java.util.Objects;

/**
 * La classe Apply représente une Application sous la forme
 * expression expression, où l'espace entre deux expression
 * est crucial, car il indique l'application.
 */
public class Apply {
    private Value left;
    private Value right;

    /**
     * shouldPar est un attribut qui aide au bon parenthésage d'une application
     */
    private boolean shouldPar;

    public Apply(Value left, Value right, boolean shouldPar) {
        this.left = left;
        this.right = right;
        this.shouldPar = shouldPar;
    }

    public Value getLeft() {
        return left;
    }

    public Value getRight() {
        return right;
    }

    @Override
    public String toString() {
        if (this.shouldPar) {
            return "(" + left + " " + right + ")";
        } else {
            return left + " " + right;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apply apply = (Apply) o;
        return Objects.equals(getLeft(), apply.getLeft()) &&
                Objects.equals(getRight(), apply.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight());
    }

}
