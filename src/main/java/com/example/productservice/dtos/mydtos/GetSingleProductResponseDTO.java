package com.example.productservice.dtos.mydtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDTO {
    @JsonProperty("product")
    private ResponseProductDTO productDTO;
    private String responseMessage;
}
