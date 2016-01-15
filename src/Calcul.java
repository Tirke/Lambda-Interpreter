import java.util.Objects;

/**
 * La classe "type" qui englobe un calcul, donc soit une multiplication, une addition, ou une soustraction.
 */
public class Calcul {
    private Value left;
    private Value right;
    private char operateur;

    public Calcul(Value left, char operateur, Value right) {
        this.left = left;
        this.right = right;
        this.operateur = operateur;
    }

    public Value getLeft() {
        return left;
    }

    public Value getRight() {
        return right;
    }

    public char getOperateur() {
        return operateur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calcul calcul = (Calcul) o;
        return getOperateur() == calcul.getOperateur() &&
                Objects.equals(getLeft(), calcul.getLeft()) &&
                Objects.equals(getRight(), calcul.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLeft(), getRight(), getOperateur());
    }

    @Override
    public String toString() {
        return left.toString() + operateur + right.toString();
    }
}
