package ru.deathkiller2009;

public class Game {

    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        new Thread(gameWindow).start();
    }

}
