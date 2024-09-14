package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.StatusHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusHistoryRepository extends JpaRepository<StatusHistoryEntity, Long> {
}
