package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.SoftDeleteBillingAccountEvent;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class SoftDeletedBillingAccountConsumer {
    private final CustomerSearchService service;
    private final Logger LOGGER = LoggerFactory.getLogger(SoftDeletedAddressConsumer.class);


    public SoftDeletedBillingAccountConsumer(CustomerSearchService service) {
        this.service = service;
    }

    public Consumer<SoftDeleteBillingAccountEvent> billingAccountSoftDeleted(){
        return event -> {
            service.softDeleteBillingAccount(event.customerId(), event.id(), event.deletedDate());
            LOGGER.info(String.format("Soft Deleted billing account",event.id()));
        };
    }
}
