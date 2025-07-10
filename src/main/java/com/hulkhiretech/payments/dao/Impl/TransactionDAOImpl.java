package com.hulkhiretech.payments.dao.Impl;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hulkhiretech.payments.Entity.TransactionEntity;
import com.hulkhiretech.payments.dao.TransactionDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TransactionDAOImpl implements TransactionDAO {
	
	
 private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	   
@Override
public boolean createTransaction(TransactionEntity entity) {
	
    log.info("Creating transaction in DAO layer");

    String sql = "INSERT INTO payments.Transaction (" +
        "userId, paymentMethodId, providerId, paymentTypeId, txnStatusId, " +
        "amount, currency, merchantTransactionReference, txnReference, providerReference, " +
        "errorCode, errorMessage,retryCount) " +
        "VALUES (:userId, :paymentMethodId, :providerId, :paymentTypeId, :txnStatusId, " +
        ":amount, :currency, :merchantTransactionReference, :txnReference, :providerReference, " +
        ":errorCode, :errorMessage, :retryCount)";

    BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity);

    int rowsAffected = namedParameterJdbcTemplate.update(sql, paramSource);
    return rowsAffected == 1;
}
	
	

}
