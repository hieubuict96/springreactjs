package com.springreactjs.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", length = 100)
  private String lastName;

  @Email
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "phone_number", length = 32, unique = true)
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "img_buyer")
  private String imgBuyer;

  @Column(name = "user_id_facebook", unique = true, length = 50)
  private String userIdFacebook;

  @OneToOne(mappedBy = "user")
  private Shop shop;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {

  }

  public User(String firstName, String lastName, String hashPassword, String phoneNumber, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = hashPassword;
    this.phoneNumber = phoneNumber;
    this.roles.add(role);
  }

  public User(String firstName, String lastName, String email, String password, String phoneNumber, String address,
      String imgBuyer, String userIdFacebook) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.imgBuyer = imgBuyer;
    this.userIdFacebook = userIdFacebook;
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
    this.firstName = email;
  }

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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}