package com.vijay.busseatbooking.exception;

public class RecordExistException extends RuntimeException {

    private final String message;

    public RecordExistException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
