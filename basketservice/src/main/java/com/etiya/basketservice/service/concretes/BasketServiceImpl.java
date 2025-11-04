package com.etiya.basketservice.service.concretes;

import com.etiya.basketservice.client.CatalogServiceClient;
import com.etiya.basketservice.client.CustomerServiceClient;
import com.etiya.basketservice.domain.Basket;
import com.etiya.basketservice.domain.BasketItem;
import com.etiya.basketservice.repository.BasketRepository;
import com.etiya.basketservice.service.abstracts.BasketService;
import com.etiya.common.responses.ActiveCampaignProductResponse;
import com.etiya.common.responses.ActiveProductOfferResponse;
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

        // 1) Aktif offer & kampanya çek
        ActiveProductOfferResponse offer = null;
        ActiveCampaignProductResponse camp = null;
        try { offer = catalogServiceClient.getBestActiveOffer(productId); } catch (Exception ignored) {}
        try { camp  = catalogServiceClient.getBestActiveCampaign(productId); } catch (Exception ignored) {}

        double offerRate = (offer != null) ? normalize(offer.getDiscountRate()) : 0.0;
        double campRate  = (camp  != null) ? normalize(camp.getDiscountRate())  : 0.0;
        double bestRate  = Math.max(offerRate, campRate);

        int offerId = (offer != null && offer.getDiscountRate() > 0) ? offer.getProductOfferId() : 0;
        int campId  = (camp  != null && camp.getDiscountRate()  > 0) ? camp.getCampaignProductId() : 0;

        // 2) Sepette aynı ürün var mı? Varsa quantity++ yoksa yeni satır
        BasketItem item = basket.getBasketItems().stream()
                .filter(i -> i.getProductId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (item == null) {
            item = new BasketItem();
            item.setProductId(product.getId());
            item.setProductName(product.getProductName());
            item.setProductPrice(product.getPrice());
            item.setQuantity(1);
            item.setDiscount(bestRate);
            item.setProductOfferId(offerId);
            item.setCampaignProductId(campId);
            basket.getBasketItems().add(item);
        } else {
            // varsa aynı satırı artır ve güncel en iyi indirimi uygula
            item.setQuantity(item.getQuantity() + 1);
            item.setProductPrice(product.getPrice()); // ürün fiyatı değişmişse güncel kabul
            item.setDiscount(bestRate);
            item.setProductOfferId(offerId);
            item.setCampaignProductId(campId);
        }

        // 3) Sepet total'ı yeniden hesapla (birikmeli değil!)
        recalcBasketTotal(basket);

        basketRepository.addItem(basket);
    }

    private void recalcBasketTotal(Basket basket) {
        double total = 0.0;
        for (var it : basket.getBasketItems()) {
            double unit = it.getDiscountedPrice(); // productPrice*(1-discount)
            total += unit * it.getQuantity();
        }
        basket.setTotalPrice(total);
    }

    private double normalize(double rate) {
        return rate > 1.0 ? rate / 100.0 : Math.max(0.0, Math.min(1.0, rate));
    }

    @Override
    public Map<String, Basket> getAll() {
        return basketRepository.getAll();
    }
}
