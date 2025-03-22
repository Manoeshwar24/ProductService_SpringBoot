package com.example.productservice.advices;

import com.example.productservice.dtos.errordto.ErrorResponseDTO;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex){
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(ex.getMessage());
        err.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRunTimeException(RuntimeException ex){
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(ex.getMessage());
        err.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException ex){
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(ex.getMessage());
        err.setDateTime(LocalDateTime.now());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException ex){
        ErrorResponseDTO err = new ErrorResponseDTO();
        err.setMessage(ex.getMessage());
        err.setDateTime(LocalDateTime.now());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
