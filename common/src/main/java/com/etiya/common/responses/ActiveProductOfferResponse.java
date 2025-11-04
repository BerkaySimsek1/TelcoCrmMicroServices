package com.etiya.common.responses;


public class ActiveProductOfferResponse {
    private int productOfferId;
    private String productId;
    private double discountRate; // 0..1 (yüzde geldiyse servis 0..1'e çevirir)
    private String status; // "Active" vs.


    public int getProductOfferId() {
        return productOfferId;
    }

    public void setProductOfferId(int productOfferId) {
        this.productOfferId = productOfferId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
