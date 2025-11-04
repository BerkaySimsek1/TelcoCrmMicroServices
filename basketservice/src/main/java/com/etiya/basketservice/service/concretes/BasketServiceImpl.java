package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final CustomerServiceClient customerServiceClient;
    private final CatalogServiceClient catalogServiceClient;

    public BasketServiceImpl(BasketRepository basketRepository, CustomerServiceClient customerServiceClient, CatalogServiceClient catalogServiceClient) {
        this.basketRepository = basketRepository;
        this.customerServiceClient = customerServiceClient;
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    public void add(int billingAccountId, String productId) {
        var billingAccount = customerServiceClient.getBillingAccountById(billingAccountId);
        var product = catalogServiceClient.getById(productId);

        var basket = basketRepository.getBasketByBillingAccountId(billingAccount.getId());

        if (basket == null) {
            basket = new Basket();
            basket.setBillingAccId(billingAccount.getId());
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setProductId(product.getId());
        basketItem.setProductName(product.getProductName());
        basketItem.setProductPrice(product.getPrice());
        basket.setBillingAccId(billingAccount.getId());
        basket.setTotalPrice(basket.getTotalPrice() + basketItem.getProductPrice());
        basket.getBasketItems().add(basketItem);

        basketRepository.addItem(basket);
    }

    @Override
    public Map<String, Basket> getAll() {
        return basketRepository.getAll();
    }
}
