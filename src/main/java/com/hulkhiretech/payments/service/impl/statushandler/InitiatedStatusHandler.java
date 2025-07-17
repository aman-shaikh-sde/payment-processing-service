package com.hulkhiretech.payments.service.impl.statushandler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.service.interfaces.TransactionStatusHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitiatedStatusHandler implements TransactionStatusHandler{

	private final ModelMapper modelMapper;
	
	private final TransactionDao transactionDao;
	
	@Override
	public TransactionDTO handleTransactionStatus(TransactionDTO transactionDTO) {
		log.info("Handling transaction status for DTO: {}", transactionDTO);
		
		
		transactionDao.updateTransaction(modelMapper.map(transactionDTO, TransactionEntity.class));
		log.info("Transaction successfully Initiated in database, transactionDTO:{}",
				transactionDTO);
		
		return transactionDTO;
	}
	

}
