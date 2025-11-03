package com.etiya.catalogservice.service.dtos.request.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @NotEmpty(message = "Product name cannot be empty.")
    @Size(min = 2, max = 100)
    private String name;

    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.")
    private double price;

    @Min(value = 0, message = "Stock cannot be negative.")
    private Integer stock;

    @NotEmpty(message = "Catalog ID cannot be empty.")
    private int catalogId;

    @NotEmpty(message = "Product Specification ID cannot be empty.")
    private int productSpecificationId;
}
