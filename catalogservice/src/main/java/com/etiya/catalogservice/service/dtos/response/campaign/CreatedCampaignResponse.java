package com.etiya.catalogservice.service.dtos.response.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedCampaignResponse {
    private int id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String campaignCode;

    private double discountRate;
}
