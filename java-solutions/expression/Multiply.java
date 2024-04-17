package expression;

public class Multiply extends BinaryOperation {
    public Multiply(MyExpression a, MyExpression b) {
        super(a, b);
    }

    protected String getSymbol() {
        return " * ";
    }

    protected int operation(int a, int b) {
        return a * b;
    }
}
