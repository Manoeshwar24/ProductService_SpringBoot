package com.example.productservice.dtos.fakestoredtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageURL(this.getImage());

        Category category = new Category();
        category.setName(this.getCategory());
        product.setCategory(category);


        return product;
    }

    public void fromProduct(Product requestedProduct) {
        this.setId((int)requestedProduct.getId());
        this.setPrice(requestedProduct.getPrice());
        this.setTitle(requestedProduct.getTitle());
        this.setDescription(requestedProduct.getDescription());
        this.setCategory(requestedProduct.getCategory().getName());
        this.setImage(requestedProduct.getImageURL());
    }
}
