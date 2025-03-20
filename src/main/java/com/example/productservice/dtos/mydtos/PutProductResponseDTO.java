package com.example.productservice.dtos.mydtos;

import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutProductResponseDTO {
    @JsonProperty("product")
    private ResponseProductDTO responseProductDTO;
    private String responseMessage;
}
