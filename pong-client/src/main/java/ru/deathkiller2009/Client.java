package ru.deathkiller2009;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8082);
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())){
            String request = """
                START_GAME\r
                \r
                {
                    "player": "deathkiller2009",
                    "mode": "singleplayer"
                }
                """;
            dataOutputStream.writeUTF(request);
        }
    }
}
