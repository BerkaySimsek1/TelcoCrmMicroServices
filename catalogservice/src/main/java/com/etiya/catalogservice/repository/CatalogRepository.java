package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Integer> {
}
