package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.SoftDeleteAddressEvent;
import com.etiya.common.events.UpdateBillingAccountEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class UpdateBillingAccountProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoftDeleteAddressProducer.class);
    private final StreamBridge streamBridge;


    public UpdateBillingAccountProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceBillingAccountUpdated(UpdateBillingAccountEvent event) {
        streamBridge.send("billingAccountUpdated-out-0", event);
        LOGGER.info(String.format("Updated Billing Account [%s]", event));
    }
}
