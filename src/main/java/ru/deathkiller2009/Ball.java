package ru.deathkiller2009;

import lombok.Data;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Data
public class Ball {

    private double x, y, width, height;

    private Color color;

    private Paddle leftPaddle, rightPaddle;

    private double vy = 10.0;

    private double vx = -150.0;

    private Text leftScoreText, rightScoreText;

    public Ball(double x, double y, Color color, Paddle leftPaddle, Paddle rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.x = x;
        this.y = y;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.width = 10;
        this.height = 10;
        this.color = color;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public double calculateNewVelocityAngle(Paddle paddle) {
        double relativeIntersectY = (paddle.getY() + (paddle.getHeight() / 2.0)) - (y + (height / 2.0));
        double normalIntersectY = relativeIntersectY / (paddle.getHeight() / 2.0);
        double theta = normalIntersectY * GameConstants.MAX_ANGLE;

        return Math.toRadians(theta);
    }

    public void update(double dt) {
        if (vx < 0.0) {
            if (x + (vx * dt) < leftPaddle.getX() + leftPaddle.getWidth()) {
                if (y + (vy * dt) > leftPaddle.getY()
                    && y + (vy * dt) + height < leftPaddle.getY() + leftPaddle.getHeight()) {
                    double theta = calculateNewVelocityAngle(leftPaddle);
                    double newVx = Math.abs((Math.cos(theta)) * GameConstants.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * GameConstants.BALL_SPEED;

                    double oldSign = Math.signum(vx);
                    vx = newVx * (-1.0 * oldSign);
                    vy = newVy;
                }
            }
        } else if (vx >= 0.0) {
            if (x + (vx * dt) + width > rightPaddle.getX()) {
                if (y + (vy * dt) > rightPaddle.getY()
                    && y + (vy * dt) + height < rightPaddle.getY() + rightPaddle.getHeight()) {
                    double theta = calculateNewVelocityAngle(rightPaddle);
                    double newVx = Math.abs((Math.cos(theta)) * GameConstants.BALL_SPEED);
                    double newVy = (-Math.sin(theta)) * GameConstants.BALL_SPEED;

                    double oldSign = Math.signum(vx);
                    vx = newVx * (-1.0 * oldSign);
                    vy = newVy;
                }
            }
        }

        if (vy >= 0.0) {
            if (y + (vy * dt) + height > WindowConstants.HEIGHT - WindowConstants.INSETS_BOTTOM) {
                vy *= -1.0;
            }
        } else if (vy < 0.0) {
            if (y + (vy * dt) < WindowConstants.TOOLBAR_HEIGHT) {
                vy *= -1.0;
            }
        }

        x += vx * dt;
        y += vy * dt;

        if (x + width < leftPaddle.getX()) {
            int rightScore = Integer.parseInt(rightScoreText.getText());
            rightScore++;
            rightScoreText.setText(String.valueOf(rightScore));
            x = WindowConstants.WIDTH / 2.0;
            y = WindowConstants.HEIGHT / 2.0;
            vx = -150.0;
            vy = 10.0;
            if (rightScore >= GameConstants.WIN_SCORE) {
                Game.changeState(2);
            }
        } else if (x > rightPaddle.getX() + rightPaddle.getWidth()) {
            int leftScore = Integer.parseInt(leftScoreText.getText());
            leftScore++;
            leftScoreText.setText(String.valueOf(leftScore));
            x = WindowConstants.WIDTH / 2.0;
            y = WindowConstants.HEIGHT / 2.0;
            vx = 150.0;
            vy = 10.0;
            if (leftScore >= GameConstants.WIN_SCORE) {
                Game.changeState(2);
            }
        }
    }


    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
