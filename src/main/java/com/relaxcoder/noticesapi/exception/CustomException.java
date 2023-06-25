package com.relaxcoder.noticesapi.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public CustomException(HttpStatus httpStatus, String message){
        this.status = httpStatus;
        this.message = message;
    }

    public CustomException(String message, HttpStatus status, String message1){
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
