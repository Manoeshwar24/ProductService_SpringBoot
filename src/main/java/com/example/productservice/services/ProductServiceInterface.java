package com.example.productservice.services;

import com.example.productservice.models.Product;

import java.util.List;

public interface ProductServiceInterface {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getSingleProduct(int id);
    String deleteProduct(int id);
    Product partialUpdateProduct(Product product);
    Product replaceProduct(Product product);
}
