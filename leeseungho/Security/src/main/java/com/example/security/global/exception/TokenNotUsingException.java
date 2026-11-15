package com.example.security.global.exception;

public class TokenNotUsingException extends RuntimeException {
    public TokenNotUsingException(String message) {
        super(message);
    }
}
