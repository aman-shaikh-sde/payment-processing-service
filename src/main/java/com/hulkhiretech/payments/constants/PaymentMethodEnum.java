package com.hulkhiretech.payments.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethodEnum {

    APM(1, "APM");

    private final int id;
    private final String name;

    public static PaymentMethodEnum fromId(int id) {
        for (PaymentMethodEnum method : values()) {
            if (method.id == id) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public static PaymentMethodEnum fromName(String name) {
        for (PaymentMethodEnum method : values()) {
            if (method.name.equalsIgnoreCase(name)) {
                return method;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
