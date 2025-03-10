public class GameOfLife implements Board {

    private int[][] board;

    public GameOfLife(int x, int y) {
        board = new int[x][y];
    }

    public void set(int x, int y, int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (x + i < board.length && y + j < board[0].length) {
                    board[x + i][y + j] = data[i][j];
                }
            }
        }
    }
    

    public void run(int turns) {
        for (int i = 0; i < turns; i++) {
            step();
        }
    }

    public void step() {
        int[][] nextBoard = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int liveNeighbors = countNeighbors(i, j);
                if (board[i][j] == 1) {
                    nextBoard[i][j] = (liveNeighbors == 2 || liveNeighbors == 3) ? 1 : 0;
                } else {
                    nextBoard[i][j] = (liveNeighbors == 3) ? 1 : 0;
                }
            }
        }

        board = nextBoard;
        print();
    }

    public int countNeighbors(int x, int y) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int neighborX = (x + i + board.length) % board.length;
                int neighborY = (y + j + board[0].length) % board[0].length;

                if (get(neighborX, neighborY) == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public int get(int x, int y) {
        return board[(x + board.length) % board.length][(y + board[0].length) % board[0].length];
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
                if (board[x][y] == 1) {
                    System.out.print("⬛");
                } else {
                    System.out.print("⬜");
                }
            }
        }
        System.out.println();
    }
}
