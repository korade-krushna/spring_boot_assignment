package com.upwork.spring_boot_assignment.security.utils;


import com.upwork.spring_boot_assignment.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String secret;

  public String extractTokenFromRequest(HttpServletRequest request) {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.isNullOrEmpty(authorizationHeader)) {
      throw new JwtException("authorization header is missing");
    }
    if (!authorizationHeader.contains("Bearer ")) {
      throw new JwtException("authorization header is invalid");
    }
    return authorizationHeader.replace("Bearer ", "");
  }

  public Long authenticateToken(String token) {
    Claims claims = extractClaimsFromToken(token, secret);
    Date expDate = claims.getExpiration();
    boolean isTokenExpired = expDate.getTime() < System.currentTimeMillis();
    if (isTokenExpired) {
      throw new JwtException("session expired");
    }
    return claims.get("id", Long.class);
  }

  private Claims extractClaimsFromToken(String token, String secret) {
    return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
  }

}
