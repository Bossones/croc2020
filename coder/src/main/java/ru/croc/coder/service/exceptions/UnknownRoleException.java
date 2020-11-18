package ru.croc.coder.service.exceptions;

public class UnknownRoleException extends RuntimeException {
    public UnknownRoleException() {
    }

    public UnknownRoleException(String message) {
        super(message);
    }
}
