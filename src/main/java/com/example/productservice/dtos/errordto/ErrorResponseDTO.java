package com.example.productservice.dtos.errordto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDTO {
    private String message;
    private LocalDateTime dateTime;
}
