package ru.deathkiller2009;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class GameUpdate {

    private double ballX;
    private double ballY;
    private double player1Y;
    private double player2Y;
    private int player1Score;
    private int player2Score;
    private boolean isFinished;

    public GameUpdate(double ballX, double ballY, double player1Y, double player2Y) {
        this.ballX = ballX;
        this.ballY = ballY;
        this.player1Y = player1Y;
        this.player2Y = player2Y;
    }

    public GameUpdate() {
    }

    public double getBallX() {
        return ballX;
    }

    public void setBallX(double ballX) {
        this.ballX = ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public void setBallY(double ballY) {
        this.ballY = ballY;
    }

    public double getPlayer1Y() {
        return player1Y;
    }

    public void setPlayer1Y(double player1Y) {
        this.player1Y = player1Y;
    }

    public double getPlayer2Y() {
        return player2Y;
    }

    public void setPlayer2Y(double player2Y) {
        this.player2Y = player2Y;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean finished) {
        isFinished = finished;
    }
}