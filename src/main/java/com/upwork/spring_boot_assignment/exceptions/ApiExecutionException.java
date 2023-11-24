package com.upwork.spring_boot_assignment.exceptions;

public class ApiExecutionException extends RuntimeException {
  public ApiExecutionException(String message) {
    super(message);
  }
}
