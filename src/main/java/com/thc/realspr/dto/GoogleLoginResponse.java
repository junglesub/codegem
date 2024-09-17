package com.thc.realspr.dto;

public class GoogleLoginResponse {
    private String token;

    public GoogleLoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
