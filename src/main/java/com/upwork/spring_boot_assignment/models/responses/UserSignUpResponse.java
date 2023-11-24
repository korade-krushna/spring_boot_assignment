package com.upwork.spring_boot_assignment.models.responses;

import lombok.Data;

@Data
public class UserSignUpResponse {
  private Long id;
  private String username;
  private String greetings;
}
