package com.etiya.catalogservice.service.dtos.request.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    @NotEmpty(message = "Product name cannot be empty.")
    @Size(min = 2, max = 100)
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.")
    private double price;

    @Min(value = 0, message = "Stock cannot be negative.")
    private Integer stock; // Servisler için null olabilir

    @NotNull(message = "Catalog ID cannot be empty.")
    private int catalogId;

    @NotNull(message = "Product Specification ID cannot be empty.")
    private int productSpecificationId;
}
