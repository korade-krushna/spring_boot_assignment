package com.upwork.spring_boot_assignment.controllers;

import com.upwork.spring_boot_assignment.entity.Product;
import com.upwork.spring_boot_assignment.http.ApiResponse;
import com.upwork.spring_boot_assignment.models.requests.AddProductsRequest;
import com.upwork.spring_boot_assignment.models.responses.UserSignInResponse;
import com.upwork.spring_boot_assignment.services.products.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> add(
      @Valid @RequestBody AddProductsRequest addProductsRequest, HttpServletRequest request) {
    ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
    apiResponse.setCode(HttpStatus.OK.value());
    apiResponse.setResult(productService.add(addProductsRequest));
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<?>> get(HttpServletRequest request) {
    ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
    apiResponse.setCode(HttpStatus.OK.value());
    apiResponse.setResult(productService.get());
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
