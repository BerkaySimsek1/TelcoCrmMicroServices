package com.etiya.catalogservice.service.dtos.campaignProduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCampaignProductRequest {
    private String productId;
    private Integer campaignId;
}
