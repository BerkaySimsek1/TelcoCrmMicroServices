package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Product;
import com.etiya.catalogservice.repository.ProductRepository;
import com.etiya.catalogservice.service.abstracts.ProductService;
import com.etiya.catalogservice.service.dtos.request.product.CreateProductRequest;
import com.etiya.catalogservice.service.dtos.request.product.UpdateProductRequest;
import com.etiya.catalogservice.service.dtos.response.product.CreatedProductResponse;
import com.etiya.catalogservice.service.dtos.response.product.GetListProductResponse;
import com.etiya.catalogservice.service.dtos.response.product.GetProductResponse;
import com.etiya.catalogservice.service.dtos.response.product.UpdatedProductResponse;
import com.etiya.catalogservice.service.mappers.ProductMapper;
import com.etiya.catalogservice.service.rules.ProductBusinessRules;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import com.etiya.common.responses.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductBusinessRules productBusinessRules;

    public ProductServiceImpl(ProductRepository productRepository,ProductBusinessRules productBusinessRules) {
        this.productRepository = productRepository;

        this.productBusinessRules = productBusinessRules;
    }

    @Override
    public CreatedProductResponse add(CreateProductRequest request) {
        productBusinessRules.checkIfCatalogExists(request.getCatalogId());
        productBusinessRules.checkIfProductSpecificationExists(request.getProductSpecificationId());
        Product product = ProductMapper.INSTANCE.productFromCreateProductRequest(request);

        Product savedProduct = productRepository.save(product);


        CreatedProductResponse response = ProductMapper.INSTANCE.createdProductResponseFromProduct(savedProduct);
        return response;
    }


    @Override
    public UpdatedProductResponse update(UpdateProductRequest request) {
        return null;
    }

    @Override
    public void softDelete(int id) {

    }

    @Override
    public void delete(int id) {

    }

//    @Override
//    public GetProductResponse getById(int id) {
//        return null;
//    }

    @Override
    public List<GetListProductResponse> getAll() {
        return List.of();
    }

    @Override
    public ProductResponse getById(String id) {
        return productRepository.findById(id).stream().map(this::mapToResponse).findFirst().orElseThrow(() -> new BusinessException("Product not found"));
    }


    private ProductResponse mapToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }
}
