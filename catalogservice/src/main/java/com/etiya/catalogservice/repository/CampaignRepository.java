package com.etiya.catalogservice.repository;

import com.etiya.catalogservice.domain.entities.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {
    // İleride business rule için lazım olacak:
    // boolean existsByCampaignCode(String campaignCode);
}
