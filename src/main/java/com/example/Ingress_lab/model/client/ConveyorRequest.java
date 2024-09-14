package com.example.Ingress_lab.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ConveyorRequest {
    private Double amount;
    private Double monthlyPayment;
    private Double interest;
    private Integer term;
}
