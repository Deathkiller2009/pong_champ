package ru.deathkiller2009;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@AllArgsConstructor
public class Text {

    private String text;

    private Font font;

    private double width, height;

    private double x, y;

    private Color color = Color.WHITE;

    public Text(String text, Font font, double x, double y, Color color) {
        this.text = String.valueOf(text);
        this.font = font;
        this.x = x;
        this.y = y;
        this.width = font.getSize() * text.length();
        this.height = font.getSize();
        this.color = color;
    }

    public Text(int text, Font font, double x, double y) {
        this.text = String.valueOf(text);
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.setFont(font);
        g2.drawString(text, (float) x, (float) y);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
