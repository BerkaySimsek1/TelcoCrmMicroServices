package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.ProductOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductOfferRepository extends JpaRepository<ProductOffer, Integer> {
    @Query("""
           select po from ProductOffer po
           where po.product.id = :productId
             and po.status = 'Active'
             and po.startDate <= CURRENT_TIMESTAMP
             and (po.endDate is null or po.endDate >= CURRENT_TIMESTAMP)
           order by po.discountRate desc
           """)
    Optional<ProductOffer> findBestActiveForProduct(String productId);

    @Query("""
           select po from ProductOffer po
           where po.status = 'Active'
             and po.startDate <= CURRENT_TIMESTAMP
             and (po.endDate is null or po.endDate >= CURRENT_TIMESTAMP)
           """)
    java.util.List<ProductOffer> findAllActive();

}
