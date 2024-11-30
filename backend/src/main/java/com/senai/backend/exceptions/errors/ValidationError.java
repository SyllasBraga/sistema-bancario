package com.senai.backend.exceptions.errors;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Getter
public class ValidationError extends StandardError{
    List<FieldMessageError> listErrors = new ArrayList<>();

    public ValidationError(Instant timestamp, int status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public List<FieldMessageError> getListErrors(){
        return this.listErrors;
    }

    public void addListErrors(String field, String message){
        listErrors.add(new FieldMessageError(field, message));
    }
}
