package com.springreactjs.payload.response.auth;

import com.springreactjs.models.User;
import com.springreactjs.security.services.UserDetailsImplement;

import java.util.Map;

public class SigninResponse {
  private Map<String, Object> user;

  private String accessToken;

  public SigninResponse(User user, String accessToken) {
    this.user = UserResponse.build(user);
    this.accessToken = accessToken;
  }

  public SigninResponse(UserDetailsImplement userDetailsImplement, String accessToken) {

  }

  public Map<String, Object> getUser() {
    return user;
  }

  public void setUser(Map<String, Object> user) {
    this.user = user;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
