package Game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    private final int m, n, k;
    private int prevLongComb;
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    public TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.prevLongComb = 0;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        int longComb = 0;
        int empty = 0;
        int w = Math.max(m, n);
        for (int i = 0; i < w; i++) {
            int inLine = 0;
            int inColumn = 0;
            int di = i;
            for (int j = 0; j < w; j++) {
                if (i < m && j < n && cells[i][j] == turn) {
                    inLine++;
                    if (inLine >= 4) {
                        longComb++;
                    }
                    int djR = j;
                    int djL = j;
                    int inDiagR = 1;
                    int inDiagL = 1;
                    for (int d = 0; d < k; d++) {
                        di++;
                        djR++;
                        djL--;
                        if (di < m && djR < n && cells[di][djR] == turn) {
                            inDiagR++;
                        } else {
                            inDiagR = 0;
                        }
                        if (di < m && djL >= 0 && cells[di][djL] == turn) {
                            inDiagL++;
                        } else {
                            inDiagL = 0;
                        }

                        if (inDiagR == k || inDiagL == k) {
                            return Result.WIN;
                        }
                    }
                    if (inDiagR >= 4) {
                        longComb++;
                    }
                    if (inDiagL >= 4) {
                        longComb++;
                    }
                } else {
                    inLine = 0;
                }
                if (j < m && i < n && cells[j][i] == turn) {
                    inColumn++;
                    if (inColumn >= 4) {
                        longComb++;
                    }
                } else {
                    inColumn = 0;
                }
                if (i < m && j < n && cells[i][j] == Cell.E) {
                    empty++;
                }

                if (inLine >= k || inColumn >= k) {
                    return Result.WIN;
                }
            }
        }
        if (empty == 0) {
            return Result.DRAW;
        } else if (longComb > prevLongComb) {
            return Result.BONUS_MOVE;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        for (int c = 0; c < n; c++) {
            sb.append(c);
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
