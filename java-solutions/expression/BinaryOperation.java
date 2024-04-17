package expression;

import expression.exceptions.NumberOutOfRangeException;

import java.util.Objects;

public abstract class BinaryOperation implements MyExpression {
    private final MyExpression a, b;
    public BinaryOperation(MyExpression a, MyExpression b) {
        this.a = a;
        this.b = b;
    }

    protected abstract String getSymbol();
    protected abstract int operation(int a, int b);

    public int evaluate(int x) {
        return operation(a.evaluate(x), b.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return operation(a.evaluate(x, y, z), b.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + a + getSymbol() + b + ")";
    }

    public boolean equals(Object exp) {
        if (exp != null && this.getClass() == exp.getClass()) {
            BinaryOperation binExp = (BinaryOperation) exp;
            return Objects.equals(a, binExp.a) && Objects.equals(b, binExp.b);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 123*a.hashCode() + 456*b.hashCode() + 789*getSymbol().hashCode();
    }

}
