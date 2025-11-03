package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.request.catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.response.catalog.CreatedCatalogResponse;

public interface CatalogService {

    CreatedCatalogResponse add(CreateCatalogRequest request);
}
