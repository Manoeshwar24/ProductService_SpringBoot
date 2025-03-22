package com.example.productservice.dtos.mydtos.categorydtos;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.example.productservice.dtos.mydtos.basedtos.ResponseTopProductDTO;
import com.example.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetTopProductsDTO {
    private ResponseCategoryDTO category;
    private List<ResponseTopProductDTO> topProducts;

    public GetTopProductsDTO(){
        this.topProducts = new ArrayList<>();
    }
}
