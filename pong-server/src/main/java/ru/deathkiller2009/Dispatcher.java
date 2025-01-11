package ru.deathkiller2009;

import lombok.RequiredArgsConstructor;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class Dispatcher {

    List<RequestProcessor> requestProcessors = new LinkedList<>();

    public void redirectRequest(Request request, Socket socket, DataInputStream dataInputStream) {
        for (RequestProcessor processor: requestProcessors){
            if (processor.canBeProcessed(request)) {
                processor.processRequest(request, socket, dataInputStream);
                break;
            }
        }
    }

    public void registerRequestProcessor(RequestProcessor requestProcessor) {
        requestProcessors.add(requestProcessor);
    }
}
