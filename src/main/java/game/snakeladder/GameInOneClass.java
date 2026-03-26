package game.snakeladder;
import java.util.*;

public class GameInOneClass {
    //main method
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

// Game Controller
//runs the game,
class Game {
    private Board board;
    private List<Player> players;
    private Dice dice;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        board = new Board(100);
        dice = new Dice();
        players = new ArrayList<>();

        initializePlayers();
        initializeSnakesAndLadders();
    }

    private void initializePlayers() {
        System.out.print("Enter number of players: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter player " + i + " name: ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
    }

    private void initializeSnakesAndLadders() {
        // Ladders
        board.addLadder(2, 38);
        board.addLadder(7, 14);
        board.addLadder(8, 31);
        board.addLadder(15, 26);

        // Snakes
        board.addSnake(16, 6);
        board.addSnake(49, 11);
        board.addSnake(62, 19);
        board.addSnake(87, 24);
    }

    public void start() {
        boolean gameEnded = false;

        while (!gameEnded) {
            for (Player player : players) {

                System.out.println("\n" + player.getName() + "'s turn. Press Enter to roll dice...");
                scanner.nextLine();

                int diceValue = dice.roll();
                System.out.println("Dice rolled: " + diceValue);

                int newPosition = player.getPosition() + diceValue;

                if (newPosition > board.getSize()) {
                    System.out.println("Move exceeds board size. Stay at " + player.getPosition());
                    continue;
                }

                newPosition = board.checkSnakeOrLadder(newPosition);

                player.setPosition(newPosition);

                System.out.println(player.getName() + " moved to " + newPosition);

                if (newPosition == board.getSize()) {
                    System.out.println(player.getName() + " wins!");
                    gameEnded = true;
                    break;
                }
            }
        }
    }
}

// Board Class
// Add snake and ladder with hash map and positions
class Board {
    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public Board(int size) {
        this.size = size;
        snakes = new HashMap<>();
        ladders = new HashMap<>();
    }

    public void addSnake(int start, int end) {
        snakes.put(start, end);
    }

    public void addLadder(int start, int end) {
        ladders.put(start, end);
    }

    public int checkSnakeOrLadder(int position) {
        if (snakes.containsKey(position)) {
            System.out.println("Bitten by snake! Go down to " + snakes.get(position));
            return snakes.get(position);
        }
        if (ladders.containsKey(position)) {
            System.out.println("Climbed a ladder! Go up to " + ladders.get(position));
            return ladders.get(position);
        }
        return position;
    }

    public int getSize() {
        return size;
    }
}

// Player Class
//player and position
class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

// Dice Class
//generate random number between 1 to 6
class Dice {
    private Random random;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}
