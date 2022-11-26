package com.springreactjs.payload.request.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SendPhoneNumberDto {
  @NotNull
  @Pattern(regexp = "^\\+[0-9]{5,20}$")
  private String phoneNumber;

  public SendPhoneNumberDto() {}

  public SendPhoneNumberDto(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}
