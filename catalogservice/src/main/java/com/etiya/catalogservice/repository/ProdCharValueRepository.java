package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProdCharValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdCharValueRepository extends JpaRepository<ProdCharValues, Integer> {
}
