import java.util.Objects;

/**
 * Classe "type" pour les variables.
 */
public class Variable {

    private String id;

    public Variable(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(getId(), variable.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}
