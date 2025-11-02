package com.etiya.catalogservice.service.dtos.request.catalog;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCatalogRequest {
    @NotEmpty(message = "Catalog name cannot be empty.")
    @Size(min = 2, max = 100)
    private String name;

    private Integer parentId; // Üst katalog ID'si, null olabilir (eğer kök katalogsa)

}
