package com.hau.identity_service.exception;

public enum SuccessMessage {
    CREATED_USER("Created user success"),
    UPDATE_USER("Update user success"),
    GET_ALL_USERS("Get all users success"),
    GET_USER_BY_ID("Get user by id success"),
    DELETE_USER("Delete user success");

    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}