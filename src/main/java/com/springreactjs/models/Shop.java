package com.springreactjs.models;

import javax.persistence.*;

@Entity
@Table(name = "shops")
public class Shop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "shop_name", nullable = false, unique = true, length = 100)
  private String shopName;

  @Column(name = "img_shop", unique = true)
  private String imgShop;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  public Shop() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getImgShop() {
    return imgShop;
  }

  public void setImgShop(String imgShop) {
    this.imgShop = imgShop;
  }

  public User getUser() {
    return user;
  }

}
