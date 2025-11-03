package com.etiya.basketservice.repository;

import com.etiya.basketservice.domain.Basket;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BasketRepository {

    public static final String Key = "BASKET";

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, Basket> basketHashOperations;



    public BasketRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.basketHashOperations = redisTemplate.opsForHash();
    }
    // billing account'a göre yapılacak
    public void addItem(Basket basket) {
        this.basketHashOperations.put(Key,basket.getId() +"_" + basket.getBillingAccId(), basket);

    }
    public Basket getBasketByCustomerId(String billingAccId) {
        return basketHashOperations.entries(Key).values().stream()
                .filter(basket -> billingAccId.equals(basket.getBillingAccId())).findFirst().orElse(null);
    }

    public Map<String, Basket> getBasketMap() {
        return this.basketHashOperations.entries(Key);
    }



}
