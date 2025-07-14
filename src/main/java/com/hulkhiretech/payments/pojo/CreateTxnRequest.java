package com.hulkhiretech.payments.pojo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateTxnRequest {

    private int userId;

    private int paymentMethodId;
    private int providerId;
    private int paymentTypeId;
    
    private BigDecimal amount;
    private String currency;

    private String merchantTransactionReference;
    
}
