package ru.croc.coder.service.exceptions;

public class TeacherIsBusyException extends RuntimeException {
    public TeacherIsBusyException() {
    }

    public TeacherIsBusyException(String message) {
        super(message);
    }
}
