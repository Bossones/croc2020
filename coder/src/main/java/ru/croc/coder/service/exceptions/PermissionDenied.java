package ru.croc.coder.service.exceptions;

public class PermissionDenied extends RuntimeException {
    public PermissionDenied() {
    }

    public PermissionDenied(String message) {
        super(message);
    }
}
