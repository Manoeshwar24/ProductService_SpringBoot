package com.example.productservice.strategies.filteringstrategies.product;

import com.example.productservice.models.Product;

import java.util.List;

public class FilterByProductPrice implements FilterProductInterface{
    @Override
    public List<Product> filter(List<Product> productList) {
        return List.of();
    }
}
