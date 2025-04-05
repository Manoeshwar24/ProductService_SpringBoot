package com.example.productservice.services;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Product> getTopProducts(Long id) throws CategoryNotFoundException {
        //check if category exists
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));

        return categoryRepository.findTopProducts(id);
    }

    public List<ResponseCategoryDTO> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        List<ResponseCategoryDTO> allCategoriesDTO = new ArrayList<>();
        for (Category category : allCategories) {
            ResponseCategoryDTO categoryDTO = new ResponseCategoryDTO();
            categoryDTO.fromCategory(category);
            allCategoriesDTO.add(categoryDTO);
        }
        return allCategoriesDTO;
    }

    public Category updateCategory(Category toUpdateCategory) {

        return null;
    }
}
