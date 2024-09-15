package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.OffersEntity;
import com.example.Ingress_lab.dao.repository.OfferRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.mapper.OfferMapper;
import com.example.Ingress_lab.model.client.ConveyorResponseDto;
import com.example.Ingress_lab.model.response.OffersResponse;
import com.example.Ingress_lab.service.abstraction.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Ingress_lab.model.enums.ExceptionConstants.OFFER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceHandler implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public void saveOffers(ConveyorResponseDto conveyorResponseDto){
        log.info("ActionLog.saveOffers.start conveyorResponseDto: {}", conveyorResponseDto);
        var offers = conveyorResponseDto.getProducts();
        offers.stream()
                .map(OfferMapper::toOfferEntity)
                .forEach(offerRepository::save);
        log.info("ActionLog.saveOffers.end");
    }

    public List<OffersResponse> getOffers(){
        log.info("ActionLog.getOffers start");
        var offers = offerRepository.findAll();

        offers.stream()
                .map(this::checkAmount)
                .toList();

        log.info("ActionLog.getOffers end");
        return offers.stream().map(OfferMapper::toOfferResponse).collect(Collectors.toList());
    }

    @Override
    public Long updateOffersFeign(Long id, String status){
        log.info("ActionLog.updateOffersFeign start id: {}, status: {}", id, status);
        var offer = offerRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(OFFER_NOT_FOUND.getCode(), OFFER_NOT_FOUND.getMessage()));

        if (status.equals("ACCEPTED"))
            offer.setAccepted(true);
        log.info("ActionLog.updateOffersFeign end");

        return offer.getProductId();
    }

    private OffersEntity checkAmount(OffersEntity offers){
        log.info("ActionLog.checkAmount start offers: {}", offers);
        if (offers.getAmount().compareTo(BigDecimal.valueOf(300.00)) < 0)
            offers.setAmount(BigDecimal.valueOf(300.00));
        log.info("ActionLog.checkAmount end");
        return offers;
    }
}
