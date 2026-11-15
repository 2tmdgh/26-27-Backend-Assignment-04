package com.example.security.global.exception;

public class InvalidTokenTypeException extends CustomException {

    public InvalidTokenTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
