package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.OffersEntity;
import com.example.Ingress_lab.model.client.ProductResponseDto;
import com.example.Ingress_lab.model.response.OffersResponse;

public enum OfferMapper {
    OFFER_MAPPER;

    public static OffersEntity toOfferEntity(ProductResponseDto productResponseDto){
        return OffersEntity.builder()
                .term(productResponseDto.getTerm())
                .interest(productResponseDto.getInterest())
                .monthlyPayment(productResponseDto.getMonthlyPayment())
                .amount(productResponseDto.getAmount())
                .accepted(false)
                .productId(productResponseDto.getProductId())
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
                .productId(offersEntity.getProductId())
                .createdAt(offersEntity.getCreatedAt())
                .updatedAt(offersEntity.getUpdatedAt())
                .build();
    }
}
