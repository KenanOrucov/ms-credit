package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.client.ConveyorRequestDto;
import com.example.Ingress_lab.model.request.CreditRequest;
import com.example.Ingress_lab.model.request.UpdateCreditStatusRequest;
import com.example.Ingress_lab.model.response.CreditResponse;
import com.example.Ingress_lab.service.abstraction.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/credits")
public class CreditController {
    private final CreditService creditService;

    @GetMapping("{id}")
    public CreditResponse getCreditById(@PathVariable Long id) {
        return creditService.getCreditById(id);
    }

    @PostMapping
    public void saveCredit(@RequestBody CreditRequest request) {
        creditService.saveCredit(request);
    }

    @PostMapping("/feign")
    public void saveCreditFeign(@RequestBody ConveyorRequestDto request) {
        creditService.saveCreditFeign(request);
    }


    @PutMapping("/{id}")
    public void updateCreditStatus(@PathVariable Long id, @RequestBody UpdateCreditStatusRequest request) {
        creditService.updateCreditStatus(id,request);
    }



}
