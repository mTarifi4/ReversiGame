public class Player {
    private int playerNumber;
    private int playerType;

    public Player(int playerNumber, int playerType){
        this.playerNumber = playerNumber;
        this.playerType = playerType;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getPlayerType() {
        return playerType;
    }
}
