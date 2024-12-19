package ru.deathkiller2009;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame implements Runnable{

    private Graphics2D graphicsRenderer;

    private Racket firstPlayer;

    private Racket secondPlayer;

    private Ball ball;

    private PlayerMovementController controller1;

    private UserInputProcessor userInputProcessor = new UserInputProcessor();

    public GameWindow() {
        setTitle("Pong");
        setSize(Integer.parseInt(PropertiesUtil.getProperty("window.width")), Integer.parseInt(PropertiesUtil.getProperty("window.height")));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphicsRenderer = (Graphics2D) this.getGraphics();
        addKeyListener(userInputProcessor);

        firstPlayer = new Racket(40, 0, Color.WHITE);
        secondPlayer = new Racket(800 - 40 - 20, 0, Color.WHITE);
        ball = new Ball(800 / 2., 600 / 2., Color.WHITE);

        controller1 = new PlayerMovementController(userInputProcessor, firstPlayer);
    }

    private void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        draw(dbg);

        graphicsRenderer.drawImage(dbImage,0, 0, this);

        controller1.update(dt);
    }

    private void draw(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setColor(Color.BLACK);
        g1.fillRect(0, 0, 800, 600);
        firstPlayer.draw(g1);
        secondPlayer.draw(g1);
        ball.draw(g1);
    }

    @Override
    public void run() {
        double lastFrame = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrame;
            lastFrame = time;

            update(deltaTime);

        }
    }
}
