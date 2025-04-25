package com.example.productservice.controllers;


import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.example.productservice.dtos.mydtos.filterdtos.ProductFilterRequestDTO;
import com.example.productservice.dtos.mydtos.productdtos.GetAllProductResponseDTO;
import com.example.productservice.enums.ProductSortingCriteria;
import com.example.productservice.models.Product;
import com.example.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // expose the search endpoints here
    // product -> get api with search by query and sorting criteria
    // product -> post api with search by query and sorting criteria and list of filters
    // category -> get api with search by query and sorting criteria

    @GetMapping("/product")
    public ResponseEntity<GetAllProductResponseDTO> searchProductsWithoutFilters(@RequestParam String query,
                                                                                 @RequestParam ProductSortingCriteria sortByCriteria,
                                                                                 @RequestParam int pageNumber,
                                                                                 @RequestParam int pageSize) {
        //find the results based on the query and then sort the results based on the criteria
        GetAllProductResponseDTO responseDTO = new GetAllProductResponseDTO();
        Page<Product> productPage = searchService.searchProductsWithoutFilters(query, sortByCriteria, pageNumber, pageSize);

        return getResponseEntityFromProductSearchResults(productPage.getContent(), responseDTO);
    }

    @PostMapping("/product")
    public ResponseEntity<GetAllProductResponseDTO> searchProductsWithFilters(@RequestParam String query,
                                                                              @RequestParam ProductSortingCriteria sortByCriteria,
                                                                              @RequestBody List<ProductFilterRequestDTO> filters,
                                                                              @RequestParam int pageNumber,
                                                                              @RequestParam int pageSize) {
        //find the results from the search service
        Page<Product> searchResults = searchService.searchProductsWithFilters(query, sortByCriteria, filters, pageNumber, pageSize);
        GetAllProductResponseDTO responseDTO = new GetAllProductResponseDTO();
        //get the results from the searchResults
        return getResponseEntityFromProductSearchResults(searchResults.getContent(), responseDTO);
    }

    private ResponseEntity<GetAllProductResponseDTO> getResponseEntityFromProductSearchResults(List<Product> searchResults, GetAllProductResponseDTO responseDTO) {
        List<ResponseProductDTO> responseProductDTOList = new ArrayList<>();
        for(Product product : searchResults){
            ResponseProductDTO responseProductDTO = new ResponseProductDTO();
            responseProductDTO.fromProduct(product);
            responseProductDTOList.add(responseProductDTO);
        }

        responseDTO.setProductDTOList(responseProductDTOList);
        responseDTO.setResponseMessage("Search results are found");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
