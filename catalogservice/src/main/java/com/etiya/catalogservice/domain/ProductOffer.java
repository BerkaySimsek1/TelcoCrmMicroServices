package com.etiya.catalogservice.domain;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_offers")
public class ProductOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date") // Bitiş tarihi null olabilir
    private LocalDateTime endDate;

    @Column(name = "discount_rate", nullable = false)
    private BigDecimal discountRate;

    @Column(name = "status", nullable = false) // Örn: "Active" [cite: 36, 179]
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false) // Bu teklif hangi ana ürüne (SKU) bağlı
    private Product product;

    // Bir teklif, birden fazla katalogda gösterilebilir
    // (CatalogProductOffer ara tablosu üzerinden)
    @OneToMany(mappedBy = "productOffer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CatalogProductOffer> catalogProductOffers;
}
