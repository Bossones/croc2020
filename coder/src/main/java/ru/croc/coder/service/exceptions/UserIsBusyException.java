package ru.croc.coder.service.exceptions;

public class UserIsBusyException extends RuntimeException {
    public UserIsBusyException() {
    }

    public UserIsBusyException(String message) {
        super(message);
    }
}
