package expression.exceptions;

public class NoParenthesisException extends IllegalExpressionCheckableException {
    public NoParenthesisException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Missing " + message + " parenthesis";
    }
}
