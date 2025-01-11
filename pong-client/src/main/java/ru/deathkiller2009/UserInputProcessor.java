package ru.deathkiller2009;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserInputProcessor implements KeyListener {

    private final boolean[] keys = new boolean[128];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode) {
        return keys[keyCode];
    }
}
