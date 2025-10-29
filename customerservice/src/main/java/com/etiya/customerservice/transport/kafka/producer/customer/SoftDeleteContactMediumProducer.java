package com.etiya.customerservice.transport.kafka.producer.customer;

import com.etiya.common.events.SoftDeleteContactMediumEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class SoftDeleteContactMediumProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SoftDeleteAddressProducer.class);
    private final StreamBridge streamBridge;


    public SoftDeleteContactMediumProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void produceContactMediumSoftDeleted(SoftDeleteContactMediumEvent event) {
        streamBridge.send("contactMediumSoftDeleted-out-0", event);
        LOGGER.info(String.format("Deleted Contact Medium [%s]", event));
    }
}
