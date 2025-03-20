package com.example.productservice.dtos.mydtos.basedtos;

import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductDTO {
    private String name;
    private double price;
    private String detail;
    private String image;
    private String categoryName;
    private String categoryDescription;

    public void fromProduct(Product product){
        this.setName(product.getTitle());
        this.setPrice(product.getPrice());
        this.setDetail(product.getDescription());
        this.setImage(product.getImageURL());

        Category category = product.getCategory();
        this.setCategoryName(category.getName());
        this.setCategoryDescription(category.getDescription());
    }

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(this.getName());
        product.setPrice(this.getPrice());
        product.setDescription(this.getDetail());
        product.setImageURL(this.getImage());

        Category category = new Category();
        category.setName(this.getCategoryName());
        category.setDescription(this.getCategoryDescription());
        product.setCategory(category);

        return product;
    }
}
