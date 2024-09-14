package com.example.Ingress_lab.model.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CustomerResponse {
    private Long id;
    private String pin;
    private String fullName;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
