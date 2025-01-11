package ru.deathkiller2009;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.event.KeyEvent;

@Data
@AllArgsConstructor
public class PlayerMovementController {

    public PlayerMovementController(Paddle paddle) {
        this.paddle = paddle;
        this.userInputProcessor = null;
    }

    private UserInputProcessor userInputProcessor;

    private Paddle paddle;

    public void update(double dt) {
        if (userInputProcessor != null) {
            if (userInputProcessor.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(dt);
            } else if (userInputProcessor.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(dt);
            }
        }
    }

    public void moveUp(double dt) {
        if (paddle.getY() - PaddleConstants.PADDLE_SPEED * dt > WindowConstants.TOOLBAR_HEIGHT)
            paddle.setY(paddle.getY() - dt * PaddleConstants.PADDLE_SPEED);
    }

    public void moveDown(double dt) {
        if ((paddle.getY() + PaddleConstants.PADDLE_SPEED * dt) + paddle.getHeight() < WindowConstants.HEIGHT - WindowConstants.INSETS_BOTTOM)
            paddle.setY(paddle.getY() + dt * PaddleConstants.PADDLE_SPEED);
    }

}
