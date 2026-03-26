package game.snakeladder;
import java.util.Random;

public class Dice {
    private Random random;

    public Dice() {
        this.random = new Random(); // Java 21 compatible
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}