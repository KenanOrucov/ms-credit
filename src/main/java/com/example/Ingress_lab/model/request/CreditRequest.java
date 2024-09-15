package com.example.Ingress_lab.model.request;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreditRequest {
    private Integer term;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private BigDecimal amount;
    private BigDecimal requestAmount;
    private LocalDateTime checkDate;
    private Long conveyorId;
    private Long customerId;
}
