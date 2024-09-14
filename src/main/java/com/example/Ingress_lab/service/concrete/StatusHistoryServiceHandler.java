package com.example.Ingress_lab.service.concrete;

import com.example.Ingress_lab.dao.entity.StatusHistoryEntity;
import com.example.Ingress_lab.dao.repository.StatusHistoryRepository;
import com.example.Ingress_lab.service.abstraction.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.Ingress_lab.mapper.StatusHistoryMapper.toStatusHistoryEntity;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatusHistoryServiceHandler implements StatusHistoryService {
    private final StatusHistoryRepository statusHistoryRepository;

    @Override
    public StatusHistoryEntity saveStatusHistory(String status) {
        log.info("Saving status: {}", status);
        return statusHistoryRepository.save(toStatusHistoryEntity(status));
    }
}
