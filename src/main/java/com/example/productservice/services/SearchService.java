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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SearchService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductFilterFactory productFilterFactory;

    @Autowired
    public SearchService(ProductRepository productRepository, 
                        CategoryRepository categoryRepository,
                        ProductFilterFactory productFilterFactory) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productFilterFactory = productFilterFactory;
    }

    public Page<Product> searchProductsWithoutFilters(String query, ProductSortingCriteria sortByCriteria, int pageNumber, int pageSize) {
        //Create a Pageable object with page number and size
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        //get all search results for the query from the repository
        List<Product> productList = productRepository.findAllByTitleIgnoreCase(query);

        //get the correct implementation based on the sorting criteria using Sorting Factory
        SortProductInterface sortingStrategy = ProductSortingFactory.getProductSortingImplementationByCriteria(sortByCriteria);
        //sort the productList using the right strategy
        sortingStrategy.apply(productList);

        // Calculate the start and end indices for the current page
        int start = Math.min((int) pageable.getOffset(), productList.size());
        int end = Math.min((start + pageable.getPageSize()), productList.size());

        // Slice the productList to get the current page's content
        List<Product> paginatedList = productList.subList(start, end);

        // Return the paginated list wrapped in a PageImpl
        return new PageImpl<>(paginatedList, pageable, productList.size());
    }

    public Page<Product> searchProductsWithFilters(String query, ProductSortingCriteria sortByCriteria,
                                                   List<ProductFilterRequestDTO> filters, int pageNumber, int pageSize) {

        //create a Pageable object with page number and size
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        //get the list of results from the query
        List<Product> productList = productRepository.findAllByTitleIgnoreCase(query);

        //apply all the filters to the productList
        for(ProductFilterRequestDTO filterDTO : filters) {
            //apply all filters one by one
            ProductFilterCriteria filterCriteria = filterDTO.getFilterName();
            List<String> filterValues = filterDTO.getFilterValues();

            //get the correct filter implementation based on the filter name
            FilterProductInterface productFilterImplementation = productFilterFactory.getFilterImplementationByCriteria(filterCriteria);
            //filter the productList using the filter implementation
            productList = productFilterImplementation.filter(productList, filterValues);
        }

        //sort the filtered product list based on the sorting criteria
        SortProductInterface sortingStrategy = ProductSortingFactory.getProductSortingImplementationByCriteria(sortByCriteria);
        sortingStrategy.apply(productList);
        // Calculate the start and end indices for the current page
        int start = pageNumber * pageSize;
        int end = start + pageable.getPageSize();

        // Slice the productList to get the current page's content
        List<Product> slicedList = productList.subList(start, end);

        // Apply pagination
        return new PageImpl<>(slicedList, pageable, productList.size());
    }
}