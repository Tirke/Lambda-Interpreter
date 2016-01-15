import java.util.Objects;

/**
 * La classe RecFunc est la classe qui représente une fonction recursive.
 */
public class RecFunction {

    private Variable funcNameVar;
    private Variable applyToVar;
    private Value function;

    public RecFunction(Variable funcNameVar, Variable applyToVar, Value function) {
        this.funcNameVar = funcNameVar;
        this.applyToVar = applyToVar;
        this.function = function;
    }

    public Variable getFuncNameVar() {
        return funcNameVar;
    }

    public Variable getApplyToVar() {
        return applyToVar;
    }

    public Value getFunction() {
        return function;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecFunction that = (RecFunction) o;
        return Objects.equals(getFuncNameVar(), that.getFuncNameVar()) &&
                Objects.equals(getApplyToVar(), that.getApplyToVar()) &&
                Objects.equals(getFunction(), that.getFunction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFuncNameVar(), getApplyToVar(), getFunction());
    }


    @Override
    public String toString() {
        return "(rec " + funcNameVar + " " + applyToVar + "." + function + ")";
    }
}
