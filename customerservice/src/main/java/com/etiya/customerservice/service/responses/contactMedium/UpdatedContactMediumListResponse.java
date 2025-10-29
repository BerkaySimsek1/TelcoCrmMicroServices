package com.etiya.customerservice.service.responses.contactMedium;

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
public class UpdatedContactMediumListResponse {
    private UUID customerId;
    private List<UpdatedContactMediumResponse> updatedContactMediums;
}

