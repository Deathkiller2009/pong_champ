package ru.deathkiller2009;

public class Game {

    private static int state = 0;
    private static Thread mainThread;
    private static MainMenu menu;
    private static GameWindow gameWindow;

    public static void main(String[] args) {
        menu = new MainMenu();

        mainThread = new Thread(menu);
        mainThread.start();
    }

    public static void changeState(int newState) {
        if (newState == 1 && state == 0) {
            menu.stop();
            gameWindow = new GameWindow();
            mainThread = new Thread(gameWindow);
            mainThread.start();
        } else if (newState == 0 && state == 1) {
            gameWindow.stop();
            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();
        } else if (newState == 2) {
            if (gameWindow != null) {
                gameWindow.stop();
            }
            if (menu != null) {
                menu.stop();
            }
        }
        state = newState;
    }
}
