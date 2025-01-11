package ru.deathkiller2009;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

@RequiredArgsConstructor
public class Server {

    private final RequestReceiver requestReceiver;

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8082)) {

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, requestReceiver)).start();
            }

        }
    }

}
