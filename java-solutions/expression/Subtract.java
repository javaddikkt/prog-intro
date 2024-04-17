package expression;

public class Subtract extends BinaryOperation {
    public Subtract(MyExpression a, MyExpression b) {
        super(a, b);
    }

    protected String getSymbol() {
        return " - ";
    }

    protected int operation(int a, int b) {
        return a - b;
    }
}
