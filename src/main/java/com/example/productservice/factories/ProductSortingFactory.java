package com.example.productservice.factories;

import com.example.productservice.enums.ProductSortingCriteria;
import com.example.productservice.strategies.sortingstrategies.product.*;

public class ProductSortingFactory {
    public static SortProductInterface getProductSortingImplementationByCriteria(ProductSortingCriteria criteria) {
        // Return the appropriate sorting implementation based on the criteria
        return switch (criteria) {
            case ProductSortingCriteria.PRICE_HIGH_TO_LOW -> new SortByProductPriceHighToLow();
            case ProductSortingCriteria.PRICE_LOW_TO_HIGH -> new SortByProductPriceLowToHigh();
            case ProductSortingCriteria.TITLE_A_TO_Z -> new SortByProductTitleLowToHigh();
            case ProductSortingCriteria.TITLE_Z_TO_A -> new SortByProductTitleHighToLow();
            default -> throw new IllegalArgumentException("Invalid sorting criteria: " + criteria);
        };
    }
}