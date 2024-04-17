package expression.exceptions;

import expression.Log;
import expression.MyExpression;

public class CheckedLog extends Log {
    public CheckedLog(MyExpression a) {
        super(a);
    }

    @Override
    public int operation(int x) {
        if (x <= 0) {
            throw new CalculationException("log");
        }
        return log(x);
    }

    private int log(int x) {
        int res = 0;
        while (x >= 2) {
            x /= 2;
            res++;
        }
        return res;
    }
}
