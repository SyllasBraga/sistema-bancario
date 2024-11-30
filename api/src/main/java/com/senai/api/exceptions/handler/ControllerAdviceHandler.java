package com.senai.api.exceptions.handler;
import java.time.Instant;

import java.util.NoSuchElementException;

import com.senai.api.exceptions.BadRequestException;
import com.senai.api.exceptions.NoContentException;
import com.senai.api.exceptions.NotFoundException;
import com.senai.api.exceptions.errors.StandardError;
import com.senai.api.exceptions.errors.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> badRequestException(BadRequestException e,
                                                             HttpServletRequest http) {
        StandardError error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> notFoundException(NotFoundException e, HttpServletRequest http) {
        StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> noSuchElementExceptionnotFoundException(NoSuchElementException e, HttpServletRequest http) {
        StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NoContentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> noContentenExceptionnotFoundException(NoContentException e, HttpServletRequest http) {
        StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e,
                                                               HttpServletRequest http) {
        ValidationError error = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                e.getTitleMessageCode(), http.getRequestURI());
        for (FieldError field : e.getFieldErrors()) {
            error.addListErrors(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
