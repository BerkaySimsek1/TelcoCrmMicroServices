package com.etiya.searchservice.transport.kafka.customer.consumer;

import com.etiya.common.events.UpdateBillingAccountEvent;
import com.etiya.searchservice.domain.BillingAccount;
import com.etiya.searchservice.service.CustomerSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class UpdatedBillingAccountConsumer {
    private final CustomerSearchService service;
    private final Logger LOGGER = LoggerFactory.getLogger(UpdatedContactMediumConsumer.class);


    public UpdatedBillingAccountConsumer(CustomerSearchService service) {
        this.service = service;
    }

    @Bean
    public Consumer<UpdateBillingAccountEvent> billingAccountUpdated() {
        return event -> {
            BillingAccount billingAccount = new BillingAccount(
                    event.addressId(),
                    event.id(),
                    event.type(),
                    event.status(),
                    event.accountNumber(),
                    event.accountName()
            );
            service.updateBillingAccount(event.customerId(), billingAccount);
            LOGGER.info(String.format("Billing account updated: %s", billingAccount));
        };
    }
}
