package com.example.productservice.dtos.fakestoredtos;

import com.example.productservice.models.Product;

public class FakeStoreCreateProductResponseDTO {
    private int id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private FakeStoreRatingDTO rating;

    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setImageURL(image);

        return product;
    }
}
