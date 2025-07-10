package com.hulkhiretech.payments.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.Entity.TransactionEntity;
import com.hulkhiretech.payments.dao.TransactionDAO;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{
	
	private final TransactionDAO transactionDAO;
	
	private final ModelMapper mapper;
	

	@Override
	public String createPayment(CreateTxnRequest createTxnRequest) {
		// TODO Auto-generated method stub
		
		
		
		
		String txnRefrence=UUID.randomUUID().toString();
		

		log.info("UUID for txnRefrence: {}",txnRefrence);

		TransactionEntity entity=mapper.map(createTxnRequest, TransactionEntity.class);
			entity.setId(1);
		entity.setTxnReference(txnRefrence);	
		entity.setTxnStatusId(1);
		log.info("txnStatusId: {}", entity.getTxnStatusId());

		log.info("Transaction Entity: {}",entity);
				
		
		
		log.info("Request is Created from Service Impl: {}",createTxnRequest);
		
		boolean isCreated=transactionDAO.createTransaction(entity);
		log.info("paymentMethodId = {}", entity.getPaymentMethodId());

		
		return "Transaction is Saved from service impl"+isCreated;
		
	}


}
