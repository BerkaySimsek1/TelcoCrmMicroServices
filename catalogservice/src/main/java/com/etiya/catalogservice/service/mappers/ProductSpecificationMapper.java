package com.etiya.catalogservice.service.mappers;

import com.etiya.catalogservice.domain.entities.ProductSpecification;
import com.etiya.catalogservice.service.dtos.request.productSpecification.CreateProductSpecificationRequest;
import com.etiya.catalogservice.service.dtos.response.productSpecification.CreatedProductSpecificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSpecificationMapper {

    ProductSpecificationMapper INSTANCE = Mappers.getMapper(ProductSpecificationMapper.class);
    //create
    ProductSpecification productSpecFromCreateProductSpecRequest(CreateProductSpecificationRequest request);
    CreatedProductSpecificationResponse createdProductSpecResponseFromProductSpec(ProductSpecification productSpecification);
}
