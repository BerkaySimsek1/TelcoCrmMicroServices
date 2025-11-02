package com.etiya.catalogservice.service.rules;

import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecificationBusinessRules {
    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductSpecificationBusinessRules(ProductSpecificationRepository productSpecificationRepository) {
        this.productSpecificationRepository = productSpecificationRepository;
    }

    /**
     * Aynı isimde bir 'ProductSpecification' (Teknik Şablon) olup olmadığını kontrol eder.
     */
    public void checkIfProductSpecificationNameExists(String name) {
        if (productSpecificationRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException("ProductSpecification with the same name already exists.");
        }
    }
}
