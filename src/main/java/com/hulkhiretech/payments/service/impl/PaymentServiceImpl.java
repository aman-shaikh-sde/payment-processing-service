package com.hulkhiretech.payments.service.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import com.hulkhiretech.payments.config.AppConfig;
import com.hulkhiretech.payments.constants.TransactionStatusEnum;
import com.hulkhiretech.payments.dao.interfaces.TransactionDao;
import com.hulkhiretech.payments.dto.TransactionDTO;
import com.hulkhiretech.payments.entity.TransactionEntity;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.InitiateTxnReq;
import com.hulkhiretech.payments.service.PaymentStatusService;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	@Autowired
    private  AppConfig appConfig;
	
	private final TransactionDao transactionDao;
	
	private final ModelMapper modelMapper;
	
	private final PaymentStatusService statusService;


    
	
	@Override
	public CreateTxnResponse createPayment(CreateTxnRequest createTxnRequest) {
	    log.info("Received payment request | createTxnRequest: {}", createTxnRequest);
	    
	    String txnReference = UUID.randomUUID().toString();
	    String txnStatus = TransactionStatusEnum.CREATED.getName(); // "CREATED"
	    log.info("Generated txnReference: {}, txnStatus: {}", txnReference, txnStatus);
	    
	    // Map CreateTxnRequest to TransactionDTO
	    TransactionDTO txnDTO = modelMapper.map(createTxnRequest, TransactionDTO.class);
	    log.info("Mapped CreateTxnRequest to txnDTO: {}", txnDTO);

	    txnDTO.setTxnStatus(txnStatus);        // ✅ Correct status set
	    txnDTO.setTxnReference(txnReference);  // ✅ Correct reference set
	    
	    log.info("Passing DTO to TransactionStatusHandler txnDTO: {}", txnDTO);

	    // Handle by status-specific logic and persist
	    txnDTO = statusService.updatePayment(txnDTO);
	    
	    // Prepare response
	    CreateTxnResponse response = new CreateTxnResponse();
	    response.setTxnReference(txnDTO.getTxnReference());
	    response.setTxnStatus(txnDTO.getTxnStatus());
	    log.info("Mapped txnDTO to CreateTxnResponse: {}", response);
	    
	    
	    
	    return response;
	}


	@Override
	public String initiatePayment(String txnRefrence, InitiateTxnReq initiateTxnReq) {
		
		log.info("Initiate Payment using txnRefrence: {}",txnRefrence);
		
		TransactionEntity entity=transactionDao.getByReference(txnRefrence);
		log.info("Fetched by TXN Entity: {}",entity);
		
		TransactionDTO txnDTO=modelMapper.map(entity, TransactionDTO.class);
		log.info("Mapped TransactionEntity ti DTO: {}",txnDTO);
	
		
		txnDTO.setTxnStatus(TransactionStatusEnum.INITIATED.getName());
		txnDTO=statusService.updatePayment(txnDTO);
		
		log.info("Processed TransactionDTO after initiated:{}",txnDTO);
		
		
		txnDTO.setTxnStatus(TransactionStatusEnum.PENDING.getName());
		txnDTO=statusService.updatePayment(txnDTO);
		
		log.info("Processed TransactionDTO after Pending:{}",txnDTO);
		
		return "From InitiatedPayment :{}"+txnDTO;
	}
	


}
