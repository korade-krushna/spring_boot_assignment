package com.upwork.spring_boot_assignment.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddProductsRequest {
  @NotBlank(message = "please provide table name")
  @NotNull
  private String table;
  @NotEmpty
  @NotNull
  private List<ProductRecordRequest> records;
}
