package ru.deathkiller2009;

import lombok.RequiredArgsConstructor;
import ru.deathkiller2009.request.ClientHandler;
import ru.deathkiller2009.request.RequestReceiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
