package expression;

public class Negate extends UnaryOperation{
    public Negate(MyExpression a) {
        super(a);
    }

    @Override
    protected String getSymbol() {
        return "-";
    }

    @Override
    protected int operation(int x) {
        return -1 * x;
    }
}
