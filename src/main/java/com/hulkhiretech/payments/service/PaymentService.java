package com.hulkhiretech.payments.service;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;

public interface PaymentService{
	
	public String createPayment(CreateTxnRequest createTxnRequest);

}
