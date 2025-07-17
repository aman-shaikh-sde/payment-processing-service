package com.hulkhiretech.payments.util.one;

import org.modelmapper.AbstractConverter;

import com.hulkhiretech.payments.constants.ProviderEnum;

public class ProviderEnumIdToStringConverter extends AbstractConverter<Integer,String> {
    @Override
    protected String convert(Integer source) {
        return source != null ? ProviderEnum.fromId(source).getName() : null;
    }
}