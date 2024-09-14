package com.example.Ingress_lab.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionConstants {
    CUSTOMER_NOT_FOUND("CUSTOMER_NOT_FOUND", "Customer not found."),
    CREDIT_NOT_FOUND("CREDIT_NOT_FOUND", "Credit not found."),

    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred."),
    HTTP_METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Method not allowed."),;
    private final String code;
    private final String message;
}
