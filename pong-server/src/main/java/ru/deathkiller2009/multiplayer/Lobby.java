package ru.deathkiller2009.multiplayer;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;

public class Lobby {

    private static Lobby instance;
    private Map<String, Socket> waitingPlayers = new LinkedHashMap<>();

    private Lobby() {}

    public static Lobby getInstance() {
        if (instance == null) {
            instance = new Lobby();
        }
        return instance;
    }

    public synchronized void addPlayer(Socket socket) {

        if (waitingPlayers.isEmpty()) {
            waitingPlayers.put("first", socket);
        } else {
            waitingPlayers.put("second", socket);
        }

        if (waitingPlayers.size() == 2) {

            try {
                DataOutputStream dataOutputStream = new DataOutputStream(waitingPlayers.get("first").getOutputStream());
                dataOutputStream.writeUTF("GAME_STARTED");

                DataOutputStream secondPlayerOutputStream = new DataOutputStream(socket.getOutputStream());
                secondPlayerOutputStream.writeUTF("GAME_STARTED");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            MultiPlayerGame game = new MultiPlayerGame(waitingPlayers.get("first"), socket);
            waitingPlayers.clear();

            game.start();

        }
    }
}
