package com.example.Ingress_lab.service.abstraction;

import com.example.Ingress_lab.dao.entity.OffersEntity;
import com.example.Ingress_lab.model.client.ConveyorRequest;
import com.example.Ingress_lab.model.client.ConveyorResponse;
import com.example.Ingress_lab.model.response.OffersResponse;

import java.util.List;

public interface OfferService {
    void saveOffers(ConveyorResponse conveyorRequest);
    List<OffersResponse> getOffers();
}
