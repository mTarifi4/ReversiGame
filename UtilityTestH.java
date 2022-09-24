    public int UtilityH (State s, Player p1, Player p2) {
        int p1Counter = 0;
        int p2Counter = 0;
        // check the second layer of the board and takes a point off for every piece 
        // that is the player's piece
        
        for (int j = 1; j < s.getBoard().length -1 ; j++) {
            int i = 1;
            if (s.getBoard()[i][j] == p1.getNumber())
                p1Counter--;
            else if (s.getBoard()[i][j] == p2.getNumber())
                p2Counter--;
        }
        
        // check the second to last layer of the board and takes a point off for every piece
        // that is the player's piece
        for (int i = s.getBoard().length - 1; i < s.getBoard().length ; i++) {
            for (int j = 1; j < s.getBoard().length - 1; j++) {
                if (s.getBoard()[i][j] == p1.getNumber())
                    p1Counter--;
                else if (s.getBoard()[i][j] == p2.getNumber())
                    p2Counter--;
            }
        }
        // check the second layer of the board and takes a point off for every piece
        // that is the player's piece
        for (int i = 1; i < s.getBoard().length - 1; i++) {
            int j = 1; 
            if (s.getBoard()[i][j] == p1.getNumber())
                p1Counter--;
            else if (s.getBoard()[i][j] == p2.getNumber())
                p2Counter--;
            
        }
        // check the second to last layer of the board and takes a point off for every piece
        // that is the player's piece
        for (int i = 1; i < s.getBoard().length - 1; i++) {
            int j = s.getBoard().length - 2;
            if (s.getBoard()[i][j] == p1.getNumber())
                p1Counter--;
            else if (s.getBoard()[i][j] == p2.getNumber())
                p2Counter--;
        }
        // check corner pieces and give 2 extra points for each corner piece
        if (s.getBoard()[0][0] == 1)
            p1Counter += 4;
        else if (s.getBoard()[0][0] == 2)
            p2Counter += 4;
        else if (s.getBoard()[0][s.getBoard().length - 1] == 1)
            p1Counter += 4;
        else if (s.getBoard()[0][s.getBoard().length - 1] == 2)
            p2Counter += 4;
        else if (s.getBoard()[s.getBoard().length - 1][0] == 1)
            p1Counter += 4;
        else if (s.getBoard()[s.getBoard().length - 1][0] == 2)
            p2Counter += 4;
        else if (s.getBoard()[s.getBoard().length - 1][s.getBoard().length - 1] == 1)
            p1Counter += 4;
        else if (s.getBoard()[s.getBoard().length - 1][s.getBoard().length - 1] == 2)
            p2Counter += 4;
        
        // check for edge pieces 
        for (int i = 0; i < s.getBoard().length; i++) {
            if (s.getBoard()[0][i] == 1)
                p1Counter++;
            else if (s.getBoard()[0][i] == 2)
                p2Counter++;
            else if (s.getBoard()[s.getBoard().length - 1][i] == 1)
                p1Counter++;
            else if (s.getBoard()[s.getBoard().length - 1][i] == 2)
                p2Counter++;
            else if (s.getBoard()[i][0] == 1)
                p1Counter++;
            else if (s.getBoard()[i][0] == 2)
                p2Counter++;
            else if (s.getBoard()[i][s.getBoard().length - 1] == 1)
                p1Counter++;
            else if (s.getBoard()[i][s.getBoard().length - 1] == 2)
                p2Counter++;
        }
        return p1Counter - p2Counter;
    }
