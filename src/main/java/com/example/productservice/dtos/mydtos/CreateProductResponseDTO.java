package com.example.productservice.dtos.mydtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDTO {
    private int productId;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
    private String responseMessage;

    public void fromProduct(Product receivedProduct){

        this.setTitle(receivedProduct.getTitle());
        this.setDescription(receivedProduct.getDescription());
        this.setPrice(receivedProduct.getPrice());
        this.setCategory(receivedProduct.getCategory());
        this.setProductId(receivedProduct.getId());
        this.setImageUrl(receivedProduct.getImageURL());
    }
}
