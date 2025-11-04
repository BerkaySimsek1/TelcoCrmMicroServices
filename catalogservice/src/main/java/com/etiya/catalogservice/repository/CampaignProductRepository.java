package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.CampaignProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignProductRepository extends JpaRepository<CampaignProducts, Integer> {
    // Ürüne bağlı, tarihi "aktif" kampanyalar içinde en yüksek indirimli olanı getir
    @Query("""
           select cp from CampaignProducts cp
           join cp.campaign c
           where cp.product.id = :productId
             and c.startDate <= CURRENT_TIMESTAMP
             and (c.endDate is null or c.endDate >= CURRENT_TIMESTAMP)
           """)
    List<CampaignProducts> findActiveByProduct(String productId);

    @Query("""
           select cp from CampaignProducts cp
           join cp.campaign c
           where c.startDate <= CURRENT_TIMESTAMP
             and (c.endDate is null or c.endDate >= CURRENT_TIMESTAMP)
           """)
    List<CampaignProducts> findAllActive();

}
