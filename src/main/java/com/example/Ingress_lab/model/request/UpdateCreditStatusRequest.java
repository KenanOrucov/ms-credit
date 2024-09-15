package com.example.Ingress_lab.model.request;

import lombok.Getter;

@Getter
public class UpdateCreditStatusRequest {
    private Long offerId;
    private String status;
}
