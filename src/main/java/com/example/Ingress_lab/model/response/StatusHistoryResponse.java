package com.example.Ingress_lab.model.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StatusHistoryResponse {
    private Long id;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
