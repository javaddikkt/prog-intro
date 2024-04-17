package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {
    private int start;
    private String line;
    @Override
    public TripleExpression parse(String expression) {
        start = 0;
        line = expression;
        return findBitwiseOr();
    }

    private MyExpression findBitwiseOr() {
        MyExpression exp = findBitwiseXor();
        char c = nextToken();
        while (c == '|') {
            exp = new BitwiseOr(exp, findBitwiseXor());
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findBitwiseXor() {
        MyExpression exp = findBitwiseAnd();
        char c = nextToken();
        while (c == '^') {
            exp = new BitwiseXor(exp, findBitwiseAnd());
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findBitwiseAnd() {
        MyExpression exp = findSumSub();
        char c = nextToken();
        while (c == '&') {
            exp = new BitwiseAnd(exp, findSumSub());
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findSumSub() {
        MyExpression exp = findMulDiv();
        char c = nextToken();
        while (c == '+' || c == '-') {
            if (c == '+') {
                exp = new Add(exp, findMulDiv());
            } else {
                exp = new Subtract(exp, findMulDiv());
            }
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findMulDiv() {
        MyExpression exp = findElement();
        char c = nextToken();
        while (c == '*' || c == '/') {
            if (c == '*') {
                exp = new Multiply(exp, findElement());
            } else {
                exp = new Divide(exp, findElement());
            }
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findElement() {
        char c = nextToken();
        if (c == '(') {
            MyExpression exp = findBitwiseOr();
            if (nextToken() == ')') {
                nextToken();
            }
            start--;
            return exp;
        } else if (c == '-') {
            c = nextToken();
            if (c >= 48 && c <= 57) {
                start--;
                int number = parseNumber(true);
                return new Const(number);
            } else {
                start--;
                return new Negate(findElement());
            }
        } else if (c == '~') {
            return new BitwiseNot(findElement());
        } else if (c >= 48 && c <= 57) {
            start--;
            int number = parseNumber(false);
            return new Const(number);
        }
        return new Variable(String.valueOf(c));
    }

    private char nextToken() {
        char c;
        boolean notNull = false;
        while (start < line.length()) {
            c  = line.charAt(start);
            if (Character.isWhitespace(c)) {
                start++;
            } else {
                notNull = true;
                break;
            }
        }
        if (start == line.length() && !notNull) {
            return '#';
        }
        return line.charAt(start++);
    }

    private int parseNumber(boolean isNegative) {
        char c = nextToken();
        StringBuilder sb = new StringBuilder(String.valueOf(c));
        while (true) {
            c = nextToken();
            if (c >= 48 && c <= 57) {
                sb.append(c);
            } else if (c == '#') {
                if (isNegative) {
                    return Integer.parseInt("-" + sb);
                }
                return Integer.parseInt(String.valueOf(sb));
            } else {
                start--;
                if (isNegative) {
                    return Integer.parseInt("-" + sb);
                }
                return Integer.parseInt(String.valueOf(sb));
            }
        }
    }
}
