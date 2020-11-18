package ru.croc.coder.service.exceptions;

public class CourseCreatingPermissionDenied extends RuntimeException {
    public CourseCreatingPermissionDenied() {
    }

    public CourseCreatingPermissionDenied(String message) {
        super(message);
    }
}
