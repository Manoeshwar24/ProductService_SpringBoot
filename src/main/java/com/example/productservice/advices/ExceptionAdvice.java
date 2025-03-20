package com.example.productservice.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex){
        return ex.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRunTimeException(Exception ex){
        return ex.getMessage();
    }
}
