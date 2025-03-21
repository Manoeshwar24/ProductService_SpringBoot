package com.example.productservice.dtos.mydtos;

import com.example.productservice.dtos.mydtos.basedtos.RequestProductDTO;
import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResponseDTO {
    @JsonProperty("product")
    private ResponseProductDTO responseProductDTO;
    private String responseMessage;
}
