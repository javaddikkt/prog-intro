package Game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;
    private final int m, n, no;

    public RandomPlayer(final Random random, int m, int n, int no) {
        this.random = random;
        this.m = m;
        this.n = n;
        this.no = no;
    }

    public RandomPlayer(int m, int n, int no) {
        this(new Random(), m, n, no);
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            int r = random.nextInt(m + 100);
            int c = random.nextInt(n + 100);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }

    @Override
    public int getNo() {
        return no;
    }
}
