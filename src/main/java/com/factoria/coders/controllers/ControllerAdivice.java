package com.factoria.coders.controllers;

import com.factoria.coders.dtos.ErrorDto;
import com.factoria.coders.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdivice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runTimeExceptionHandler(RuntimeException ex) {
        var error = ErrorDto.builder().message(ex.getMessage()).code("P500").build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException ex) {
        var error = ErrorDto.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
