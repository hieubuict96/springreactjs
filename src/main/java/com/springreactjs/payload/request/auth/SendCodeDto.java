package com.springreactjs.payload.request.auth;

import javax.validation.constraints.NotNull;

public class SendCodeDto {
  @NotNull
  private String phoneNumber;

  @NotNull
  private String code;

  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
