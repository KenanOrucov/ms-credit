package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.client.ConveyorClient;
import com.example.Ingress_lab.dao.entity.CreditEntity;
import com.example.Ingress_lab.dao.repository.CreditRepository;
import com.example.Ingress_lab.exception.NotFoundException;
import com.example.Ingress_lab.model.client.ConveyorRequest;
import com.example.Ingress_lab.model.client.ConveyorResponse;
import com.example.Ingress_lab.model.request.CreditRequest;
import com.example.Ingress_lab.model.request.UpdateCreditStatusRequest;
import com.example.Ingress_lab.model.response.CreditResponse;
import com.example.Ingress_lab.service.abstraction.CreditService;
import com.example.Ingress_lab.service.abstraction.OfferService;
import com.example.Ingress_lab.service.abstraction.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Ingress_lab.mapper.CreditMapper.toCreditEntity;
import static com.example.Ingress_lab.mapper.CreditMapper.toCreditResponse;
import static com.example.Ingress_lab.mapper.StatusHistoryMapper.toStatusHistoryEntity;
import static com.example.Ingress_lab.model.enums.CreditStatus.DRAFT;
import static com.example.Ingress_lab.model.enums.ExceptionConstants.CREDIT_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditServiceHandler implements CreditService {
    private final CreditRepository creditRepository;
    private final StatusHistoryService statusHistoryService;
    private final OfferService offerService;
    private final ConveyorClient client;


    @Override
    public void saveCreditFeign(ConveyorRequest conveyorRequest){
        var credit = creditRepository.findById(1L).orElseThrow(() -> new NotFoundException(CREDIT_NOT_FOUND.getCode(), CREDIT_NOT_FOUND.getMessage()));

        log.info("Saving credit: {}", conveyorRequest);
        var conveyorResponses = getConveyorOffer(conveyorRequest);
        var conveyorResponse = conveyorResponses.get(0);

        credit.setCheckDate(conveyorResponse.getCheckDate());
        credit.setConveyorId(conveyorResponse.getConveyorId());

        offerService.saveOffers(conveyorResponse);
        var saved = creditRepository.save(credit);
        log.info("saved: {}", saved.getId());
    }

    @Override
    public void saveCredit(CreditRequest creditRequest) {
        log.info("Saving credit: {}", creditRequest);
        var credit = toCreditEntity(creditRequest);
        credit.getStatusHistories().add(toStatusHistoryEntity(DRAFT.name()));
        creditRepository.save(toCreditEntity(creditRequest));
    }

    @Override
    public CreditResponse getCreditById(Long id) {
        log.info("Getting credit by id: {}", id);
        var credit = creditRepository.findCreditEntityById(id);
        return toCreditResponse(credit);
    }

    @Override
    public void updateCreditStatus(UpdateCreditStatusRequest request) {
        log.info("Updating credit status: {}", request);
        var credit = creditRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("exception"));
        var status = statusHistoryService.saveStatusHistory(request.getStatus());
        credit.getStatusHistories().add(status);
    }

    public List<ConveyorResponse> getConveyorOffer(ConveyorRequest conveyorRequest){
        return client.getConveyorOffer(conveyorRequest);
    }

}
