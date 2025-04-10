package com.example.productservice.enums;

public enum ProductFilterCriteria {
    PRICE_FILTER,
    TITLE_FILTER,
    CATEGORY_FILTER;

    public static ProductFilterCriteria fromString(String filter) {
        if (filter == null || filter.isEmpty()) {
            throw new IllegalArgumentException("Filter criteria cannot be null or empty");
        }
        switch (filter.toLowerCase()) {
            case "price_filter":
                return PRICE_FILTER;
            case "title_filter":
                return TITLE_FILTER;
            case "category_filter":
                return CATEGORY_FILTER;
            default:
                throw new IllegalArgumentException("Invalid filter criteria: " + filter);
        }
    }
}