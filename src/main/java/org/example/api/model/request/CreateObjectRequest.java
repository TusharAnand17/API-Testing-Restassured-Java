package org.example.api.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = CreateObjectRequest.Builder.class)
public class CreateObjectRequest {

    @JsonProperty("name")
    private final String name;

    @JsonProperty("data")
    private final CreateObjectData data;

    private CreateObjectRequest(Builder builder) {
        this.name = builder.name;
        this.data = builder.data;
    }

    public String getName() {
        return name;
    }

    public CreateObjectData getData() {
        return data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private CreateObjectData data;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder data(CreateObjectData data) {
            this.data = data;
            return this;
        }

        public CreateObjectRequest build() {
            if (name == null || data == null) {
                throw new IllegalStateException("name and data are required");
            }
            return new CreateObjectRequest(this);
        }
    }
}
