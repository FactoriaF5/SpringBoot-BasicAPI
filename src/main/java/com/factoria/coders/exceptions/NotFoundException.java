package com.factoria.coders.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException{

    private String code;
    private HttpStatus httpStatus;

    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
