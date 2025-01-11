package ru.deathkiller2009;

public class SinglePlayerRequest extends Request{

    private String player;

    private String mode;

    public SinglePlayerRequest(String player, String mode) {
        this.player = player;
        this.mode = mode;
    }

    public SinglePlayerRequest() {
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "SinglePlayerRequest{" +
               "player='" + player + '\'' +
               ", mode='" + mode + '\'' +
               '}';
    }
}
