package com.etiya.customerservice.service.requests.contactMedium;

import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateContactMediumListRequest {
    @NotNull(message = Messages.ContactMediumCustomerIdNotNull)
    private UUID customerId;

    @NotEmpty(message = "At least two contact medium is required")
    private List<@Valid CreateContactMediumItem> contactMediums;
}
