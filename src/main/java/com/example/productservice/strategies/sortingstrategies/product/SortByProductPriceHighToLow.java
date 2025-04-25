package com.example.productservice.strategies.sortingstrategies.product;


import com.example.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SortByProductPriceHighToLow implements SortProductInterface {

    @Override
    public void apply(List<Product> productList) {
        // Sort the modifiable list based on the price in descending order
        productList.sort((product1, product2) -> {
            if (product1.getPrice() == null || product2.getPrice() == null) {
                return 0; // Handle null prices as equal
            }
            return Double.compare(product2.getPrice(), product1.getPrice());
        });
    }
}
