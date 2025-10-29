package com.etiya.common.events;

public record SoftDeleteContactMediumEvent(
        String customerId,
        int id,
        String deletedDate
) {
}
