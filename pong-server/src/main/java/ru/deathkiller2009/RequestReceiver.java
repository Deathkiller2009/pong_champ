package ru.deathkiller2009;

import lombok.RequiredArgsConstructor;
import ru.deathkiller2009.request.Request;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

@RequiredArgsConstructor
public class RequestReceiver {

    private final RequestParser requestParser;

    private final Dispatcher dispatcher;

    public void receiveRequest(Socket socket) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            byte[] buf = new byte[1024];
            int read = dataInputStream.read(buf);
            byte[] request = Arrays.copyOf(buf, read);
            Request parsedRequest = requestParser.parseRequest(request);
            dispatcher.redirectRequest(parsedRequest);
        }
    }

}
