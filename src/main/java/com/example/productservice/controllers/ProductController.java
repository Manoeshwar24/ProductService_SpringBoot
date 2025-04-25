package com.example.productservice.controllers;

import com.example.productservice.dtos.mydtos.basedtos.ResponseProductDTO;
import com.example.productservice.dtos.mydtos.productdtos.*;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductServiceInterface;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceInterface productService;

    public ProductController(@Qualifier("dbProductService") ProductServiceInterface productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<GetAllProductResponseDTO> getAllProducts() {
        GetAllProductResponseDTO responseDTO = new GetAllProductResponseDTO();
        List<Product> allProducts = productService.getAllProducts();
        for (Product each : allProducts) {
            ResponseProductDTO responseProductDTO = new ResponseProductDTO();
            responseProductDTO.fromProduct(each);
            responseDTO.getProductDTOList().add(responseProductDTO);
        }
        responseDTO.setResponseMessage("Successfully retrieved all products");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSingleProductResponseDTO> getSingleProduct(@PathVariable Long id) throws ProductNotFoundException {
        Product requestedProduct = productService.getSingleProduct(id);
        GetSingleProductResponseDTO responseDTO = new GetSingleProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(requestedProduct);
        responseDTO.setProductDTO(responseProductDTO);
        responseDTO.setResponseMessage("Successfully retrieved single product with id: " + id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CreateProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        Product toBeCreatedProduct = requestDTO.getRequestProductDTO().toProduct();
        Product createdProduct = productService.createProduct(toBeCreatedProduct);
        CreateProductResponseDTO responseDTO = new CreateProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(createdProduct);
        responseDTO.setResponseProductDTO(responseProductDTO);
        responseDTO.setResponseMessage("Successfully created single product");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteProductResponseDTO> deleteProduct(@PathVariable Long id) {
        String responseMessage = productService.deleteProduct(id);
        DeleteProductResponseDTO responseDTO = new DeleteProductResponseDTO();
        responseDTO.setResponseMessage(responseMessage);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatchProductResponseDTO> patchProduct(@PathVariable Long id, @RequestBody PatchProductRequestDTO patchProductRequestDTO)
            throws ProductNotFoundException, BadRequestException {
        Product toUpdateProduct = patchProductRequestDTO.getProductDTO().toProduct();
        Product updatedProduct = productService.partialUpdateProduct(id, toUpdateProduct);
        PatchProductResponseDTO patchProductResponseDTO = new PatchProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(updatedProduct);
        patchProductResponseDTO.setProductDTO(responseProductDTO);
        patchProductResponseDTO.setResponseMessage("Successfully updated product");
        return new ResponseEntity<>(patchProductResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PutProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody PutProductRequestDTO putProductRequestDTO)
            throws ProductNotFoundException {
        Product toReplaceProduct = putProductRequestDTO.getRequestProductDTO().toProduct();
        toReplaceProduct.setId(id);
        Product replacedProduct = productService.updateProduct(id, toReplaceProduct);
        PutProductResponseDTO putProductResponseDTO = new PutProductResponseDTO();
        ResponseProductDTO responseProductDTO = new ResponseProductDTO();
        responseProductDTO.fromProduct(replacedProduct);
        putProductResponseDTO.setResponseProductDTO(responseProductDTO);
        putProductResponseDTO.setResponseMessage("Successfully updated product");
        return new ResponseEntity<>(putProductResponseDTO, HttpStatus.OK);
    }
}