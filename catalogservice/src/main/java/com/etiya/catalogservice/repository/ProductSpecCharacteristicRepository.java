package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProductSpecCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpecCharacteristicRepository extends JpaRepository<ProductSpecCharacteristic, Integer> {
}
