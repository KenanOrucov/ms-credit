package com.example.Ingress_lab.model.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class OffersResponse {
    private Long id;
    private Integer term;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private BigDecimal amount;
    private Boolean accepted;
    private Long productId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
