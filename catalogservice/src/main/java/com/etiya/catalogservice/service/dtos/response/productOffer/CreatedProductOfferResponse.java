package com.etiya.catalogservice.service.dtos.response.productOffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedProductOfferResponse {
    private int id;
    private String name;
    private String productId;
    private double discountRate;
}
