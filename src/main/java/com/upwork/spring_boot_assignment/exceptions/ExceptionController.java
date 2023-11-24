package com.upwork.spring_boot_assignment.exceptions;

import com.upwork.spring_boot_assignment.http.ApiResponse;
import com.upwork.spring_boot_assignment.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {


  @ExceptionHandler(value = ApiExecutionException.class)
  public ResponseEntity<ApiResponse<?>> apiExecutionException(ApiExecutionException e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<Object> apiResponse = new ApiResponse<>();
    apiResponse.setMessage(e.getMessage());
    return ResponseEntity.ok(apiResponse);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiResponse<?>> exception(Exception e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<Object> apiResponse = new ApiResponse<>();
    apiResponse.setMessage("Something's wrong");
    return new ResponseEntity<>(apiResponse, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiResponse<?>> httpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<Object> apiResponse = new ApiResponse<>();
    apiResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(apiResponse, HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> methodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    log.error("exception {}", e.getMessage());
    ApiResponse<Object> apiResponse = new ApiResponse<>();
    apiResponse.setMessage(e.getAllErrors().get(0).getDefaultMessage());
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }
}
