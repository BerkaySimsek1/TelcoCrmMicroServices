package com.etiya.common.events;

public record DeleteBillingAccountEvent(
        String customerId,
        int billingAccountId
) {
}
