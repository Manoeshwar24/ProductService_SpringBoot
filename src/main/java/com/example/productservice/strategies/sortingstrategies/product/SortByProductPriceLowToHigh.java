package com.example.productservice.strategies.sortingstrategies.product;

import com.example.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;


public class SortByProductPriceLowToHigh implements SortProductInterface {

    @Override
    public void apply(List<Product> productList) {

        //sort the productList based on the price in ascending order
        productList.sort((product1, product2) -> {
            if (product1.getPrice() == null || product2.getPrice() == null) {
                return 0; // Handle null prices as equal
            }
            return Double.compare(product1.getPrice(), product2.getPrice());
        });
    }
}
