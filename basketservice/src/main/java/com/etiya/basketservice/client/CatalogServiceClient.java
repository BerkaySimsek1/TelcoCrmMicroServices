package com.etiya.basketservice.client;


import com.etiya.common.responses.ActiveCampaignProductResponse;
import com.etiya.common.responses.ActiveProductOfferResponse;
import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "catalogservice"
)
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getById(@PathVariable("id") String id);

    // Ürüne bağlı en iyi aktif teklif
    @GetMapping("/api/product-offers/active/{productId}")
    ActiveProductOfferResponse getBestActiveOffer(@PathVariable("productId") String productId);

    // Ürüne bağlı en iyi aktif kampanya
    @GetMapping("/api/campaign-products/active/{productId}")
    ActiveCampaignProductResponse getBestActiveCampaign(@PathVariable("productId") String productId);

}
