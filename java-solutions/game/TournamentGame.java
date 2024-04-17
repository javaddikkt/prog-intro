package Game;

import java.util.*;

public class TournamentGame {
    private List<Player> players;
    private final int m, n, k;
    private int roundNo = 0;
    private final boolean log;
    private Map<Integer, Integer> results = new HashMap<>();
    public TournamentGame (boolean log, int m, int n, int k, List<Player> players) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.players = players;
        this.log = log;
    }

    public Map<Integer, Integer> play() {
        roundNo++;
        if (players.size() > 1) {
            log("------");
            log("Round " + roundNo);
            List<Player> winPlayers = new ArrayList<>();
            for (int i = 0; i < players.size() - 1; i += 2) {
                log("\nGame " + (i/2 + 1));
                while (true) {
                    Game game = new Game(log, players.get(i), players.get(i + 1));
                    int winner = game.play(new TicTacToeBoard(m, n, k));
                    if (winner != 0) {
                        winPlayers.add(players.get(i + winner - 1));
                        results.put(players.get(i + (3 - winner) - 1).getNo(), roundNo);
                        break;
                    }
                }
            }
            if (players.size() % 2 == 1) {
                winPlayers.add(players.get(players.size() - 1));
            }
            players = winPlayers;
            return play();
        }
        results.put(players.get(0).getNo(), roundNo);
        results.put(-1, roundNo);
        return results;
    }

    public void log(String message) {
        if (log) {
            System.out.println(message);
        }
    }

    public int getNumberOfplayers() {
        return players.size();
    }
}
