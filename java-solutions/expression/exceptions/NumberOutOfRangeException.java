package expression.exceptions;

public class NumberOutOfRangeException extends IllegalExpressionUncheckableException {
    public NumberOutOfRangeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Overflow in " + message;
    }
}
