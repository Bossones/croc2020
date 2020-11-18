package ru.croc.coder.service.exceptions;

public class TimeEndedException extends ProblemConstraintException{

    public TimeEndedException() {
    }

    public TimeEndedException(String message) {
        super(message);
    }
}
