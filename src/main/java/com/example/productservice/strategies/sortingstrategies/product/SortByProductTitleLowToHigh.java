package com.example.productservice.strategies.sortingstrategies.product;

import com.example.productservice.models.Product;

import java.util.ArrayList;
import java.util.List;


public class SortByProductTitleLowToHigh implements SortProductInterface {

    @Override
    public void apply(List<Product> productList) {

        //sort the productList based on the title in ascending order
        productList.sort((product1, product2) -> {
            String title1 = product1.getTitle();
            String title2 = product2.getTitle();
            return title1.compareTo(title2);
        });
    }
}
