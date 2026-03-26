package game.snakeladder;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int size;
    private Map<Integer, Jump> jumps;

    public Board(int size) {
        this.size = size;
        this.jumps = new HashMap<>();
    }

    public void addJump(int start, int end) {
        jumps.put(start, new Jump(start, end));
    }

    public int getFinalPosition(int position) {
        if (jumps.containsKey(position)) {
            Jump jump = jumps.get(position);
            if (jump.getStart() < jump.getEnd()) {
                System.out.println("Climbed ladder to " + jump.getEnd());
            } else {
                System.out.println("Bitten by snake to " + jump.getEnd());
            }
            return jump.getEnd();
        }
        return position;
    }

    public int getSize() {
        return size;
    }
}
