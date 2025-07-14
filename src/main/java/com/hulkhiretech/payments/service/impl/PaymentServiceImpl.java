package com.hulkhiretech.payments.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.service.impl.statushandler.CreatedStatusHandler;
import com.hulkhiretech.payments.service.interfaces.PaymentService;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private final TransactionDao transactionDao;
	
	private final ModelMapper modelMapper;
	
	private final CreatedStatusHandler createdStatusHandler;

	@Override
	public CreateTxnResponse createPayment(CreateTxnRequest createTxnRequest) {
		log.info("Received payment request | createTxnRequest: {}", createTxnRequest);
		
		String txnReference = UUID.randomUUID().toString();
		int txnStatusId = 1;
		log.info("Generated txnReference: {}, txnStatusId: {}", txnReference, txnStatusId);
		
		TransactionStatusHandler statusHandler = createdStatusHandler; // TODO
		
		TransactionDTO txnDTO = modelMapper.map(createTxnRequest, 
				TransactionDTO.class);
		log.info("Mapped CreateTxnRequest to txnDTO: {}", txnDTO);
		
		txnDTO.setTxnStatusId(txnStatusId);
		txnDTO.setTxnReference(txnReference);
		
		log.info("Passnig DTO to TransactionStatusHandler txnDTO: {}", txnDTO);
		txnDTO = statusHandler.handleTransactionStatus(txnDTO);
		
		CreateTxnResponse response = new CreateTxnResponse();
		response.setTxnReference(txnDTO.getTxnReference());
		response.setTxnStatusId(txnDTO.getTxnStatusId());
		log.info("Mapped txnDTO to CreateTxnResponse: {}", response);
		
		return response;
	}

}
