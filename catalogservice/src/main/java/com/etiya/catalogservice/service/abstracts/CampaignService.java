package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.service.dtos.request.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.response.campaign.CreatedCampaignResponse;

public interface CampaignService {
    CreatedCampaignResponse createCampaign(CreateCampaignRequest request);
}
