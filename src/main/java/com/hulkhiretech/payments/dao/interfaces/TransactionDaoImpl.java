package com.hulkhiretech.payments.dao.interfaces;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hulkhiretech.payments.entity.TransactionEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TransactionDaoImpl implements TransactionDao {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public boolean createTransaction(TransactionEntity entity) {
		log.info("Creating transaction in DAO layer");

        String sql = "INSERT INTO payments.Transaction (" +
                "userId, paymentMethodId, providerId, paymentTypeId, txnStatusId, " +
                "amount, currency, merchantTransactionReference, txnReference, providerReference, " +
                "errorCode, errorMessage, retryCount) " +
                "VALUES (:userId, :paymentMethodId, :providerId, :paymentTypeId, :txnStatusId, " +
                ":amount, :currency, :merchantTransactionReference, :txnReference, :providerReference, " +
                ":errorCode, :errorMessage, :retryCount)";

        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(entity);

        int rowsAffected = namedParameterJdbcTemplate.update(sql, paramSource);
        log.info("Rows affected by transaction creation: {}", rowsAffected);
        return rowsAffected == 1;
	}

	@Override
	public boolean updateTransaction(TransactionEntity entity) {
		log.info("Updation TransactionDao for txnRef: {} | txnStatusId: {}"
				,entity.getTxnReference(),entity.getTxnStatusId());
		
		String sql = """
			    UPDATE payments.transaction
			    SET txnStatusId = :txnStatusId
			    WHERE txnReference = :txnReference
			""";

		
		Map<String,Object> params=new HashMap<>();
		
		params.put("txnStatusId", entity.getTxnStatusId());
		params.put("txnReference", entity.getTxnReference());
		
		int update=namedParameterJdbcTemplate.update(sql, params);
		log.info("Updated Successfully for refrence: {}",entity.getTxnReference());
		
		return update>0;
	}


	@Override
	public TransactionEntity getByReference(String reference) {
		
		log.info("Get Status by refrence from entity");
		
	    String sql = "SELECT * FROM payments.transaction WHERE txnReference = :txnReference";
		Map<String, Object> params = new HashMap<>();
	    params.put("txnReference", reference);

	    try {
	        return namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(TransactionEntity.class));
	    } catch (EmptyResultDataAccessException e) {
	        log.warn("No transaction found for reference: {}", reference);
	        return null;
	    }

	}
}
