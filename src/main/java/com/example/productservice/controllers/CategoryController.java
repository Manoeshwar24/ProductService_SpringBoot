package com.example.productservice.controllers;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.example.productservice.dtos.mydtos.basedtos.ResponseTopProductDTO;
import com.example.productservice.dtos.mydtos.categorydtos.GetTopProductsDTO;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public GetTopProductsDTO getTopProducts(@PathVariable Long id) throws CategoryNotFoundException {

        List<Product> topProducts = categoryService.getTopProducts(id);
        //convert the products list to responseDTO
        GetTopProductsDTO topProductsDTO = new GetTopProductsDTO();
        List<ResponseTopProductDTO> productDTO = new ArrayList<>();
        for (Product product : topProducts) {
            //set the category detail in response DTO
            if(topProductsDTO.getCategory() == null){
                ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
                responseCategoryDTO.fromCategory(product.getCategory());

                topProductsDTO.setCategory(responseCategoryDTO);
            }

            ResponseTopProductDTO responseTopProductDTO = new ResponseTopProductDTO();
            responseTopProductDTO.fromProduct(product);
            productDTO.add(responseTopProductDTO);

            topProductsDTO.getTopProducts().add(responseTopProductDTO);
        }

        return topProductsDTO;
    }
}
