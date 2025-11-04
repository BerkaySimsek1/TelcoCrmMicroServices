package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.service.dtos.request.productOffer.CreateProductOfferRequest;
import com.etiya.catalogservice.service.dtos.response.productOffer.CreatedProductOfferResponse;
import com.etiya.common.responses.ActiveProductOfferResponse;

import java.util.List;
import java.util.Optional;

public interface ProductOfferService {
    Optional<ActiveProductOfferResponse> getBestActiveForProduct(String productId);
    List<ActiveProductOfferResponse> getAllActive();
    CreatedProductOfferResponse add(CreateProductOfferRequest request);
}
