package com.etiya.catalogservice.service.dtos.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String catalogId;
    private String productSpecificationId;
    // İstersek buraya Catalog'un adını veya Spec'in adını da ekleyebiliriz
    private String catalogName;
    private String productSpecificationName;
}
