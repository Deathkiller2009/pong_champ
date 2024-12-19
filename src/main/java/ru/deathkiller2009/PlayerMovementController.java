package ru.deathkiller2009;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.event.KeyEvent;

@Data
@AllArgsConstructor
public class PlayerMovementController {

    private UserInputProcessor userInputProcessor;

    private Racket racket;

    public void update(double dt) {
        if (userInputProcessor.isKeyPressed(KeyEvent.VK_DOWN)) {
            if ((racket.getY() + 250 * dt) + 100 < 600 - 8)
                racket.setY(racket.getY() + dt * 250);
        } else if (userInputProcessor.isKeyPressed(KeyEvent.VK_UP)) {
            if (racket.getY() - 250 * dt > 31)
                racket.setY(racket.getY() - dt * 250);
        }
    }

}
