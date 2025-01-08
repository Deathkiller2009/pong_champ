package ru.deathkiller2009;

import ru.deathkiller2009.request.Request;

public interface RequestProcessor {

    void processRequest(Request request);

    boolean canBeProcessed(Request request);
}
