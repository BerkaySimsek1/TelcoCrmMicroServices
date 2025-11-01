package com.etiya.customerservice.controller;

import com.etiya.customerservice.service.orchestrators.CustomerOnboardingService;
import com.etiya.customerservice.service.requests.CreateFullCustomerRequest;
import com.etiya.customerservice.service.responses.CreateFullCustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final CustomerOnboardingService service;

    public OnboardingController(CustomerOnboardingService service) {
        this.service = service;
    }

    @PostMapping("/customers/full")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateFullCustomerResponse createFull(@RequestBody CreateFullCustomerRequest req) {
        return service.createFull(req);
    }
}
