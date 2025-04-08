package com.example.productservice.enums;

public enum ProductSortingCriteria {
    PRICE_HIGH_TO_LOW,
    PRICE_LOW_TO_HIGH,
    TITLE_A_TO_Z,
    TITLE_Z_TO_A;

    public static ProductSortingCriteria fromString(String criteria) {
        if (criteria == null || criteria.isEmpty()) {
            throw new IllegalArgumentException("Sorting criteria cannot be null or empty");
        }
        switch (criteria.toLowerCase()) {
            case "price_high_to_low":
                return PRICE_HIGH_TO_LOW;
            case "price_low_to_high":
                return PRICE_LOW_TO_HIGH;
            case "title_a_to_z":
                return TITLE_A_TO_Z;
            case "title_z_to_a":
                return TITLE_Z_TO_A;
            default:
                throw new IllegalArgumentException("Invalid sorting criteria: " + criteria);
        }
    }
}