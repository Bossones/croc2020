package ru.croc.coder.service.exceptions;

public class TaskHasNotStartedException extends ProblemConstraintException {
    public TaskHasNotStartedException() {
    }

    public TaskHasNotStartedException(String message) {
        super(message);
    }
}
