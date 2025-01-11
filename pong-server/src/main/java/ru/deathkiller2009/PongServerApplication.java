package ru.deathkiller2009;

import ru.deathkiller2009.request.*;

import java.io.IOException;

public class PongServerApplication {
    public static void main(String[] args) throws IOException {
        DefaultRequestFactoryImpl requestFactory = new DefaultRequestFactoryImpl();
        RequestParser requestParser = new RequestParser(requestFactory);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.registerRequestProcessor(new GameRequestProcessor());
        RequestReceiver requestReceiver = new RequestReceiver(requestParser, dispatcher);
        Server server = new Server(requestReceiver);
        server.start();
    }
}
