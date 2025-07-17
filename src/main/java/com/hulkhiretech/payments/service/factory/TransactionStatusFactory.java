package com.hulkhiretech.payments.service.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.hulkhiretech.payments.constants.TransactionStatusEnum;
import com.hulkhiretech.payments.service.impl.statushandler.CreatedStatusHandler;
import com.hulkhiretech.payments.service.impl.statushandler.InitiatedStatusHandler;
import com.hulkhiretech.payments.service.impl.statushandler.PendingStatusHandler;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionStatusFactory {

    private final ApplicationContext context;

    public TransactionStatusHandler getTransactionStatusHandler(TransactionStatusEnum txnStatusEnum) {
        log.info("Fetching handler for Transaction status: {}", txnStatusEnum);

        switch (txnStatusEnum) {
            case CREATED:
                log.info("Returning StatusHandler for status: {}", txnStatusEnum);
                return context.getBean(CreatedStatusHandler.class);

            case INITIATED:
                log.info("Returning InitiatedStatusHandler for status: {}", txnStatusEnum);
                return context.getBean(InitiatedStatusHandler.class);
            case PENDING:
                log.info("Returning PendingStatusHandler for status: {}", txnStatusEnum);
                return context.getBean(PendingStatusHandler.class);
                
            default:
                log.warn("No handler found for status: {}", txnStatusEnum);
                return null;
        }
    }
}
