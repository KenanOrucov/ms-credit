package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.client.ConveyorClient;
import com.example.Ingress_lab.dao.entity.CreditEntity;
import com.example.Ingress_lab.dao.repository.CreditRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.client.ConveyorRequestDto;
import com.example.Ingress_lab.model.client.ConveyorResponseDto;
import com.example.Ingress_lab.model.enums.CreditStatus;
import com.example.Ingress_lab.model.request.CreditRequest;
import com.example.Ingress_lab.model.request.UpdateCreditStatusRequest;
import com.example.Ingress_lab.model.response.CreditResponse;
import com.example.Ingress_lab.service.abstraction.CreditService;
import com.example.Ingress_lab.service.abstraction.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static com.example.Ingress_lab.mapper.CreditMapper.toCreditEntity;
import static com.example.Ingress_lab.mapper.CreditMapper.toCreditResponse;
import static com.example.Ingress_lab.mapper.StatusHistoryMapper.toStatusHistoryEntity;
import static com.example.Ingress_lab.mapper.StatusHistoryMapper.toUpdateProductStatusDto;
import static com.example.Ingress_lab.model.enums.CreditStatus.ACCEPTED;
import static com.example.Ingress_lab.model.enums.CreditStatus.DRAFT;
import static com.example.Ingress_lab.model.enums.CreditStatus.EXPIRED;
import static com.example.Ingress_lab.model.enums.CreditStatus.REJECTED;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.CREDIT_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditServiceHandler implements CreditService {
    private final CreditRepository creditRepository;
    private final OfferService offerService;
    private final ConveyorClient client;


    @Override
    public void saveCreditFeign(ConveyorRequestDto conveyorRequestDto){
        log.info("ActionLog.saveCreditFeign.start conveyorRequestDto: {}", conveyorRequestDto);

        var credit = creditRepository.findById(1L).orElseThrow(() -> new NotFoundException(CREDIT_NOT_FOUND.getCode(), CREDIT_NOT_FOUND.getMessage()));

        log.info("Saving credit: {}", conveyorRequestDto);
        var conveyorResponses = getConveyorOffer(conveyorRequestDto);
        var conveyorResponse = conveyorResponses.get(0);

        setCreditFields(credit, conveyorResponse);

        offerService.saveOffers(conveyorResponse);
        var saved = creditRepository.save(credit);
        log.info("ActionLog.saveCreditFeign.end saved: {}", saved);
    }

    @Override
    public void saveCredit(CreditRequest creditRequest) {
       log.info("ActionLog.saveCredit.start creditRequest: {}", creditRequest);
        var credit = toCreditEntity(creditRequest);
        var status = toStatusHistoryEntity(DRAFT.name(), credit);

        credit.setStatusHistories(Set.of(status));

        creditRepository.save(credit);
        log.info("ActionLog.saveCredit.end");
    }

    @Override
    public CreditResponse getCreditById(Long id) {
        log.info("ActionLog.getCreditById.start id: {}", id);
        var credit = creditRepository.findCreditEntityById(id)
                .orElseThrow(() -> new NotFoundException(CREDIT_NOT_FOUND.getCode(), CREDIT_NOT_FOUND.getMessage()));
        log.info("ActionLog.getCreditById.end credit: {}", credit);
        return toCreditResponse(credit);
    }

    @Override
    @Transactional
    public void updateCreditStatus(Long id, UpdateCreditStatusRequest request) {
        log.info("ActionLog.updateCreditStatus.start id: {}, request: {}", id, request);
        var credit = creditRepository
                .findCreditEntityById(id)
                .orElseThrow(() -> new NotFoundException(CREDIT_NOT_FOUND.getCode(), CREDIT_NOT_FOUND.getMessage()));

        var creditStatus = findStatus(request.getStatus());
        var status = toStatusHistoryEntity(creditStatus, credit);
        credit.getStatusHistories().add(status);
        credit.setStatus(CreditStatus.valueOf(creditStatus));

        var productId = offerService.updateOffersFeign(request.getOfferId(), request.getStatus());

        creditRepository.save(credit);
        client.updateProductStatus(credit.getConveyorId(), toUpdateProductStatusDto(productId, request.getStatus()));
        log.info("ActionLog.updateCreditStatus.end");
    }

    @Override
    public void updateCreditStatusToExpired() {
        log.info("ActionLog.updateCreditStatusToExpired.start");
        var credits = creditRepository
                .findAll();

        for (var credit: credits){
            if (credit.getCheckDate().isBefore(LocalDateTime.now())){
                var status = toStatusHistoryEntity(EXPIRED.name(), credit);

                credit.setStatusHistories(Set.of(status));
                credit.setStatus(EXPIRED);
                creditRepository.save(credit);
            }
        }
        log.info("ActionLog.updateCreditStatusToExpired.end");
    }

    public List<ConveyorResponseDto> getConveyorOffer(ConveyorRequestDto conveyorRequestDto){
        log.info("ActionLog.getConveyorOffer.start conveyorRequestDto: {}", conveyorRequestDto);
        return client.getConveyorOffer(conveyorRequestDto);
    }

    private void setCreditFields(CreditEntity credit, ConveyorResponseDto conveyorResponseDto) {
        log.info("ActionLog.setCreditFields.start conveyorResponseDto: {}", conveyorResponseDto);
        credit.setCheckDate(conveyorResponseDto.getCheckDate());
        credit.setConveyorId(conveyorResponseDto.getConveyorId());
        log.info("ActionLog.setCreditFields.end");
    }

    private String findStatus(String status){
        switch (status) {
            case "ACCEPTED" -> {
                return ACCEPTED.name();
            }
            case "REJECTED" -> {
                return REJECTED.name();
            }
        }

        return DRAFT.name();
    }

}
