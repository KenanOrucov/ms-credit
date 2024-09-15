package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.CreditEntity;
import com.example.Ingress_lab.dao.entity.StatusHistoryEntity;
import com.example.Ingress_lab.model.client.UpdateProductStatusDto;
import com.example.Ingress_lab.model.response.StatusHistoryResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum StatusHistoryMapper {
    OFFER_MAPPER;

    public static StatusHistoryEntity toStatusHistoryEntity(String status, CreditEntity credit){
        return StatusHistoryEntity.builder()
                .credit(credit)
                .status(status)
                .build();
    }

    public static StatusHistoryResponse toStatusHistoryResponse(StatusHistoryEntity statusHistoryEntity){
        return StatusHistoryResponse.builder()
                .id(statusHistoryEntity.getId())
                .status(statusHistoryEntity.getStatus())
                .createdAt(statusHistoryEntity.getCreatedAt())
                .updatedAt(statusHistoryEntity.getUpdatedAt())
                .build();
    }

    public static Set<StatusHistoryResponse> toStatusHistoryResponses(Set<StatusHistoryEntity> statusHistoryEntities){
        return statusHistoryEntities.stream()
                .map(StatusHistoryMapper::toStatusHistoryResponse)
                .collect(Collectors.toSet());
    }

    public static UpdateProductStatusDto toUpdateProductStatusDto(Long productId, String status){
        return UpdateProductStatusDto.builder()
                .productId(productId)
                .status(status)
                .build();

    }
}
