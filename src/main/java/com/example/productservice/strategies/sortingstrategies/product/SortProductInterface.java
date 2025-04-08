package com.example.productservice.strategies.sortingstrategies.product;

import com.example.productservice.models.Product;

import java.util.List;

public interface SortProductInterface {
    List<Product> findAllAndSort(String query);
}
