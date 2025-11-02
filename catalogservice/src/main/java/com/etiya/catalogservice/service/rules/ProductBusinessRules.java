package com.etiya.catalogservice.service.rules;

import com.etiya.catalogservice.repository.CatalogRepository;
import com.etiya.catalogservice.repository.ProductSpecificationRepository;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public class ProductBusinessRules {
    private final CatalogRepository catalogRepository;
    private final ProductSpecificationRepository productSpecificationRepository;

    public ProductBusinessRules(CatalogRepository catalogRepository, ProductSpecificationRepository productSpecificationRepository) {
        this.catalogRepository = catalogRepository;
        this.productSpecificationRepository = productSpecificationRepository;
    }
    // Gerekirse ProductRepository de enjekte edilebilir (örn: aynı isimde ürün kontrolü)

    public void checkIfCatalogExists(int catalogId) {
        if (!catalogRepository.existsById(catalogId)) {
            // Normalde bu mesajları messages.properties'ten almalıyız
            throw new BusinessException("Catalog with ID " + catalogId + " does not exist.");
        }
    }

    public void checkIfProductSpecificationExists(int specId) {
        if (!productSpecificationRepository.existsById(specId)) {
            throw new BusinessException("ProductSpecification with ID " + specId + " does not exist.");
        }
    }
}
