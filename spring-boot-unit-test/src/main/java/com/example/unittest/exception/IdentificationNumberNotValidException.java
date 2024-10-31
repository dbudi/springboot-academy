package com.example.unittest.exception;

public class IdentificationNumberNotValidException extends RuntimeException{
    public IdentificationNumberNotValidException(String message){
        super(message);
    }
}
