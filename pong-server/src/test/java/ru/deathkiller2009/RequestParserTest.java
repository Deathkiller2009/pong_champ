package ru.deathkiller2009;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.deathkiller2009.request.RequestParser;
import ru.deathkiller2009.request.SinglePlayerRequest;

import java.nio.charset.StandardCharsets;

class RequestParserTest {

    RequestParser requestParser;

    @Test
    void parseRequest_ReturnsValidRequestObject() throws JsonProcessingException {

        String request = """
               START_GAME\r
               \r
               {
                   "player": "deathkiller2009",
                   "mode": "singleplayer"
               }
                """;

        SinglePlayerRequest singlePlayerRequest = new SinglePlayerRequest("deathkiller2009", "singleplayer");
        ObjectMapper objectMapper = new ObjectMapper();
        requestParser.parseRequest(request.getBytes(StandardCharsets.UTF_8));
    }

}