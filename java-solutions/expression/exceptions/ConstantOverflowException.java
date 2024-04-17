package expression.exceptions;

public class ConstantOverflowException extends IllegalExpressionCheckableException {
    public ConstantOverflowException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Constant overflow in: " + message;
    }
}
