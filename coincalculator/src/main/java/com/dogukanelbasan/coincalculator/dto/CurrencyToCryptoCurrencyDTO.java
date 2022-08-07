package com.dogukanelbasan.coincalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyToCryptoCurrencyDTO implements Serializable {
    private CurrencyDTO crypto;
    private CurrencyDTO fiatCurrency;
    private String orderType;
    private Date date;
}
