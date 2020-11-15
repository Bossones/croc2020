package ru.croc.coder.service.exceptions;

public class TimeEndedException extends RuntimeException {

    public TimeEndedException() {
    }

    public TimeEndedException(String message) {
        super(message);
    }
}
