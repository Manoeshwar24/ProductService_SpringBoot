package com.example.productservice.services;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceDBImpl implements ProductServiceInterface {
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product getSingleProduct(int id) {
        return null;
    }

    @Override
    public String deleteProduct(int id) {

        return null;
    }

    @Override
    public Product partialUpdateProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product) {

        return null;
    }
}
