package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.request.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.request.product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.response.product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.response.product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.response.product.GetProductResponse;
import com.etiya.catalogservice.service.dtos.response.product.UpdatedProductResponse;
import com.etiya.common.responses.ProductResponse;

import java.util.List;

public interface ProductService {

    CreatedProductResponse add(CreateProductRequest request);
    UpdatedProductResponse update(UpdateProductRequest request);
    void softDelete(int id); // soft delete
    void delete(int id);
    //GetProductResponse getById(int id);
    List<GetListProductResponse> getAll();

    ProductResponse getById(String id);
}
