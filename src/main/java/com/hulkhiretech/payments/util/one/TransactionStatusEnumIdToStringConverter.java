package com.hulkhiretech.payments.util.one;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constants.TransactionStatusEnum;

public class TransactionStatusEnumIdToStringConverter extends AbstractConverter<Integer,String> {
    @Override
    protected String convert(Integer source) {
        return source != null ? TransactionStatusEnum.fromId(source).getName() : null;
    }
}