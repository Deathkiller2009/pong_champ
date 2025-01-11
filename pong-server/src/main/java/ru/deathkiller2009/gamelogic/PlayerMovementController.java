package ru.deathkiller2009.gamelogic;

import lombok.Data;
import ru.deathkiller2009.entities.Paddle;
import ru.deathkiller2009.entities.PaddleConstants;


@Data
public class PlayerMovementController {

    public PlayerMovementController(Paddle paddle) {
        this.paddle = paddle;
    }

    private Paddle paddle;

    public void update(String direction, double dt) {
        if (direction != null) {
            if (direction.equals("UP")) {
                moveUp(dt);
            } else if (direction.equals("DOWN")) {
                moveDown(dt);
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