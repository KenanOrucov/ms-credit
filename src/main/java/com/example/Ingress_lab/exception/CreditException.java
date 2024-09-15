package com.example.Ingress_lab.exception;

import lombok.Getter;

@Getter
public class CreditException extends RuntimeException{
    private String code;

    public CreditException(String message, String code) {
        super(message);
        this.code = code;
    }
}
