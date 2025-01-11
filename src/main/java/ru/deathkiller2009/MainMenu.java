package ru.deathkiller2009;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame implements Runnable {

    private Graphics2D graphicsRenderer;

    private UserInputProcessor userInputProcessor = new UserInputProcessor();

    private Text startGame, exitGame, title;

    private ButtonClickProcessor buttonClickProcessor = new ButtonClickProcessor();

    private boolean isRunning = true;

    public MainMenu() {
        setTitle("Pong");
        setSize(WindowConstants.WIDTH, WindowConstants.HEIGHT);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        graphicsRenderer = (Graphics2D) this.getGraphics();
        addKeyListener(userInputProcessor);
        addMouseListener(buttonClickProcessor);
        addMouseMotionListener(buttonClickProcessor);
        startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), WindowConstants.WIDTH / 2.0 - 140.0, WindowConstants.HEIGHT / 2.0, Color.WHITE);
        exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40), WindowConstants.WIDTH / 2.0 - 80.0, WindowConstants.HEIGHT / 2.0 + 60, Color.WHITE);
        title = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 100), WindowConstants.WIDTH / 2.0 - 155.0, 200.0, Color.WHITE);
    }

    private void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        draw(dbg);

        graphicsRenderer.drawImage(dbImage, 0, 0, this);

        if (buttonClickProcessor.getX() > startGame.getX() && buttonClickProcessor.getX() < startGame.getX() + startGame.getWidth()
            && buttonClickProcessor.getY() > startGame.getY() - startGame.getHeight() / 2.0 && buttonClickProcessor.getY() < startGame.getY() + startGame.getHeight() / 2.0) {
            startGame.setColor(new Color(158, 158, 158));
            if (buttonClickProcessor.isPressed()) {
                Game.changeState(1);
            }
        } else {
            startGame.setColor(Color.WHITE);
        }

        if (buttonClickProcessor.getX() > exitGame.getX() && buttonClickProcessor.getX() < exitGame.getX() + exitGame.getWidth()
            && buttonClickProcessor.getY() > exitGame.getY() - exitGame.getHeight() / 2.0 && buttonClickProcessor.getY() < exitGame.getY() + exitGame.getHeight() / 2.0) {
            exitGame.setColor(new Color(158, 158, 158));
            if (buttonClickProcessor.isPressed()) {
                Game.changeState(2);
            }
        } else {
            exitGame.setColor(Color.WHITE);
        }
    }

    private void draw(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setColor(Color.BLACK);
        g1.fillRect(0, 0, WindowConstants.WIDTH, WindowConstants.HEIGHT);

        startGame.draw(g1);
        exitGame.draw(g1);
        title.draw(g1);
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
