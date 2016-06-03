package com.gillessed.daedalus.rest.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by gcole on 5/22/16.
 */
public class LoginResponse {
    private final String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    @JsonProperty
    public String getToken() {
        return token;
    }
}
