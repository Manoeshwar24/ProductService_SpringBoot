package com.example.productservice.strategies.filteringstrategies.product;

import com.example.productservice.enums.ProductFilterCriteria;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FilterByProductPrice implements FilterProductInterface{
    private final ProductRepository productRepository;

    public FilterByProductPrice(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductFilterCriteria getFilterCriteria() {
        return ProductFilterCriteria.PRICE_FILTER;
    }

    @Override
    public List<Product> filter(List<Product> productList, List<String> filterValues) {

        return productRepository.findAllByPriceIn(filterValues.stream()
                .map(Double::parseDouble)
                .toList());
    }
}
