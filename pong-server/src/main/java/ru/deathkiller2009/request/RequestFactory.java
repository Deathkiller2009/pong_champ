package ru.deathkiller2009.request;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestFactory {

    Request makeRequest(String header, String jsonData) throws JsonProcessingException;

}
