package com.upwork.spring_boot_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringBootAssignmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootAssignmentApplication.class, args);
  }

}
