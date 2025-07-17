package com.hulkhiretech.payments.service.interfaces;

import com.hulkhiretech.payments.pojo.CreateTxnRequest;
import com.hulkhiretech.payments.pojo.CreateTxnResponse;
import com.hulkhiretech.payments.pojo.InitiateTxnReq;

public interface PaymentService {
	
	public CreateTxnResponse createPayment(CreateTxnRequest createTxnRequest);
	public String initiatePayment(String txnRefrence,InitiateTxnReq initiateTxnReq);

}
