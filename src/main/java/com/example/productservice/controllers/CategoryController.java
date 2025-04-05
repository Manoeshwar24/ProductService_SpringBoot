package com.example.productservice.controllers;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.example.productservice.dtos.mydtos.basedtos.ResponseTopProductDTO;
import com.example.productservice.dtos.mydtos.categorydtos.GetTopProductsDTO;
import com.example.productservice.dtos.mydtos.categorydtos.PutCategoryRequestDTO;
import com.example.productservice.dtos.mydtos.categorydtos.PutCategoryResponseDTO;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/topProducts/{id}")
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

    @GetMapping("/")
    public List<ResponseCategoryDTO> getAllCategories() {
        List<ResponseCategoryDTO> allCategories = categoryService.getAllCategories();
        return allCategories;
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<PutCategoryResponseDTO> updateCategory(@PathVariable Long categoryId,
                                                                @RequestBody PutCategoryRequestDTO requestDTO){
        Category toUpdateCategory = requestDTO.getCategoryDTO().toCategory();

        //set the id of the category to be updated
        toUpdateCategory.setId(categoryId);

        //call the service to update the category
        Category updatedCategory = categoryService.updateCategory(toUpdateCategory);

        //create the response DTO
        PutCategoryResponseDTO responseDTO = new PutCategoryResponseDTO();
        ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
        responseCategoryDTO.fromCategory(updatedCategory);
        responseDTO.setCategoryDTO(responseCategoryDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
