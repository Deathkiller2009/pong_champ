package ru.deathkiller2009.windows;


import ru.deathkiller2009.entities.Ball;
import ru.deathkiller2009.entities.Paddle;
import ru.deathkiller2009.entities.Text;
import ru.deathkiller2009.gameupdate.GameUpdate;
import ru.deathkiller2009.gameupdate.GameUpdateParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class GameWindow extends JFrame implements Runnable {

    private Graphics2D graphicsRenderer;

    private Paddle firstPlayer;

    private Paddle secondPlayer;

    private Ball ball;

    private boolean isRunning = true;

    private final Socket socket;

    private final PlayerMovementController playerMovementController;

    private final UserInputProcessor userInputProcessor;

    private Text leftScoreText, rightScoreText;

    private DataInputStream dataInputStream;

    public GameWindow(Socket socket) {
        setTitle("Pong");
        setSize(WindowConstants.WIDTH, WindowConstants.HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        graphicsRenderer = (Graphics2D) this.getGraphics();

        leftScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, GameConstants.TEXT_SIZE), WindowConstants.SCORE_X_POSITION, WindowConstants.SCORE_Y_POSITION);
        rightScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, GameConstants.TEXT_SIZE), WindowConstants.WIDTH - WindowConstants.SCORE_X_POSITION - 16, WindowConstants.SCORE_Y_POSITION);

        firstPlayer = new Paddle(PaddleConstants.HZ_PADDING, 40, Color.WHITE);
        secondPlayer = new Paddle(WindowConstants.WIDTH - PaddleConstants.HZ_PADDING - PaddleConstants.PADDLE_WIDTH, 40, Color.WHITE);
        this.ball = new Ball(WindowConstants.WIDTH / 2., WindowConstants.HEIGHT / 2., Color.WHITE, socket);
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ignore) {
        }
        this.userInputProcessor = new UserInputProcessor();
        addKeyListener(userInputProcessor);
        this.playerMovementController = new PlayerMovementController(userInputProcessor, firstPlayer, socket);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                super.windowClosing(e);
            }
        });
    }


    private void update() {
        try {
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            draw(dbg);
            graphicsRenderer.drawImage(dbImage, 0, 0, this);

            playerMovementController.update();

            byte[] buf = new byte[100000];
            int read = dataInputStream.read(buf);
            byte[] response = Arrays.copyOf(buf, read);
            GameUpdateParser gameUpdateParser = new GameUpdateParser();
            if (new String(response, StandardCharsets.UTF_8).equals("PLAYER_DISCONNECTED")) {
                stop();
            }
            GameUpdate gameUpdate = gameUpdateParser.parseRequest(response);

            if (gameUpdate.isIsFinished()) {
                stop();
                if (gameUpdate.getPlayer1Score() > gameUpdate.getPlayer2Score()) {
                    new GameOverWindow("Игрок 1");
                } else {
                    new GameOverWindow("Игрок 2");
                }
            }

            leftScoreText.setText(Integer.valueOf(gameUpdate.getPlayer1Score()).toString());
            rightScoreText.setText(Integer.valueOf(gameUpdate.getPlayer2Score()).toString());

            firstPlayer.update(gameUpdate.getPlayer1Y());
            secondPlayer.update(gameUpdate.getPlayer2Y());
            ball.update(gameUpdate);
        } catch (IOException ignore) {
        }
    }

    private void draw(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setColor(Color.BLACK);
        g1.fillRect(0, 0, WindowConstants.WIDTH, WindowConstants.HEIGHT);

        leftScoreText.draw(g1);
        rightScoreText.draw(g1);
        firstPlayer.draw(g1);
        secondPlayer.draw(g1);
        ball.draw(g1);
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            update();
        }
        dispose();
    }

    @Override
    public void dispose() {
        stop();
        try {
            dataInputStream.close();
            socket.close();
        } catch (IOException ignore) {
        }

        super.dispose();
    }
}
