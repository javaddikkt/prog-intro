package expression.exceptions;

public class NoOperatorException extends IllegalExpressionCheckableException {
    public NoOperatorException(int position) {
        super(position);
    }

    @Override
    public String getMessage() {
        return "Missing operator at position " + position;
    }
}
