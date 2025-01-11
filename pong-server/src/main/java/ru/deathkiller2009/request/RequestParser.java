package ru.deathkiller2009.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

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
