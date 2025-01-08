package ru.deathkiller2009;

import ru.deathkiller2009.request.Request;
import ru.deathkiller2009.request.SinglePlayerRequest;

public class SinglePlayerRequestProcessor implements RequestProcessor {

    @Override
    public void processRequest(Request request) {
        //todo отправить игроку ack, т.е. что мы поняли что он хочет начать синглплеер сессию
        //todo создать сессию, если игрок с таким ником уже играет написать что что-то не то
        //todo начать игру
        System.out.println(request.toString());
    }

    @Override
    public boolean canBeProcessed(Request request) {
        return request instanceof SinglePlayerRequest;
    }
}
