package com.springreactjs.security.services;

import java.util.Set;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springreactjs.models.Role;
import com.springreactjs.models.Shop;
import com.springreactjs.models.User;

public class UserDetailsImplement implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private String password;

  private String phoneNumber;

  private String address;

  private String imgBuyer;

  private String userIdFacebook;

  private Shop shop;

  private Set<? extends GrantedAuthority> authorities;

  UserDetailsImplement(User user, boolean isUsernameAnEmail) {
    this.id = user.getId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();

    if (isUsernameAnEmail) {
      this.username = user.getEmail();
    } else {
      this.username = user.getPhoneNumber();
    }

    this.email = user.getEmail();
    this.password = user.getPassword();
    this.phoneNumber = user.getPhoneNumber();
    this.address = user.getAddress();
    this.imgBuyer = user.getImgBuyer();
    this.userIdFacebook = user.getUserIdFacebook();

    Set<Role> roles = user.getRoles();
    Set<GrantedAuthority> authorities = new HashSet<>();

    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    }

    this.authorities = authorities;
    this.shop = user.getShop();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getImgBuyer() {
    return imgBuyer;
  }

  public void setImgBuyer(String imgBuyer) {
    this.imgBuyer = imgBuyer;
  }

  public String getUserIdFacebook() {
    return userIdFacebook;
  }

  public void setUserIdFacebook(String userIdFacebook) {
    this.userIdFacebook = userIdFacebook;
  }

  public Shop getShop() {
    return shop;
  }

  public void setShop(Shop shop) {
    this.shop = shop;
  }

  @Override
  public Set<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
