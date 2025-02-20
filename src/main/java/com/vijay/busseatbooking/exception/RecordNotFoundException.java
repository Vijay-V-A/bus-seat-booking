package com.vijay.busseatbooking.exception;


public class RecordNotFoundException extends RuntimeException {

    private final String message;

    public RecordNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
