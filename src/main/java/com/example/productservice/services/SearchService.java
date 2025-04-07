package com.example.productservice.services;

import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SearchService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Implement search methods here
}
