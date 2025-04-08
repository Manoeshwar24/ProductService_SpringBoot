package com.example.productservice.services;

import com.example.productservice.enums.ProductSortingCriteria;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.factories.ProductSortingFactory;
import com.example.productservice.strategies.sortingstrategies.product.SortProductInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SearchService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> searchProductsWithoutFilters(String query, String sortByCriteria) {

        // get the correct implementation based on the sorting criteria using sortingfactory
        ProductSortingCriteria productSortingCriteria = ProductSortingCriteria.valueOf(sortByCriteria);
        SortProductInterface sortingStrategy = ProductSortingFactory.getProductSortingImplementationByCriteria(productSortingCriteria);
        //sort the productList using the right strategy
        List<Product> productList = sortingStrategy.findAllAndSort(query);

        return productList;
    }
}
