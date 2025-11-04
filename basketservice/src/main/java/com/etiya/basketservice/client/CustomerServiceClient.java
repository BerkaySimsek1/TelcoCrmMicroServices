package com.etiya.basketservice.client;

import com.etiya.common.responses.BillingAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// service to service iletişiminde doğrudan servisin portuna istek atmak gerek.
@FeignClient(name = "customerservice")
public interface CustomerServiceClient {
    @GetMapping("/api/billing-accounts/findByIdForBasket/{id}")
    BillingAccountResponse getBillingAccountById(@PathVariable("id") int id);
}
