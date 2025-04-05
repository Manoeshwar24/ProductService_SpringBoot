package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepository;
import com.example.productservice.repositories.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductServiceInterface {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Category currentCategory = this.checkIfExistsAndCreateCategory(product.getCategory());

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

        productRepository.deleteById(id);
        return "Deleted product with id : " + id;
    }
    @Override
    public Product partialUpdateProduct(Long id, Product product)
            throws ProductNotFoundException, BadRequestException {
        //check if product with id is present in the DB
        Optional<Product> retrievedProduct = productRepository.findProductById(id);
        if(retrievedProduct.isEmpty()){
            throw new ProductNotFoundException("Product with ID : " + id + " not found");
        }

        //cannot patch category details part of this API
        if(product.getCategory().getName() != null &&
        product.getCategory().getDescription() != null){
            throw new BadRequestException("Category details cannot be patched as part of this API");
        }

        Product patchedProduct = patchProductWithDTO(product, retrievedProduct.get());
        return productRepository.save(patchedProduct);
    }

    private Product patchProductWithDTO(Product dtoProduct, Product dbProduct) {

        if(dtoProduct.getId() == null){
            dtoProduct.setId(dbProduct.getId());
        }
        if(dtoProduct.getTitle() == null){
            dtoProduct.setTitle(dbProduct.getTitle());
        }
        if(dtoProduct.getDescription() == null){
            dtoProduct.setDescription(dbProduct.getDescription());
        }
        if(dtoProduct.getPrice() == null){
            dtoProduct.setPrice(dbProduct.getPrice());
        }
        if(dtoProduct.getImageURL() == null){
            dtoProduct.setImageURL(dbProduct.getImageURL());
        }
        if(dtoProduct.getCreatedDate() == null){
            dtoProduct.setCreatedDate(dbProduct.getCreatedDate());
        }
        //category details in the dto will be null
        //update the dbCategory in the dtoProduct
        Category dbCategory = categoryRepository.findCategoryByName(dbProduct.getCategory().getName());
        dtoProduct.setCategory(dbCategory);

        //change the updated date
        dtoProduct.setUpdatedDate(LocalDateTime.now());

        return dtoProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {

        //check if product exists
        if(!productRepository.existsById(id)) {
            throw new ProductNotFoundException("There is no product with ID : " + id);
        }

        //CHECK IF THE CATEGORY IS PRESENT
        Category requestCategory = this.checkIfExistsAndCreateCategory(product.getCategory());
        //saving the category object in the product
        product.setCategory(requestCategory);

        //change updated date of the product
        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());

        return productRepository.save(product);
    }

    public Category checkIfExistsAndCreateCategory(Category category) {
        Category toAddCategory = categoryRepository.findCategoryByName(category.getName());
        if(toAddCategory == null) {
            //set created and updated date
            category.setCreatedDate(LocalDateTime.now());
            category.setUpdatedDate(LocalDateTime.now());

            toAddCategory = categoryRepository.save(category);
        }

        return toAddCategory;
    }
}
