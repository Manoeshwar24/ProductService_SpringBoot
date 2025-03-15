package com.example.productservice.dtos.mydtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {

    private String title;
    private double price;
    private String detail;
    private String category;
    private String image;

    public Product toProduct(){
        Product productReceived = new Product();
        productReceived.setTitle(this.getTitle());
        productReceived.setPrice(this.getPrice());
        productReceived.setDescription(this.getDetail());
        productReceived.setCategory(this.getCategory());
        productReceived.setImageURL(this.getImage());

        return productReceived;
    }
}
