package ru.deathkiller2009;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class SinglePlayerGame {

    private final Ball ball;

    private final Paddle player;

    private final Score playerScore;

    private final Paddle server;

    private final Score aiScore;

    private boolean isRunning = true;

    private final Socket socket;

    private final ResponseCreator responseCreator;

    private final PlayerMovementController playerMovementController;

    private final AiController aiController;

    private final DataInputStream dataInputStream;

    private final DataOutputStream dataOutputStream;

    public SinglePlayerGame(Socket socket, DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
        this.playerScore = new Score();
        this.aiScore = new Score();
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.player = new Paddle(PaddleConstants.HZ_PADDING, 40);
        this.server = new Paddle(WindowConstants.WIDTH - PaddleConstants.HZ_PADDING - PaddleConstants.PADDLE_WIDTH, 40);
        this.ball = new Ball(WindowConstants.WIDTH / 2., WindowConstants.HEIGHT / 2., player, server, playerScore, aiScore);
        this.socket = socket;
        responseCreator = new ResponseCreator();
        this.playerMovementController = new PlayerMovementController(player);
        this.aiController = new AiController(new PlayerMovementController(server), ball);
    }

    @SneakyThrows
    private void update(double dt) {
        ball.update(dt);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(responseCreator.gameUpdate(ball.getX(), ball.getY(), player.getY(), server.getY(), playerScore, aiScore));

        if (playerScore.isGameFinished() || aiScore.isGameFinished()) {
            stop();
        }

        if (dataInputStream.available() > 0) {
            String request = dataInputStream.readUTF();
            playerMovementController.update(request, dt);
            System.out.println(player.getY());
        }
        aiController.update(dt);
    }

    public void stop() {
        isRunning = false;
    }

    public void start() {
        double lastFrame = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrame;
            lastFrame = time;

            try {
                Thread.sleep(45);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            update(deltaTime);

        }

        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
