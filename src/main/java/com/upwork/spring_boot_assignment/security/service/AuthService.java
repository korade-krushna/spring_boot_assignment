package com.upwork.spring_boot_assignment.security.service;

import com.upwork.spring_boot_assignment.entity.User;
import com.upwork.spring_boot_assignment.exceptions.ApiExecutionException;
import com.upwork.spring_boot_assignment.models.requests.UserSignInRequest;
import com.upwork.spring_boot_assignment.models.responses.UserSignInResponse;
import com.upwork.spring_boot_assignment.security.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
@Slf4j
public class AuthService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.max_life}")
  private Long jwtMaxLife;

  @Value("${jwt.subject}")
  private String jwtSubject;

  @Value("${jwt.issuer}")
  private String jwtIssuer;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public AuthService(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }


  private String generateToken(Long id, String secret) {
    Date jwtIssuedAt = new Date(System.currentTimeMillis());
    Date jwtExpiredAt = new Date(System.currentTimeMillis() + jwtMaxLife);
    return Jwts.builder().setSubject(jwtSubject).setIssuer(jwtIssuer).claim("id", id)
        .setIssuedAt(jwtIssuedAt).setExpiration(jwtExpiredAt)
        .signWith(SignatureAlgorithm.HS512, Base64.getDecoder().decode(secret)).compact();
  }

  public UserSignInResponse authenticateUser(UserSignInRequest signInRequest, User user) {
    if (!bCryptPasswordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
      throw new ApiExecutionException("Incorrect password");
    }
    UserSignInResponse userSignInResponse = new UserSignInResponse();
    String accessToken = generateToken(user.getId(), secret);
    userSignInResponse.setAccessToken(accessToken);
    return userSignInResponse;
  }
}
