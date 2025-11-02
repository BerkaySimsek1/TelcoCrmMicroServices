package com.etiya.catalogservice.service.dtos.request.productSpecification;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductSpecificationRequest {
    @NotEmpty(message = "Specification name cannot be empty.")
    @Size(min = 2, max = 100)
    private String name;

    private String description;

    @NotEmpty(message = "Lifecycle status cannot be empty.")
    private String lifecycleStatus; // Örn: "Active", "Planning"

    @NotEmpty(message = "Product type cannot be empty.")
    private String productType; // Örn: "Service", "Device"
}
