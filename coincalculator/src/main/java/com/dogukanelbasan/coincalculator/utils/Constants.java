package com.dogukanelbasan.coincalculator.utils;


public class Constants {


    public static final String INVALID_DATA = "Invalid data type!";

    public static final String NOT_SPENDABLE_MESSAGE = "This fiat currency is not valid!";
    public static final String NOT_RECEIVABLE_MESSAGE = "This crypto currency is not valid!";

    public static final String INVALID_COIN_MSG = "Invalid coin type!";
    public static final String INVALID_CURRENCY_MSG = "Invalid fiat currency type!";
    public static final String SMALL_PRICE_MSG = "Invalid fiat currency amount, it must be greater than or equals to 25!";
    public static final String NULL_MSG = "Invalid crypto or fiat currency type!";
    public static final String ORDER_TYPE_MSG = "Invalid order type!";
    public static final String BIG_PRICE_MSG = "Invalid currency amount, it must be less than or equals to 5000!";
    public static final String REST_REQUEST_ERROR = "Blockchain.com returned empty!";
    public static final String DELIMITER = "-" ;

    public enum ORDER_TYPE {

        CRYPTO("CRYPTO"),
        FIAT("FIAT");

        private String value = null;

        public String value() {
            return this.value;
        }

        private ORDER_TYPE(String val) {
            this.value = val;
        }

        public static ORDER_TYPE fromValue(String v) {
            for (ORDER_TYPE c : values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }
    }

}