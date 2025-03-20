package com.example.productservice.controllers;

import com.example.productservice.dtos.mydtos.*;
import com.example.productservice.dtos.mydtos.basedtos.RequestProductDTO;
import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
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
            ResponseProductDTO responseProductDTO = new ResponseProductDTO();
            responseProductDTO.fromProduct(each);

            responseDTO.getProductDTOList().add(responseProductDTO);
        }
        responseDTO.setResponseMessage("Successfully retrieved all products");
        return responseDTO;
    }

    @GetMapping("/{id}")
    public GetSingleProductResponseDTO getSingleProduct(@PathVariable Long id) throws ProductNotFoundException {

        Product requestedProduct = productService.getSingleProduct(id);
        GetSingleProductResponseDTO responseDTO = new GetSingleProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(requestedProduct);

        responseDTO.setProductDTO(responseProductDTO);
        responseDTO.setResponseMessage("Successfully retrieved single product with id: " + id);
        return responseDTO;
    }

    @PostMapping("")
    public CreateProductResponseDTO createProduct(@RequestBody CreateProductRequestDTO requestDTO){
        //get the product from requestDTO mapping
        Product toBeCreatedProduct = requestDTO.getRequestProductDTO().toProduct();

        //send the product to Product service and persist in the database
        Product createdProduct = productService.createProduct(toBeCreatedProduct);

        CreateProductResponseDTO responseDTO = new CreateProductResponseDTO();
        RequestProductDTO afterRequestProductDTO = new RequestProductDTO();
        afterRequestProductDTO.fromProduct(createdProduct);

        responseDTO.setRequestProductDTO(afterRequestProductDTO);
        responseDTO.setResponseMessage("Successfully created single product");

        return responseDTO;
    }

    @DeleteMapping("/{id}")
    public DeleteProductResponseDTO deleteProduct(@PathVariable Long id){

        String responseMessage = productService.deleteProduct(id);

        DeleteProductResponseDTO responseDTO = new DeleteProductResponseDTO();
        responseDTO.setResponseMessage(responseMessage);

        return responseDTO;
    }

    @PatchMapping("/{id}")
    public PatchProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody PatchProductRequestDTO patchProductRequestDTO){
        Product toUpdateProduct = patchProductRequestDTO.getProductDTO().toProduct();

        Product updatedProduct = productService.partialUpdateProduct(toUpdateProduct);

        PatchProductResponseDTO patchProductResponseDTO = new PatchProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(updatedProduct);

        patchProductResponseDTO.setProductDTO(responseProductDTO);
        patchProductResponseDTO.setResponseMessage("Successfully updated product");

        return patchProductResponseDTO;
    }

    @PutMapping("/{id}")
    public PutProductResponseDTO replaceProduct(@PathVariable Long id, @RequestBody PutProductRequestDTO putProductRequestDTO)
    throws ProductNotFoundException {

        Product toReplaceProduct = putProductRequestDTO.getRequestProductDTO().toProduct();
        //setting id from the path variable
        toReplaceProduct.setId(id);
        Product replacedProduct = productService.replaceProduct(toReplaceProduct);

        PutProductResponseDTO putProductResponseDTO = new PutProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(replacedProduct);
        putProductResponseDTO.setResponseProductDTO(responseProductDTO);
        putProductResponseDTO.setResponseMessage("Successfully updated product");

        return putProductResponseDTO;
    }

}
