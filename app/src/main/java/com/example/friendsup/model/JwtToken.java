package com.example.friendsup.model;

public class JwtToken {
    public String token;

    public JwtToken(String response) {
        this.token = response;
    }

    public String getToken() {
        return token;
    }
}
