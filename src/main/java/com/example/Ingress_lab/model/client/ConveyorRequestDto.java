package com.example.Ingress_lab.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ConveyorRequestDto {
    private Double amount;
    private Double monthlyPayment;
    private Double interest;
    private Integer term;
}
