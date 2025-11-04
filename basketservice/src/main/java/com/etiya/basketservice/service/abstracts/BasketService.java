package com.etiya.basketservice.service.abstracts;

import com.etiya.basketservice.domain.Basket;

import java.util.Map;

public interface BasketService {
    void add(int billingAccountId, String productId);

    Map<String, Basket> getAll();
}
