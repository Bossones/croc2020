package ru.croc.coder.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.croc.coder.controller.dto.ExceptionDto;
import ru.croc.coder.service.exceptions.InvalidEmailException;
import ru.croc.coder.service.exceptions.PasswordsDontMatchException;
import ru.croc.coder.service.exceptions.ProblemConstraintException;
import ru.croc.coder.service.exceptions.UserExistsException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProblemConstraintException.class)
    public ResponseEntity<Object> handleProblemConstraintException(ProblemConstraintException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ExceptionDto(ex.getClass().getName(), ex.getMessage(), new HashMap<>() {{
                    put("sessionId", request.getSessionId()); }}),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExistsException(UserExistsException uex, WebRequest request) {
        return handleExceptionInternal(
                uex,
                new ExceptionDto(
                        uex.getClass().getSimpleName(),
                        uex.getMessage(),
                        Map.of("sessionId", request.getSessionId())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<Object> handleInvalidEmailException(InvalidEmailException iex, WebRequest request) {
        return handleExceptionInternal(
                iex,
                new ExceptionDto(
                        iex.getClass().getSimpleName(),
                        iex.getMessage(),
                        Map.of("sessionId", request.getSessionId())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PasswordsDontMatchException.class)
    public ResponseEntity<Object> handlePasswordDontMatchException(PasswordsDontMatchException pdmex, WebRequest request) {
        return handleExceptionInternal(
                pdmex,
                new ExceptionDto(
                        pdmex.getClass().getSimpleName(),
                        pdmex.getMessage(),
                        Map.of("sessionId", request.getSessionId())),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }
}
