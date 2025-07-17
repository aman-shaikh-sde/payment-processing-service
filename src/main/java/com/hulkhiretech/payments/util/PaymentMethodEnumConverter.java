package com.hulkhiretech.payments.util;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constants.PaymentMethodEnum;

public class PaymentMethodEnumConverter extends AbstractConverter<String, Integer> {
    @Override
    protected Integer convert(String source) {
        if (source == null || source.isEmpty()) {
            return 0;
        }
        return PaymentMethodEnum.fromName(source).getId();
    }
}