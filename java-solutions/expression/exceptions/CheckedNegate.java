package expression.exceptions;

import expression.MyExpression;
import expression.Negate;

public class CheckedNegate extends Negate {
    public CheckedNegate(MyExpression a) {
        super(a);
    }

    @Override
    public int operation(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new NumberOutOfRangeException("negate");
        }
        return -1 * x;
    }
}
