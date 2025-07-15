package com.hulkhiretech.payments.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentTypeEnum {

    SALE(1, "SALE");

    private final int id;
    private final String name;

    public static PaymentTypeEnum fromId(int id) {
        for (PaymentTypeEnum type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public static PaymentTypeEnum fromName(String name) {
        for (PaymentTypeEnum type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
