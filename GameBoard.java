/**
 * class to create the game state, inlcuding te board and palyers
 * provides a method to check legal moves
 */
public class GameBoard {
    // using a 2D array as a board
    private int[][] board;
    // Track whose turn is it to play
    private int turn;
    // Track the number of moves made
    private int moves;
    // Track the number of pieces on the board
    private int pieces;
    // Track the number of pieces for each player
    private int p1Pieces;
    private int p2Pieces;
    // Track the number of legal moves for each player
    private int p1LegalMoves;
    private int p2LegalMoves;
    // Track the number of pieces flipped for each player
    private int p1Flipped;
    private int p2Flipped;
    // piece values
    public static final int EMPTY = 0;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    // player values
    public static final int HUMAN = 1;
    public static final int COMPUTER = 2;
    // player objects
    private Player player1;
    private Player player2;

    /**
     * constructor
     * 
     * @param p1
     * @param p2
     * @param boardSize
     */
    public GameBoard(Player p1, Player p2, int boardSize) {
        // create board and initialize all values to zero
        this.board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                this.board[i][j] = EMPTY;
            }
        }

    }

    /**
     * method to return the board
     * 
     * @return
     */

    public int[][] getBoard() {
        return board;
    }

    /**
     * method to set the board
     * 
     * @param board
     */
    public void setBoard(int[][] board) {
        this.board = board;

    }

    /**
     * method to return the turn
     * 
     * @return
     */
    public int getTurn() {
        return turn;
    }

    /**
     * method to set the turn
     * 
     * @param turn
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * method to generate a board of legal moves with
     * boolean values
     * 
     * @param player
     * @return 2D list of legal moves
     */

    public boolean[][] computeLegalMoves(int[][] board, Player player) {
        // create a 2D array of legal moves
        boolean[][] legalMoves = new boolean[board.length][board.length];
        // initialize all values to false
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                legalMoves[i][j] = false;
            }
        }
        // check for legal moves
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // check if the current position is empty
                if (board[i][j] == EMPTY) {
                    // check if the current position is a legal move
                    if (isLegalMove(board, i, j, player)) {
                        legalMoves[i][j] = true;
                    }
                }
            }
        }
        return legalMoves;
    }

    /**
     * method to check if a move is legal
     * 
     * @param board
     * @param row
     * @param col
     * @param player
     * @return true if legal, false otherwise
     */
    public boolean isLegalMove(int[][] board, int row, int col, Player player) {
        // check if the current position is empty
        if (board[row][col] != EMPTY) {
            return false;
        }
        // check if position is within the board
        if (row < 0 || row >= board.length || col < 0 || col >= board.length) {
            return false;
        }
        // check the surronding positions for pieces of the opposite color
        // check the top left position
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (board[row - 1][col - 1] != player.getPlayerNumber() && board[row - 1][col - 1] != EMPTY) {
                // check if there is a piece of the same color in the same row
                for (int i = row - 2; i >= 0; i--) {
                    if (board[i][col - 1] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][col - 1] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same column
                for (int i = col - 2; i >= 0; i--) {
                    if (board[row - 1][i] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[row - 1][i] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same diagonal
                for (int i = row - 2, j = col - 2; i >= 0 && j >= 0; i--, j--) {
                    if (board[i][j] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][j] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the top position
        if (row - 1 >= 0) {
            if (board[row - 1][col] != player.getPlayerNumber() && board[row - 1][col] != EMPTY) {
                // check if there is a piece of the same color in the same row
                for (int i = row - 2; i >= 0; i--) {
                    if (board[i][col] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][col] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the top right position
        if (row - 1 >= 0 && col + 1 < board.length) {
            if (board[row - 1][col + 1] != player.getPlayerNumber() && board[row - 1][col + 1] != EMPTY) {
                // check if there is a piece of the same color in the same row
                for (int i = row - 2; i >= 0; i--) {
                    if (board[i][col + 1] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][col + 1] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same column
                for (int i = col + 2; i < board.length; i++) {
                    if (board[row - 1][i] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[row - 1][i] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same diagonal
                for (int i = row - 2, j = col + 2; i >= 0 && j < board.length; i--, j++) {
                    if (board[i][j] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][j] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the left position
        if (col - 1 >= 0) {
            if (board[row][col - 1] != player.getPlayerNumber() && board[row][col - 1] != EMPTY) {
                // check if there is a piece of the same color in the same column
                for (int i = col - 2; i >= 0; i--) {
                    if (board[row][i] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[row][i] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the right position
        if (col + 1 < board.length) {
            if (board[row][col + 1] != player.getPlayerNumber() && board[row][col + 1] != EMPTY) {
                // check if there is a piece of the same color in the same column
                for (int i = col + 2; i < board.length; i++) {
                    if (board[row][i] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[row][i] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the bottom left position
        if (row + 1 < board.length && col - 1 >= 0) {
            if (board[row + 1][col - 1] != player.getPlayerNumber() && board[row + 1][col - 1] != EMPTY) {
                // check if there is a piece of the same color in the same row
                for (int i = row + 2; i < board.length; i++) {
                    if (board[i][col - 1] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][col - 1] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same column
                for (int i = col - 2; i >= 0; i--) {
                    if (board[row + 1][i] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[row + 1][i] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same diagonal
                for (int i = row + 2, j = col - 2; i < board.length && j >= 0; i++, j--) {
                    if (board[i][j] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][j] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the bottom position
        if (row + 1 < board.length) {
            if (board[row + 1][col] != player.getPlayerNumber() && board[row + 1][col] != EMPTY) {
                // check if there is a piece of the same color in the same row
                for (int i = row + 2; i < board.length; i++) {
                    if (board[i][col] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][col] == EMPTY) {
                        break;
                    }
                }
            }
        }
        // check the bottom right position
        if (row + 1 < board.length && col + 1 < board.length) {
            if (board[row + 1][col + 1] != player.getPlayerNumber() && board[row + 1][col + 1] != EMPTY) {
                // check if there is a piece of the same color in the same row
                for (int i = row + 2; i < board.length; i++) {
                    if (board[i][col + 1] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][col + 1] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same column
                for (int i = col + 2; i < board.length; i++) {
                    if (board[row + 1][i] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[row + 1][i] == EMPTY) {
                        break;
                    }
                }
                // check if there is a piece of the same color in the same diagonal
                for (int i = row + 2, j = col + 2; i < board.length && j < board.length; i++, j++) {
                    if (board[i][j] == player.getPlayerNumber()) {
                        return true;
                    } else if (board[i][j] == EMPTY) {
                        break;
                    }
                }
            }
        }
        return false;

    }

    

}
