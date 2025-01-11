package ru.deathkiller2009;

import lombok.Data;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Arrays;

@Data
public class Ball {

    private double x, y, width, height;

    private Color color;

    private Socket socket;


    public Ball(double x, double y, Color color, Socket socket) {
        this.x = x;
        this.y = y;

        this.width = 10;
        this.height = 10;
        this.color = color;

        this.socket = socket;
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fill(new Rectangle2D.Double(x, y, width, height));
    }

    @SneakyThrows
    public void update(GameUpdate gameUpdate) {
        this.x = gameUpdate.getBallX();
        this.y = gameUpdate.getBallY();
    }
}
