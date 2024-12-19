package ru.deathkiller2009;

import lombok.Data;

import java.awt.*;
import java.awt.geom.Rectangle2D;

@Data
public class Racket {

    private double x, y, width, height;

    private Color color;

    public Racket(double x, double y, Color color) {
        this.x = x;
        this.y = y + 31;
        this.width = 10;
        this.height = 100;
        this.color = color;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
