package com.dogukanelbasan.coincalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyToCryptoCurrencyDTO {
    private String cryptocurrency;
    private Number cryptocurrency_amount;
    private Number currency_amount;
    private String currency_code;
    private String order_type;
}
