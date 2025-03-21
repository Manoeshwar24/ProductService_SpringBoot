package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface ProductServiceInterface {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    String deleteProduct(Long id);
    Product partialUpdateProduct(Long id, Product product) throws ProductNotFoundException, BadRequestException;
    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;
}
