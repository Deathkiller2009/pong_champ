package ru.deathkiller2009;

public class MultiPlayerRequest extends Request {

    public MultiPlayerRequest() {

    }

    private String player;

    public MultiPlayerRequest(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}