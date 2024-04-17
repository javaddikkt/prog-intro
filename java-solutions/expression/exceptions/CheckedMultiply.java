package expression.exceptions;

import expression.Multiply;
import expression.MyExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(MyExpression a, MyExpression b) {
        super(a, b);
    }

    @Override
    protected int operation(int a, int b) {
        // a * b > max
        // a * b < min
        int mul = a * b;
        if ((b == Integer.MIN_VALUE && a == -1) || (a == Integer.MIN_VALUE && b == -1) ||
                (a > 0 && (b > Integer.MAX_VALUE / a || b < Integer.MIN_VALUE / a)) ||
                (a < -1 && (b > Integer.MIN_VALUE / a || b < Integer.MAX_VALUE / a))) {
            throw new NumberOutOfRangeException("multiply");
        }
        return a * b;
    }
}
