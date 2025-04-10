package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //get all products
    List<Product> findAll();
    //get product by ID
    Optional<Product> findProductById(Long id);
    //upsert
    @Override
    Product save(Product product);
    //delete by id
    @Override
    void deleteById(Long id);
    //check if product exists by id
    boolean existsById(Long id);
    //sort the list of products
    //by price ascending
    List<Product> findAllByTitleIgnoreCaseOrderByPriceAsc(String query);
    //by price descending
    List<Product> findAllByTitleIgnoreCaseOrderByPriceDesc(String query);
    //by name ascending
    List<Product> findAllByTitleIgnoreCaseOrderByTitleAsc(String query);
    //by name descending
    List<Product> findAllByTitleIgnoreCaseOrderByTitleDesc(String query);

    List<Product> findAllByTitleIgnoreCase(String query);
}
