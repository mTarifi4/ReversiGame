// To Do 
// handle illegal moves from user 
// create random player 
// fix interaction with user ( use his input and output format)
// use utility terminal, multiply the score by 10000 

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Exception;
// import the MiniMaxAlg class

public class Reversi {
    private Player player1; // always ai
    private Player player2; // always player
    private State state;

    public Reversi(Player p1, Player p2, int size) {
        player1 = p1;
        player2 = p2;
        state = new State(size);
    }

    public State getGameState() {
        return state;
    }

    public void setState(State tarifiSuckMyCock) {
        state = tarifiSuckMyCock;
    }

    public static State makeMove(State state, int row, int col, Player player) {
        State board = new State(state.getBoard(), player);
        if (board.isLegalMove(row, col, player)) {
            // search east
            if (col + 1 < board.getBoard()[row].length && board.getBoard()[row][col + 1] != 0
                    && board.getBoard()[row][col + 1] != player.getNumber()) {
                for (int i = col + 1; i < board.getBoard()[row].length && board.getBoard()[row][i] != 0; i++) {
                    if (board.getBoard()[row][i] == player.getNumber()) {
                        for (int j = i; j >= col; j--)
                            board.getBoard()[row][j] = player.getNumber();
                        break;
                    }
                }

            }

            // search west
            if (col - 1 >= 0 && board.getBoard()[row][col - 1] != 0
                    && board.getBoard()[row][col - 1] != player.getNumber()) {
                for (int i = col - 1; i >= 0 && board.getBoard()[row][i] != 0; i--) {
                    if (board.getBoard()[row][i] == player.getNumber()) {
                        for (int j = i; j <= col; j++)
                            board.getBoard()[row][j] = player.getNumber();
                        break;
                    }
                }

            }

            // search north
            if (row - 1 >= 0 && board.getBoard()[row - 1][col] != 0
                    && board.getBoard()[row - 1][col] != player.getNumber()) {
                for (int i = row - 1; i >= 0 && board.getBoard()[i][col] != 0; i--) {
                    if (board.getBoard()[i][col] == player.getNumber()) {
                        for (int j = i; j <= row; j++)
                            board.getBoard()[j][col] = player.getNumber();
                        break;
                    }
                }
            }

            // search south
            if (row + 1 < board.getBoard().length && board.getBoard()[row + 1][col] != 0
                    && board.getBoard()[row + 1][col] != player.getNumber()) {
                for (int i = row + 1; i < board.getBoard().length && board.getBoard()[i][col] != 0; i++) {
                    if (board.getBoard()[i][col] == player.getNumber()) {
                        for (int j = i; j >= row; j--)
                            board.getBoard()[j][col] = player.getNumber();
                        break;
                    }
                }
            }

            // search northwest
            if (row - 1 >= 0 && col - 1 >= 0 && board.getBoard()[row - 1][col - 1] != 0
                    && board.getBoard()[row - 1][col - 1] != player.getNumber()) {
                int i = row - 1;
                int j = col - 1;
                while (i >= 0 && j >= 0 && board.getBoard()[i][j] != 0) {
                    if (board.getBoard()[i][j] == player.getNumber()) {
                        while (i <= row && j <= col) {
                            board.getBoard()[i][j] = player.getNumber();
                            i++;
                            j++;
                        }
                        break;
                    }
                    i--;
                    j--;
                }
            }

            // search northeast
            if (row - 1 >= 0 && col + 1 < board.getBoard()[row].length && board.getBoard()[row - 1][col + 1] != 0
                    && board.getBoard()[row - 1][col + 1] != player.getNumber()) {
                int i = row - 1;
                int j = col + 1;
                while (i >= 0 && j < board.getBoard()[0].length && board.getBoard()[i][j] != 0) {
                    if (board.getBoard()[i][j] == player.getNumber()) {
                        while (i <= row && j >= col) {
                            board.getBoard()[i][j] = player.getNumber();
                            i++;
                            j--;
                        }
                        break;
                    }
                    i--;
                    j++;
                }
            }

            // search southeast
            if (row + 1 < board.getBoard().length && col + 1 < board.getBoard()[0].length
                    && board.getBoard()[row + 1][col + 1] != 0
                    && board.getBoard()[row + 1][col + 1] != player.getNumber()) {
                int i = row + 1;
                int j = col + 1;
                while (i < board.getBoard().length && j < board.getBoard()[0].length && board.getBoard()[i][j] != 0) {
                    if (board.getBoard()[i][j] == player.getNumber()) {
                        while (i >= row && j >= col) {
                            board.getBoard()[i][j] = player.getNumber();
                            i--;
                            j--;
                        }
                        break;
                    }
                    i++;
                    j++;
                }
            }

            // search southwest
            if (row + 1 < board.getBoard().length && col - 1 >= 0 && board.getBoard()[row + 1][col - 1] != 0
                    && board.getBoard()[row + 1][col - 1] != player.getNumber()) {
                int i = row + 1;
                int j = col - 1;
                while (i < board.getBoard().length && j >= 0 && board.getBoard()[i][j] != 0) {
                    if (board.getBoard()[i][j] == player.getNumber()) {
                        while (i >= row && j <= col) {
                            board.getBoard()[i][j] = player.getNumber();
                            i--;
                            j++;
                        }
                        break;
                    }
                    i++;
                    j--;
                }
            }
            if (player.getNumber() == 1)
                board.setPlayer(new Player(2));
            else
                board.setPlayer(new Player(1));
            return board;
        }
        return board;
    }

    public static ArrayList<State> getActions(State s, Player p) {
        ArrayList<State> actions = new ArrayList<State>();
        for (int i = 0; i < s.getBoard().length; i++)
            for (int j = 0; j < s.getBoard()[0].length; j++)
                if (s.isLegalMove(i, j, p))
                    actions.add(makeMove(s, i, j, p));
        return actions;
    }
    public int Utility(State s) {
        if (s.isTerminal()) {
            int whiteCounter = 0;
            int blackCounter = 0;
            for (int i = 0; i < s.getBoard().length; i++) {
                for (int j = 0; j < s.getBoard().length; j++) {
                    if (s.getBoard()[i][j] == 1)
                        whiteCounter++;
                    else if (s.getBoard()[i][j] == 2)
                        blackCounter++;
                }
            }
            if (whiteCounter > blackCounter)
                return 1;
            else if (whiteCounter < blackCounter)
                return -1;
            else if (whiteCounter == blackCounter)
                return 0;
        }
        return 0;
    }
// bro let's get out of here for the love of god
    public State minimax(State s) {
        ArrayList<State> actions = getActions(s, s.getPlayer());
        int max = Integer.MIN_VALUE;
        State move = s;
        for(State state: actions) {
            int val = min(state);
            if(val > max) {
                max = val;
                move = state;
            }
        }
        return move;
    }

    public int min(State s) {
        int v = Integer.MAX_VALUE;
        if (s.isTerminal())
            return Utility(s);
        ArrayList<State> actions = getActions(s, s.getPlayer());
        for (State state : actions) {
            v = Integer.min(max(state), v);
        }
        return v;
    }

    public int max(State s) {
        int v = Integer.MIN_VALUE;
        if (s.isTerminal())
            return Utility(s);
        ArrayList<State> actions = getActions(s, s.getPlayer());
        for (State bruh : actions) {
            v = Integer.max(min(bruh), v);
        }
        return v;
    }

    public int UtilityH (State s, Player p1, Player p2) {
        int p1Counter = 0;
        int p2Counter = 0;

        // for every piece give 50 points to the player that owns it 
        for (int i = 0; i < s.getBoard().length; i++) {
            for (int j = 0; j < s.getBoard().length; j++) {
                if (s.getBoard()[i][j] == p1.getNumber())
                    p1Counter += 10;
                else if (s.getBoard()[i][j] == p2.getNumber())
                    p2Counter += 10;
            }
        }
        // check the second layer of the board and takes a point off for every piece 
        // that is the player's piece
        
        for (int j = 1; j < s.getBoard().length -1 ; j++) {
            int i = 1;
            if (s.getBoard()[i][j] == p1.getNumber())
                p1Counter -= 750;
            else if (s.getBoard()[i][j] == p2.getNumber())
                p2Counter -= 750;
        }
        
        // check the second to last layer of the board and takes a point off for every piece
        // that is the player's piece
       
        for (int j = 1; j < s.getBoard().length - 1; j++) {
                int i = s.getBoard().length - 2;
                if (s.getBoard()[i][j] == p1.getNumber())
                    p1Counter -= 750;
                else if (s.getBoard()[i][j] == p2.getNumber())
                    p2Counter -= 750;
            }
        
        // check the second layer of the board and takes a point off for every piece
        // that is the player's piece
        for (int i = 1; i < s.getBoard().length - 1; i++) {
            int j = 1; 
            if (s.getBoard()[i][j] == p1.getNumber())
                p1Counter -= 750;
            else if (s.getBoard()[i][j] == p2.getNumber())
                p2Counter -= 750;
            
        }
        // check the second to last layer of the board and takes a point off for every piece
        // that is the player's piece
        for (int i = 1; i < s.getBoard().length - 1; i++) {
            int j = s.getBoard().length - 2;
            if (s.getBoard()[i][j] == p1.getNumber())
                p1Counter -= 750;
            else if (s.getBoard()[i][j] == p2.getNumber())
                p2Counter -= 750;
        }
        // check corner pieces and give 2 extra points for each corner piece
        if (s.getBoard()[0][0] == 1)
            p1Counter += 2500;
        if (s.getBoard()[0][0] == 2)
            p2Counter += 2500;
        if (s.getBoard()[0][s.getBoard().length - 1] == 1)
            p1Counter += 2500;
        if (s.getBoard()[0][s.getBoard().length - 1] == 2)
            p2Counter += 2500;
        if (s.getBoard()[s.getBoard().length - 1][0] == 1)
            p1Counter += 2500;
        if (s.getBoard()[s.getBoard().length - 1][0] == 2)
            p2Counter += 2500;
        if (s.getBoard()[s.getBoard().length - 1][s.getBoard().length - 1] == 1)
            p1Counter += 2500;
        if (s.getBoard()[s.getBoard().length - 1][s.getBoard().length - 1] == 2)
            p2Counter += 2500;
        
        // check for edge pieces 
        for (int i = 0; i < s.getBoard().length; i++) {
            if (s.getBoard()[0][i] == 1)
                p1Counter += 200;
            if (s.getBoard()[0][i] == 2)
                p2Counter += 200;
            if (s.getBoard()[s.getBoard().length - 1][i] == 1)
                p1Counter += 200;
            if (s.getBoard()[s.getBoard().length - 1][i] == 2)
                p2Counter += 200;
            if (s.getBoard()[i][0] == 1)
                p1Counter += 200;
            if (s.getBoard()[i][0] == 2)
                p2Counter += 200;
            if (s.getBoard()[i][s.getBoard().length - 1] == 1)
                p1Counter += 200;
            if (s.getBoard()[i][s.getBoard().length - 1] == 2)
                p2Counter += 200;
        }
        return p1Counter - p2Counter;
    }
    //implement minimax algorithm with depth limit 
    public State minimaxH(State s, int depth) {
        return miniMaxHHelper(s, 0, depth, Integer.MIN_VALUE, Integer.MAX_VALUE); 
    }
    // function utilityHTerminal (s, p1, p2) returns the score of the state s for the player p1
    public int utilityHTerminal (State s, Player p1, Player p2){
        int p1Counter = 0;
        int p2Counter = 0;

       
        for (int i = 0; i < s.getBoard().length; i++) {
            for (int j = 0; j < s.getBoard().length; j++) {
                if (s.getBoard()[i][j] == p1.getNumber())
                    p1Counter += 1;
                else if (s.getBoard()[i][j] == p2.getNumber())
                    p2Counter += 1;
            }
        }
        return p1Counter - p2Counter;
    }

    public State miniMaxHHelper(State s, int depthAt, int depth, int alpha, int beta) {
        ArrayList<State> actions = getActions(s, s.getPlayer());
        int max = Integer.MIN_VALUE;
        State move = s;
        for (State state : actions) {
            int val = minH(state, depthAt++, depth, alpha, beta);
            if (val > max) {
                max = val;
                move = state;
            }
        }
        return move;
    }

    public int minH(State s, int depthAt, int depth, int alpha, int beta) {
        int v = Integer.MAX_VALUE;
        if (s.isTerminal() || depthAt >= depth)
            return UtilityH(s, s.getPlayer(), s.getOpponent());
        ArrayList<State> actions = getActions(s, s.getPlayer());
        for (State state : actions) {
            v = Integer.min(maxH(state, depthAt++, depth, alpha, beta), v);
            if(v <= alpha)
                return v;
            beta = Integer.min(beta, v);
            
        }
        return v;

    }

    public int maxH(State s, int depthAt, int depth, int alpha, int beta) {
        int v = Integer.MIN_VALUE;
        if(s.isTerminal() || depthAt >= depth)
            return UtilityH(s, s.getPlayer(), s.getOpponent());
        ArrayList<State> actions = getActions(s, s.getPlayer());
        for(State state: actions) {  
           v = Integer.max(v, minH(state, depthAt++, depth, alpha, beta));
           if(v >= beta)
            return v;
           alpha = Integer.max(alpha, v); 
        } 
        return v;  
    }

    public static void main(String[] args) {
        Player p1 = new Player(1);
        Player p2 = new Player(2);
        Reversi dude = new Reversi(p1, p2, 8);
        Scanner scanner = new Scanner(System.in);
        dude.getGameState().drawState();
        int userX = 0;
        int userY = 0;
        while (!dude.getGameState().isTerminal()) {
            System.out.println("Enter x and y for your move");
            userX = scanner.nextInt();
            userY = scanner.nextInt();
            if (dude.getActions(dude.getGameState(), dude.getGameState().getPlayer()).isEmpty()) {
                System.out.println("pass");
                if (dude.getGameState().getPlayer().getNumber() == 1)
                    dude.getGameState().setPlayer(p2);
                else
                    dude.getGameState().setPlayer(p1);
            } else
                dude.setState(dude.makeMove(dude.getGameState(), userX, userY, p1));
            dude.getGameState().drawState();
            System.out.println();
            if (dude.getActions(dude.getGameState(), dude.getGameState().getPlayer()).isEmpty()) {
                System.out.println("pass");
                if (dude.getGameState().getPlayer().getNumber() == 1)
                    dude.getGameState().setPlayer(p2);
                else
                    dude.getGameState().setPlayer(p1);
            } else
                dude.setState(dude.minimaxH(dude.getGameState(), 5));
            dude.getGameState().drawState();
        }

    }

}
