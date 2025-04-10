package com.example.productservice.strategies.filteringstrategies.product;

import com.example.productservice.models.Product;

import java.util.List;

public interface FilterProductInterface {
    List<Product> filter(List<Product> productList);
}
