package com.example.productservice.services;

import com.example.productservice.dtos.fakestoredtos.FakeStoreCreateProductRequestDTO;
import com.example.productservice.dtos.fakestoredtos.FakeStoreCreateProductResponseDTO;
import com.example.productservice.dtos.fakestoredtos.FakeStoreProductDTO;
import com.example.productservice.dtos.mydtos.GetProductResponseDTO;
import com.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ProductServiceFakeStoreImpl implements ProductServiceInterface {
    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(int id){

        FakeStoreProductDTO responseDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        return responseDTO.toProduct();
    }
    @Override
    public Product deleteProduct(int id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return getSingleProduct(id);
    }
    @Override
    public Product updateProduct(Product product) {

        restTemplate.put("https://fakestoreapi.com/products/" + product.getId(), product);

        return getSingleProduct(product.getId());
    }
    @Override
    public void replaceProduct(Product product) {

    }
    public Product createProduct(Product requestedProduct) {
        FakeStoreCreateProductRequestDTO requestDTO = new FakeStoreCreateProductRequestDTO();
        requestDTO.fromProduct(requestedProduct);


        FakeStoreCreateProductResponseDTO responseDTO =
                restTemplate.postForObject("https://fakestoreapi.com/products",requestDTO,
                FakeStoreCreateProductResponseDTO.class);

        return responseDTO.toProduct();
    }
    @Override
    public List<Product> getAllProducts() {
        ArrayList responseDTOList =
            restTemplate.getForObject("https://fakestoreapi.com/products",
            ArrayList.class);

        List<Product> products = new ArrayList<>();
        for(Object each : responseDTOList){
            //
        }

        return products;
    }
}
