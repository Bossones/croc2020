package ru.croc.coder.service.exceptions;

public class StudentDoesNotEnrollException extends RuntimeException {
    public StudentDoesNotEnrollException() {
    }

    public StudentDoesNotEnrollException(String message) {
        super(message);
    }
}
