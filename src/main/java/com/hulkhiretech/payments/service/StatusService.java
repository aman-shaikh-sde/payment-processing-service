package com.hulkhiretech.payments.service;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.exceptions.NullPointerException;
import com.hulkhiretech.payments.service.factory.TransactionStatusFactory;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusService {

	private final TransactionStatusFactory factory ;


	public TransactionDTO updatePayment(TransactionDTO txnDTO) {
		
		int txnStatusId=txnDTO.getTxnStatusId();
		TransactionStatusHandler statusHandler=factory.getTransactionStatusHandler(txnStatusId);
		txnDTO = statusHandler.handleTransactionStatus(txnDTO);
		
		
		if(statusHandler==null) {
			throw new NullPointerException("ErrorCode2002", "Status Handler Cant be null");
		}
		
		return txnDTO; 
	}
}
