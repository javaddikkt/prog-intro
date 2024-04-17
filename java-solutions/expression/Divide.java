package expression;

public class Divide extends BinaryOperation{
    public Divide(MyExpression a, MyExpression b) {
        super(a, b);
    }

    protected String getSymbol() {
        return " / ";
    }

    protected int operation(int a, int b) {
        return a / b;
    }
}
