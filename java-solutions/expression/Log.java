package expression;

public class Log extends UnaryOperation {
    public Log(MyExpression a) {
        super(a);
    }

    @Override
    protected String getSymbol() {
        return "log2";
    }

    @Override
    protected int operation(int x) {
        return (int) Math.floor(Math.log(x) / Math.log(2));
    }
}
