package com.etiya.basketservice.client;


import com.etiya.common.responses.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "catalogservice",
        configuration = com.etiya.basketservice.configuration.FeignAuthConfig.class

)
public interface CatalogServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getById(@PathVariable("id") String id);
}
