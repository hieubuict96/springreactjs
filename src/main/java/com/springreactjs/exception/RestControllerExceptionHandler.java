package com.springreactjs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ExceptionResponse> badRequestExceptionResponse(BadRequestException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ExceptionResponse> unauthorizedExceptionResponse(UnauthorizedException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionResponse> notFoundExceptionResponse(NotFoundException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ServerErrorException.class)
  public ResponseEntity<ExceptionResponse> serverErrorExceptionResponse(ServerErrorException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
