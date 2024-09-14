package com.example.Ingress_lab.service.abstraction;


import com.example.Ingress_lab.dao.entity.StatusHistoryEntity;

public interface StatusHistoryService {
    StatusHistoryEntity saveStatusHistory(String status);

}
