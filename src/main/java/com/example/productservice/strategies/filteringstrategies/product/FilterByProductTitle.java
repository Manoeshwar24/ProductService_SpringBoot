package com.example.productservice.strategies.filteringstrategies.product;

import com.example.productservice.enums.ProductFilterCriteria;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterByProductTitle implements FilterProductInterface{
    private final ProductRepository productRepository;

    public FilterByProductTitle(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductFilterCriteria getFilterCriteria() {
        return ProductFilterCriteria.TITLE_FILTER;
    }

    @Override
    public List<Product> filter(List<Product> productList, List<String> filterValues) {

        // Filter the productList by the different titles in the filter values
        return productRepository.findAllByTitleIn(filterValues);
    }
}
