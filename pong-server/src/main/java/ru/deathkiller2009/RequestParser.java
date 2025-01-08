package ru.deathkiller2009;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.deathkiller2009.request.Request;
import ru.deathkiller2009.request.RequestFactory;
import ru.deathkiller2009.request.SinglePlayerRequest;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class RequestParser {

    private final RequestFactory requestFactory;

    public Request parseRequest(byte[] requestBytes) throws JsonProcessingException {
        String request = new String(requestBytes, StandardCharsets.UTF_8);
        int space = request.indexOf("\r\n\r\n");
        String header = request.substring(2, space);
        String jsonBody = request.substring(space + 4);
        jsonBody = jsonBody.replaceAll("\\s", "");
        return requestFactory.makeRequest(header, jsonBody);
    }

}
