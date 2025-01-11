package ru.deathkiller2009;

import java.io.DataInputStream;
import java.net.Socket;

public class GameRequestProcessor implements RequestProcessor {

    @Override
    public void processRequest(Request request, Socket socket, DataInputStream dataInputStream) {
        if (request instanceof SinglePlayerRequest) {
            new SinglePlayerGame(socket, dataInputStream).start();
        } else if (request instanceof MultiPlayerRequest) {
            MultiPlayerRequest multiPlayerRequest = (MultiPlayerRequest) request;
            Lobby.getInstance().addPlayer(socket);
        }
    }

    @Override
    public boolean canBeProcessed(Request request) {
        return request instanceof SinglePlayerRequest || request instanceof MultiPlayerRequest;
    }
}
