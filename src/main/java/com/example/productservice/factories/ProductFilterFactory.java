package com.example.productservice.factories;

import com.example.productservice.enums.ProductFilterCriteria;
import com.example.productservice.strategies.filteringstrategies.product.FilterByProductCategory;
import com.example.productservice.strategies.filteringstrategies.product.FilterByProductPrice;
import com.example.productservice.strategies.filteringstrategies.product.FilterByProductTitle;
import com.example.productservice.strategies.filteringstrategies.product.FilterProductInterface;

public class ProductFilterFactory {
    public static FilterProductInterface getFilterImplementationByCriteria(ProductFilterCriteria criteria) {
        // Return the appropriate filter implementation based on the criteria
        return switch (criteria) {
            case PRICE_FILTER -> new FilterByProductPrice();
            case TITLE_FILTER -> new FilterByProductTitle();
            case CATEGORY_FILTER -> new FilterByProductCategory();
            default -> throw new IllegalArgumentException("Invalid filter criteria: " + criteria);
        };
    }
}