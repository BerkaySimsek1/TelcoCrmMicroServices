package com.etiya.common.events;

public record SoftDeleteCustomerEvent(
        String customerId,
        String deletedDate // Silinme tarihini String olarak gönderelim
) {
}
