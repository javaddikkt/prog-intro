package expression;

public class BitwiseXor extends BinaryOperation{
    public BitwiseXor(MyExpression a, MyExpression b) {
        super(a, b);
    }

    @Override
    protected String getSymbol() {
        return " ^ ";
    }

    @Override
    protected int operation(int a, int b) {
        return a ^ b;
    }
}
