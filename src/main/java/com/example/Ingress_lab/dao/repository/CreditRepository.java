package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.CreditEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditRepository extends JpaRepository<CreditEntity, Long> {

    @EntityGraph(attributePaths = "statusHistories")
    Optional<CreditEntity> findCreditEntityById(Long id);

    @Override
    @EntityGraph(attributePaths = {"statusHistories", "offers"})
    List<CreditEntity> findAll();
}
