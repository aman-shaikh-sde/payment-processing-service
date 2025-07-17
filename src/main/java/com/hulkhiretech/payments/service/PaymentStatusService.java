package com.hulkhiretech.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.constants.TransactionStatusEnum;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.service.factory.TransactionStatusFactory;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

@Service
public class PaymentStatusService {

		@Autowired
		private TransactionStatusFactory factory;

		public TransactionDTO updatePayment(TransactionDTO txnDTO) {
			
			String txnStatus = txnDTO.getTxnStatus();
			TransactionStatusEnum statusEnum = TransactionStatusEnum.fromName(txnStatus);

			TransactionStatusHandler statusHandler = factory.getTransactionStatusHandler(statusEnum);

			if (statusHandler == null) {
				throw new com.hulkhiretech.payments.exceptions.NullPointerException("ErrorCode2002", "Status Handler can't be null");
			}

			txnDTO = statusHandler.handleTransactionStatus(txnDTO);
			return txnDTO;
		}
	}

 
