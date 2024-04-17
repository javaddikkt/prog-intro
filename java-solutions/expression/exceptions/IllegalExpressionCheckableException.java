package expression.exceptions;

public abstract class IllegalExpressionCheckableException extends Exception {
    protected final int position;
    protected final String message;

    public IllegalExpressionCheckableException(int position) {
        this.position = position;
        message = null;
    }

    public IllegalExpressionCheckableException(String message) {
        this.position = -1;
        this.message = message;
    }
}
