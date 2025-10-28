package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.DeleteBillingAccountEvent;
import com.etiya.common.events.SoftDeleteAddressEvent;
import com.etiya.common.events.SoftDeleteBillingAccountEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SoftDeleteBillingAccountProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoftDeleteAddressProducer.class);
    private final StreamBridge streamBridge;

    public SoftDeleteBillingAccountProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void produceBillingAccountSoftDeleted(SoftDeleteBillingAccountEvent event) {
        streamBridge.send("billingAccountSoftDeleted-out-0", event);
        LOGGER.info(String.format("Deleted Billing Account [%s]", event));
    }
}
