package com.example.productservice.dtos.mydtos;

import com.example.productservice.dtos.mydtos.basedtos.RequestProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutProductRequestDTO {
    @JsonProperty("product")
    private RequestProductDTO requestProductDTO;
}
