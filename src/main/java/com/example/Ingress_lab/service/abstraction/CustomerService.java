package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.request.CustomerRequest;
import com.example.Ingress_lab.model.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getCustomerByPin(String pin);
    void createCustomer(CustomerRequest customerRequest);

}
