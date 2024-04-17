package expression;

public class Const implements MyExpression {
    private final int cons;
    public Const(int cons) {
        this.cons = cons;
    }

    public int evaluate(int x) {
        return cons;
    }

    public int evaluate(int x, int y, int z) {
        return cons;
    }

    public String toString() {
        return String.valueOf(cons);
    }

    @Override
    public int hashCode() {
        return cons;
    }

    public boolean equals(Object b) {
        if (b != null && this.getClass() == b.getClass()) {
            return cons == ((Const) b).cons;
        }
        return false;
    }
}
