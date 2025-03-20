package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceDBImpl implements ProductServiceInterface {
    private ProductRepository productRepository;

    public ProductServiceDBImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException{
        Optional<Product> retrievedProduct = productRepository.findProductById(id);

        if(retrievedProduct.isEmpty()){
            throw new ProductNotFoundException("There is no product with ID : " + id);
        }

        return retrievedProduct.get();
    }
    @Override
    public String deleteProduct(Long id) {

        return null;
    }
    @Override
    public Product partialUpdateProduct(Product product) {
        return null;
    }
    @Override
    public Product replaceProduct(Product product) throws ProductNotFoundException {

        return null;
    }
}
