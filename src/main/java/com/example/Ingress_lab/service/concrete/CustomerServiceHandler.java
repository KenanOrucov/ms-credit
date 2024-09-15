package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.CustomerEntity;
import com.example.Ingress_lab.dao.repository.CustomerRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.request.CustomerRequest;
import com.example.Ingress_lab.model.response.CustomerResponse;
import com.example.Ingress_lab.service.abstraction.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.Ingress_lab.mapper.CustomerMapper.toCustomerEntity;
import static com.example.Ingress_lab.mapper.CustomerMapper.toCustomerResponse;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.CUSTOMER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceHandler implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse getCustomerByPin(String pin) {
        log.info("ActionLog.getCustomerByPin pin: {}", pin);
        var customer = customerRepository
                .getCustomerEntityByPin(pin)
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND.getCode(), CUSTOMER_NOT_FOUND.getMessage()));
        log.info("ActionLog.getCustomerByPin customer: {}", customer);
        return toCustomerResponse(customer);
    }

    @Override
    public void createCustomer(CustomerRequest customerRequest) {
        log.info("ActionLog.createCustomer customerRequest: {}", customerRequest);
        customerRepository.save(toCustomerEntity(customerRequest));
        log.info("ActionLog.createCustomer end");
    }

}
