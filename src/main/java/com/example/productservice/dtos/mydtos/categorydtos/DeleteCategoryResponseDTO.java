package com.example.productservice.dtos.mydtos.categorydtos;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteCategoryResponseDTO {
    private String responseMessage;
}
