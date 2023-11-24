package com.upwork.spring_boot_assignment.security.config;

import com.upwork.spring_boot_assignment.repository.UserRepository;
import com.upwork.spring_boot_assignment.security.fiters.JwtFilter;
import com.upwork.spring_boot_assignment.security.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

  private final JwtUtils jwtUtils;
  private final UserRepository userRepository;

  public SecurityConfig(JwtUtils jwtUtils, UserRepository userRepository) {
    this.jwtUtils = jwtUtils;
    this.userRepository = userRepository;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests(
                    authorizeRequests ->
                            authorizeRequests.requestMatchers("/user/add", "/user/authenticate").permitAll()
     .anyRequest().authenticated())
            .addFilterBefore(new JwtFilter(jwtUtils), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
