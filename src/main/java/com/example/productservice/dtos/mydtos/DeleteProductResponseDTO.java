package com.example.productservice.dtos.mydtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductResponseDTO {
    private String responseMessage;
}
