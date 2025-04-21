package com.example.itinerarymaker.exception;

public class InvalidDatesException extends RuntimeException {

    public InvalidDatesException() {
    }

    public InvalidDatesException(String message) {
        super(message);
    }
}
