package com.example.productservice.services;

import com.example.productservice.dtos.fakestoredtos.FakeStoreProductDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class ProductServiceFakeStoreImpl implements ProductServiceInterface {
    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(Long id) throws ProductNotFoundException {

        FakeStoreProductDTO responseDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        if(responseDTO == null){
            throw new ProductNotFoundException("There is no product with id : " + id);
        }

        return responseDTO.toProduct();
    }

    @Override
    public String deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return "Product with id : " + id + " is deleted successfully";
    }

    @Override
    public Product partialUpdateProduct(Long id, Product product) throws ProductNotFoundException, BadRequestException {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException{

        FakeStoreProductDTO toReplaceDTO = new FakeStoreProductDTO();
        restTemplate.put("https://fakestoreapi.com/products/" + id,
                toReplaceDTO, FakeStoreProductDTO.class);

        Product replacedProduct = this.getSingleProduct(product.getId());

        return replacedProduct;
    }

    @Override
    public Product createProduct(Product requestedProduct) {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.fromProduct(requestedProduct);

        FakeStoreProductDTO fakeStoreProductResponseDTO =
                restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDTO,
                FakeStoreProductDTO.class);

        return fakeStoreProductResponseDTO.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] responseDTOArray  =
            restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();
        for(int i=0; i<responseDTOArray.length; i++){
            FakeStoreProductDTO eachFakeStoreProductDTO = responseDTOArray[i];
            products.add(eachFakeStoreProductDTO.toProduct());
        }

        return products;
    }
}
