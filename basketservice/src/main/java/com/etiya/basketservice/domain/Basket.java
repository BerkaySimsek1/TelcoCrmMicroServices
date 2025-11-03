package com.etiya.basketservice.domain;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Basket implements Serializable {

    private String id;

    private String customer_id; // billing account id'si kullanılacak

    private double totalPrice;

    private List<BasketItem> basketItems;

    public Basket(){
        this.id = UUID.randomUUID().toString();
        this.basketItems = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}
