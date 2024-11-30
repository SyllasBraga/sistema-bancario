package com.senai.api.exceptions;

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(String msg){
        super(msg);
    }
}
