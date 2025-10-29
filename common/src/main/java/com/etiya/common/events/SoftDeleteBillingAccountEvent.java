package com.etiya.common.events;

public record SoftDeleteBillingAccountEvent(
        String customerId,
        int id,
        String deletedDate
) {

}
