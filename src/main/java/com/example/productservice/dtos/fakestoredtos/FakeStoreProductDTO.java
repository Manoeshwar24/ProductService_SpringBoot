package com.example.productservice.dtos.fakestoredtos;

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
    private FakeStoreRatingDTO rating;

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setCategory(this.getCategory());
        product.setPrice(this.getPrice());
        product.setImageURL(this.getImage());

        return product;
    }
}
