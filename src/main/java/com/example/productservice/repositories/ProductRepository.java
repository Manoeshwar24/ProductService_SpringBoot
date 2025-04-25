package com.example.productservice.repositories;

import com.example.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    //check if the product exists by id
    boolean existsById(Long id);

    Page<Product> findAllByTitleIgnoreCase(String query, Pageable pageable);
    List<Product> findAllByTitleIgnoreCase(String query);

    List<Product> findAllByTitleIn(List<String> filterValues);
    List<Product> findAllByCategory_NameIn(List<String> filterValues);
    List<Product> findAllByPriceIn(List<Double> filterValues);
}
