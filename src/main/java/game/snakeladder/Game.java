package game.snakeladder;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        board = new Board(100);
        dice = new Dice();
        players = new ArrayList<>();

        initPlayers();
        initBoard();
    }

    private void initPlayers() {
        System.out.print("Enter number of players: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter player " + i + " name: ");
            players.add(new Player(scanner.nextLine()));
        }
    }

    private void initBoard() {
        // Ladders
        board.addJump(2, 38);
        board.addJump(7, 14);
        board.addJump(8, 31);

        // Snakes
        board.addJump(16, 6);
        board.addJump(49, 11);
        board.addJump(62, 19);
    }

    public void start() {
        boolean gameEnded = false;

        while (!gameEnded) {
            for (Player player : players) {

                System.out.println("\n" + player.getName() + "'s turn. Press Enter...");
                scanner.nextLine();

                int diceValue = dice.roll();
                System.out.println("Dice: " + diceValue);

                int newPos = player.getPosition() + diceValue;

                if (newPos > board.getSize()) {
                    System.out.println("Move skipped.");
                    continue;
                }

                newPos = board.getFinalPosition(newPos);
                player.setPosition(newPos);

                System.out.println(player.getName() + " at " + newPos);

                if (newPos == board.getSize()) {
                    System.out.println("🎉 " + player.getName() + " wins!");
                    gameEnded = true;
                    break;
                }
            }
        }
    }
}
