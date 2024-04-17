package expression;

public class Add extends BinaryOperation{
    public Add(MyExpression a, MyExpression b) {
        super(a, b);
    }

    protected String getSymbol() {
        return " + ";
    }

    @Override
    protected int operation(int a, int b) {
        return a + b;
    }

}
