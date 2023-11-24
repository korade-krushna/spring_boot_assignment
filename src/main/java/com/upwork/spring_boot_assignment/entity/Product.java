package com.upwork.spring_boot_assignment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "entry_date")
  private Date entryDate;

  @Column(name = "item_code")
  private String itemcode;

  @Column(name = "item_name")
  private String itemname;

  @Column(name = "item_quantity")
  private int itemquantity;

  @Column(name = "upc")
  private String upc;

  @Column(name = "ean")
  private String ean;

  @Column(name = "sku")
  private String sku;

  @Column(name = "isbn")
  private String isbn;

  @Column(name = "mpc")
  private String mpc;

  @Column(name = "s_status")
  private String sStatus;
}
