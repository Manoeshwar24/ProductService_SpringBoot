package com.example.productservice.factories;

import com.example.productservice.enums.ProductFilterCriteria;
import com.example.productservice.strategies.filteringstrategies.product.FilterProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductFilterFactory {
    private final Map<ProductFilterCriteria, FilterProductInterface> filterMap;

    @Autowired
    public ProductFilterFactory(List<FilterProductInterface> filters) {
        filterMap = new EnumMap<>(ProductFilterCriteria.class);
        filters.forEach(filter -> filterMap.put(filter.getFilterCriteria(), filter));
    }

    public FilterProductInterface getFilterImplementationByCriteria(ProductFilterCriteria criteria) {
        FilterProductInterface filter = filterMap.get(criteria);
        if (filter == null) {
            throw new IllegalArgumentException("No filter implementation found for criteria: " + criteria);
        }
        return filter;
    }
}