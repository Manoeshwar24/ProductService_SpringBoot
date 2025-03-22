package com.example.productservice.dtos.mydtos.basedtos;

import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCategoryDTO {
    private String name;
    private String description;

    public void fromCategory(Category category) {
        this.setName(category.getName());
        this.setDescription(category.getDescription());
    }
}
