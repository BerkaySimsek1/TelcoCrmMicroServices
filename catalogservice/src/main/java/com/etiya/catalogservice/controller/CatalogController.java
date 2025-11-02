package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.dtos.request.catalog.CreateCatalogRequest;

import com.etiya.catalogservice.service.dtos.response.catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.response.product.CreatedProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalogs/")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCatalogResponse addCatalog(@Valid @RequestBody CreateCatalogRequest createCatalogRequest) {
        return catalogService.add(createCatalogRequest);
    }
}
