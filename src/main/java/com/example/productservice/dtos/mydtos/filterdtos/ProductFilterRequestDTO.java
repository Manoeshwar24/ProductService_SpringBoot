package com.example.productservice.dtos.mydtos.filterdtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilterRequestDTO {
    @JsonProperty("filterName")
    private String filterName;
    @JsonProperty("filterValues")
    private List<String> filterValues;
}
