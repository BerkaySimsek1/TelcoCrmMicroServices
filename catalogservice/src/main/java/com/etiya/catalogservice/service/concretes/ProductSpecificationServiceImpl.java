package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.catalogservice.service.abstracts.ProductSpecificationService;
import com.etiya.catalogservice.service.dtos.request.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.response.productSpecification.CreatedProductSpecificationResponse;
import com.etiya.catalogservice.service.mappers.ProductSpecificationMapper;
import com.etiya.catalogservice.service.rules.ProductSpecificationBusinessRules;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {
    private final ProductSpecificationRepository productSpecificationRepository;
    private final ProductSpecificationBusinessRules productSpecificationBusinessRules;

    public ProductSpecificationServiceImpl(ProductSpecificationRepository productSpecificationRepository, ProductSpecificationBusinessRules productSpecificationBusinessRules) {
        this.productSpecificationRepository = productSpecificationRepository;
        this.productSpecificationBusinessRules = productSpecificationBusinessRules;
    }


    @Override
    public CreatedProductSpecificationResponse add(CreateProductSpecificationRequest createProductSpecificationRequest) {

        productSpecificationBusinessRules.checkIfProductSpecificationNameExists(createProductSpecificationRequest.getName());

        ProductSpecification productSpecification = ProductSpecificationMapper
                .INSTANCE
                .productSpecFromCreateProductSpecRequest(createProductSpecificationRequest);


        ProductSpecification savedProductSpec = productSpecificationRepository.save(productSpecification);



        CreatedProductSpecificationResponse response = ProductSpecificationMapper
                .INSTANCE
                .createdProductSpecResponseFromProductSpec(savedProductSpec);

        return response;
    }
}
