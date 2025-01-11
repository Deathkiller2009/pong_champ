package ru.deathkiller2009.entities;

import lombok.Data;
import ru.deathkiller2009.gamelogic.WindowConstants;


@Data
public class Paddle {

    private double x, y, width, height;


    public Paddle(double x, double y) {
        this.x = x;
        this.y = y + WindowConstants.TOOLBAR_HEIGHT;
        this.width = 10;
        this.height = 100;
    }

}
