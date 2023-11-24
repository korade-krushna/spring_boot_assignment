package com.upwork.spring_boot_assignment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class User {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "username", unique = true, nullable = false)
  private String username;
  @Column(name = "password", nullable = false)
  private String password;
}
