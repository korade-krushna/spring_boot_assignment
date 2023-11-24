package com.upwork.spring_boot_assignment.controllers;

import com.upwork.spring_boot_assignment.models.requests.UserSignInRequest;
import com.upwork.spring_boot_assignment.models.requests.UserSignUpRequest;
import com.upwork.spring_boot_assignment.models.responses.UserSignInResponse;
import com.upwork.spring_boot_assignment.models.responses.UserSignUpResponse;
import com.upwork.spring_boot_assignment.http.ApiResponse;
import com.upwork.spring_boot_assignment.services.users.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> add(@Valid @RequestBody UserSignUpRequest signUpRequest,
      HttpServletRequest request) {
    ApiResponse<UserSignUpResponse> apiResponse = new ApiResponse<>();
    apiResponse.setCode(HttpStatus.OK.value());
    apiResponse.setResult(userService.addUser(signUpRequest));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> authenticate(
      @Valid @RequestBody UserSignInRequest signInRequest, HttpServletRequest request) {
    ApiResponse<UserSignInResponse> apiResponse = new ApiResponse<>();
    apiResponse.setCode(HttpStatus.OK.value());
    apiResponse.setResult(userService.authenticateUser(signInRequest));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
