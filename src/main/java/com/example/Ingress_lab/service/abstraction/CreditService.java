package com.example.Ingress_lab.service.abstraction;


import com.example.Ingress_lab.model.client.ConveyorRequest;
import com.example.Ingress_lab.model.request.CreditRequest;
import com.example.Ingress_lab.model.request.UpdateCreditStatusRequest;
import com.example.Ingress_lab.model.response.CreditResponse;

public interface CreditService {
    void saveCredit(CreditRequest credit);
    CreditResponse getCreditById(Long id);
    void saveCreditFeign(ConveyorRequest conveyorRequest);
    void updateCreditStatus(UpdateCreditStatusRequest request);
}
