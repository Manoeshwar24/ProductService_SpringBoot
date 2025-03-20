package com.example.productservice.dtos.mydtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetAllProductResponseDTO {
    @JsonProperty("productList")
    private List<ResponseProductDTO> productDTOList;
    private String responseMessage;

    public GetAllProductResponseDTO() {
        this.productDTOList = new ArrayList<>();
    }
}
