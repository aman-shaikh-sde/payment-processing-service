package com.hulkhiretech.payments.service.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.hulkhiretech.payments.service.impl.statushandler.CreatedStatusHandler;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component 
@RequiredArgsConstructor
public class TransactionStatusFactory {


	private final ApplicationContext context;

		public TransactionStatusHandler getTransactionStatusHandler(int txnStatusId) {
		log.info("Fetching hand;erfor Transaction status ID: {}" ,txnStatusId );

		switch(txnStatusId) {
		case 1:
			log.info("Returning CreatedHandlerStatus for Transaction ID : {},",txnStatusId);
			return context.getBean(CreatedStatusHandler.class);
		default:
			log.warn("No Status Found");
			return null;
		}

	}
}
