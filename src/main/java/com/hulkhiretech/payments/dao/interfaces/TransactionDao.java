package com.hulkhiretech.payments.dao.interfaces;

import com.hulkhiretech.payments.entity.TransactionEntity;

public interface TransactionDao {
	
	public boolean createTransaction(TransactionEntity entity);

}
