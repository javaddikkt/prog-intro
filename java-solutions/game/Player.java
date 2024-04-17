package Game;

public interface Player {
    Move move(Position position, Cell cell);
    int getNo();
}
