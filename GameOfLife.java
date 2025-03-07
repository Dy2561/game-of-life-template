import java.util.Arrays;

public class GameOfLife implements Board {

    private int[][] board;

    public GameOfLife(int x, int y) {
        board = new int[x][y]; 
    }

    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    
}
