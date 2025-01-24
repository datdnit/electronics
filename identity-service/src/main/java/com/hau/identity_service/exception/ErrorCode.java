package com.hau.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED("Uncategorized error"),
    EMAIL_EXISTED("Email already exists"),
    INVALID_EMAIL_DOMAIN("Invalid email domain, email must end with @gmail.com"),
    PASSWORD_INVALID("Password must be at least 8 characters"),
    USER_NOT_FOUND("User not found")
    ;

    ErrorCode(String message) {

        this.message = message;
    }
    private final String message;

    public String getMessage() {
        return message;
    }
}