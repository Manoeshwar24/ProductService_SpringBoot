package com.example.productservice.controllers;

import com.example.productservice.dtos.mydtos.basedtos.ResponseCategoryDTO;
import com.example.productservice.dtos.mydtos.basedtos.ResponseTopProductDTO;
import com.example.productservice.dtos.mydtos.categorydtos.*;
import com.example.productservice.exceptions.CategoryAlreadyExistsException;
import com.example.productservice.exceptions.CategoryNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/topProducts/{id}")
    public ResponseEntity<GetTopProductsDTO> getTopProducts(@PathVariable Long id) throws CategoryNotFoundException {

        List<Product> topProducts = categoryService.getTopProducts(id);
        //convert the product list to responseDTO
        GetTopProductsDTO topProductsDTO = new GetTopProductsDTO();
        List<ResponseTopProductDTO> productDTO = new ArrayList<>();
        for (Product product : topProducts) {
            //set the category detail in response DTO
            if(topProductsDTO.getCategory() == null){
                ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
                responseCategoryDTO.fromCategory(product.getCategory());

                topProductsDTO.setCategory(responseCategoryDTO);
            }

            ResponseTopProductDTO responseTopProductDTO = new ResponseTopProductDTO();
            responseTopProductDTO.fromProduct(product);
            productDTO.add(responseTopProductDTO);

            topProductsDTO.getTopProducts().add(responseTopProductDTO);
        }

        return new ResponseEntity<>(topProductsDTO, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<GetAllCategoryResponseDTO> getAllCategories() {
        //create the responseDTO
        GetAllCategoryResponseDTO responseDTO = new GetAllCategoryResponseDTO();
        List<ResponseCategoryDTO> allCategories = categoryService.getAllCategories();
        responseDTO.setCategoryDTOList(allCategories);
        responseDTO.setResponseMessage("Successfully retrieved all categories");

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<GetCategoryResponseDTO> getCategory(@PathVariable Long categoryId)
    throws CategoryNotFoundException {
        //call the service to get the category
        Category requestedCategory = categoryService.getCategory(categoryId);
        //create the response DTO
        GetCategoryResponseDTO responseDTO = new GetCategoryResponseDTO();
        ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
        responseCategoryDTO.fromCategory(requestedCategory);
        responseDTO.setCategoryDTO(responseCategoryDTO);
        responseDTO.setResponseMessage("Successfully retrieved category with id: " + categoryId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<PutCategoryResponseDTO> updateCategory(@PathVariable Long categoryId,
                                                                @RequestBody PutCategoryRequestDTO requestDTO)
    throws CategoryNotFoundException {
        Category toUpdateCategory = requestDTO.getCategoryDTO().toCategory();

        //set the id of the category to be updated
        toUpdateCategory.setId(categoryId);

        //call the service to update the category
        Category updatedCategory = categoryService.updateCategory(toUpdateCategory);

        //create the response DTO
        PutCategoryResponseDTO responseDTO = new PutCategoryResponseDTO();
        ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
        responseCategoryDTO.fromCategory(updatedCategory);
        responseDTO.setCategoryDTO(responseCategoryDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PostCategoryResponseDTO> createCategory(@RequestBody PostCategoryRequestDTO requestDTO)
    throws CategoryAlreadyExistsException {
        //create the category object from the request DTO
        Category toBeCreatedCategory = requestDTO.getCategoryDTO().toCategory();
        //call the service to create the category
        Category createdCategory = categoryService.createCategory(toBeCreatedCategory);
        //create the response DTO
        PostCategoryResponseDTO responseDTO = new PostCategoryResponseDTO();
        ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
        responseCategoryDTO.fromCategory(createdCategory);
        responseDTO.setCategoryDTO(responseCategoryDTO);
        responseDTO.setResponseMessage("Successfully created category");
        //set the response message
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<DeleteCategoryResponseDTO> deleteCategory(@PathVariable Long categoryId)
    throws CategoryNotFoundException {
        //call the service to delete the category
        String responseMessage = categoryService.deleteCategory(categoryId);
        //create the response DTO
        DeleteCategoryResponseDTO responseDTO = new DeleteCategoryResponseDTO();
        responseDTO.setResponseMessage(responseMessage);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<PatchCategoryResponseDTO> patchCategory(@PathVariable Long categoryId,
                                            @RequestBody PatchCategoryRequestDTO requestDTO)
    throws CategoryNotFoundException{
        //create the category object from the request DTO
        Category toBePatchedCategory = requestDTO.getRequestCategoryDTO().toCategory();
        //set the id of the category to be patched
        toBePatchedCategory.setId(categoryId);
        //call the service to patch the category
        Category patchedCategory = categoryService.patchCategory(toBePatchedCategory);
        //create the response DTO
        PatchCategoryResponseDTO responseDTO = new PatchCategoryResponseDTO();
        ResponseCategoryDTO responseCategoryDTO = new ResponseCategoryDTO();
        responseCategoryDTO.fromCategory(patchedCategory);
        responseDTO.setCategoryDTO(responseCategoryDTO);
        responseDTO.setResponseMessage("Successfully patched category with id: " + categoryId);
        //set the response message
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
