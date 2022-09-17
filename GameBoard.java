/**
 * class to create the game state, inlcuding te board and palyers
 * provides a method to check legal moves
 */
public class GameBoard {
    // using a 2D array as a board
    private int[][] board;
    // players for this game
    private Player player1;
    private Player player2;
    // Track whose turn is it to play
    private int turn;

    /**
     * constructor
     * @param p1
     * @param p2
     * @param boardSize
     */
    public GameBoard(Player p1, Player p2, int boardSize) {
        // create board and initialize all values to zero
        this.board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                this.board[i][j] = 0;
            }
        }

    }


}
