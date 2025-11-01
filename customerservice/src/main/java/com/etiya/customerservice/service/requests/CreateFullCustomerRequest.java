package com.etiya.customerservice.service.requests;

import com.etiya.customerservice.service.requests.address.CreateAddressItem;
import com.etiya.customerservice.service.requests.address.CreateAddressRequest;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumItem;
import com.etiya.customerservice.service.requests.individualCustomer.CreateIndividualCustomerRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateFullCustomerRequest {
    private CreateIndividualCustomerRequest individualCustomer;
    private List<CreateAddressItem> addresses;
    private List<CreateContactMediumItem> contactMediums;
}
