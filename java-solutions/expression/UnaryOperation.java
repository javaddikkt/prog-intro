package expression;

import java.util.Objects;

public abstract class UnaryOperation implements MyExpression{
    private final MyExpression a;

    public UnaryOperation(MyExpression a) {
        this.a = a;
    }
    protected abstract String getSymbol();
    protected abstract int operation(int x);

    public int evaluate(int x) {
        return operation(a.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return operation(a.evaluate(x, y, z));
    }

    public String toString() {
        return getSymbol() + "(" + a + ")";
    }

    @Override
    public boolean equals(Object exp) {
        if (exp != null && this.getClass() == exp.getClass()) {
            UnaryOperation binExp = (UnaryOperation) exp;
            return Objects.equals(a, binExp.a);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 123 * a.hashCode() + 456 * getSymbol().hashCode();
    }
}
