package ru.deathkiller2009;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@RequiredArgsConstructor
public class Server {

    private final RequestReceiver requestReceiver;

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8082)) {

            while (true) {

                try (Socket socket = serverSocket.accept()) {
                   requestReceiver.receiveRequest(socket);
                }

            }

        }
    }

}
