package com.hau.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User already exists")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
