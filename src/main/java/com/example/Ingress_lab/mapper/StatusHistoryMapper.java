package com.example.Ingress_lab.mapper;

import com.example.Ingress_lab.dao.entity.StatusHistoryEntity;

public enum StatusHistoryMapper {
    OFFER_MAPPER;

    public static StatusHistoryEntity toStatusHistoryEntity(String status){
        return StatusHistoryEntity.builder()
                .status(status)
                .build();
    }
}
