package com.example.productservice.strategies.filteringstrategies.product;

import com.example.productservice.enums.ProductFilterCriteria;
import com.example.productservice.models.Product;

import java.util.List;

public interface FilterProductInterface {
    ProductFilterCriteria getFilterCriteria();
    List<Product> filter(List<Product> products, List<String> filterValues);
}