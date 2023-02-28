package com.epam.esm.model.jwt;

public class AuthenticationResponse {

    private final String jwtToken;


    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }
}
