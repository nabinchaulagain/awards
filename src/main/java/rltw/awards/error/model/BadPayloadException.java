package rltw.awards.error.model;

import java.util.Map;

public class BadPayloadException extends RuntimeException {
    private Map<String, Object> response;

    public BadPayloadException(Map<String, Object> response) {
        this.response = response;
    }

    public Map<String, Object> getResponse() {
        return response;
    }
}
