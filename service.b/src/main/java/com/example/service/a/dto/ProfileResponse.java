package com.example.service.a.dto;

import java.io.Serializable;

public class ProfileResponse implements Serializable {

    private boolean success;
    private String username;
    private String message;

    public ProfileResponse() {
    }

    public ProfileResponse(
            boolean success,
            String username,
            String message
    ) {
        this.success = success;
        this.username = username;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}