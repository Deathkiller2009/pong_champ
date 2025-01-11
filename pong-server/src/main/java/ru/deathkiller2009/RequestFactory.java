package ru.deathkiller2009;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestFactory {

    Request makeRequest(String header, String jsonData) throws JsonProcessingException;

}
