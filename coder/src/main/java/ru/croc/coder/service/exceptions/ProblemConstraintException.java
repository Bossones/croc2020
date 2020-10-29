package ru.croc.coder.service.exceptions;

public class ProblemConstraintException extends RuntimeException {
    public ProblemConstraintException() {
    }

    public ProblemConstraintException(String message) {
        super(message);
    }
}
