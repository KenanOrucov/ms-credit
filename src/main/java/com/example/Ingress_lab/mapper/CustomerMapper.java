package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.CustomerEntity;
import com.example.Ingress_lab.model.request.CustomerRequest;
import com.example.Ingress_lab.model.response.CustomerResponse;

public enum CustomerMapper {
    CUSTOMER_MAPPER;

    public static CustomerEntity toCustomerEntity(CustomerRequest customerRequest){
        return CustomerEntity.builder()
                .pin(customerRequest.getPin())
                .fullName(customerRequest.getFullName())
                .phoneNumber(customerRequest.getPhoneNumber())
                .build();
    }

    public static CustomerResponse toCustomerResponse(CustomerEntity customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .pin(customer.getPin())
                .fullName(customer.getFullName())
                .phoneNumber(customer.getPhoneNumber())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}
