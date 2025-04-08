package com.example.productservice.configs;

import com.example.productservice.enums.ProductSortingCriteria;
import com.example.productservice.strategies.sortingstrategies.product.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
public class ConfigurationClass {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Map<ProductSortingCriteria, SortProductInterface> sortingStrategies(
            SortByProductPriceHighToLow priceHighToLow,
            SortByProductPriceLowToHigh priceLowToHigh,
            SortByProductTitleLowToHigh titleLowToHigh,
            SortByProductTitleHighToLow titleHighToLow) {
        return Map.of(
                ProductSortingCriteria.PRICE_HIGH_TO_LOW, priceHighToLow,
                ProductSortingCriteria.PRICE_LOW_TO_HIGH, priceLowToHigh,
                ProductSortingCriteria.TITLE_A_TO_Z, titleLowToHigh,
                ProductSortingCriteria.TITLE_Z_TO_A, titleHighToLow
        );
    }
}

