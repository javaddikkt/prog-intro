package expression.exceptions;

public class NoArgumentException extends IllegalExpressionCheckableException {
    public NoArgumentException(int position) {
        super(position);
    }

    @Override
    public String getMessage() {
        return "Missing argument at position " + position;
    }
}
