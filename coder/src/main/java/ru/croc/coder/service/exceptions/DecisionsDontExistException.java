package ru.croc.coder.service.exceptions;

public class DecisionsDontExistException extends RuntimeException {
    public DecisionsDontExistException() {
    }

    public DecisionsDontExistException(String message) {
        super(message);
    }
}
