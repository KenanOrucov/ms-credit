package com.example.Ingress_lab.model.client;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateProductStatusDto {
    private Long productId;
    private String status;
}
