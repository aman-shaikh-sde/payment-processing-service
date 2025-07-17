package com.hulkhiretech.payments.util.one;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constants.PaymentMethodEnum;

public class PaymentMethodEnumIdToStringConverter extends AbstractConverter<Integer,String> {
    @Override
    protected String convert(Integer source) {
        if (source == null) {
            return null;
        }
        return PaymentMethodEnum.fromId(source).getName();
    }
}