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

    @Override
    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (i + x < board.length && j + y < board[0].length) {
                    board[i + x][j + y] = data[i][j];
                }
            }
        }
    }

    public void step() {
        int[][] newBoard = new int[board.length][board[0].length];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                int neighbors = countNeighbors(x, y);

                if (board[x][y] == 1) {
                    newBoard[x][y] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    newBoard[x][y] = (neighbors == 3) ? 1 : 0;
                }
            }
        }

        board = newBoard;
        print();
    }

    public int countNeighbors(int x, int y) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length) {
                count += board[nx][ny];
            }
        }
        return count;
    }

    public int get(int x, int y) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
            return board[x][y];
        }
        return 0;
    }

    public int[][] get() {
        return board;
    }

    public void print() {
        System.out.print("\n ");
        for (int y = 0; y < board[0].length; y++) {
            System.out.print(y % 10 + " ");
        }

        for (int x = 0; x < board.length; x++) {
            System.out.print("\n" + x % 10);
            for (int y = 0; y < board[x].length; y++) {
                System.out.print(board[x][y] == 1 ? "⬛" : "⬜");
            }
        }
        System.out.println();
    } 
}