package com.etiya.basketservice.repository;

import com.etiya.basketservice.domain.Basket;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BasketRepository {

    public static final String Key = "BASKET";
    private final HashOperations<String, String, Basket> basketHashOperations;

    private final RedisTemplate<String, Object> redisTemplate;

    public BasketRepository(HashOperations<String, String, Basket> basketHashOperations, RedisTemplate<String, Object> redisTemplate) {
        this.basketHashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
    }
    // billing account'a göre yapılacak
    public void addItem(Basket basket) {
        this.basketHashOperations.put(Key,basket.getId() +"_" + basket.getCustomer_id(), basket);

    }
    public Basket getBasketByCustomerId(String customerId) {
        return basketHashOperations.entries(Key).values().stream()
                .filter(basket -> customerId.equals(basket.getCustomer_id())).findFirst().orElse(null);
    }

    public Map<String, Basket> getBasketMap() {
        return this.basketHashOperations.entries(Key);
    }



}
