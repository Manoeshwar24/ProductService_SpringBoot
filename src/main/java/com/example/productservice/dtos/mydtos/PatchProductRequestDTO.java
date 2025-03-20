package com.example.productservice.dtos.mydtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchProductRequestDTO {
    @JsonProperty("product")
    private RequestProductDTO productDTO;
}
