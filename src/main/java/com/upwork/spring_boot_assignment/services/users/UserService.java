package com.upwork.spring_boot_assignment.services.users;

import com.upwork.spring_boot_assignment.entity.User;
import com.upwork.spring_boot_assignment.exceptions.ApiExecutionException;
import com.upwork.spring_boot_assignment.models.requests.UserSignInRequest;
import com.upwork.spring_boot_assignment.models.requests.UserSignUpRequest;
import com.upwork.spring_boot_assignment.models.responses.UserSignInResponse;
import com.upwork.spring_boot_assignment.models.responses.UserSignUpResponse;
import com.upwork.spring_boot_assignment.repository.UserRepository;
import com.upwork.spring_boot_assignment.security.service.AuthService;
import com.upwork.spring_boot_assignment.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final AuthService authService;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, AuthService authService,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.authService = authService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  private final Function<UserSignUpRequest, User> prepareUser = signUpRequest -> {
    User user = new User();
    user.setUsername(signUpRequest.getUsername());
    user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
    return user;
  };

  private final Function<User, UserSignUpResponse> prepareSignUpResponse = user -> {
    UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
    userSignUpResponse.setUsername(user.getUsername());
    userSignUpResponse.setId(user.getId());
    userSignUpResponse.setGreetings("Welcome aboard !!!");
    return userSignUpResponse;
  };

  public UserSignUpResponse addUser(UserSignUpRequest signUpRequest) {
    Optional<User> userOpt = userRepository.findByUsername(signUpRequest.getUsername());
    if (userOpt.isPresent()) {
      throw new ApiExecutionException("username is not available");
    }
    log.info("user {}", JacksonUtils.toJson(signUpRequest));
    User user = userRepository.save(prepareUser.apply(signUpRequest));
    return prepareSignUpResponse.apply(user);
  }

  public UserSignInResponse authenticateUser(UserSignInRequest signInRequest) {
    Optional<User> userOpt = userRepository.findByUsername(signInRequest.getUsername());
    if (userOpt.isEmpty()) {
      throw new ApiExecutionException("user with username dose not exists");
    }
    return authService.authenticateUser(signInRequest, userOpt.get());
  }
}
