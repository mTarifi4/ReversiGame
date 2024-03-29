
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Exception;

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

    public void setState(State newState) {
        state = newState;
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
                    p1Counter += 100;
                else if (s.getBoard()[i][j] == p2.getNumber())
                    p2Counter += 100;
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
        return 10000 * (p1Counter - p2Counter);
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
        if (s.isTerminal() ){
            return utilityHTerminal(s, s.getPlayer(), s.getOpponent());
        }
        else if(depthAt >= depth){
            return UtilityH(s, s.getPlayer(), s.getOpponent());
        }
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
        if(s.isTerminal()){
            return utilityHTerminal(s, s.getPlayer(), s.getOpponent());
        } 
        else if (depthAt >= depth){
            return UtilityH(s, s.getPlayer(), s.getOpponent());
        }
        ArrayList<State> actions = getActions(s, s.getPlayer());
        for(State state: actions) {  
           v = Integer.max(v, minH(state, depthAt++, depth, alpha, beta));
           if(v >= beta)
            return v;
           alpha = Integer.max(alpha, v); 
        } 
        return v;  
        
    }

    public void runMiniMaxHuman(Reversi board) {
        Scanner scanner = new Scanner(System.in);
        while(!board.getGameState().isTerminal()) {
            board.getGameState().drawState();
            if(board.getActions(board.getGameState(), board.getGameState().getPlayer()).isEmpty() && !board.getGameState().isTerminal()) {
                System.out.println("There are no legal moves so you must pass");
                if(board.getGameState().getPlayer().getNumber() == 1) 
                    board.getGameState().setPlayer(new Player(2));
                else 
                    board.getGameState().setPlayer(new Player(1));

            } else {
                System.out.println("Please enter an x and y for your move starting with 0"); 
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(board.getGameState().isLegalMove(x, y, board.getGameState().getPlayer())) {
                    board.setState(board.makeMove(board.getGameState(), x, y, board.player1));
                    board.getGameState().drawState();
                } else {
                    System.out.println("Your move was invalid");
                }    
            }
            if(board.getActions(board.getGameState(), board.getGameState().getPlayer()).isEmpty() && !board.getGameState().isTerminal()) {
                System.out.println("There are no legal moves so the computer passes");
                if(board.getGameState().getPlayer().getNumber() == 1) 
                    board.getGameState().setPlayer(new Player(2));
                else 
                    board.getGameState().setPlayer(new Player(1));
    
            } else {
                board.setState(minimax(board.getGameState()));
            }
        }
        if(board.getGameState().getWinner() == 1) {
            System.out.println("You Win!");
        } else System.out.println("You Lose :(");
        scanner.close();

    }

    public void runHueristicAlphaBeta(Reversi board, int depth) {
        Scanner scanner = new Scanner(System.in);
        while(!board.getGameState().isTerminal()) {
            board.getGameState().drawState();
            if(board.getActions(board.getGameState(), board.getGameState().getPlayer()).isEmpty() && !board.getGameState().isTerminal()) {
                System.out.println("There are no legal moves so you must pass");
                if(board.getGameState().getPlayer().getNumber() == 1) 
                    board.getGameState().setPlayer(new Player(2));
                else 
                    board.getGameState().setPlayer(new Player(1));

            } else {
                System.out.println("Please enter an x and y for your move starting with 0"); 
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if(board.getGameState().isLegalMove(x, y, board.getGameState().getPlayer())) {
                    board.setState(board.makeMove(board.getGameState(), x, y, board.player1));
                    board.getGameState().drawState();
                } else {
                    System.out.println("Your move was invalid");
                }    
            }
            if(board.getActions(board.getGameState(), board.getGameState().getPlayer()).isEmpty() && !board.getGameState().isTerminal()) {
                System.out.println("There are no legal moves so the computer passes");
                if(board.getGameState().getPlayer().getNumber() == 1) 
                    board.getGameState().setPlayer(new Player(2));
                else 
                    board.getGameState().setPlayer(new Player(1));
    
            } else {
                board.setState(minimaxH(board.getGameState(), depth));
            }
        }
        if(board.getGameState().getWinner() == 1) {
            System.out.println("You Win!");
        } else System.out.println("You Lose :(");
        scanner.close();

    }

    public void run() {
        Player player = new Player(1);
        Player computer = new Player(2);
        Scanner scanner = new Scanner(System.in);
        Reversi game = null;
        // beginning of repl
        System.out.println("Choose your game: \n 1. Small 4x4 Reversi \n 2. Medium 6x6 Reversi \n 3. Standard 8x8 Reversi \n");
        System.out.print("Your Choice: ");
        int size = scanner.nextInt();
        System.out.println("Choose your opponent: \n 1. An Agent that uses Minimax to choose the optimal move \n 2. An agent that uses H-Minimax with fixed deptth cutoff and A B Pruning\n");
        System.out.print("Your Choice: ");
        int opponent = scanner.nextInt();
        if(size == 1) {
            game = new Reversi(player, computer, 4);
            if(opponent == 1) {
                runMiniMaxHuman(game);
            } else if(opponent == 2) {
                System.out.println("Enter depth you'd like to search too a depth larger than 6 is not reccomended");
                int depth = scanner.nextInt();
                runHueristicAlphaBeta(game, depth);
            }
        } else if(size == 2) {
            game = new Reversi(player, computer, 6); 
            if(opponent == 1) {
                runMiniMaxHuman(game);
            } else if(opponent == 2) {
                System.out.println("Enter depth you'd like to search too a depth larger than 6 is not reccomended");
                int depth = scanner.nextInt();
                runHueristicAlphaBeta(game, depth);
            }
        } else if(size == 3) {
            game = new Reversi(player, computer, 8);
            if(opponent == 1) {
                runMiniMaxHuman(game);
            } else if(opponent == 2) {
                System.out.println("Enter depth you'd like to search too a depth larger than 6 is not reccomended");
                int depth = scanner.nextInt();
                runHueristicAlphaBeta(game, depth);
            }
        }
        

        
    } 

    public static void main(String[] args) {
        Reversi game = new Reversi(new Player(1), new Player(1), 4);
        game.run();
        System.out.println("he");
        game.getGameState().drawState();
    }

}
