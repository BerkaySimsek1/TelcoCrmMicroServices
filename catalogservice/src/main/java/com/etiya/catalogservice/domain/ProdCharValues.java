package com.etiya.catalogservice.domain;

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
@Table(name = "product_char_values",
        uniqueConstraints = {
        // Bir ürün için bir nitelik değeri sadece bir kez sabitlenebilir
        @UniqueConstraint(columnNames = {"char_value_id", "product_id"})
})
public class ProdCharValues extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "char_value_id", nullable = false)
    private CharacteristicValue characteristicValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


}
