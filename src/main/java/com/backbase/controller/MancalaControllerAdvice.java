package com.backbase.controller;

import com.backbase.exception.GameNotFoundException;
import com.backbase.exception.InvalidPitNumberException;
import com.backbase.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class MancalaControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({GameNotFoundException.class})
    public ResponseEntity handleGameNotFoundException(GameNotFoundException exception) {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidPitNumberException.class})
    public ResponseEntity handleInvalidPitNumberException(InvalidPitNumberException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleUnexpectedException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
