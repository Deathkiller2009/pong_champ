package ru.deathkiller2009;

import lombok.RequiredArgsConstructor;
import ru.deathkiller2009.request.Request;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class Dispatcher {

    List<RequestProcessor> requestProcessors = new LinkedList<>();

    public void redirectRequest(Request request) {
        for (RequestProcessor processor: requestProcessors){
            if (processor.canBeProcessed(request)) {
                processor.processRequest(request);
                break;
            }
        }
    }

    public void registerRequestProcessor(RequestProcessor requestProcessor) {
        requestProcessors.add(requestProcessor);
    }
}
