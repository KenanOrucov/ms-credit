package com.example.Ingress_lab.dao.repository;

import com.example.Ingress_lab.dao.entity.OffersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<OffersEntity, Long> {
}
