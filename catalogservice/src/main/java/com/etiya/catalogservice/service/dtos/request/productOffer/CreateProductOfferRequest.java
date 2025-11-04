package com.etiya.catalogservice.service.dtos.request.productOffer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductOfferRequest {

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate; // null olabilir

    private double discountRate; // 0..1 ya da yüzde (örn 15). Service normalize eder.

    private String status; // örn: "Active"

    private String productId;
}
