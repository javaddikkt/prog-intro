package expression.exceptions;

import expression.Add;
import expression.MyExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(MyExpression a, MyExpression b) {
        super(a, b);
    }

    @Override
    protected int operation(int a, int b) {
        //a + b > max
        //a + b < min
        if ((b > 0 && Integer.MAX_VALUE - b < a) || (b < 0 && Integer.MIN_VALUE - b > a)) {
            throw new NumberOutOfRangeException("Sum overflow");
        } else {
            return a + b;
        }
    }
}
