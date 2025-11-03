package com.etiya.catalogservice.service.dtos.response.catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedCatalogResponse {
    private int id;
    private String name;
    private Integer parentId;
    private LocalDateTime createdDate;// Üst katalog ID'si, null olabilir (eğer kök katalogsa)
}
