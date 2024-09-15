package com.example.Ingress_lab.scheduler;

import com.example.Ingress_lab.service.abstraction.CreditService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditScheduler {
    private final CreditService creditService;

    @Scheduled(cron = "0 0 0 * * ?")
    @SchedulerLock(name = "updateCreditStatus", lockAtLeastForString = "PT1M", lockAtMostForString = "PT3M")
    public void updateCardAmount() {
        creditService.updateCreditStatusToExpired();
    }
}
