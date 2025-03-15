package com.example.productservice.dtos.mydtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllProductResponseDTO {
    private List<Product> productList;
    public GetAllProductResponseDTO() {
        this.productList = new ArrayList<Product>();
    }
}
