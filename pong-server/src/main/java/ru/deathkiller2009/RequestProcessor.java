package ru.deathkiller2009;

import java.io.DataInputStream;
import java.net.Socket;

public interface RequestProcessor {

    void processRequest(Request request, Socket socket, DataInputStream dataInputStream);

    boolean canBeProcessed(Request request);
}
