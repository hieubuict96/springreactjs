package com.springreactjs.payload.response;

public class SuccessResponse {
  private String success = "success";

  public SuccessResponse() {}

  public SuccessResponse(String message) {
    this.success = message;
  }

  public String getSuccess() {
    return success;
  }
}
