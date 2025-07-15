package com.hulkhiretech.payments.constants;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProviderEnum {

    TRUSTLY(1, "TRUSTLY");

    private final int id;
    private final String name;

    public static ProviderEnum fromId(int id) {
        for (ProviderEnum provider : values()) {
            if (provider.id == id) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + id);
    }

    public static ProviderEnum fromName(String name) {
        for (ProviderEnum provider : values()) {
            if (provider.name.equalsIgnoreCase(name)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("Invalid name: " + name);
    }
}
