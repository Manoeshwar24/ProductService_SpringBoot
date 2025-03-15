package com.example.productservice.controllers;

import com.example.productservice.dtos.mydtos.CreateProductRequestDTO;
import com.example.productservice.dtos.mydtos.CreateProductResponseDTO;
import com.example.productservice.dtos.mydtos.GetAllProductResponseDTO;
import com.example.productservice.dtos.mydtos.GetProductResponseDTO;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductServiceInterface;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final RestTemplate restTemplate;
    private ProductServiceInterface productService;

    public ProductController(ProductServiceInterface productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public GetAllProductResponseDTO getAllProducts(){

        GetAllProductResponseDTO responseDTO = new GetAllProductResponseDTO();
        List<Product> allProducts =  productService.getAllProducts();
        //move all the products data to the responseDTO
        for(Product each : allProducts){
            responseDTO.getProductList().add(each);
        }

        return responseDTO;
    }
    @GetMapping("/{id}")
    public GetProductResponseDTO getSingleProduct(@PathVariable int id){

        Product requestedProduct = productService.getSingleProduct(id);
        GetProductResponseDTO responseDTO = new GetProductResponseDTO();
        responseDTO.fromProduct(requestedProduct);

        return responseDTO;
    }
    @PostMapping("")
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO requestDTO){
        //get the product from requestDTO mapping
        Product requestedProduct = requestDTO.toProduct();

        //send the product to Product service and persist in the database
        Product createdProduct = productService.createProduct(requestedProduct);
        CreateProductResponseDTO responseDTO = new CreateProductResponseDTO();
        responseDTO.fromProduct(createdProduct);

        return responseDTO;
    }
    @DeleteMapping("/{id}")
    public CreateProductResponseDTO deleteProduct(@PathVariable int id){
        CreateProductResponseDTO responseDTO = new CreateProductResponseDTO();
        responseDTO.fromProduct(productService.deleteProduct(id));

        return responseDTO;
    }
    @PatchMapping("/{id}")
    public CreateProductResponseDTO updateProduct(@PathVariable int id, @RequestBody CreateProductRequestDTO requestDTO){
        Product toUpdateProduct = requestDTO.toProduct();

        CreateProductResponseDTO responseDTO = new CreateProductResponseDTO();
        responseDTO.fromProduct(productService.updateProduct(toUpdateProduct));

        return responseDTO;
    }
    public void replaceProduct(){}
}
