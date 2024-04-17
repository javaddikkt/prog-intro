package expression.exceptions;

import expression.*;

import java.util.List;

public class ExpressionParser implements TripleParser {
    private int start;
    private String line;

    @Override
    public TripleExpression parse(String expression) throws IllegalExpressionCheckableException {
        start = 0;
        line = expression;
        int balance = checkParenthesis();
        if (balance == 0) {
            return findBitwiseOr();
        } else if (balance == 1) {
            throw new NoParenthesisException("closing");
        } else {
            throw new NoParenthesisException("opening");
        }
    }

    private MyExpression findBitwiseOr() throws IllegalExpressionCheckableException {
        MyExpression exp = findBitwiseXor();
        char c = nextToken();
        if (!isLegal(c)) {
            throw new IllegalSymbolException(start - 1);
        }
        while (c == '|') {
            exp = new BitwiseOr(exp, findBitwiseXor());
            c = nextToken();

        }
        if ((Character.isLetter(c) || Character.isDigit(c) || c == '(') && start < line.length()) {
            throw new NoOperatorException(start - 1);
        }
        start--;
        return exp;
    }

    private MyExpression findBitwiseXor() throws IllegalExpressionCheckableException {
        MyExpression exp = findBitwiseAnd();
        char c = nextToken();
        while (c == '^') {
            exp = new BitwiseXor(exp, findBitwiseAnd());
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findBitwiseAnd() throws IllegalExpressionCheckableException {
        MyExpression exp = findSumSub();
        char c = nextToken();
        while (c == '&') {
            exp = new BitwiseAnd(exp, findSumSub());
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findSumSub() throws IllegalExpressionCheckableException {
        MyExpression exp = findMulDiv();
        char c = nextToken();
        while (c == '+' || c == '-') {
            if (c == '+') {
                exp = new CheckedAdd(exp, findMulDiv());
            } else {
                exp = new CheckedSubtract(exp, findMulDiv());
            }
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findMulDiv() throws IllegalExpressionCheckableException {
        MyExpression exp = findElement();
        char c = nextToken();
        while (c == '*' || c == '/') {
            if (c == '*') {
                exp = new CheckedMultiply(exp, findElement());
            } else {
                exp = new CheckedDivide(exp, findElement());
            }
            c = nextToken();
        }
        start--;
        return exp;
    }

    private MyExpression findElement() throws IllegalExpressionCheckableException {
        char c = nextToken();
        //System.out.println(c);
        //System.out.println(start);
        if (c == '(') {
            MyExpression exp = findBitwiseOr();
            if (nextToken() == ')') {
                nextToken();
            }
            start--;
            return exp;
        } else if (c == '-') {
            c = nextToken();
            if (Character.isDigit(c)) {
                start--;
                int number = parseNumber(true);
                return new Const(number);
            } else if (c == '#') {
                throw new NoArgumentException(line.length());
            } else {
                start--;
                return new CheckedNegate(findElement());
            }
        } else if (c == '~') {
            return new BitwiseNot(findElement());
        } else if (c == 'l') {
            start--;
            if (findLogPow("log2")) {
                return new CheckedLog(findElement());
            } else {
                throw new IllegalSymbolException(start);
            }
        } else if (c == 'p') {
            start--;
            if (findLogPow("pow2")) {
                return new CheckedPow(findElement());
            } else {
                throw new IllegalSymbolException(start);
            }
        } else if (Character.isDigit(c)) {
            start--;
            int number = parseNumber(false);
            return new Const(number);
        } else if (c == 'x' || c == 'y' || c == 'z') {
            return new Variable(String.valueOf(c));
        } else if (isLegal(c)) {
            throw new NoArgumentException(Math.max(start - 2, 0));
        }
        throw new IllegalSymbolException(start - 1);
    }

    private char nextToken() {
        char c;
        boolean notNull = false;
        while (start < line.length()) {
            c = line.charAt(start);
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

    private int parseNumber(boolean isNegative) throws IllegalExpressionCheckableException {
        char c = nextToken();
        String stringNumber;
        StringBuilder sb = new StringBuilder(String.valueOf(c));
        while (true) {
            c = nextToken();
            if (Character.isDigit(c)) {
                if (start - 2 >= 0 && Character.isWhitespace(line.charAt(start - 2))) {
                    throw new NoOperatorException(start - 2);
                }
                sb.append(c);
                continue;
            } else if (c == '#') {
                if (isNegative) {
                    stringNumber = "-" + sb;
                } else {
                    stringNumber = sb.toString();
                }
            } else {
                start--;
                if (isNegative) {
                    stringNumber = "-" + sb;
                } else {
                    stringNumber = sb.toString();
                }
            }
            try {
                return Integer.parseInt(stringNumber);
            } catch (NumberFormatException e) {
                throw new ConstantOverflowException(stringNumber);
            }
        }
    }

    private int checkParenthesis() {
        int balance = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                balance++;
            } else if (line.charAt(i) == ')') {
                balance--;
            }
            if (balance < 0) {
                return -1;
            }
        }
        if (balance > 0) {
            return 1;
        }
        return 0;
    }

    private boolean findLogPow(String toFind) throws IllegalExpressionCheckableException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (start + i - 1 >= line.length()) {
                throw new IllegalSymbolException(start + i);
            }
            sb.append(line.charAt(start + i));
        }
        if (sb.toString().equals(toFind)) {
            if (start + 4 >= line.length()) {
                throw new NoArgumentException(start + 4);
            }
            if (Character.isWhitespace(line.charAt(start + 4)) || line.charAt(start + 4) == '(') {
                start += 4;
                return true;
            }
            throw new IllegalSymbolException(start + 4);
        }
        return false;
    }

    private boolean isLegal(char c) {
        return c == '(' || c == ')' || c == 'x' || c == 'y' || c == 'z'
                || Character.isDigit(c) || c == '-' || c == '+' || c == '*'
                || c == '/' || c == '~' || c == '&' || c == '|' || c == '^'
                || (c == '#' && start >= line.length());
    }
}