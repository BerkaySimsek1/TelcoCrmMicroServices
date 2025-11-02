package com.etiya.catalogservice.service.dtos.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedProductResponse {

    private String name;
    private BigDecimal price;
    private Integer stock;
    private String catalogId;
    private String productSpecificationId;
    private LocalDateTime updatedDate;
}