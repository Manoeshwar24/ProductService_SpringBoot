package com.example.productservice.enums;

public enum ProductFilterCriteria {
    PRICE_FILTER,
    TITLE_FILTER,
    CATEGORY_FILTER;

    public static ProductFilterCriteria fromString(String filter) {
        if (filter == null || filter.isEmpty()) {
            throw new IllegalArgumentException("Filter criteria cannot be null or empty");
        }
        return switch (filter.toLowerCase()) {
            case "price_filter" -> PRICE_FILTER;
            case "title_filter" -> TITLE_FILTER;
            case "category_filter" -> CATEGORY_FILTER;
            default -> throw new IllegalArgumentException("Invalid filter criteria: " + filter);
        };
    }
}