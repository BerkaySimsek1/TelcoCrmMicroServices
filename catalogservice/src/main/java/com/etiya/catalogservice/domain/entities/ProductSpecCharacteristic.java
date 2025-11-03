package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_spec_characteristics",
        uniqueConstraints = {
                // Bir 'Spec' için bir 'Characteristic' sadece bir kez tanımlanabilir
                @UniqueConstraint(columnNames = {"spec_id", "characteristic_id"})
        })
public class ProductSpecCharacteristic extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_required", nullable = false)
    private boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id", nullable = false)
    private ProductSpecification productSpecification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characteristic_id", nullable = false)
    private Characteristic characteristic;
}
