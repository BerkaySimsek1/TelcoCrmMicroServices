package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.domain.entities.Campaign;
import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.request.campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.response.campaign.CreatedCampaignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campaigns/")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CreatedCampaignResponse createCampaign(@RequestBody CreateCampaignRequest campaign) {
        return campaignService.createCampaign(campaign);
    }
}
