package com.upwork.spring_boot_assignment.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSignInRequest {
  @NotBlank(message = "please enter username")
  private String username;
  @NotBlank(message = "please enter password")
  private String password;
}
