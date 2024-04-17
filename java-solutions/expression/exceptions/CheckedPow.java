package expression.exceptions;

import expression.MyExpression;
import expression.Pow;

public class CheckedPow extends Pow {
    public CheckedPow(MyExpression a) {
        super(a);
    }

    @Override
    public int operation(int x) {
        if (x >= 31) {
            throw new CalculationException("pow");
        }
        if (x < 0) {
            throw new CalculationException("pow");
        }
        return pow(x);
    }

    private int pow(int x) {
        int res = 1;
        for (int i = 0; i < x; i++) {
            res *= 2;
        }
        return res;
    }
}
