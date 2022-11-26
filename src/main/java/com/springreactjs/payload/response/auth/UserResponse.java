package com.springreactjs.payload.response.auth;

import com.springreactjs.models.User;
import com.springreactjs.security.services.UserDetailsImplement;

import java.util.HashMap;
import java.util.Map;

public class UserResponse {
  public static Map<String, Object> build(User user) {
    Map<String, Object> userResponse = new HashMap<>();

    userResponse.put("_id", user.getId());

    if (user.getEmail() != null) {
      userResponse.put("email", user.getEmail());
    }

    if (user.getFirstName() != null) {
      userResponse.put("firstName", user.getFirstName());
    }

    if (user.getLastName() != null) {
      userResponse.put("lastName", user.getLastName());
    }

    if (user.getPhoneNumber() != null) {
      userResponse.put("phoneNumber", user.getPhoneNumber());
    }

    if (user.getAddress() != null) {
      userResponse.put("address", user.getAddress());
    }

    if (user.getImgBuyer() != null) {
      userResponse.put("imgBuyer", user.getImgBuyer());
    }

    if (user.getUserIdFacebook() != null) {
      userResponse.put("userIdFacebook", user.getImgBuyer());
    }

    return userResponse;
  }

  public static Map<String, Object> build(UserDetailsImplement userDetailsImplement) {
    Map<String, Object> userResponse = new HashMap<>();

    userResponse.put("_id", userDetailsImplement.getId());

    if (userDetailsImplement.getEmail() != null) {
      userResponse.put("email", userDetailsImplement.getEmail());
    }

    if (userDetailsImplement.getFirstName() != null) {
      userResponse.put("firstName", userDetailsImplement.getFirstName());
    }

    if (userDetailsImplement.getLastName() != null) {
      userResponse.put("lastName", userDetailsImplement.getLastName());
    }

    if (userDetailsImplement.getPhoneNumber() != null) {
      userResponse.put("phoneNumber", userDetailsImplement.getPhoneNumber());
    }

    if (userDetailsImplement.getAddress() != null) {
      userResponse.put("address", userDetailsImplement.getAddress());
    }

    if (userDetailsImplement.getImgBuyer() != null) {
      userResponse.put("imgBuyer", userDetailsImplement.getImgBuyer());
    }

    if (userDetailsImplement.getUserIdFacebook() != null) {
      userResponse.put("userIdFacebook", userDetailsImplement.getImgBuyer());
    }

    return userResponse;
  }
}
