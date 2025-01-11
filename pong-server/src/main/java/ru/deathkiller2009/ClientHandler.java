package ru.deathkiller2009;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable{

    private Socket socket;

    private RequestReceiver requestReceiver;

    public ClientHandler(Socket socket, RequestReceiver requestReceiver) {
        this.socket = socket;
        this.requestReceiver = requestReceiver;
    }


    @Override
    public void run() {
        try {
            requestReceiver.receiveRequest(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
