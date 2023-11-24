package com.upwork.spring_boot_assignment.http;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
  private Integer code;
  private String message;
  private T result;

  public ApiResponse(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
