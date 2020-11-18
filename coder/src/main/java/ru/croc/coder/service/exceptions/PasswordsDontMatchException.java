package ru.croc.coder.service.exceptions;

public class PasswordsDontMatchException extends RuntimeException {

    public PasswordsDontMatchException() {
    }

    public PasswordsDontMatchException(String message) {
        super(message);
    }
}
