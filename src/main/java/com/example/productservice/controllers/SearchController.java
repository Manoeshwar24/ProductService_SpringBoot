package com.example.productservice.controllers;

import com.example.productservice.services.SearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
