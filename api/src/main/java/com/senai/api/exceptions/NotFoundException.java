package com.senai.api.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String msg) {
        super(msg);
    }
}
