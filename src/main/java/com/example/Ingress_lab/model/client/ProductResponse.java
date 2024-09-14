package com.example.Ingress_lab.model.client;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
public class ProductResponse {
    private BigDecimal amount;
    private BigDecimal monthlyPayment;
    private BigDecimal interest;
    private Integer term;
    private Long productId;
}
