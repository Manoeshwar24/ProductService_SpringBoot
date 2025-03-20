package com.example.productservice.dtos.mydtos.basedtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String details;
    private String image;
    private String categoryName;
    private String categoryDescription;

    public void fromProduct(Product product){
        this.setId(product.getId());
        this.setName(product.getTitle());
        this.setPrice(product.getPrice());
        this.setDetails(product.getDescription());
        this.setImage(product.getImageURL());
        this.setCategoryName(product.getCategory().getName());
        this.setCategoryDescription(product.getCategory().getDescription());
    }
}
