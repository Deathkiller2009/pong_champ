package ru.deathkiller2009;

import lombok.Getter;
import lombok.Setter;

import java.net.Socket;
import java.util.List;

@Getter
@Setter
public class Session {

    private int sessionId;
    private List<Player> players;
    private Socket player1Socket;
    private Socket player2Socket;
    private String player1Name;
    private String player2Name;

}
