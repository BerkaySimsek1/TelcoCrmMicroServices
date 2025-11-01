package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CatalogProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogProductOfferRepository extends JpaRepository<CatalogProductOffer, Integer> {
}
