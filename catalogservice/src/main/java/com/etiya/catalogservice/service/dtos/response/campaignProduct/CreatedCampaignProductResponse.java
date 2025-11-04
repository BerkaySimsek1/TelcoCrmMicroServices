package com.etiya.catalogservice.service.dtos.response.campaignProduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedCampaignProductResponse {
    private int id; // campaignProductId
    private String productId;
    private int campaignId;
}
