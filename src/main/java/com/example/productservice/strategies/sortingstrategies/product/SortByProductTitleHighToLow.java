package com.example.productservice.strategies.sortingstrategies.product;

import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SortByProductTitleHighToLow implements SortProductInterface {

    private ProductRepository productRepository;
    @Override
    public List<Product> findAllAndSort(String query) {

        //find all the products based on the query and sort them by title high to low
        List<Product> productList = productRepository.findAllByTitleIgnoreCaseOrderByTitleDesc(query);
        return productList;
    }
}
