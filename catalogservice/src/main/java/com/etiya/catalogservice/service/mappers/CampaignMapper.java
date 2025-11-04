package com.etiya.catalogservice.service.mappers;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.service.dtos.request.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.response.campaign.CreatedCampaignResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CampaignMapper {
    CampaignMapper INSTANCE = Mappers.getMapper(CampaignMapper.class);

    //create

    Campaign campaignFromCreateCampaignRequest(CreateCampaignRequest request);
    CreatedCampaignResponse createdCampaignResponseFromCampaign(Campaign campaign);
}
