package com.etiya.customerservice.service.requests.billingAccount;

import com.etiya.customerservice.service.messages.Messages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountRequest {
    @NotBlank(message = Messages.BillingAccountAccountNameNotBlank)
    private String accountName;
    private Integer addressId;
    // accuntNumber immutable tutuldu.
}
