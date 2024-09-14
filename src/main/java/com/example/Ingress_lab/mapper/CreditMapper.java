package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.CreditEntity;
import com.example.Ingress_lab.model.request.CreditRequest;
import com.example.Ingress_lab.model.response.CreditResponse;

import java.util.HashSet;

import static com.example.Ingress_lab.model.enums.CreditStatus.DRAFT;

public enum CreditMapper {
    CREDIT_MAPPER;

    public static CreditEntity toCreditEntity(CreditRequest creditRequest){
        return CreditEntity.builder()
                .term(creditRequest.getTerm())
                .interest(creditRequest.getInterest())
                .monthlyPayment(creditRequest.getMonthlyPayment())
                .amount(creditRequest.getAmount())
                .requestAmount(creditRequest.getRequestAmount())
                .checkDate(creditRequest.getCheckDate())
                .conveyorId(creditRequest.getConveyorId())
                .productId(creditRequest.getProductId())
                .status(DRAFT)
                .statusHistories(new HashSet<>())
                .build();
    }

    public static CreditResponse toCreditResponse(CreditEntity creditEntity){
        return CreditResponse.builder()
                .id(creditEntity.getId())
                .term(creditEntity.getTerm())
                .interest(creditEntity.getInterest())
                .monthlyPayment(creditEntity.getMonthlyPayment())
                .amount(creditEntity.getAmount())
                .requestAmount(creditEntity.getRequestAmount())
                .checkDate(creditEntity.getCheckDate())
                .productId(creditEntity.getProductId())
                .conveyorId(creditEntity.getConveyorId())
                .status(creditEntity.getStatus())
                .build();
    }
}
