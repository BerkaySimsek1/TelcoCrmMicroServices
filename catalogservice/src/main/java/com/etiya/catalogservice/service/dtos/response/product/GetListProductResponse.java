package com.etiya.catalogservice.service.dtos.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListProductResponse {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String catalogName;
}
