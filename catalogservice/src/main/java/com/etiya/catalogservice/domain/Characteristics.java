package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "characteristics")
public class Characteristics extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "data_type", nullable = false)
    private String dataType; // Örn: "Number", "String", "Boolean"

    @Column(name = "unit_of_measure", nullable = false)
    private String unitOfMeasure; // Örn: "Mbps", "Ay", "GB"

    // Bir niteliğin (örn: Hız) birden fazla ön tanımlı değeri olabilir (örn: 8, 16, 35)
    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CharacteristicValue> characteristicValues;

    // Bir nitelik, birden fazla ürün şablonu (spec) tarafından kullanılabilir
    @OneToMany(mappedBy = "characteristic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductSpecCharacteristic> productSpecCharacteristics;
}
