package com.example.productservice.strategies.sortingstrategies.product;

import com.example.productservice.models.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SortByProductPriceLowToHigh implements SortProductInterface {

    @Override
    public List<Product> findAllAndSort(String query) {
        return List.of();
    }
}
