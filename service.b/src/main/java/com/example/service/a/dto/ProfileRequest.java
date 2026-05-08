package com.example.service.a.dto;

import java.io.Serializable;

public class ProfileRequest implements Serializable {

    private String token;

    public ProfileRequest() {
    }

    public ProfileRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}