package com.example.Ingress_lab.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldNameConstants
@Entity
@Table(name = "offers")
public class OffersEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Integer term;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private BigDecimal amount;
    private Boolean accepted;
    @ManyToOne(fetch = LAZY)
    private CreditEntity credit;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
