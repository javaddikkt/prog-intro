package expression.exceptions;

public class CalculationException extends IllegalExpressionUncheckableException {
    public CalculationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Calculation error in " + message;
    }
}
