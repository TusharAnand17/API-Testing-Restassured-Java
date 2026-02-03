package org.example.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjectResponse {
    private String id;
    private String name;
    private ObjectData data;
    private String createdAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ObjectData getData() {
        return data;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
