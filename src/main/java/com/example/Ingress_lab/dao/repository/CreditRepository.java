package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.CreditEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    @EntityGraph(attributePaths = "statusHistories")
    CreditEntity findCreditEntityById(Long id);
}
