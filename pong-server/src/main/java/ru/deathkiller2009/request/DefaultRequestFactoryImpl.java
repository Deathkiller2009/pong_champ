package ru.deathkiller2009.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultRequestFactoryImpl implements RequestFactory {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Request makeRequest(String header, String jsonData) throws JsonProcessingException {
        return switch (header) {
            case "START_GAME" -> objectMapper.readValue(jsonData, SinglePlayerRequest.class);
            case "START_MULTIPLAYER_GAME" -> objectMapper.readValue(jsonData, MultiPlayerRequest.class);
            default -> null;
        };

    }

}
