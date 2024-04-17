package expression.exceptions;

public abstract class IllegalExpressionUncheckableException extends RuntimeException{
    protected final String message;

    public IllegalExpressionUncheckableException(String message) {
        this.message = message;
    }

}
