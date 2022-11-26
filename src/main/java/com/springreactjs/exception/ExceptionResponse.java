package com.springreactjs.exception;

public class ExceptionResponse {
  private String error;

  public ExceptionResponse(String message) {
    this.error = message;
  }

  public String getError() {
    return error;
  }
}
