// Player class to create player objects and store their information

public class Player {
    private int pNumber;
    private int pScore;

    public Player(int number) {
        pNumber = number;
        pScore = 0;
    }

    public void setPlayer(int num) {
        pNumber = num;
    }

    public void setScore(int score) {
        pScore = score;
    }

    public int getNumber() {
        return pNumber;
    }

    public int getScore() {
        return pScore;
    }

}
