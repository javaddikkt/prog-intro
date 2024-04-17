package Game;

import java.util.NoSuchElementException;

public class Game {
    private final boolean log;
    private final Player player1, player2;

    public Game(final boolean log, final Player player1, final Player player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(Board board) {
        while (true) {
            while (true) {
                int result1 = move(board, player1, 1);
                if (result1 == -2) {
                    continue;
                }
                if (result1 != -1) {
                    return result1;
                }
                break;
            }
            while (true) {
                int result2 = move(board, player2, 2);
                if (result2 == -2) {
                    continue;
                }
                if (result2 != -1) {
                    return result2;
                }
                break;
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        Result result;
        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            result = board.makeMove(move);
            log("Player " + no + " move: " + move);
            log("Position:\n" + board);
        } catch (NoSuchElementException e) {
            result = Result.LOSE;
        }

        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.BONUS_MOVE) {
            log("Player " + no + " has bonus move");
            return -2;
        }
        else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
