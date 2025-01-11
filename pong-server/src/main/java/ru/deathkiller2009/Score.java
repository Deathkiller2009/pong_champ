package ru.deathkiller2009;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {

    private int score = 0;

    private boolean isGameFinished = false;

    public void incrementScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }

    public void finishGame() {
        isGameFinished = true;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
