package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.request.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.response.product.CreatedProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductResponse addProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return productService.add(createProductRequest);
    }

}
