package expression;

import java.util.Objects;

public class BitwiseNot extends UnaryOperation {

    public BitwiseNot(MyExpression a) {
        super(a);
    }

    @Override
    protected String getSymbol() {
        return "~";
    }

    @Override
    protected int operation(int x) {
        return ~x;
    }
}
