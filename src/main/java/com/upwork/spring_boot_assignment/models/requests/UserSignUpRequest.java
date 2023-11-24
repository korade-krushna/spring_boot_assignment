package com.upwork.spring_boot_assignment.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserSignUpRequest {

  @NotBlank(message = "please enter username")
  @NotNull
  private String username;
  @NotBlank(message = "please enter password")
  @NotNull
  private String password;
}
