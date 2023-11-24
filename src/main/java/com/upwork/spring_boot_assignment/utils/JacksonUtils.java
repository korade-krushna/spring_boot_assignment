package com.upwork.spring_boot_assignment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JacksonUtils {

  private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

  private static ObjectMapper objectMapper = new ObjectMapper();

  private JacksonUtils() {}

  public static ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      logger.error("Exception while converting Object to JSON due to {}",
          StringUtils.printStackTrace(e));
      return null;
    }
  }
}
