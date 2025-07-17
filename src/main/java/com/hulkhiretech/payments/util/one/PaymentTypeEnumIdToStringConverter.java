package com.hulkhiretech.payments.util.one;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constants.PaymentTypeEnum;

public class PaymentTypeEnumIdToStringConverter extends AbstractConverter<Integer,String> {
    @Override
    protected String convert(Integer source) {
        return source != null ? PaymentTypeEnum.fromId(source).getName() : null;
    }
}
