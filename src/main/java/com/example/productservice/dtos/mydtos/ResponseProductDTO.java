package com.example.productservice.dtos.mydtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDTO {
    private long id;
    private String name;
    private double price;
    private String details;
    private String image;
    private String category;

    public void fromProduct(Product product){
        this.setId(product.getId());
        this.setName(product.getTitle());
        this.setPrice(product.getPrice());
        this.setDetails(product.getDescription());
        this.setImage(product.getImageURL());
        this.setCategory(product.getCategory().getName());
    }
}
