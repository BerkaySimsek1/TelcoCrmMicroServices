package com.etiya.catalogservice.service.mappers;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.service.dtos.request.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.response.product.CreatedProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    //create
    @Mapping(target = "catalog.id", source = "catalogId")
    @Mapping(target = "productSpecification.id", source = "productSpecificationId")
    Product productFromCreateProductRequest(CreateProductRequest request);
    @Mapping(target = "catalogId", source = "catalog.id")
    @Mapping(target = "productSpecificationId", source = "productSpecification.id")
    CreatedProductResponse createdProductResponseFromProduct(Product product);
}
