package com.etiya.searchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingAccount {
    int addressId;
    int id;
    String type;
    String status;
    String accountNumber;
    String accountName;
}
