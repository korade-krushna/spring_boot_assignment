package com.upwork.spring_boot_assignment.services.products;

import com.upwork.spring_boot_assignment.entity.Product;
import com.upwork.spring_boot_assignment.models.requests.AddProductsRequest;
import com.upwork.spring_boot_assignment.models.requests.ProductRecordRequest;
import com.upwork.spring_boot_assignment.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  private final Function<ProductRecordRequest, Product> prepareProduct = productRecordRequest -> {
    Product product = new Product();
    product.setEntryDate(productRecordRequest.getEntryDate());
    product.setItemcode(productRecordRequest.getItemcode());
    product.setItemname(productRecordRequest.getItemname());
    product.setItemquantity(productRecordRequest.getItemquantity());
    product.setUpc(productRecordRequest.getUpc());
    product.setEan(productRecordRequest.getEan());
    product.setSku(productRecordRequest.getSku());
    product.setIsbn(productRecordRequest.getIsbn());
    product.setMpc(productRecordRequest.getMpc());
    product.setSStatus(productRecordRequest.getSStatus());
    return product;
  };

  public List<Product> add(AddProductsRequest addProductsRequest) {
    return productRepository.saveAll(
        addProductsRequest.getRecords().stream().map(prepareProduct).collect(Collectors.toList()));
  }

  public List<Product> get() {
    return productRepository.findAll();
  }
}
