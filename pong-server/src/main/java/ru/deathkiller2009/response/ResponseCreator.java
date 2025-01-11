package ru.deathkiller2009.response;

import ru.deathkiller2009.entities.Score;

public class ResponseCreator {

    public String gameUpdate(double ballX, double ballY, double playerY, double aiY, Score playerScore, Score aiScore) {
        return """
                GAME_UPDATE\r
                \r
                {
                    "ballX": %s,
                    "ballY": %s,
                    "player1Y": %s,
                    "player2Y": %s,
                    "player1Score": %d,
                    "player2Score": %d,
                    "isFinished": %b
                }
                """.formatted(Double.valueOf(ballX).toString().replaceAll(",", "."),
                Double.valueOf(ballY).toString().replaceAll(",", "."),
                Double.valueOf(playerY).toString().replaceAll(",", "."),
                Double.valueOf(aiY).toString().replaceAll(",", "."),
                playerScore.getScore(), aiScore.getScore(), playerScore.isGameFinished() || (aiScore.isGameFinished()));
    }

    public String playerDisconnected() {
        return "PLAYER_DISCONNECTED";
    }

}
