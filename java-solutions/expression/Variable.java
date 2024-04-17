package expression;

import java.util.Objects;

public class Variable implements MyExpression {
    private final String x;
    public Variable(String x) {
        this.x = x;
    }

    public int evaluate(int x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        if (this.x.equals("x")) {
            return x;
        }
        if (this.x.equals("y")) {
            return y;
        }
        return z;
    }

    public String toString() {
        return x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    public boolean equals(Object b) {
        if (b != null && this.getClass() == b.getClass()) {
            return Objects.equals(x, ((Variable) b).x);
        }
        return false;
    }
}
