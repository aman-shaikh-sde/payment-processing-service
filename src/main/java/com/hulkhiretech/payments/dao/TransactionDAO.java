package com.hulkhiretech.payments.dao;

import com.hulkhiretech.payments.Entity.TransactionEntity;

public interface TransactionDAO {
	
	public boolean createTransaction(TransactionEntity entity);

}
