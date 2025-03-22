package com.example.productservice.dtos.mydtos.productdtos;

import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchProductResponseDTO {
    @JsonProperty("product")
    private ResponseProductDTO productDTO;
    private String responseMessage;
}
