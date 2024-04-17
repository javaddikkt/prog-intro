package expression;

public class Pow extends UnaryOperation{
    public Pow(MyExpression a) {
        super(a);
    }

    @Override
    protected String getSymbol() {
        return "pow2";
    }

    @Override
    protected int operation(int a) {
        return (int) Math.pow(2, a);
    }
}
