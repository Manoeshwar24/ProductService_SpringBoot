package com.example.productservice.dtos.mydtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;

@Getter
@Setter
public class PutProductResponseDTO {
    @JsonProperty("product")
    private ResponseProductDTO responseProductDTO;
    private String responseMessage;
}
