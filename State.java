// of Reversi.  The state is represented by a 2D array 
// of integers.  The integers represent the tiles on the
// board.  The integers are 0 for empty, 1 for black,
// and 2 for white.  The board is 8x8. 

import java.util.Arrays;

public class State {
    // The board is represented by a 2D array of integers.
    private int[][] board;
    // turn is 1 for black and 2 for white.
    private Player turn;

    /**
     * initializes starting board given a size
     * 
     * @param boardsize size of board
     */
    public State(int boardSize) {
        this.board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = 0;
            }
        }

        // sets initial pos
        int middleOfB = boardSize / 2;
        board[middleOfB][middleOfB] = 1;
        board[middleOfB][middleOfB - 1] = 2;
        board[middleOfB - 1][middleOfB] = 2;
        board[middleOfB - 1][middleOfB - 1] = 1;

        // set turn to white first
        turn = new Player(1);
    }

    /**
     * State constructor that takes in a board and a turn
     * 
     * @param pos
     * @param p
     */
    public State(int[][] pos, Player p) {
        int[][] copyBoard = new int[pos.length][pos[0].length];
        for (int i = 0; i < pos.length; i++)
            copyBoard[i] = Arrays.copyOf(pos[i], pos.length);
        board = copyBoard;
        turn = p;
    }

    /**
     * returns the board
     * 
     * @return
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * returns the tile at a given position
     * 
     * @param row
     * @param col
     * @return
     */
    public int getBoardAt(int row, int col) {
        if (row > 0 && col > 0 && row < board.length && col < board[0].length)
            return board[row][col];
        return -1;
    }

    /**
     * sets the tile at a given position
     * 
     * @param row
     * @param col
     * @param p
     */
    public void setBoardAt(int row, int col, Player p) {
        if (row > 0 && col > 0 && row < board.length && col < board[0].length)
            board[row][col] = p.getNumber();
        else
            System.out.println("You fucked up");
    }

    /**
     * 
     * @param pos
     */
    public void setBoard(int[][] pos) {
        board = pos;
    }

    public Player getPlayer() {
        return turn;
    }

    public Player getOpponent() {
        if(turn.getNumber() == 1) 
            return new Player(0);
        else return new Player(1);    
    }

    public void setPlayer(Player p) {
        turn = p;
    }

    /**
     * 
     * @param row
     * @param col
     * @return
     * 
     */
    public boolean isLegalMove(int row, int col, Player player) {

        if (board[row][col] != 0)
            return false;

        if (col + 1 < board[row].length && board[row][col + 1] != 0 && board[row][col + 1] != player.getNumber()) {
            for (int i = col + 1; i < board[row].length && board[row][i] != 0; i++) {
                if (board[row][i] == player.getNumber())
                    return true;
            }
        }

        if (col - 1 >= 0 && board[row][col - 1] != 0 && board[row][col - 1] != player.getNumber()) {
            for (int i = col - 1; i >= 0 && board[row][i] != 0; i--) {
                if (board[row][i] == player.getNumber())
                    return true;
            }
        }

        if (row - 1 >= 0 && board[row - 1][col] != 0 && board[row - 1][col] != player.getNumber()) {
            for (int i = row - 1; i >= 0 && board[i][col] != 0; i--) {
                if (board[i][col] == player.getNumber()) {
                    return true;
                }
            }
        }

        if (row + 1 < board.length && board[row + 1][col] != 0 && board[row + 1][col] != player.getNumber()) {
            for (int i = row + 1; i < board.length && board[i][col] != 0; i++) {
                if (board[i][col] == player.getNumber())
                    return true;
            }
        }

        if (row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] != 0
                && board[row - 1][col - 1] != player.getNumber()) {
            int i = row - 1;
            int j = col - 1;
            while (i >= 0 && j >= 0 && board[i][j] != 0) {
                if (board[i][j] == player.getNumber()) {
                    return true;
                }
                i--;
                j--;
            }
        }

        if (row - 1 >= 0 && col + 1 < board[row].length && board[row - 1][col + 1] != 0
                && board[row - 1][col + 1] != player.getNumber()) {
            int i = row - 1;
            int j = col + 1;
            while (i >= 0 && j < board[0].length && board[i][j] != 0) {
                if (board[i][j] == player.getNumber()) {
                    return true;
                }
                i--;
                j++;
            }
        }

        if (row + 1 < board.length && col + 1 < board[0].length && board[row + 1][col + 1] != 0
                && board[row + 1][col + 1] != player.getNumber()) {
            int i = row + 1;
            int j = col + 1;
            while (i < board.length && j < board[0].length && board[i][j] != 0) {
                if (board[i][j] == player.getNumber()) {
                    return true;
                }
                i++;
                j++;
            }
        }

        if (row + 1 < board.length && col - 1 >= 0 && board[row + 1][col - 1] != 0
                && board[row + 1][col - 1] != player.getNumber()) {
            int i = row + 1;
            int j = col - 1;
            while (i < board.length && j >= 0 && board[i][j] != 0) {
                if (board[i][j] == player.getNumber()) {
                    return true;
                }
                i++;
                j--;
            }
        }
        return false;
    }

    /**
     * draw the board
     */
    public void drawState() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.printf("%d  ", board[i][j]);
            }
            System.out.println("\n");
        }
    }

    /**
     * returns true if the board is in a final state
     * 
     * @return
     */
    public boolean isTerminal() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    if (isLegalMove(i, j, new Player(1)))
                        return false;
                    if (isLegalMove(i, j, new Player(2)))
                        return false;
                }
            }
        }
        return true;
    }

    

}
