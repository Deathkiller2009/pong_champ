package ru.deathkiller2009;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame implements Runnable {

    private Graphics2D graphicsRenderer;

    private Paddle firstPlayer;

    private Paddle secondPlayer;

    private Ball ball;

    private PlayerMovementController controller1;

    private AiController controller2;

    private UserInputProcessor userInputProcessor = new UserInputProcessor();

    private Text leftScoreText, rightScoreText;

    private boolean isRunning = true;

    public GameWindow() {
        setTitle("Pong");
        setSize(WindowConstants.WIDTH, WindowConstants.HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphicsRenderer = (Graphics2D) this.getGraphics();
        addKeyListener(userInputProcessor);

        leftScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, GameConstants.TEXT_SIZE), WindowConstants.SCORE_X_POSITION, WindowConstants.SCORE_Y_POSITION);
        rightScoreText = new Text(0, new Font("Times New Roman", Font.PLAIN, GameConstants.TEXT_SIZE), WindowConstants.WIDTH - WindowConstants.SCORE_X_POSITION - 16, WindowConstants.SCORE_Y_POSITION);

        firstPlayer = new Paddle(PaddleConstants.HZ_PADDING, 40, Color.WHITE);
        secondPlayer = new Paddle(WindowConstants.WIDTH - PaddleConstants.HZ_PADDING - PaddleConstants.PADDLE_WIDTH, 40, Color.WHITE);
        ball = new Ball(WindowConstants.WIDTH / 2., WindowConstants.HEIGHT / 2., Color.WHITE, firstPlayer, secondPlayer, leftScoreText, rightScoreText);

        controller1 = new PlayerMovementController(userInputProcessor, firstPlayer);
        controller2 = new AiController(new PlayerMovementController(secondPlayer), ball);


    }

    private void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        draw(dbg);

        graphicsRenderer.drawImage(dbImage, 0, 0, this);

        controller1.update(dt);
        controller2.update(dt);
        ball.update(dt);
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
        double lastFrame = 0.0;
        while (isRunning) {
            double time = Time.getTime();
            double deltaTime = time - lastFrame;
            lastFrame = time;

            update(deltaTime);

        }
        dispose();
    }
}
