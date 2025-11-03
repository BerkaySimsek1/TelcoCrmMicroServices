package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spec_id", nullable = false) // Hangi teknik özellikleri (spec) kullanıyor
    private ProductSpecification productSpecification;

    // Bir ürün birden fazla teklifte (Offer) kullanılabilir
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOffer> productOffers;

    // Bir ürünün birden fazla sabitlenmiş karakteristik değeri olabilir (Örn: 8 Mbps ürünü -> Hız = 8)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProdCharValues> prodCharValues;

    // Bir ürün birden fazla kampanyaya dahil edilebilir
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CampaignProducts> campaignProducts;
}
