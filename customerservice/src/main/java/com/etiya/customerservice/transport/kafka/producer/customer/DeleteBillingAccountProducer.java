package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.DeleteBillingAccountEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class DeleteBillingAccountProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAddressProducer.class);
    private final StreamBridge streamBridge;

    public DeleteBillingAccountProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceBillingAccountDeleted(DeleteBillingAccountEvent event) {
        streamBridge.send("billingAccountDeleted-out-0", event);
        LOGGER.info(String.format("Deleted Billing Account [%s]", event));
    }
}
