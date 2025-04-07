package com.example.productservice.repositories;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByName(String name);

    @Query(value = "select * from product where category_id = :id" +
            " order by price desc limit 3", nativeQuery = true)
    List<Product> findTopProducts(@Param("id") Long id);

    boolean existsByName(String name);
}
