package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOfferRepository extends JpaRepository<ProductOffer, Integer> {
}
