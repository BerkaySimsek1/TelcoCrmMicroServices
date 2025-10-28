package com.etiya.common.events;

public record CreateBillingAccountEvent(
        String customerId,
        int addressId,
        int id,
        String type,
        String status,
        String accountNumber,
        String accountName
        ) {
}
