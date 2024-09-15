package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.model.client.ConveyorResponseDto;
import com.example.Ingress_lab.model.response.OffersResponse;

import java.util.List;

public interface OfferService {
    void saveOffers(ConveyorResponseDto conveyorRequest);
    List<OffersResponse> getOffers();
    public Long updateOffersFeign(Long id, String status);
}
