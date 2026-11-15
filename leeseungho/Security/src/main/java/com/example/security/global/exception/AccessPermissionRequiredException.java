package com.example.security.global.exception;

public class AccessPermissionRequiredException extends CustomException {

    public AccessPermissionRequiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
