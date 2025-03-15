package com.example.productservice.dtos.fakestoredtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDTO {
    private double price;
    private String title;
    private String description;
    private String category;
    private String image;
    private int id;

    public void fromProduct(Product requestedProduct) {
        this.setId(requestedProduct.getId());
        this.setPrice(requestedProduct.getPrice());
        this.setTitle(requestedProduct.getTitle());
        this.setDescription(requestedProduct.getDescription());
        this.setCategory(requestedProduct.getCategory());
        this.setImage(requestedProduct.getImageURL());
    }
}
