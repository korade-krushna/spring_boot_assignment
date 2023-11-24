package com.upwork.spring_boot_assignment.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class ProductRecordRequest {
  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date entryDate;
  private String itemcode;
  private String itemname;
  private int itemquantity;
  private String upc;
  private String ean;
  private String sku;
  private String isbn;
  private String mpc;
  private String sStatus;
}
