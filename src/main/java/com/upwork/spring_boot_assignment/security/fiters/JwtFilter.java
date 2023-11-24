package com.upwork.spring_boot_assignment.security.fiters;

import com.upwork.spring_boot_assignment.http.ApiResponse;
import com.upwork.spring_boot_assignment.security.utils.JwtUtils;
import com.upwork.spring_boot_assignment.utils.JacksonUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;

  @Autowired
  public JwtFilter(JwtUtils jwtUtils) {
    this.jwtUtils = jwtUtils;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = jwtUtils.extractTokenFromRequest(request);
      jwtUtils.authenticateToken(token);
      filterChain.doFilter(request, response);
    } catch (JwtException e) {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write(
          JacksonUtils.toJson(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage())));
    } catch (Exception e) {
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
      response.getWriter().write(JacksonUtils
          .toJson(new ApiResponse<>(HttpStatus.SERVICE_UNAVAILABLE.value(), "Something's wrong")));
    }
  }
}
