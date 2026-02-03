package org.example.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTokenRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    private CreateTokenRequest(Builder builder){
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String username;
        private String password;

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public CreateTokenRequest build(){
            if(username == null || password == null){
                throw new IllegalStateException("username and password are required");
            }
            return new CreateTokenRequest(this);
        }
    }
}
