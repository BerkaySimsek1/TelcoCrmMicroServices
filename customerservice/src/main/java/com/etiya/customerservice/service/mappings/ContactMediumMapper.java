package com.etiya.customerservice.service.mappings;

import com.etiya.customerservice.domain.entities.ContactMedium;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactMedium.CreatedContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.GetListContactMediumResponse;
import com.etiya.customerservice.service.responses.contactMedium.UpdatedContactMediumResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMediumMapper {
    ContactMediumMapper INSTANCE = Mappers.getMapper(ContactMediumMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    ContactMedium contactMediumFromContactMediumRequest(CreateContactMediumRequest request);

    @Mapping(target = "customerId", source = "customer.id")
    CreatedContactMediumResponse createdContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    @Mapping(target = "customerId", source = "customer.id")
    GetListContactMediumResponse getListContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    List<GetListContactMediumResponse> getListContactMediumResponseFromContactMedium(List<ContactMedium> contactMedium);

    @Mapping(target = "customerId", source = "customer.id")
    GetContactMediumResponse getContactMediumResponseFromContactMedium(ContactMedium contactMedium);

    @Mapping(target = "customer", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    ContactMedium contactMediumFromUpdateRequest(UpdateContactMediumRequest request, @MappingTarget ContactMedium contactMedium);

    @Mapping(target = "customerId", source = "customer.id")
    UpdatedContactMediumResponse updatedContactMediumResponseFromContactMedium(ContactMedium contactMedium);
}
