package com.dogukanelbasan.coincalculator.enums;

public enum OrderType {

    CRYPTO("CRYPTO"),
    FIAT("FIAT");

    private String value = null;

    public String value() {
        return this.value;
    }

    private OrderType(String val) {
        this.value = val;
    }

    public static OrderType fromValue(String v) {
        for (OrderType c : values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
