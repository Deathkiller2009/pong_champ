package ru.deathkiller2009;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultRequestFactoryImpl implements RequestFactory {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Request makeRequest(String header, String jsonData) throws JsonProcessingException {
        switch (header) {
            case "START_GAME": {
                return objectMapper.readValue(jsonData, SinglePlayerRequest.class);
            }
            case "START_MULTIPLAYER_GAME": {
                return objectMapper.readValue(jsonData, MultiPlayerRequest.class);
            }
        }

        return null;
    }

}
