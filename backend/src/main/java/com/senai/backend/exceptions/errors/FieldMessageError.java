package com.senai.backend.exceptions.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessageError {

    private String fieldMessage;
    private String name;
}
