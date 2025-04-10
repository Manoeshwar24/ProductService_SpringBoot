package com.example.productservice.services;

import com.example.productservice.dtos.mydtos.filterdtos.ProductFilterRequestDTO;
import com.example.productservice.enums.ProductFilterCriteria;
import com.example.productservice.enums.ProductSortingCriteria;
import com.example.productservice.factories.ProductFilterFactory;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import com.example.productservice.factories.ProductSortingFactory;
import com.example.productservice.strategies.filteringstrategies.product.FilterProductInterface;
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
        ProductSortingCriteria productSortingCriteria = ProductSortingCriteria.fromString(sortByCriteria);
        SortProductInterface sortingStrategy = ProductSortingFactory.getProductSortingImplementationByCriteria(productSortingCriteria);
        //sort the productList using the right strategy
        List<Product> productList = sortingStrategy.findAllAndSort(query);

        return productList;
    }

    public List<Product> searchProductsWithFilters(String query, String sortByCriteria, List<ProductFilterRequestDTO> filters) {
        //get the list of results from the query
        List<Product> productList = productRepository.findAllByTitleIgnoreCase(query);

        //apply all the filters to the productList
        for(ProductFilterRequestDTO filterDTO : filters) {
            //apply all filters one by one
            ProductFilterCriteria filterCriteria = ProductFilterCriteria.fromString(filterDTO.getFilterName());
            List<String> filterValue = filterDTO.getFilterValues();

            //get the correct filter implementation based on the filter name
            FilterProductInterface currentFilterImplementation = ProductFilterFactory.getFilterImplementationByCriteria(filterCriteria);
            //filter the productList using the filter implementation
            productList = currentFilterImplementation.filter(productList);
        }

        //sort the filtered product list based on the sorting criteria
        ProductSortingCriteria productSortingCriteria = ProductSortingCriteria.fromString(sortByCriteria);
        SortProductInterface sortingStrategy = ProductSortingFactory.getProductSortingImplementationByCriteria(productSortingCriteria);

        return productList;
    }
}
