package expression.exceptions;

public class IllegalSymbolException extends IllegalExpressionCheckableException {
    public IllegalSymbolException(int position) {
        super(position);
    }
    @Override
    public String getMessage() {
        return "Illegal symbol at position " + position;
    }
}
