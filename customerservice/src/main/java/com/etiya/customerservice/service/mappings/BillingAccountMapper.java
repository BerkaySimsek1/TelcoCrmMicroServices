package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.Address;
import com.etiya.customerservice.domain.entities.BillingAccount;
import com.etiya.customerservice.service.requests.address.UpdateAddressRequest;
import com.etiya.customerservice.service.requests.billingAccount.CreateBillingAccountRequest;
import com.etiya.customerservice.service.requests.billingAccount.UpdateBillingAccountRequest;
import com.etiya.customerservice.service.responses.address.UpdatedAddressResponse;
import com.etiya.customerservice.service.responses.billingAccount.CreatedBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.GetBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.GetListBillingAccountResponse;
import com.etiya.customerservice.service.responses.billingAccount.UpdatedBillingAccountResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BillingAccountMapper {
    BillingAccountMapper INSTANCE = Mappers.getMapper(BillingAccountMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "address.id", source = "addressId")
    BillingAccount billingAccountFromBillingAccountRequest(CreateBillingAccountRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "addressId", source = "address.id")
    CreatedBillingAccountResponse createdBillingAccountFromBillingAccount(BillingAccount billingAccount);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "address", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    BillingAccount billingAccountFromUpdateBillingAccountRequest(UpdateBillingAccountRequest request, @MappingTarget BillingAccount  billingAccount);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "addressId", source = "address.id")
    UpdatedBillingAccountResponse updatedBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "addressId", source = "address.id")
    @Mapping(target = "cityName", source = "address.district.city.name")        // <-- EK
    @Mapping(target = "districtName", source = "address.district.name")
    GetListBillingAccountResponse getListBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);

    List<GetListBillingAccountResponse> getListBillingAccountResponseFromBillingAccount(List<BillingAccount> billingAccount);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "addressId", source = "address.id")
    GetBillingAccountResponse getBillingAccountResponseFromBillingAccount(BillingAccount billingAccount);


}
