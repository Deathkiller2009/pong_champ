package ru.deathkiller2009;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Ball {

    private double x, y, width, height;

    private Color color;

    public Ball(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
        this.color = color;
    }

    public void draw(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
