package com.example.productservice.strategies.sortingstrategies.product;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SortByProductTitleLowToHigh implements SortProductInterface {
    private ProductRepository productRepository;
    @Override
    public List<Product> findAllAndSort(String query) {

        //find all products by title and sort by title ascending
        List<Product> productList = productRepository.findAllByTitleIgnoreCaseOrderByTitleAsc(query);
        return productList;
    }
}
