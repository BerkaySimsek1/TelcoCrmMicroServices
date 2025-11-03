package com.etiya.customerservice.service.requests.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressItem {
    private String street;
    private String houseNumber;
    private String description;
    private int districtId;
    private boolean isDefault;
}
