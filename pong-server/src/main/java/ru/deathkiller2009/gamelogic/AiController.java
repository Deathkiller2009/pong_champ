package ru.deathkiller2009.gamelogic;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.deathkiller2009.entities.Ball;

@Data
@AllArgsConstructor
public class AiController {

    private PlayerMovementController playerMovementController;

    private Ball ball;

    public void update(double dt) {
        if (ball.getY() < playerMovementController.getPaddle().getY()) {
            playerMovementController.moveUp(dt);
        } else if (ball.getY() + ball.getHeight() > playerMovementController.getPaddle().getY() + playerMovementController.getPaddle().getHeight()) {
            playerMovementController.moveDown(dt);
        }
    }
}
