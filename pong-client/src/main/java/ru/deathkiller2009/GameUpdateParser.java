package ru.deathkiller2009;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class GameUpdateParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public GameUpdate parseRequest(byte[] requestBytes) throws JsonProcessingException {
        String request = new String(requestBytes, StandardCharsets.UTF_8);
        int space = request.indexOf("\r\n\r\n");
        String header = request.substring(2, space);
        String jsonBody = request.substring(space + 4);
        jsonBody = jsonBody.replaceAll("\\s", "");
        return objectMapper.readValue(jsonBody, GameUpdate.class);
    }

}
