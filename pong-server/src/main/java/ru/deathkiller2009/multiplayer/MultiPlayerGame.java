package ru.deathkiller2009.multiplayer;

import ru.deathkiller2009.entities.PaddleConstants;
import ru.deathkiller2009.gamelogic.PlayerMovementController;
import ru.deathkiller2009.gamelogic.Time;
import ru.deathkiller2009.gamelogic.WindowConstants;
import ru.deathkiller2009.entities.Ball;
import ru.deathkiller2009.entities.Paddle;
import ru.deathkiller2009.entities.Score;
import ru.deathkiller2009.response.ResponseCreator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class MultiPlayerGame {

    private final Ball ball;

    private final Paddle player1;

    private final Paddle player2;

    private final Score player1Score;

    private final Score player2Score;

    private boolean isRunning = true;

    private final Socket player1Socket;

    private final Socket player2Socket;

    private final ResponseCreator responseCreator;

    private final PlayerMovementController player1MovementController;

    private final PlayerMovementController player2MovementController;

    private final DataOutputStream player1OutputStream;

    private final DataOutputStream player2OutputStream;

    private final DataInputStream player1InputStream;

    private final DataInputStream player2InputStream;

    public MultiPlayerGame(Socket player1Socket, Socket player2Socket) {
        try {
            this.player1InputStream = new DataInputStream(player1Socket.getInputStream());
            this.player2InputStream = new DataInputStream(player2Socket.getInputStream());
            this.player1OutputStream = new DataOutputStream(player1Socket.getOutputStream());
            this.player2OutputStream = new DataOutputStream(player2Socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.player1Score = new Score();
        this.player2Score = new Score();

        this.player1 = new Paddle(PaddleConstants.HZ_PADDING, 40);
        this.player2 = new Paddle(WindowConstants.WIDTH - PaddleConstants.HZ_PADDING - PaddleConstants.PADDLE_WIDTH, 40);
        this.ball = new Ball(WindowConstants.WIDTH / 2., WindowConstants.HEIGHT / 2., player1, player2, player1Score, player2Score);
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        this.responseCreator = new ResponseCreator();
        this.player1MovementController = new PlayerMovementController(player1);
        this.player2MovementController = new PlayerMovementController(player2);
    }

    private void update(double dt) {

        try {
            ball.update(dt);

            DataOutputStream player1OutputStream = new DataOutputStream(player1Socket.getOutputStream());
            player1OutputStream.writeUTF(responseCreator.gameUpdate(ball.getX(), ball.getY(), player1.getY(), player2.getY(), player1Score, player2Score));

            DataOutputStream player2OutputStream = new DataOutputStream(player2Socket.getOutputStream());
            player2OutputStream.writeUTF(responseCreator.gameUpdate(ball.getX(), ball.getY(), player1.getY(), player2.getY(), player1Score, player2Score));

            if (player1Score.isGameFinished() || player2Score.isGameFinished()) {
                stop();
            }

            DataInputStream player1InputStream = new DataInputStream(player1Socket.getInputStream());
            if (player1InputStream.available() > 0) {
                String request = player1InputStream.readUTF();
                player1MovementController.update(request, dt);
            }

            DataInputStream player2InputStream = new DataInputStream(player2Socket.getInputStream());
            if (player2InputStream.available() > 0) {
                String request = player2InputStream.readUTF();
                player2MovementController.update(request, dt);
            }
        } catch (SocketException e) {
            if (!player1Socket.isClosed()) {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(player1Socket.getOutputStream());
                    dataOutputStream.writeUTF("PLAYER_DISCONNECTED");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (!player2Socket.isClosed()) {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(player2Socket.getOutputStream());
                    dataOutputStream.writeUTF("PLAYER_DISCONNECTED");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }

            update(deltaTime);
        }

        try {
            player1OutputStream.close();
            player2OutputStream.close();
            player1InputStream.close();
            player1OutputStream.close();
            player1Socket.close();
            player2Socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}