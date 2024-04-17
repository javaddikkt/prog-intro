package expression.exceptions;

import expression.MyExpression;
import expression.Subtract;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(MyExpression a, MyExpression b) {
        super(a, b);
    }

    @Override
    protected int operation(int a, int b) {
        // a - b < min  a < min + b
        // a - b > max  a > max + b
        if ((b > 0 && a < Integer.MIN_VALUE + b) || (b < 0 && a > Integer.MAX_VALUE + b)) {
            throw new NumberOutOfRangeException("subtract");
        }
        return a - b;
    }
}
