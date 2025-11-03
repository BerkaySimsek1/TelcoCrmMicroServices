package com.etiya.catalogservice.service.rules;

import com.etiya.catalogservice.repository.CatalogRepository;
import com.etiya.common.crosscuttingconcerns.exceptions.types.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CatalogBusinessRules {
    private final CatalogRepository catalogRepository;

    public CatalogBusinessRules(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    /**
     * Eğer bir parentId (üst katalog) gönderildiyse,
     * bu ID'ye sahip bir kataloğun gerçekten var olup olmadığını kontrol eder.
     */
    public void checkIfParentCatalogExists(Integer parentId) {
        if (parentId != null && !catalogRepository.existsById(parentId)) {
            // Normalde bu mesajları messages.properties'ten almalıyız
            throw new BusinessException("Parent Catalog with ID " + parentId + " does not exist.");
        }
    }

    // İleride eklenebilir:
    // public void checkIfCatalogNameExists(String name) { ... }
}
