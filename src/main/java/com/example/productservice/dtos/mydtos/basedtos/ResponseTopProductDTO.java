package com.example.productservice.dtos.mydtos.basedtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTopProductDTO {
    private String name;
    private double price;
    private String detail;
    private String image;

    public void fromProduct(Product product) {
        this.setName(product.getTitle());
        this.setPrice(product.getPrice());
        this.setDetail(product.getDescription());
        this.setImage(product.getImageURL());
    }
}
