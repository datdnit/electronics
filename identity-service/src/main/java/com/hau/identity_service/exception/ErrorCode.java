package com.hau.identity_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED("Uncategorized error"),
    EMAIL_EXISTED("Email already exists"),
    INVALID_EMAIL_DOMAIN("Invalid email domain, email must end with @gmail.com"),
    PASSWORD_INVALID("Password must be at least 8 characters"),
    USER_NOT_FOUND("User not found")
    ;

    private final String message;

    ErrorCode(String message) {

        this.message = message;
    }
}