package com.example.productservice.dtos.mydtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductResponseDTO {

    private int id;
    private String name;
    private double price;
    private String details;
    private String image;

    public void fromProduct(Product product){
        this.setId(product.getId());
        this.setName(product.getTitle());
        this.setPrice(product.getPrice());
        this.setDetails(product.getDescription());
        this.setImage(product.getImageURL());
    }
}
