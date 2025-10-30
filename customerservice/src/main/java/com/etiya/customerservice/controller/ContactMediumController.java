package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.abstracts.ContactMediumService;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumListRequest;
import com.etiya.customerservice.service.requests.contactMedium.CreateContactMediumRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumListRequest;
import com.etiya.customerservice.service.requests.contactMedium.UpdateContactMediumRequest;
import com.etiya.customerservice.service.responses.contactMedium.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contact-mediums")
public class ContactMediumController {
    private final ContactMediumService contactMediumService;

    public ContactMediumController(ContactMediumService contactMediumService) {
        this.contactMediumService = contactMediumService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedContactMediumResponse add(@Valid @RequestBody CreateContactMediumRequest request) {
        return contactMediumService.add(request);
    }
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CreatedContactMediumResponse> addAsList(@Valid @RequestBody CreateContactMediumListRequest request) {
        return contactMediumService.addAsList(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getList() {
        return contactMediumService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        contactMediumService.delete(id);
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        contactMediumService.softDelete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedContactMediumResponse update(@PathVariable int id, @Valid @RequestBody UpdateContactMediumRequest request) {
        return contactMediumService.update(id,request);
    }

    @PutMapping("/updateAsList")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedContactMediumListResponse updateAsList(
            @Valid @RequestBody UpdateContactMediumListRequest request) {
        return contactMediumService.updateAsList(request);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetContactMediumResponse getByIdContactMedium(@PathVariable int id) {
        return contactMediumService.getById(id);
    }

    @GetMapping("findAllByTypeMail")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> findAllByTypeMail() {
        return contactMediumService.findAllByTypeMail();
    }

    @GetMapping("findAllByValueStartingPrefix/{prefix}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> findAllByValueStartingPrefix(@PathVariable String prefix) {
        return contactMediumService.findAllByValueStartingPrefix(prefix);
    }

    @GetMapping("findAllOrderByValueDesc")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> findAllOrderByValueDesc() {
        return  contactMediumService.findAllOrderByValueAsc();
    }
    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListContactMediumResponse> getByCustomerId(@PathVariable UUID customerId) {
        return contactMediumService.getByCustomerId(customerId);
    }

}
