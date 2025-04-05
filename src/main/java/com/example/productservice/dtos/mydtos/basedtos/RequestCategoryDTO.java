package com.example.productservice.dtos.mydtos.basedtos;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCategoryDTO {
    private String name;
    private String description;

    public Category toCategory(){
        Category category = new Category();
        category.setName(this.getName());
        category.setDescription(this.getDescription());
        return category;
    }
}
