package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.request.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.response.productSpecification.CreatedProductSpecificationResponse;

public interface ProductSpecificationService {

    CreatedProductSpecificationResponse add(CreateProductSpecificationRequest createProductSpecificationRequest);
}
