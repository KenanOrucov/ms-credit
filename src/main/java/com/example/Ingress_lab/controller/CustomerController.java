package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.request.CustomerRequest;
import com.example.Ingress_lab.model.response.CustomerResponse;
import com.example.Ingress_lab.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/{pin}")
    public CustomerResponse getCustomerByPin(@PathVariable String pin) {
        return customerService.getCustomerByPin(pin);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCustomer(@RequestBody CustomerRequest request) {
        customerService.createCustomer(request);
    }
}
