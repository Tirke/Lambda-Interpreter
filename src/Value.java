import java.util.Objects;

/**
 * La classe Value est une simple classe Wrapper de tous les autres types.
 * Les méthodes des Visitors crée par ANTLR4 devant tous retourner le même type,
 * il est nécessaire d'englober tout nos types dans ce type.
 */
public class Value {

    final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public Integer asInteger() {
        return (Integer) value;
    }

    public String asString() {
        return String.valueOf(value);
    }

    public Abstraction asAbstraction() {
        return ((Abstraction) value);
    }

    public RecFunction asRec() {
        return (RecFunction) value;
    }

    public IfStat asIfStat() {
        return (IfStat) value;
    }

    public Calcul asCalcul() {
        return (Calcul) value;
    }

    public boolean isInteger() {
        return value instanceof Integer;
    }

    public boolean isAbstraction() {
        return value instanceof Abstraction;
    }

    public boolean isVariable() {
        return value instanceof Variable;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isRec() {
        return value instanceof RecFunction;
    }

    public boolean isCalcul() {
        return value instanceof Calcul;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return Objects.equals(value, value1.value);
    }


    @Override
    public int hashCode() {

        if (value == null) {
            return 0;
        }

        return this.value.hashCode();
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }


    /**
     * Cette méthode est la pour vérifier si la boucle du parser/lexer doit s'areter (dans le cas ou on a une abstraction ou
     * une valeur)
     *
     * @return <code>true</code> si on s'arrête, <code>false</code> si l'on doit continuer.
     */
    public boolean checkContinue() {
        return !(this.isAbstraction() || this.isVariable() || this.isInteger());
    }

}
