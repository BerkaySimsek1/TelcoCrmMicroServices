package com.etiya.customerservice.service.requests.billingAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountRequest {
    private String accountName;
    private Integer addressId;
    // accuntNumber immutable tutuldu.
}
