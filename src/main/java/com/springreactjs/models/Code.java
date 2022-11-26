package com.springreactjs.models;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "code")
public class Code {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "phone_number", unique = true)
  private String phoneNumber;

  @Email
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "time_send_code", nullable = false)
  private Long timeSendCode;

  public Code() {}

  public Code(String phoneNumber, String code, Long timeSendCode) {
    this.phoneNumber = phoneNumber;
    this.code = code;
    this.timeSendCode = timeSendCode;
  }

  public Code(Long timeSendCode, String code, String email) {
    this.timeSendCode = timeSendCode;
    this.code = code;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }

  public Long getTimeSendCode() {
    return timeSendCode;
  }

  public void setTimeSendCode(Long timeSendCode) {
    this.timeSendCode = timeSendCode;
  }
}
