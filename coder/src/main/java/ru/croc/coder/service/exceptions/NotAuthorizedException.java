package ru.croc.coder.service.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
    }

    public NotAuthorizedException(String message) {
        super(message);
    }
}
