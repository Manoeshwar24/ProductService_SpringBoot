package com.example.productservice.dtos.mydtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDTO {
    private String name;
    private double price;
    private String details;
    private String image;
    private String category;

    public void fromProduct(Product product){
        this.setName(product.getTitle());
        this.setPrice(product.getPrice());
        this.setDetails(product.getDescription());
        this.setImage(product.getImageURL());
        this.setCategory(product.getCategory());
    }

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(this.getName());
        product.setPrice(this.getPrice());
        product.setDescription(this.getDetails());
        product.setImageURL(this.getImage());
        product.setCategory(this.getCategory());

        return product;
    }
}
