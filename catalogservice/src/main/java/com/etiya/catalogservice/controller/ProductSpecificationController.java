package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.request.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.response.productSpecification.CreatedProductSpecificationResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-spec/")
public class ProductSpecificationController {

    private final ProductSpecificationService productSpecificationService;
    public ProductSpecificationController(ProductSpecificationService productSpecificationService) {
        this.productSpecificationService = productSpecificationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedProductSpecificationResponse addProductSpecification(@Valid @RequestBody CreateProductSpecificationRequest request) {
        return productSpecificationService.add(request);
    }
}
