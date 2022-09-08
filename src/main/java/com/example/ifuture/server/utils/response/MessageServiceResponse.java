package com.example.ifuture.server.utils.response;

public enum MessageServiceResponse {
    UNKNOWN_PROBLEM("Unknown problem"),
    OK("Well done");

    private final String message;

    MessageServiceResponse(String message) {
        this.message = message;
    }
}
