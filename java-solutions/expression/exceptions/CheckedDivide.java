package expression.exceptions;

import expression.Divide;
import expression.MyExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(MyExpression a, MyExpression b) {
        super(a, b);
    }

    @Override
    protected int operation(int a, int b)  {
        // a / b > max
        // a / b < min
        // b == 0
        if (b == 0) {
            throw new CalculationException("division");
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new NumberOutOfRangeException("division");
        }
        return a / b;
    }
}
