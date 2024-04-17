package Game;

import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private Scanner in;
    private final int no;

    public HumanPlayer(final PrintStream out, final Scanner in, int no) {
        this.out = out;
        this.in = in;
        this.no = no;
    }

    public HumanPlayer(int no) {
        this(System.out, new Scanner(System.in), no);
    }

    @Override
    public Move move(final Position position, final Cell cell) throws NoSuchElementException{
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column (in one line)");
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            try {
                String row = lineScanner.next();
                String column = lineScanner.next();
                try {
                    final Move move = new Move(row, column, cell);
                    if (position.isValid(move)) {
                        return move;
                    }
                    out.println("Move " + move + " is invalid (not by the rules)");
                } catch (NumberFormatException e) {
                    out.println("Move " + row + " " + column + " is invalid (use numbers, not words)");
                }
            } catch (NoSuchElementException e) {
                System.out.println("IN ONE LINE!");
            }
        }
    }

    @Override
    public int getNo() {
        return no;
    }
}
