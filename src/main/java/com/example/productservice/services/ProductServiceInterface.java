package com.example.productservice.services;

import com.example.productservice.dtos.mydtos.GetProductResponseDTO;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductServiceInterface {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getSingleProduct(int id);
    Product deleteProduct(int id);
    Product updateProduct(Product product);
    void replaceProduct(Product product);
}
