package com.etiya.common.events;

public record UpdateBillingAccountEvent(
        int id,
        String customerId,
        int addressId,
        String type,
        String status,
        String accountNumber,
        String accountName
) {
}
