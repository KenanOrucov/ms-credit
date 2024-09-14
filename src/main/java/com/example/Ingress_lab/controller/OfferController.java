package com.example.Ingress_lab.controller;

import com.example.Ingress_lab.model.response.OffersResponse;
import com.example.Ingress_lab.service.abstraction.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/offers")
public class OfferController {
    private final OfferService offerService;

    @GetMapping
    public List<OffersResponse> getOffers(){
        return offerService.getOffers();
    }

}
