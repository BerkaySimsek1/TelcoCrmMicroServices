package com.etiya.customerservice.service.abstracts;


import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumListRequest;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumListRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactMedium.*;

import java.util.List;
import java.util.UUID;

public interface ContactMediumService {

    CreatedContactMediumResponse add(CreateContactMediumRequest request);
    List<CreatedContactMediumResponse> addAsList(CreateContactMediumListRequest request);

    List<GetListContactMediumResponse> getAll();
    GetContactMediumResponse getById(int id);

    UpdatedContactMediumResponse update(int id,UpdateContactMediumRequest request);
    UpdatedContactMediumListResponse updateAsList(UpdateContactMediumListRequest request);

    void delete(int id);
    void softDelete(int id);

    List<GetListContactMediumResponse> findAllByTypeMail();
    List<GetListContactMediumResponse> findAllByValueStartingPrefix(String prefix);
    List<GetListContactMediumResponse> findAllOrderByValueAsc();
    List<GetListContactMediumResponse> getByCustomerId(UUID customerId);


}
