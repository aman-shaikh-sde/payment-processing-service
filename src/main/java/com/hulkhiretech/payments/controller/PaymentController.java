package com.hulkhiretech.payments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.dao.TransactionDAO;
import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payments")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

	
	private final PaymentService service;
	
	
	@PostMapping
	public String  creatPayment(@RequestBody CreateTxnRequest createTxnRequest) {
		
		log.info("Tarnsaction Request: {}",createTxnRequest);
		
		log.info("Payment Created Succussfully");
		return service.createPayment(createTxnRequest);
	}
	
	@PostMapping("/{txnId}/initiate")
	public String paymentInitiate(@PathVariable int txnId) {
		log.info("Transaction Id: {}",txnId);
		
		return "Transaction Id:"+txnId;
	}
}
