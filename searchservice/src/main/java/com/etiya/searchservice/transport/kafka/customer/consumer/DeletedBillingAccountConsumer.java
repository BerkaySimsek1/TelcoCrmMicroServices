package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.DeleteBillingAccountEvent;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DeletedBillingAccountConsumer {
    private final CustomerSearchService service;
    private final Logger LOGGER = LoggerFactory.getLogger(DeletedAddressConsumer.class);

    public DeletedBillingAccountConsumer(CustomerSearchService service) {
        this.service = service;
    }

    @Bean
    public Consumer<DeleteBillingAccountEvent> billingAccountDeleted() {
        return event -> {
            // HATALIYDI: deleteAddress(...) çağrılıyordu
            service.deleteBillingAccount(event.customerId(), event.billingAccountId()); // YÖNTEM DÜZELTİLDİ
            LOGGER.info("Deleted billing account id={}", event.billingAccountId()); // LOG FORMAT DÜZELTİLDİ
        };
    }
}
