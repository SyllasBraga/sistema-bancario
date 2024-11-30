package com.senai.backend.exceptions.handler;

import com.senai.backend.exceptions.BadRequestException;
import com.senai.backend.exceptions.NotFoundException;
import com.senai.backend.exceptions.errors.StandardError;
import com.senai.backend.exceptions.errors.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

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
