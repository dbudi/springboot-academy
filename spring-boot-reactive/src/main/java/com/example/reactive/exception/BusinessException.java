package com.example.reactive.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable throwable){
        super(message, throwable);
    }

    public BusinessException(Throwable throwable){
        super(throwable);
    }
}
