package com.example.productservice.controllers;


import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.example.productservice.dtos.mydtos.productdtos.GetAllProductResponseDTO;
import com.example.productservice.models.Product;
import com.example.productservice.services.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                                                                                 @RequestParam String sortByCriteria){
        //find the results based on the query and then sort the results based on the criteria
        GetAllProductResponseDTO responseDTO = new GetAllProductResponseDTO();
        List<Product> searchResults = searchService.searchProductsWithoutFilters(query, sortByCriteria);
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
