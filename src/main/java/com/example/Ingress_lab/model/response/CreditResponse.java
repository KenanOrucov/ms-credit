package com.example.Ingress_lab.model.response;

import com.example.Ingress_lab.model.enums.CreditStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CreditResponse {
    private Long id;
    private Integer term;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private BigDecimal amount;
    private BigDecimal requestAmount;
    private CreditStatus status;
    private LocalDateTime checkDate;
    private Long productId;
    private Long conveyorId;
    private List<StatusHistoryResponse> statusHistories;
}
