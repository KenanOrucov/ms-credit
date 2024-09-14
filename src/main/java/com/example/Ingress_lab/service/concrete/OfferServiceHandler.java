package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.client.ConveyorClient;
import com.example.Ingress_lab.dao.entity.CreditEntity;
import com.example.Ingress_lab.dao.entity.OffersEntity;
import com.example.Ingress_lab.dao.repository.OfferRepository;
import com.example.Ingress_lab.mapper.OfferMapper;
import com.example.Ingress_lab.model.client.ConveyorRequest;
import com.example.Ingress_lab.model.client.ConveyorResponse;
import com.example.Ingress_lab.model.response.OffersResponse;
import com.example.Ingress_lab.service.abstraction.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.mapper.OfferMapper.toOfferEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceHandler implements OfferService {
    private final OfferRepository offerRepository;
    private final ConveyorClient client;

    @Override
    public void saveOffers(ConveyorResponse conveyorResponse){
        var offers = conveyorResponse.getProducts();
        offers.stream()
                .map(OfferMapper::toOfferEntity)
                .forEach(offerRepository::save);

    }

    public List<OffersResponse> getOffers(){
        var offers = offerRepository.findAll();

        offers.stream()
                .map(this::checkAmount)
                .toList();

        return offers.stream().map(OfferMapper::toOfferResponse).collect(Collectors.toList());
    }

    private OffersEntity checkAmount(OffersEntity offers){
        if (offers.getAmount().compareTo(BigDecimal.valueOf(300.00)) < 0)
            offers.setAmount(BigDecimal.valueOf(300.00));

        return offers;
    }
}
