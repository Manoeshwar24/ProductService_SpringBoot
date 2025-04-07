package com.example.productservice.services;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.example.productservice.exceptions.CategoryAlreadyExistsException;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Category updateCategory(Category toUpdateCategory)
            throws CategoryNotFoundException {

        //check if category doesn't exist
        if (!categoryRepository.existsById(toUpdateCategory.getId())) {
            //if yes, throw exception
            throw new CategoryNotFoundException("Category not found with id " + toUpdateCategory.getId());
        }
        else{
            //else, update the category
            //update the last updated date
            toUpdateCategory.setUpdatedDate(LocalDateTime.now());
            //save the category in the db
            Category updatedCategory = categoryRepository.save(toUpdateCategory);
            return updatedCategory;
        }
    }

    public Category createCategory(Category toBeCreatedCategory) throws CategoryAlreadyExistsException {

        //check if category already exists
        if (categoryRepository.existsByName(toBeCreatedCategory.getName())) {
            //if yes, throw exception
            throw new CategoryAlreadyExistsException("Category already exists with name " + toBeCreatedCategory.getName());
        } else {
            //else, create new one
            //update the created and updated date
            toBeCreatedCategory.setCreatedDate(LocalDateTime.now());
            toBeCreatedCategory.setUpdatedDate(LocalDateTime.now());
            //save the category in the db
            Category createdCategory = categoryRepository.save(toBeCreatedCategory);
            return createdCategory;
        }
    }

    public String deleteCategory(Long categoryId)
    throws CategoryNotFoundException {
        //check if category doesn't exist
        if(!categoryRepository.existsById(categoryId)) {
            //if yes, throw exception
            throw new CategoryNotFoundException("Category not found with id " + categoryId);
        }
        //else, delete the category
        categoryRepository.deleteById(categoryId);

        return "Category deleted successfully with id " + categoryId;
    }

    public Category getCategory(Long categoryId)
    throws CategoryNotFoundException {
        //check if category doesn't exist
        if(!categoryRepository.existsById(categoryId)) {
            //if yes, throw exception
            throw new CategoryNotFoundException("Category not found with id " + categoryId);
        }
        //else, get the category
        Optional<Category> requestedCategory = categoryRepository.findById(categoryId);

        return requestedCategory.get();
    }
}
