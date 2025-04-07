package com.example.productservice.dtos.mydtos.categorydtos;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllCategoryResponseDTO {
    @JsonProperty("categories")
    private List<ResponseCategoryDTO> categoryDTOList;
    private String responseMessage;

    public GetAllCategoryResponseDTO() {
        this.categoryDTOList = new ArrayList<>();
    }
}
