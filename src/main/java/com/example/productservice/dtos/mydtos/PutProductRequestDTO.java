package com.example.productservice.dtos.mydtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
public class PutProductRequestDTO {
    @JsonProperty("product")
    private RequestProductDTO requestProductDTO;
}
