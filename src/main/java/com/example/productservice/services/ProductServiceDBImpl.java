package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceDBImpl implements ProductServiceInterface {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Category currentCategory = categoryRepository.findCategoryByName(product.getCategory().getName());

        if(currentCategory == null){
            //add created and updated dates for new category
            currentCategory = product.getCategory();
            currentCategory.setCreatedDate(LocalDateTime.now());
            currentCategory.setUpdatedDate(LocalDateTime.now());

            categoryRepository.save(currentCategory);
        }
        //add created and updates dates for new product
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());

        //setting the category object in product
        product.setCategory(currentCategory);
        return productRepository.save(product);
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException{
        Optional<Product> retrievedProduct = productRepository.findProductById(id);

        if(retrievedProduct.isEmpty()){
            throw new ProductNotFoundException("There is no product with ID : " + id);
        }

        return retrievedProduct.get();
    }
    @Override
    public String deleteProduct(Long id) {

        return null;
    }
    @Override
    public Product partialUpdateProduct(Product product) {
        return null;
    }
    @Override
    public Product replaceProduct(Product product) throws ProductNotFoundException {

        return null;
    }
}
