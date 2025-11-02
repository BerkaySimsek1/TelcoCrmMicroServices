package com.etiya.catalogservice.service.dtos.response.productSpecification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedProductSpecificationResponse {
    private Integer id;
    private String name;
    private String description;
    private String lifecycleStatus;
    private String productType;
    private LocalDateTime createdDate;
}
