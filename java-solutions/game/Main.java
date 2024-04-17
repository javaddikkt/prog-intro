package Game;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            TournamentGame tournament = initialize();
            int numberOfPlayers = tournament.getNumberOfplayers();
            Map<Integer, Integer> results = tournament.play();
            int numberOfRounds = results.get(-1);
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Player " + (i + 1) + " : place " + (numberOfRounds - results.get(i) + 1));
            }
        } catch (NoSuchElementException e) {
            System.out.println("Initialization was stopped");
        }
    }
    // Обработать ^D, mnk или число игроков - буквы, во вводе > 2 чисел
    public static TournamentGame initialize() throws NoSuchElementException{
        Scanner scanner = new Scanner(System.in);
        int m, n, k;
        Scanner lineScanner;
        while (true) {
            System.out.println("Enter m, n and k (in one line)");
            String line = scanner.nextLine();
            lineScanner = new Scanner(line);
            try {
                m = Integer.parseInt(lineScanner.next());
                n = Integer.parseInt(lineScanner.next());
                k = Integer.parseInt(lineScanner.next());
                if ((m >= k || n >= k) && m > 0 && n > 0 && k > 0) {
                    break;
                }
                System.out.println("mnk are illegal");
            } catch (NumberFormatException e) {
                System.out.println("mnk are not numbers");
            } catch (NoSuchElementException e) {
                System.out.println("IN ONE LINE!");
            }
        }
        int c;
        while (true) {
            System.out.println("Enter number of players");
            String line = scanner.nextLine();
            lineScanner = new Scanner(line);
            try {
                c = Integer.parseInt(lineScanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("This is not a number");
            }
        }
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            players.add(new HumanPlayer(i));
        }


        return new TournamentGame(true, m, n, k, players);
    }
}
