package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.OffersEntity;
import com.example.Ingress_lab.model.client.ProductResponse;
import com.example.Ingress_lab.model.response.OffersResponse;

public enum OfferMapper {
    OFFER_MAPPER;

    public static OffersEntity toOfferEntity(ProductResponse productResponse){
        return OffersEntity.builder()
                .term(productResponse.getTerm())
                .interest(productResponse.getInterest())
                .monthlyPayment(productResponse.getMonthlyPayment())
                .amount(productResponse.getAmount())
                .accepted(true)
                .build();
    }

    public static OffersResponse toOfferResponse(OffersEntity offersEntity){
        return OffersResponse.builder()
                .id(offersEntity.getId())
                .term(offersEntity.getTerm())
                .interest(offersEntity.getInterest())
                .monthlyPayment(offersEntity.getMonthlyPayment())
                .amount(offersEntity.getAmount())
                .accepted(offersEntity.getAccepted())
                .createdAt(offersEntity.getCreatedAt())
                .updatedAt(offersEntity.getUpdatedAt())
                .build();
    }
}
