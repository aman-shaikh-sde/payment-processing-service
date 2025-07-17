package com.hulkhiretech.payments.util;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constants.TransactionStatusEnum;

public class TransactionStatusEnumConverter extends AbstractConverter<String, Integer> {
    @Override
    protected Integer convert(String source) {
        return source != null ? TransactionStatusEnum.fromName(source).getId() : null;
    }
}